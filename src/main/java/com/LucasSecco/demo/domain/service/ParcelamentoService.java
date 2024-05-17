package com.LucasSecco.demo.domain.service;

import com.LucasSecco.demo.domain.exception.NegocioException;
import com.LucasSecco.demo.domain.model.Cliente;
import com.LucasSecco.demo.domain.model.Parcelamento;
import com.LucasSecco.demo.domain.repository.ClienteRepository;
import com.LucasSecco.demo.domain.repository.ParcelamentoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class ParcelamentoService {

    private final ParcelamentoRepository parcelamentoRepository;
    private final ClienteRepository clienteRepository;
    private ClienteService clienteService;

    @Transactional
    public Parcelamento cadastrar(Parcelamento parcelamento) {
        if (parcelamento.getId() != null){
            throw new NegocioException("Parcelamento a ser criado não deve possuir um código");
        }

        Cliente cliente = clienteService.buscar(parcelamento.getCliente().getId());

        parcelamento.setCliente(cliente);

        parcelamento.setDataCriacao(OffsetDateTime.now());

        return parcelamentoRepository.save(parcelamento);
    }
}
