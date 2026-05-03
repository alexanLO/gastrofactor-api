package br.com.coccionapi.factorcc.port.output;

import java.util.Optional;

import br.com.coccionapi.factorcc.domain.model.FoodYieldVO;

public interface FoodYieldPort {

    Optional<FoodYieldVO> getByName(String foodName);

}
