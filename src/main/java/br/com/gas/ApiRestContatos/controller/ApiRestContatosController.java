package br.com.gas.ApiRestContatos.controller;

import br.com.gas.ApiRestContatos.model.Pessoa;
import br.com.gas.ApiRestContatos.model.Contato;
import br.com.gas.ApiRestContatos.repository.PessoaRepository;
import br.com.gas.ApiRestContatos.repository.ContatoRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api") // http://localhost:8080/api
public class ApiRestContatosController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ContatoRepository contatoRepository;

    @Operation(summary = "Adiciona novas pessoas", description = "Este endpoint adiciona pessoas ao banco de dados.")
    @PostMapping("/pessoas/adicionar")
    public List<Pessoa> adicionarPessoas() {
        List<Pessoa> listaPessoas = new ArrayList<>();

        Pessoa pessoa1 = new Pessoa();
        pessoa1.setNome("João Silva");
        pessoa1.setEndereco("Rua A, 123");
        pessoa1.setCep("11111-000");
        pessoa1.setCidade("São Paulo");
        pessoa1.setUf("SP");

        Pessoa pessoa2 = new Pessoa();
        pessoa2.setNome("Maria Souza");
        pessoa2.setEndereco("Rua B, 456");
        pessoa2.setCep("22222-000");
        pessoa2.setCidade("Rio de Janeiro");
        pessoa2.setUf("RJ");

        listaPessoas.add(pessoa1);
        listaPessoas.add(pessoa2);

        return pessoaRepository.saveAll(listaPessoas);
    }


    // LISTAR CONTATOS DE UMA PESSOA
    @Operation(summary = "Adiciona novos contatos", description = "Este endpoint adiciona contatos ao banco de dados.")
    @PostMapping("/contatos/adicionar")
    public List<Contato> adicionarContatos() {
        List<Contato> listaContatos = new ArrayList<>();

        Pessoa pessoa1 = pessoaRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Pessoa com ID 1 não encontrada"));

        Contato contato1 = new Contato();
        contato1.setTipoContato(0); // Telefone
        contato1.setContato("1111-1111");
        contato1.setPessoa(pessoa1);

        Contato contato2 = new Contato();
        contato2.setTipoContato(1); // Celular
        contato2.setContato("99999-9999");
        contato2.setPessoa(pessoa1); // Associando ao mesmo João Silva

        listaContatos.add(contato1);
        listaContatos.add(contato2);

        return contatoRepository.saveAll(listaContatos);
    }

    @Operation(summary = "Lista todas as pessoas", description = "Este endpoint retorna uma lista de todas as pessoas cadastradas no banco de dados.")
    @GetMapping("/pessoas/listar")
    public List<Pessoa> listarPessoas() {
        return pessoaRepository.findAll();
    }

    @Operation(summary = "Lista todos os contatos", description = "Este endpoint retorna todos os contatos cadastrados no sistema.")
    @GetMapping("/contatos/listar")
    public List<Contato> listarContatos() {
        return contatoRepository.findAll();
    }

    @Operation(summary = "Verifica o status da API", description = "Este endpoint retorna uma mensagem simples para verificar se a API está funcionando corretamente.")
    @GetMapping
    public String getApi() {
        return "API Java funcionando! 😁";
    }
}
