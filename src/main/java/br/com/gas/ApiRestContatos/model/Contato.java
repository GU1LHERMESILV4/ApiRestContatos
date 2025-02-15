package br.com.gas.ApiRestContatos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_contatos")
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer tipoContato; // 0 - Telefone, 1 - Celular

    @Column(nullable = false)
    private String contato;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = false)
    @JsonBackReference
    private Pessoa pessoa;

    public Contato() {}

    public Contato(Integer tipoContato, String contato, Pessoa pessoa) {
        this.tipoContato = tipoContato;
        this.contato = contato;
        this.pessoa = pessoa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(Integer tipoContato) {
        this.tipoContato = tipoContato;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
