package br.com.gas.ApiRestContatos.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gas.ApiRestContatos.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

}
