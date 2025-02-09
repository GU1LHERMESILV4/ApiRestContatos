package br.com.gas.ApiRestContatos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_contato")
public class Contato {

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

        TELEFONE(0),
        CELULAR(1);

        private final int valor;

        TipoContato(int valor) {
            this.valor = valor;
        }

        public int getValor() {
            return valor;
        }
    }


//
//    @OneToOne
//    @JoinColumn(name = "produto_id", referencedColumnName = "id")
//    private Pessoa pessoa;
//    /*
//     * SELECT * FROM Produto p inner join Contatos e ON p.id = e.produto_id
//     */

    public Contato() {}


    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Contatos: [ " +
                "id: " + this.id + "\n" +
                "quantidade: " + this.quantidade + "\n" +
                "produto: " + this.pessoa.getId() + "]";
    }


}
