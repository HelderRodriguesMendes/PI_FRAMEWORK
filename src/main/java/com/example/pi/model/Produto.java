/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.pi.model;

import java.util.Collection;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author dp
 */
@Entity
public class Produto {

    private Long id;
    private String nome;
    private double preco;
    private double custo;
    private int quantidade;
    private Collection<Categoria> categoria;
    private List<Imagem> imagem;

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the preco
     */
    public double getPreco() {
        return preco;
    }

    /**
     * @param preco the preco to set
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }

    /**
     * @return the custo
     */
    public double getCusto() {
        return custo;
    }

    /**
     * @param custo the custo to set
     */
    public void setCusto(double custo) {
        this.custo = custo;
    }

    /**
     * @return the quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the categoria
     */
    // TESTE
    @ManyToMany()
    public Collection<Categoria> getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(Collection<Categoria> categoria) {
        this.categoria = categoria;
    }

    @OneToMany()
    @JoinColumn(name = "id_produto")
    public Collection<Imagem> getImagem() {
        return imagem;
    }

    /**
     * @param imagem the imagem to set
     */
    public void setImagem(List<Imagem> imagem) {
        this.imagem = imagem;
    }

}
