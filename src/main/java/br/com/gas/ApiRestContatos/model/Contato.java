package br.com.gas.ApiRestContatos.model;

public class Contato {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private Boolean ativo;
    private int tipoContato; // Novo campo
    private String contato; // Novo campo
    private Pessoa pessoa; // Novo campo

    public Contato() {}

    public Contato(Long id, String nome, String email, String telefone, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.ativo = ativo;
    }

    // Construtor atualizado para incluir tipoContato (opcional)
    public Contato(Long id, String nome, String email, String telefone, Boolean ativo, int tipoContato) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.ativo = ativo;
        this.tipoContato = tipoContato;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public int getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(int tipoContato) {
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
