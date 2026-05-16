package br.com.coccionapi.factorcc.adapters.output.ports;

import java.util.Optional;

import br.com.coccionapi.factorcc.domain.model.FoodYieldVO;

public interface FoodYieldPort {

    Optional<FoodYieldVO> getByName(String foodName);

}
