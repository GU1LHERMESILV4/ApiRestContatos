package br.com.gas.ApiRestContatos.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_pessoas")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String endereco;
    private String cep;
    private String cidade;
    private String uf;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Contato> contatos;

    // Construtor sem parâmetros
    public Pessoa() {
    }

    // Construtor com parâmetros
    public Pessoa(List<Contato> contatos, String uf, String cidade, String cep, String endereco, String nome) {
        this.contatos = contatos;
        this.uf = uf;
        this.cidade = cidade;
        this.cep = cep;
        this.endereco = endereco;
        this.nome = nome;
    }

    // Método estático para criar uma Pessoa com o nome
    public static Pessoa fromString(String nome) {
        Pessoa pessoa = new Pessoa(); // Usa o construtor sem parâmetros
        pessoa.setNome(nome);
        return pessoa;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }
}
