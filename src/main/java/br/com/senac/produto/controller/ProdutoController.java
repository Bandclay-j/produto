package br.com.senac.produto.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.senac.produto.entity.Produto;
import br.com.senac.produto.repository.ProdutoRepository;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class ProdutoController {

    private ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping("/produto")
    public ResponseEntity<?> getDadosProduto() {
        return new ResponseEntity<>(produtoRepository.findAll(), HttpStatus.OK);
    }
    
    @GetMapping("/produto/{id}")
    public ResponseEntity<?> getProdutoById(@PathVariable int id) {
        return new ResponseEntity<>(produtoRepository.findById(id), HttpStatus.OK);
    }

    @GetMapping("/produto/nome/{nome}")
    public ResponseEntity<?> getProdutoByNome(@PathVariable String nome) {
        return new ResponseEntity<>(produtoRepository.findByNomeLike('%' + nome + '%'), HttpStatus.OK);
    }

    @GetMapping("/produto/preco/maior/{preco}")
    public ResponseEntity<?> getByPrecoMaior(@PathVariable double preco) {
        return new ResponseEntity<>(produtoRepository.findByPrecoGreaterThanEqual(preco), HttpStatus.OK);
    }

    @GetMapping("/produto/preco/menor/{preco}")
    public ResponseEntity<?> getByPrecoMenor(@PathVariable double preco) {
        return new ResponseEntity<>(produtoRepository.findByPrecoLessThanEqual(preco), HttpStatus.OK);
    }

    @PostMapping("/produto")
    public ResponseEntity<?> salvarProdutos(@RequestBody Produto entity) {
        Produto produtoSalvo;
        try {
            produtoSalvo = produtoRepository.save(entity);
        } catch (Exception e) {
            return new ResponseEntity<String>("Erro ao salvar o Produto", 
                                                HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Produto>(produtoSalvo, 
                                            HttpStatus.OK);
    }

    @PutMapping("/produto/{id}")
    public ResponseEntity<?> atualizaProduto(@PathVariable int id,
                                                @RequestBody Produto entity) {
        
        Optional<Produto> produtoAtualizar = produtoRepository.findById(id);
        Produto p = null;

        if (produtoAtualizar.isPresent()) {
            p = produtoAtualizar.get();

            p.setNome(entity.getNome());
            p.setCategoria(entity.getCategoria());
            p.setDataCadastro(entity.getDataCadastro());
            p.setPreco(entity.getPreco());

            try {
                p = produtoRepository.save(p);
            } catch(Exception e) {
                return new ResponseEntity<String>("Erro ao atualizar o Produto", 
                                                    HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<Produto>(p, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Produto não encontrado", 
                                                HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/produto/{id}")
    public ResponseEntity<?> deletaProduto(@PathVariable int id) {

        Optional<Produto> produtoExcluir = produtoRepository.findById(id);
        Produto p = null;

        if (produtoExcluir.isPresent()) {
            p = produtoExcluir.get();

            try {
                produtoRepository.delete(p);
            } catch (Exception e) {
                return new ResponseEntity<String>("Erro ao excluir o Produto", 
                                                    HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<Produto>(p, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Produto não encontrado", 
                                                HttpStatus.NOT_FOUND);
        }
    }
}