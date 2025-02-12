package br.com.gas.ApiRestContatos.controller;

import br.com.gas.ApiRestContatos.model.Pessoa;
import br.com.gas.ApiRestContatos.model.Contato;
import br.com.gas.ApiRestContatos.repository.PessoaRepository;
import br.com.gas.ApiRestContatos.repository.ContatoRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Operation(summary = "Lista todas as pessoas", description = "Este endpoint retorna uma lista de todas as pessoas cadastradas no banco de dados. Se não houver nenhuma pessoa, retorna uma lista vazia.")
    @GetMapping("/pessoas/listar")
    public List<Pessoa> listarPessoas() {
        List<Pessoa> listaPessoas = new ArrayList<Pessoa>();

        // DADOS TESTE PESSOA
        Pessoa pessoa1 = new Pessoa();
        pessoa1.setId(1L);
        pessoa1.setNome("João Silva");
        pessoa1.setEndereco("Rua A, 123");
        pessoa1.setCep("11111-000");
        pessoa1.setCidade("São Paulo");
        pessoa1.setUf("SP");
        listaPessoas.add(pessoa1);

        Pessoa pessoa2 = new Pessoa();
        pessoa2.setId(2L);
        pessoa2.setNome("Maria Souza");
        pessoa2.setEndereco("Rua B, 456");
        pessoa2.setCep("22222-000");
        pessoa2.setCidade("Rio de Janeiro");
        pessoa2.setUf("RJ");
        listaPessoas.add(pessoa2);

        // SALVAR PESSOAS
        pessoaRepository.saveAll(listaPessoas);

        return pessoaRepository.findAll();
    }

    // LISTAR CONTATOS DE UMA PESSOA
    @Operation(summary = "Lista todos os contatos", description = "Este endpoint retorna todos os contatos cadastrados no sistema. É possível filtrar os contatos por tipo de pessoa, como telefone ou celular.")
    @GetMapping("/contatos/listar")
    public List<Contato> listarContatos() {
        List<Contato> listaContatos = new ArrayList<Contato>();

        // DADOS TESTE CONTATO
        Contato contato1 = new Contato();
        contato1.setId(1L);
        contato1.setTipoContato(0); // Telefone
        contato1.setContato("1111-1111");
        Pessoa pessoa = pessoaRepository.findById(1L).orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        contato1.setPessoa(pessoa);
        listaContatos.add(contato1);

        Contato contato2 = new Contato();
        contato2.setId(2L);
        contato2.setTipoContato(1); // Celular
        contato2.setContato("99999-9999");
        listaContatos.add(contato2);

        // SALVAR CONTATOS
        contatoRepository.saveAll(listaContatos);

        return contatoRepository.findAll();
    }

    @Operation(summary = "Verifica o status da API", description = "Este endpoint retorna uma mensagem simples para verificar se a API está funcionando corretamente.")
    @GetMapping
    public String getApi() {
        return "API Java funcionando! 😁";
    }
}
