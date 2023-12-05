package com.ipog.projetointegrador.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "grupos")
public class Grupo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    @OneToMany(mappedBy = "grupo")
    private List<Documento> documentos;

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
