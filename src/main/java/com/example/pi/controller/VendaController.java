/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.pi.controller;

import com.example.pi.model.Venda;
import com.example.pi.services.VendaService;
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
@RequestMapping(value = "/venda")
public class VendaController {
    
    @Autowired
    VendaService vendaService;
    
    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, value = "/admim")
    ResponseEntity cadastrarVenda(@RequestBody Venda venda) {

        vendaService.cadastrarVenda(venda);

        return new ResponseEntity(HttpStatus.CREATED);

    }
    
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}/admim/admimAut")
    ResponseEntity removerVenda(@PathVariable Long id) {

        vendaService.excluirVenda(id);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/admim7admimAut")
    ResponseEntity alterarVenda(@RequestBody Venda venda) {
        
        vendaService.alterarVenda(venda);
        
        return new ResponseEntity(HttpStatus.OK);
        
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Venda> mostraVenda(@PathVariable Long id) {
        
        Venda v = vendaService.buscaVenda(id);
        
        return new ResponseEntity(v, HttpStatus.OK);
    }
}
