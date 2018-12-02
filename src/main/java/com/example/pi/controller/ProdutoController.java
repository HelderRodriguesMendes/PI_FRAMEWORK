/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.pi.controller;

import com.example.pi.model.Produto;
import com.example.pi.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author dp
 */
@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {
    
    @Autowired
    ProdutoService produtoService;

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, value = "/admim/admimAut" )
    ResponseEntity cadastrarProduto(@RequestBody Produto produto) {

        produtoService.cadastrarProduto(produto);

        return new ResponseEntity(HttpStatus.CREATED);

    }

    @RequestMapping(method = RequestMethod.DELETE,
            value = "/{id}/admim/admimAut")
    ResponseEntity removerProduto(@PathVariable Long id) {

        produtoService.excluirProduto(id);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/admim/admimAut") 
    ResponseEntity editarProduto(@RequestBody Produto produto) {
        
        produtoService.editarProduto(produto);
        
        return new ResponseEntity(HttpStatus.OK);
        
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Produto> mostraProduto(@PathVariable Long id) {
        
        Produto pr = produtoService.buscaProduto(id);
        
        return new ResponseEntity(pr, HttpStatus.OK);
    }
    
}
