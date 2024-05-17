package com.LucasSecco.demo.api.assembler;

import com.LucasSecco.demo.api.model.ParcelamentoModel;
import com.LucasSecco.demo.api.model.input.ParcelamentoInput;
import com.LucasSecco.demo.domain.model.Parcelamento;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class ParcelamentoAssembler {

    private final ModelMapper modelMapper;

    public ParcelamentoModel toModel(Parcelamento parcelamento){
        return modelMapper.map(parcelamento, ParcelamentoModel.class);
    }

    public List<ParcelamentoModel> toCollectionModel(List<Parcelamento> parcelamentos){
        return parcelamentos.stream()
                .map(this::toModel)
                .toList();
    }

    public Parcelamento toEntity(ParcelamentoInput parcelamentoInput){
        return modelMapper.map(parcelamentoInput, Parcelamento.class);
    }
}
