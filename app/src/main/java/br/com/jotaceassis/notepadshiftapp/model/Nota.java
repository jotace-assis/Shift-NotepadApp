package br.com.jotaceassis.notepadshiftapp.model;

import com.google.gson.annotations.SerializedName;

public class Nota {

    private String id;
    private String titulo;
    @SerializedName(value = "texto")
    private String descricao;

    public Nota() {}
    public Nota(String titulo, String descricao) {
        setTitulo(titulo);
        setDescricao(descricao);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
