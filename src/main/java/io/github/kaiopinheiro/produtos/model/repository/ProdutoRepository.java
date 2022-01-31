package io.github.kaiopinheiro.produtos.model.repository;

import io.github.kaiopinheiro.produtos.model.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Query(" select p from Produto p " +
            " where upper( p.nome ) like upper( :nome )   ")
    List<Produto> findByNome(
            @Param("nome") String nome);

    @Query(" select p from Produto p " +
            " where upper( p.user ) like upper( :user )   ")
    List<Produto> findByUser(
            @Param("user") String user);
}

