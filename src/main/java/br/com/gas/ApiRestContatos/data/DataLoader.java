package br.com.gas.ApiRestContatos.data;

import br.com.gas.ApiRestContatos.model.Contato;
import br.com.gas.ApiRestContatos.model.Pessoa;
import br.com.gas.ApiRestContatos.repository.ContatoRepository;
import br.com.gas.ApiRestContatos.repository.PessoaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final PessoaRepository pessoaRepository;
    private final ContatoRepository contatoRepository;

    public DataLoader(PessoaRepository pessoaRepository, ContatoRepository contatoRepository) {
        this.pessoaRepository = pessoaRepository;
        this.contatoRepository = contatoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("João Silva");
        pessoa.setEndereco("Rua Exemplo, 123");
        pessoa.setCep("12345-678");
        pessoa.setCidade("São Paulo");
        pessoa.setUf("SP");
        pessoaRepository.save(pessoa);

        Contato contato = new Contato();
        contato.setTipoContato(0);
        contato.setContato("123456789");
        contato.setPessoa(pessoa);
        contatoRepository.save(contato);

        System.out.println("Dados Inseridos.");
    }
}
