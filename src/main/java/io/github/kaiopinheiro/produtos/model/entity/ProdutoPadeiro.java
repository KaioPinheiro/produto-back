package io.github.kaiopinheiro.produtos.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

    @Entity
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class ProdutoPadeiro {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @ManyToOne
        @JoinColumn (name="id_produto")
        private Produto produto;

        @Column(nullable = false, length = 11)
        private Integer quantidade;

        @Column(name = "data_atualizacao",updatable = false)
        @JsonFormat(pattern = "dd/MM/yyyy")
        private LocalDate dataAtualizacao;

        @Column(nullable = false, length = 150)
        private String user;

        @PrePersist
        public void prePersist(){
            setDataAtualizacao(LocalDate.now());
        }


}
