package br.com.gas.ApiRestContatos.controller;

import br.com.gas.ApiRestContatos.model.Pessoa;
import br.com.gas.ApiRestContatos.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @Operation(summary = "Cria uma nova pessoa", description = "Este endpoint cria uma nova pessoa e a salva no banco de dados.")
    @PostMapping
    public ResponseEntity<Pessoa> criarPessoa(@RequestBody Pessoa pessoa) {
        return ResponseEntity.ok(pessoaService.salvar(pessoa));
    }

    @Operation(summary = "Lista todas as pessoas", description = "Este endpoint retorna uma lista de todas as pessoas cadastradas.")
    @GetMapping
    public ResponseEntity<List<Pessoa>> listarPessoas() {
        return ResponseEntity.ok(pessoaService.listarTodas());
    }

    @Operation(summary = "Busca uma pessoa por ID", description = "Este endpoint retorna os dados de uma pessoa com o ID fornecido.")
    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPessoaPorId(@PathVariable Long id) {
        Optional<Pessoa> pessoa = pessoaService.buscarPorId(id);
        return pessoa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Atualiza uma pessoa existente", description = "Este endpoint atualiza as informações de uma pessoa já cadastrada.")
    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoaAtualizada) {
        return pessoaService.buscarPorId(id)
                .map(pessoa -> {
                    pessoaAtualizada.setId(id);
                    return ResponseEntity.ok(pessoaService.salvar(pessoaAtualizada));
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deleta uma pessoa", description = "Este endpoint exclui uma pessoa com o ID fornecido do banco de dados.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) {
        if (pessoaService.buscarPorId(id).isPresent()) {
            pessoaService.deletar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
