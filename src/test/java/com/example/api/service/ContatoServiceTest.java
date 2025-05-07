package com.example.api.service;

import br.com.gas.ApiRestContatos.model.Contato;
import br.com.gas.ApiRestContatos.service.ContatoService;
import br.com.gas.ApiRestContatos.repository.ContatoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ContatoServiceTest {

    private final ContatoRepository contatoRepository = Mockito.mock(ContatoRepository.class);
    private final ContatoService contatoService = new ContatoService(contatoRepository); // Usa o construtor real

    @Test
    void deveCriarContato() {
        Contato contato = new Contato(null, "João", "joao@email.com", "123456789", true);
        Mockito.when(contatoRepository.save(Mockito.any(Contato.class))).thenReturn(contato);

        Contato resultado = contatoService.criarContato(contato);

        assertNotNull(resultado);
        assertEquals("João", resultado.getNome());
    }

    @Test
    void deveRetornarContatoPorId() {
        Contato contato = new Contato(1L, "João", "joao@email.com", "123456789", true);
        Mockito.when(contatoRepository.findById(1L)).thenReturn(Optional.of(contato));

        Contato resultado = contatoService.buscarContatoPorId(1L);

        assertNotNull(resultado);
        assertEquals("João", resultado.getNome());
    }

    @Test
    void deveLancarExcecaoSeContatoNaoExistir() {
        Mockito.when(contatoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> contatoService.buscarContatoPorId(1L));
    }
}

