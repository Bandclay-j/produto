package br.com.senac.produto.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.senac.produto.repository.CategoriaRepository;
import br.com.senac.produto.repository.ProdutoRepository;

@Controller
public class AplicacaoController {

    private CategoriaRepository categoriaRepository;
    private ProdutoRepository produtoRepository;
    
    public CategoriaRepository getCategoriaRepository() {
        return categoriaRepository;
    }

    public void setCategoriaRepository(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public ProdutoRepository getProdutoRepository() {
        return produtoRepository;
    }

    public void setProdutoRepository(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public AplicacaoController(CategoriaRepository categoriaRepository,
                                 ProdutoRepository produtoRepository) {
                                    
        this.categoriaRepository = categoriaRepository;
        this.produtoRepository = produtoRepository;
    }

    @GetMapping("/index")
    public String showProdutoLista(Model model) {
        model.addAttribute("produto", produtoRepository.findAll());
        return "index";
    }
}