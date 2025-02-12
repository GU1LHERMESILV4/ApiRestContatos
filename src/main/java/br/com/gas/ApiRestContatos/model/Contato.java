package br.com.gas.ApiRestContatos.model;

import jakarta.persistence.*;
import lombok.*; //Usei Lombok Edu :(

@Entity
@Table(name = "contatos")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
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
    private Pessoa pessoa;
}
