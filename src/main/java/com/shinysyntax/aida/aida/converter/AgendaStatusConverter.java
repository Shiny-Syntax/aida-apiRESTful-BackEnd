package com.shinysyntax.aida.aida.converter;

import com.shinysyntax.aida.aida.enums.AgendaStatus;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AgendaStatusConverter implements AttributeConverter<AgendaStatus, String> {

    @Override
    public String convertToDatabaseColumn(AgendaStatus attribute) {
        return attribute == null ? null : attribute.getLabel();
    }

    @Override
    public AgendaStatus convertToEntityAttribute(String dbData) {
        return AgendaStatus.fromLabel(dbData);
    }
}
