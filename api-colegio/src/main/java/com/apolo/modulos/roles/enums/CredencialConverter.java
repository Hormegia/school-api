package com.apolo.modulos.roles.enums;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class CredencialConverter implements AttributeConverter<Credencial, String> {


    @Override
    public String convertToDatabaseColumn(Credencial cedencial) {


        if (cedencial == null) {
            return null;
        }
        return cedencial.getCodigo();
    }

    @Override
    public Credencial convertToEntityAttribute(String codigo) {


        if (codigo == null) {
            return null;
        }
        return Stream.of(Credencial.values())
                .filter(c -> c.getCodigo().equals(codigo))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
