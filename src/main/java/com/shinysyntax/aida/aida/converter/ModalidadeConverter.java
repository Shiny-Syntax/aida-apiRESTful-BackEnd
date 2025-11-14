package com.shinysyntax.aida.aida.converter;

import com.shinysyntax.aida.aida.enums.Modalidade;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ModalidadeConverter implements AttributeConverter<Modalidade, String> {

    @Override
    public String convertToDatabaseColumn(Modalidade attribute) {
        return attribute == null ? null : attribute.getLabel();
    }

    @Override
    public Modalidade convertToEntityAttribute(String dbData) {
        return Modalidade.fromLabel(dbData);
    }
}
