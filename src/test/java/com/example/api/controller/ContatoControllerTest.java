package com.example.api.controller;

import br.com.gas.ApiRestContatos.controller.ContatoController;
import br.com.gas.ApiRestContatos.model.Contato;
import br.com.gas.ApiRestContatos.service.ContatoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ContatoControllerTest {

    private MockMvc mockMvc;
    private ContatoService contatoService;
    private ContatoController contatoController;

    @BeforeEach
    void setup() {
        contatoService = Mockito.mock(ContatoService.class);
        contatoController = new ContatoController(contatoService); // Usa o construtor real
        mockMvc = MockMvcBuilders.standaloneSetup(contatoController).build();
    }

    @Test
    void deveRetornarListaDeContatos() throws Exception {
        Mockito.when(contatoService.listarContatos())
                .thenReturn(Arrays.asList(new Contato(1L, "João", "joao@email.com", "123456789", true)));

        mockMvc.perform(get("/contatos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].nome").value("João"));
    }

    @Test
    void deveCriarContato() throws Exception {
        Contato contato = new Contato(1L, "Maria", "maria@email.com", "987654321", true);
        Mockito.when(contatoService.criarContato(Mockito.any(Contato.class))).thenReturn(contato);

        mockMvc.perform(post("/contatos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nome\":\"Maria\",\"email\":\"maria@email.com\",\"telefone\":\"987654321\",\"ativo\":true}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Maria"));
    }

    @Test
    void deveAtualizarContato() throws Exception {
        Contato contatoAtualizado = new Contato(1L, "Maria Atualizada", "maria@email.com", "987654321", true);
        Mockito.when(contatoService.atualizarContato(Mockito.eq(1L), Mockito.any(Contato.class)))
                .thenReturn(contatoAtualizado);

        mockMvc.perform(put("/contatos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nome\":\"Maria Atualizada\",\"email\":\"maria@email.com\",\"telefone\":\"987654321\",\"ativo\":true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Maria Atualizada"));
    }

    @Test
    void deveDeletarContato() throws Exception {
        Mockito.doNothing().when(contatoService).deletarContato(1L);

        mockMvc.perform(delete("/contatos/1"))
                .andExpect(status().isNoContent());
    }
}
