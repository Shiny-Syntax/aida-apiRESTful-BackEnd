package com.shinysyntax.aida.aida.converter;

import com.shinysyntax.aida.aida.enums.Priority;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PriorityConverter implements AttributeConverter<Priority, String> {

    @Override
    public String convertToDatabaseColumn(Priority attribute) {
        return attribute == null ? null : attribute.getLabel();
    }

    @Override
    public Priority convertToEntityAttribute(String dbData) {
        return Priority.fromLabel(dbData);
    }
}
