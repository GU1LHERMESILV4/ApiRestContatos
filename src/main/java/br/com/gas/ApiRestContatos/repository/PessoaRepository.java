package br.com.gas.ApiRestContatos.repository;

import br.com.gas.ApiRestContatos.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
