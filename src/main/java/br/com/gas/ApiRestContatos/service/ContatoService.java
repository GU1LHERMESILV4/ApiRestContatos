package br.com.gas.ApiRestContatos.service;

import br.com.gas.ApiRestContatos.model.Contato;
import br.com.gas.ApiRestContatos.repository.ContatoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {

    private final ContatoRepository contatoRepository;

    public ContatoService(ContatoRepository contatoRepository) {
        this.contatoRepository = contatoRepository;
    }

    public Contato salvar(Contato contato) {
        return contatoRepository.save(contato);
    }

    public List<Contato> listarPorPessoa(Long pessoaId) {
        return contatoRepository.findByPessoaId(pessoaId);
    }

    public Optional<Contato> buscarPorId(Long id) {
        return contatoRepository.findById(id);
    }

    public Optional<Contato> atualizar(Long id, Contato contato) {
        return contatoRepository.findById(id).map(existingContato -> {
            existingContato.setTipoContato(contato.getTipoContato());
            existingContato.setContato(contato.getContato());
            return contatoRepository.save(existingContato);
        });
    }

    public void deletar(Long id) {
        contatoRepository.deleteById(id);
    }
}
