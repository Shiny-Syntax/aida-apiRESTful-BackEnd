package com.shinysyntax.aida.aida.security;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SupabaseJwtFilter extends HttpFilter {

    private static final long serialVersionUID = 1L;

    @Value("${APP_SUPABASE_JWT_SECRET:}")
    private String jwtSecret;

    @Value("${app.supabase.jwks-url:}")
    private String jwksUrl;

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        String auth = req.getHeader("Authorization");
        if (auth != null && auth.startsWith("Bearer ")) {
            String token = auth.substring(7);
            try {
                // Try HS256 verification when a secret is provided via env var `APP_SUPABASE_JWT_SECRET`.
                DecodedJWT jwt = null;
                if (jwtSecret != null && !jwtSecret.isBlank()) {
                    try {
                        Algorithm alg = Algorithm.HMAC256(jwtSecret);
                        JWTVerifier verifier = JWT.require(alg).build();
                        jwt = verifier.verify(token);
                    } catch (JWTVerificationException vex) {
                        // verification failed; fall through to try JWKS or decode-only
                        jwt = null;
                    }
                }

                // If HS256 verification didn't run or failed, try JWKS if configured
                if (jwt == null && jwksUrl != null && !jwksUrl.isBlank()) {
                    try {
                        java.net.URL url = new java.net.URI(jwksUrl).toURL();
                        com.auth0.jwk.UrlJwkProvider provider = new com.auth0.jwk.UrlJwkProvider(url);
                        String kid = JWT.decode(token).getKeyId();
                        if (kid != null) {
                            com.auth0.jwk.Jwk jwk = provider.get(kid);
                            java.security.interfaces.RSAPublicKey publicKey = (java.security.interfaces.RSAPublicKey) jwk.getPublicKey();
                            Algorithm alg = Algorithm.RSA256(publicKey, null);
                            JWTVerifier verifier = JWT.require(alg).build();
                            jwt = verifier.verify(token);
                        }
                    } catch (java.net.URISyntaxException | java.io.IOException | com.auth0.jwk.JwkException | JWTVerificationException ex) {
                        // JWKS verification failed or not available; fall back to decode-only below
                        jwt = null;
                    }
                }

                // Fallback: decode without verification (not recommended for production)
                if (jwt == null) {
                    jwt = JWT.decode(token);
                }

                if (jwt != null) {
                    String userId = jwt.getClaim("user_id").asString();
                    if (userId == null) {
                        userId = jwt.getSubject();
                    }
                    if (userId != null) {
                        req.setAttribute("supabase_user_id", userId);
                    }
                }
            } catch (JWTDecodeException | IllegalArgumentException e) {
                // don't fail request on decode/parse problems; leave attribute absent
            }
        }
        chain.doFilter(req, res);
    }
}
