package com.LucasSecco.demo.domain.service;

import com.LucasSecco.demo.domain.exception.NegocioException;
import com.LucasSecco.demo.domain.model.Cliente;
import com.LucasSecco.demo.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public Cliente buscar(Long clienteId){
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new NegocioException("Cliente não encontrado"));
    }

    @Transactional
    public Cliente salvar (Cliente cliente) {
        boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
                .filter(c -> !c.equals(cliente)) //filtra para nao ser o cliente que está editando
                .isPresent();

        if (emailEmUso){
            throw new NegocioException("Já existe um cliente cadastrado com esse e-mail!");
        }

        return clienteRepository.save(cliente);
    }

    @Transactional
    public void excluir(Long clienteId) {
      clienteRepository.deleteById(clienteId);
    }

}
