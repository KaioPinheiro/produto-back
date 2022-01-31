package io.github.kaiopinheiro.produtos.model.repository;

import io.github.kaiopinheiro.produtos.model.entity.ProdutoPadeiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoPadeiroRepository extends JpaRepository<ProdutoPadeiro, Integer> {

    @Query(" select p from ProdutoPadeiro p " +
            " where upper( p.user ) like upper( :user )   ")
    List<ProdutoPadeiro> findByUser(
            @Param("user") String user);
}

