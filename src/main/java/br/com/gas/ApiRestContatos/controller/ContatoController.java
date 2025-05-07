package br.com.gas.ApiRestContatos.controller;

import br.com.gas.ApiRestContatos.model.Contato;
import br.com.gas.ApiRestContatos.service.ContatoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

    private final ContatoService contatoService;

    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    @GetMapping
    public ResponseEntity<List<Contato>> listarContatos() {
        return ResponseEntity.ok(contatoService.listarContatos());
    }

    @PostMapping
    public ResponseEntity<Contato> criarContato(@RequestBody Contato contato) {
        Contato novoContato = contatoService.criarContato(contato);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoContato);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contato> atualizarContato(@PathVariable Long id, @RequestBody Contato contato) {
        Contato contatoAtualizado = contatoService.atualizarContato(id, contato);
        return ResponseEntity.ok(contatoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarContato(@PathVariable Long id) {
        contatoService.deletarContato(id);
        return ResponseEntity.noContent().build();
    }
}
