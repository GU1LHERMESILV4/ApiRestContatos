package br.com.gas.ApiRestContatos.controller;

import br.com.gas.ApiRestContatos.dto.MalaDiretaDTO;
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

    @Operation(summary = "Cria uma nova Pessoa", description = "Este endpoint cria uma nova pessoa e a salva no banco de dados.")
    @PostMapping
    public ResponseEntity<Pessoa> criarPessoa(@RequestBody Pessoa pessoa) {
        try {
            Pessoa pessoaCriada = pessoaService.salvar(pessoa);
            return ResponseEntity.ok(pessoaCriada);
        } catch (Exception e) {
            return ResponseEntity.status(500).build(); // 500 Internal Server Error
        }
    }

    @Operation(summary = "Lista todas as pessoas", description = "Este endpoint retorna uma lista de todas as pessoas cadastradas.")
    @GetMapping
    public ResponseEntity<List<Pessoa>> listarPessoas() {
        try {
            List<Pessoa> pessoas = pessoaService.listarTodas();
            return ResponseEntity.ok(pessoas);
        } catch (Exception e) {
            return ResponseEntity.status(500).build(); // 500 Internal Server Error
        }
    }

    @Operation(summary = "Retorna uma Pessoa por ID", description = "Este endpoint retorna os dados de uma pessoa com o ID fornecido.")
    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPessoaPorId(@PathVariable Long id) {
        try {
            Optional<Pessoa> pessoa = pessoaService.buscarPorId(id);
            return pessoa.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(500).build(); // 500 Internal Server Error
        }
    }

    @Operation(summary = "Retorna os dados de uma Pessoa por ID para mala direta", description = "Este endpoint retorna os dados de uma pessoa (ID, Nome e informações concatenadas de Endereço, CEP, Cidade e UF) para mala direta.")
    @GetMapping("/maladireta/{id}")
    public ResponseEntity<MalaDiretaDTO> buscarPessoaMalaDireta(@PathVariable Long id) {
        try {
            Optional<Pessoa> pessoaOpt = pessoaService.buscarPorId(id);

            if (pessoaOpt.isPresent()) {
                Pessoa pessoa = pessoaOpt.get();
                String malaDireta = String.format("%s – CEP: %s – %s/%s",
                        pessoa.getEndereco(),
                        pessoa.getCep(),
                        pessoa.getCidade(),
                        pessoa.getUf());

                MalaDiretaDTO dto = new MalaDiretaDTO(pessoa.getId(), pessoa.getNome(), malaDireta);
                return ResponseEntity.ok(dto);
            }

            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build(); // 500 Internal Server Error
        }
    }

    @Operation(summary = "Atualiza uma pessoa existente",
            description = "Este endpoint atualiza as informações de uma pessoa já cadastrada.")
    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoaAtualizada) {
        try {
            return pessoaService.buscarPorId(id)
                    .map(pessoa -> {
                        pessoaAtualizada.setId(id);
                        return ResponseEntity.ok(pessoaService.salvar(pessoaAtualizada));
                    }).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(500).build(); // 500 Internal Server Error
        }
    }

    @Operation(summary = "Remove uma Pessoa por ID",
            description = "Este endpoint exclui uma pessoa com o ID fornecido do banco de dados.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) {
        try {
            if (pessoaService.buscarPorId(id).isPresent()) {
                pessoaService.deletar(id);
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build(); // 500 Internal Server Error
        }
    }
}
