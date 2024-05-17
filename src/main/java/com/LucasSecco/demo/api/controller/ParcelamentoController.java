package com.LucasSecco.demo.api.controller;

import com.LucasSecco.demo.api.assembler.ParcelamentoAssembler;
import com.LucasSecco.demo.api.model.ParcelamentoModel;
import com.LucasSecco.demo.api.model.input.ParcelamentoInput;
import com.LucasSecco.demo.domain.exception.NegocioException;
import com.LucasSecco.demo.domain.model.Parcelamento;
import com.LucasSecco.demo.domain.repository.ParcelamentoRepository;
import com.LucasSecco.demo.domain.service.ParcelamentoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/parcelamentos")
public class ParcelamentoController {

    private final ParcelamentoRepository parcelamentoRepository;
    private final ParcelamentoService parcelamentoService;
    private final ParcelamentoAssembler parcelamentoAssembler;

    @GetMapping
    public List<ParcelamentoModel> listar() {
        return parcelamentoAssembler.toCollectionModel(parcelamentoRepository.findAll());
    }

    @GetMapping("/{parcelamentoId}")
    public ResponseEntity<ParcelamentoModel> buscar(@PathVariable Long parcelamentoId){
        return parcelamentoRepository.findById(parcelamentoId)
                .map(parcelamentoAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParcelamentoModel cadastrar(@Valid @RequestBody ParcelamentoInput parcelamentoInput) {
        Parcelamento novoParcelamento = parcelamentoAssembler.toEntity(parcelamentoInput);
        Parcelamento parcelamentoCadastrado = parcelamentoService.cadastrar(novoParcelamento);
        return parcelamentoAssembler.toModel(parcelamentoCadastrado);
    }
}
