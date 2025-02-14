package br.com.gas.ApiRestContatos.controller;

import br.com.gas.ApiRestContatos.model.Contato;
import br.com.gas.ApiRestContatos.service.ContatoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contatos")
public class ContatoController {

    private final ContatoService contatoService;

    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    @Operation(summary = "Adiciona um novo Contato a uma Pessoa",
            description = "Este endpoint cria um novo contato e adiciona a uma Pessoa e o salva no banco de dados.")
    @PostMapping
    public ResponseEntity<Contato> criarContato(@RequestBody Contato contato) {
        try {
            Contato savedContato = contatoService.salvar(contato);
            return ResponseEntity.ok(savedContato);
        } catch (Exception e) {
            return ResponseEntity.status(500).build(); // Retorna erro 500 se algo der errado
        }
    }

    @Operation(summary = "Retorna os dados de um Contato por ID", description = "Este endpoint retorna os detalhes de um contato específico com base no ID fornecido.")
    @GetMapping("/{id}")
    public ResponseEntity<Contato> buscarContatoPorId(@PathVariable Long id) {
        try {
            return contatoService.buscarPorId(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(500).build(); // Retorna erro 500 em caso de erro no serviço
        }
    }

    @Operation(summary = "Lista todos os Contatos de uma Pessoa",
            description = "Este endpoint retorna uma lista de contatos vinculados a uma pessoa com o ID fornecido.")
    @GetMapping("/pessoa/{pessoaId}")
    public ResponseEntity<List<Contato>> listarContatosPorPessoa(@PathVariable Long pessoaId) {
        try {
            List<Contato> contatos = contatoService.listarPorPessoa(pessoaId);
            return ResponseEntity.ok(contatos);
        } catch (Exception e) {
            return ResponseEntity.status(500).build(); // Retorna erro 500 em caso de erro no serviço
        }
    }

    @Operation(summary = "Atualiza um Contato existente", description = "Este endpoint atualiza os dados de um contato específico com base no ID fornecido.")
    @PutMapping("/{id}")
    public ResponseEntity<Contato> atualizarContato(@PathVariable Long id, @RequestBody Contato contato) {
        try {
            return contatoService.atualizar(id, contato)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(500).build(); // Retorna erro 500 em caso de erro no serviço
        }
    }

    @Operation(summary = "Remove um Contato por ID",
            description = "Este endpoint exclui o contato com o ID fornecido do banco de dados.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarContato(@PathVariable Long id) {
        try {
            contatoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build(); // Retorna erro 500 se falhar ao deletar
        }
    }
}
