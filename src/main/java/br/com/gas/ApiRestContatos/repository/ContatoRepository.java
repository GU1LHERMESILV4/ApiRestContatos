package br.com.gas.ApiRestContatos.repository;

import br.com.gas.ApiRestContatos.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
    List<Contato> findByPessoaId(Long pessoaId);
}
