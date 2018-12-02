/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.pi.controller;

import com.example.pi.model.Carrinho;
import com.example.pi.services.CarrinhoService;
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
@RequestMapping(value = "/carrinho/clienteAut")
public class CarrinhoController {

    @Autowired
    CarrinhoService carrinhoService;

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity cadastrarCarrinho(@RequestBody Carrinho carrinho) {

        carrinhoService.cadastrarCarrinho(carrinho);

        return new ResponseEntity(HttpStatus.CREATED);

    }

    @RequestMapping(method = RequestMethod.DELETE,
            value = "/{id}")
    ResponseEntity removerCarrinho(@PathVariable Long id) {

        carrinhoService.excluirCarrinho(id);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    ResponseEntity editarCarrinho(@RequestBody Carrinho carrinho) {

        carrinhoService.editarCarrinho(carrinho);

        return new ResponseEntity(HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Carrinho> mostraCarrinho(@PathVariable Long id) {

       Carrinho car = carrinhoService.buscaCrrinho(id);
       
       return new ResponseEntity(car, HttpStatus.OK);
    }

}
