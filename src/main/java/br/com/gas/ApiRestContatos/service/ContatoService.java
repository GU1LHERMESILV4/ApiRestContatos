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

    public List<Contato> listarContatos() {
        return contatoRepository.findAll();
    }

    public Contato criarContato(Contato contato) {
        return contatoRepository.save(contato);
    }

    public Contato atualizarContato(Long id, Contato contato) {
        Optional<Contato> existente = contatoRepository.findById(id);
        if (existente.isPresent()) {
            Contato contatoExistente = existente.get();
            contatoExistente.setNome(contato.getNome());
            contatoExistente.setEmail(contato.getEmail());
            contatoExistente.setTelefone(contato.getTelefone());
            // Se houver outros campos, adicione aqui
            return contatoRepository.save(contatoExistente);
        } else {
            throw new RuntimeException("Contato não encontrado com id: " + id);
        }
    }

    public void deletarContato(Long id) {
        contatoRepository.deleteById(id);
    }

    public Contato buscarContatoPorId(Long id) {
        return contatoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contato não encontrado com id: " + id));
    }
}
