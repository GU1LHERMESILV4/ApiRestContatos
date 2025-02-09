package br.com.gas.ApiRestContatos.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_pessoas")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrement
    private Long idPessoa;

    @Column(nullable = false) //not null
    private String nome;

    @Column // null
    private String endereco;

    @Column // null
    private String cep;

    @Column // null
    private String cidade;

    @Column // null
    private String uf;

    public Pessoa() { }

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    //Comentários para eu não me perder:
    // - CascadeType.ALL indica que todas as operações realizadas na entidade Pessoa
    // também serão aplicadas automaticamente aos objetos Contato relacionados
    // - mappedBy define o lado inverso do relacionamento
    private List<Contato> contatos;

    public Pessoa(Long idPessoa, String nome, String endereco, String cep, String cidade, String uf) {
        this.idPessoa = idPessoa;
        this.nome = nome;
        this.endereco = endereco;
        this.cep = cep;
        this.cidade = cidade;
        this.uf = uf;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
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

    @Override
    public String toString() {
        String retorno = "[" +
                "ID: "             + this.idPessoa + ", " +
                "Nome: " + this.nome + ", " +
                "Endereço: " + this.endereco + ", " +
                "CEP: " + this.cep + ", " +
                "Cidade: " + this.cidade + ", " +
                "UF: "            + this.uf        + "]";
        return retorno;
    }

}
