package br.com.senac.produto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "produto")
public class Produto {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Nome do Produto é obrigatório")
    @Column
    private String nome;

    @NotBlank(message = "Preço do Produto é obrigatório")
    @Column
    private Double preco;

    @NotBlank(message = "Data de Cadastro do Produto é obrigatório")
    @Column
    private String dataCadastro;

    @NotBlank(message = "Categoria do Produto é obrigatório")
    @Column
    private String categoria;

    @OneToOne
    @JoinColumn(name = "fornecedor_id", referencedColumnName = "id")
    private Fornecedor fornecedor;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Double getPreco() {
        return preco;
    }
    public void setPreco(Double preco) {
        this.preco = preco;
    }
    public String getDataCadastro() {
        return dataCadastro;
    }
    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String exibeDados() {
        return "Id do Produto: " + getId() + "\nNome do Produto: " + getNome() + 
        "\nPreço do Produto: " + getPreco() + " \nData de Cadastro do Produto: " + getDataCadastro() + 
        "\nCategoria do Produto: " + getCategoria();
    }
}
