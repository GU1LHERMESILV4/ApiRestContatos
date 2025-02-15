package br.com.gas.ApiRestContatos.controller;

import br.com.gas.ApiRestContatos.exception.PessoaNotFoundException;
import br.com.gas.ApiRestContatos.model.Pessoa;
import br.com.gas.ApiRestContatos.model.Contato;
import br.com.gas.ApiRestContatos.repository.PessoaRepository;
import br.com.gas.ApiRestContatos.repository.ContatoRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiRestContatosController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ContatoRepository contatoRepository;

    @Operation(summary = "Lista todas as pessoas")
    @GetMapping("/pessoas/listar")
    public ResponseEntity<List<Pessoa>> listarPessoas() {
        try {
            List<Pessoa> pessoas = pessoaRepository.findAll();
            return ResponseEntity.ok(pessoas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Lista todos os contatos")
    @GetMapping("/contatos/listar")
    public ResponseEntity<List<Contato>> listarContatos() {
        try {
            List<Contato> contatos = contatoRepository.findAll();
            return ResponseEntity.ok(contatos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Adiciona novas pessoas")
    @PostMapping("/pessoas/cadastrar")
    public ResponseEntity<Pessoa> cadastrarPessoa(@RequestBody Pessoa pessoa) {
        try {
            Pessoa pessoaSalva = pessoaRepository.save(pessoa);
            return ResponseEntity.ok(pessoaSalva);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Adiciona novos contatos")
    @PostMapping("/contatos/cadastrar")
    public ResponseEntity<Contato> cadastrarContato(@RequestBody Contato contato) {
        try {
            Pessoa pessoa = pessoaRepository.findById(contato.getPessoa().getId())
                    .orElseThrow(() -> new PessoaNotFoundException("Pessoa não encontrada"));
            contato.setPessoa(pessoa);
            Contato contatoSalvo = contatoRepository.save(contato);
            return ResponseEntity.ok(contatoSalvo);
        } catch (PessoaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Verifica o status da API")
    @GetMapping
    public ResponseEntity<String> getApi() {
        return ResponseEntity.ok("API Java funcionando! 😁");
    }
}