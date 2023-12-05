package com.ipog.projetointegrador.model;

import jakarta.persistence.*;

@Entity
@Table(name = "documentos")
public class Documento {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String nomeArmazenamento;

    @ManyToOne
    @JoinColumn(name = "idGrupo", referencedColumnName = "id")
    private Grupo grupo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeArmazenamento() {
        return nomeArmazenamento;
    }

    public void setNomeArmazenamento(String nomeArmazenamento) {
        this.nomeArmazenamento = nomeArmazenamento;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
}
