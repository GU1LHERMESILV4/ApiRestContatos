package br.com.gas.ApiRestContatos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_contato")
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrement
    private Long idContato;

    @Enumerated(EnumType.ORDINAL)
    private TipoContato tipoContato;

    @Column(nullable = false)
    private String contato;

    @ManyToOne
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;

    public enum TipoContato {
        //Comentários para eu não me perder
        //Enum criado dentro da classe Contato pois é um caso específico

        telefone(0),
        celular(1);

        private final int valor;

        TipoContato(int valor) {
            this.valor = valor;
        }

        public int getValor() {
            return valor;
        }
    }

    public Contato() {}


    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Contatos: [ " +
                "ID: " + this.idContato + "\n" +
                "Tipo do Contato: " + this.tipoContato + "\n" + "]";
    }
}
