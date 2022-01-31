package io.github.kaiopinheiro.produtos.rest;

import io.github.kaiopinheiro.produtos.model.entity.Produto;
import io.github.kaiopinheiro.produtos.model.entity.ProdutoPadeiro;
import io.github.kaiopinheiro.produtos.model.repository.ProdutoPadeiroRepository;
import io.github.kaiopinheiro.produtos.model.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping ("/api/produtos")
@CrossOrigin("http://localhost:4200")
public class ProdutoController {

    private final ProdutoRepository repository;
    private final ProdutoPadeiroRepository repositoryUser;

    @Autowired
    public ProdutoController(ProdutoRepository repository, ProdutoPadeiroRepository repositoryUser) {
        this.repository = repository;
        this.repositoryUser = repositoryUser;
    }

   /* @GetMapping
    public List<Produto> obterTodos(){
        return repository.findAll();
    }*/

    @GetMapping
    public List<Produto> pesquisar(
            @RequestParam(value = "nome", required = false, defaultValue = "") String nome
    ) {
        return repository.findByNome("%" + nome + "%");
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto salvar( @RequestBody @Valid Produto produto ){
        return repository.save(produto);

    }

    @GetMapping("{id}")
    public Produto acharporId( @PathVariable Integer id ){
        return repository
                .findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado") );
    }

    @GetMapping("{user}")
    public  List<Produto> produtoPorUser( @PathVariable String user ){
        return repository
                .findByUser(user);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar( @PathVariable Integer id, @RequestBody @Valid Produto produtoAtualizado ){
              Produto produto=new Produto();
              produto=produtoAtualizado;
              produto.setQuantidade(produtoAtualizado.getQuantidade()-1);
        repository.save(produto);
    }
}
