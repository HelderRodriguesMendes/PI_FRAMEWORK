/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.pi.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author dp
 */
@Entity
public class Carrinho {
    
    private Long id; 
   /// private List<ItemCarrinho> itens;
    private Date expireTime;
    private Cliente cli;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Temporal(TemporalType.DATE)
    public Date getExpireTime() {
        return expireTime;
    }

    /**
     * @param expireTime the expireTime to set
     */
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    @OneToOne
    @JoinColumn(name = "id_cliente")
    public Cliente getCli() {
        return cli;
    }

    /**
     * @param cli the cli to set
     */
    public void setCli(Cliente cli) {
        this.cli = cli;
    }
    
    
    
}
