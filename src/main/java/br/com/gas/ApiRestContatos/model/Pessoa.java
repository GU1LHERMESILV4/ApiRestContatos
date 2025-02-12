package br.com.gas.ApiRestContatos.model;

import jakarta.persistence.*;
import lombok.*; //Usei Lombok Edu :(

import java.util.List;

@Entity
@Table(name = "pessoas")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
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
    private List<Contato> contatos;
}
