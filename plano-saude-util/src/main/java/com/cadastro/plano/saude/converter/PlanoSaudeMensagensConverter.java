package com.cadastro.plano.saude.converter;

import com.cadastro.plano.saude.util.PlanoSaudeMensagens;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

@Converter(autoApply = true)
public class PlanoSaudeMensagensConverter implements AttributeConverter<PlanoSaudeMensagens, String> {
    @Override
    public String convertToDatabaseColumn(PlanoSaudeMensagens planoSaudeMensagens) {
        return Optional.ofNullable(planoSaudeMensagens).map(a -> planoSaudeMensagens.getValor()).orElse(null);
    }

    @Override
    public PlanoSaudeMensagens convertToEntityAttribute(String string) {
        return PlanoSaudeMensagens.findByCodigo(string);
    }
}
