package br.com.gas.ApiRestContatos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gas.ApiRestContatos.model.Contato;
import br.com.gas.ApiRestContatos.service.ContatoService;

@RestController
@RequestMapping("/api/contatos/") //http://localhost:8080/api/contatos/
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @PostMapping
    public ResponseEntity<Contato> save(@RequestBody Contato contato){
        Contato newContato = contatoService.save(contato);
        if(newContato == null)
            return ResponseEntity.badRequest().build(); //400
        return ResponseEntity.ok(newContato);
    }

    @GetMapping("/api/contatos/{id}")
    public ResponseEntity<Optional<Contato>> findById(@PathVariable Long id){
        Optional<Contato> findContato = contatoService.findbyId(id);
        if(findContato == null)
            return ResponseEntity.badRequest().build(); //400
        return ResponseEntity.ok(findContato); //200
    }

    @GetMapping
    public ResponseEntity<List<Contato>> findAll(){
        List<Contato> findContato = contatoService.findAll();
        if(findContato == null)
            return ResponseEntity.badRequest().build(); //400
        return ResponseEntity.ok(findContato); //200
    }

    @PutMapping
    public ResponseEntity<Contato> update(@RequestBody Contato contato){
        Contato updContato = contatoService.update(contato);
        if(updContato == null)
            return ResponseEntity.badRequest().build(); //400
        return ResponseEntity.ok(updContato); //200
    }

    @DeleteMapping("/api/contatos/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        contatoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);//204
    }

}