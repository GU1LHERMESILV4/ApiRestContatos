package br.com.gas.ApiRestContatos.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.gas.ApiRestContatos.dto.MalaDiretaDTO;
import br.com.gas.ApiRestContatos.model.Pessoa;
import br.com.gas.ApiRestContatos.service.PessoaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;


    @Operation(summary = "Cria uma nova Pessoa")
    @PostMapping
    public ResponseEntity<Pessoa> createPessoa(@RequestBody Pessoa pessoa) {
        Pessoa savedPessoa = pessoaService.save(pessoa);
        if (savedPessoa != null) {
            return new ResponseEntity<>(savedPessoa, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "Retorna os dados de uma Pessoa por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> getPessoaById(@PathVariable Long id) {
        Optional<Pessoa> pessoa = pessoaService.findById(id);
        if (pessoa.isPresent()) {
            return new ResponseEntity<>(pessoa.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Retorna os dados de uma Pessoa por ID para mala direta")
    @GetMapping("/maladireta/{id}")
    public ResponseEntity<MalaDiretaDTO> getPessoaForMailing(@PathVariable Long id) {
        MalaDiretaDTO malaDireta = pessoaService.getPessoaForMailing(id);
        if (malaDireta != null) {
            return new ResponseEntity<>(malaDireta, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Lista todas as Pessoas")
    @GetMapping
    public ResponseEntity<List<Pessoa>> getAllPessoas() {
        List<Pessoa> pessoas = pessoaService.findAll();
        return new ResponseEntity<>(pessoas, HttpStatus.OK);
    }

    @Operation(summary = "Atualiza uma Pessoa existente")
    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> updatePessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        Pessoa updatedPessoa = pessoaService.update(id, pessoa);
        if (updatedPessoa != null) {
            return new ResponseEntity<>(updatedPessoa, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Remove uma Pessoa por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable Long id) {
        pessoaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
