package br.com.senac.produto.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senac.produto.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    ArrayList<Produto> findByNomeLike(String nome);

    ArrayList<Produto> findByPrecoGreaterThanEqual(double preco);

    ArrayList<Produto> findByPrecoLessThanEqual(double preco);
}
