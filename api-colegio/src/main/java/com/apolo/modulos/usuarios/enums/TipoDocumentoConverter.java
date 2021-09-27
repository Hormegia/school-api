package com.apolo.modulos.usuarios.enums;

import com.apolo.modulos.usuarios.enums.TipoDocumento;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class TipoDocumentoConverter implements AttributeConverter<TipoDocumento, String> {


    @Override
    public String convertToDatabaseColumn(TipoDocumento tipoDocumento) {


        if (tipoDocumento == null) {
            return null;
        }
        return tipoDocumento.getCodigo();
    }

    @Override
    public TipoDocumento convertToEntityAttribute(String codigo) {


        if (codigo == null) {
            return null;
        }
        return Stream.of(TipoDocumento.values())
                .filter(c -> c.getCodigo().equals(codigo))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
