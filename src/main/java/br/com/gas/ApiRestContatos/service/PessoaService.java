package br.com.gas.ApiRestContatos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gas.ApiRestContatos.dto.MalaDiretaDTO;
import br.com.gas.ApiRestContatos.model.Pessoa;
import br.com.gas.ApiRestContatos.repository.PessoaRepository;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    // CRUD - Create: Criar Pessoa
    public Pessoa save(Pessoa pessoa) {
        if (pessoa.getNome() == null || pessoa.getNome().isEmpty()) {
            System.out.println("O campo nome está vazio.");
            return null;
        }
        return pessoaRepository.save(pessoa);
    }

    // CRUD - Read: Obter Pessoa por ID
    public Optional<Pessoa> findById(Long idPessoa) {
        return pessoaRepository.findById(idPessoa);
    }

    // CRUD - Read: Obter Pessoa para Mala Direta
    public MalaDiretaDTO getPessoaForMailing(Long idPessoa) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(idPessoa);
        if (pessoa.isPresent()) {
            Pessoa pessoaData = pessoa.get();
            String malaDireta = pessoaData.getEndereco() + ", " +
                    pessoaData.getCep() + " – " +
                    "CEP: " + pessoaData.getCep() + " – " +
                    pessoaData.getCidade() + "/" + pessoaData.getUf();
            return new MalaDiretaDTO(pessoaData.getIdPessoa(), pessoaData.getNome(), malaDireta);
        }
        return null;
    }

    // CRUD - Read: Listar todas as Pessoas
    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

    // CRUD - Update: Atualizar Pessoa
    public Pessoa update(Long id, Pessoa pessoa) {
        Optional<Pessoa> pessoaExistente = pessoaRepository.findById(id);
        if (pessoaExistente.isPresent()) {
            Pessoa updatedPessoa = pessoaExistente.get();
            updatedPessoa.setNome(pessoa.getNome());
            updatedPessoa.setEndereco(pessoa.getEndereco());
            updatedPessoa.setCep(pessoa.getCep());
            updatedPessoa.setCidade(pessoa.getCidade());
            updatedPessoa.setUf(pessoa.getUf());
            return pessoaRepository.save(updatedPessoa);
        }
        return null;
    }

    // CRUD - Delete: Deletar Pessoa por ID
    public void delete(Long id) {
        pessoaRepository.deleteById(id);
    }
}
