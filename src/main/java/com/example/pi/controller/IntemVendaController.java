/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.pi.controller;

import com.example.pi.model.ItemVenda;
import com.example.pi.model.Venda;
import com.example.pi.services.ItemVendaService;
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
@RequestMapping(value = "/itemVenda")
public class IntemVendaController {
    
    @Autowired
    ItemVendaService itemVendaService;
    
    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, value = "/admim")
    ResponseEntity cadastrarItemVenda(@RequestBody ItemVenda itemVenda) {

        itemVendaService.cadastrarItemVenda(itemVenda);

        return new ResponseEntity(HttpStatus.CREATED);

    }
    
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}/admim/admimAut")
    ResponseEntity removerItemVenda(@PathVariable Long id) {

        itemVendaService.excluirItemVenda(id);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/admim7admimAut")
    ResponseEntity alterarItemVenda(@RequestBody ItemVenda itemVenda) {
        
        itemVendaService.alterarItemVenda(itemVenda);
        
        return new ResponseEntity(HttpStatus.OK);
        
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Venda> mostraItemVenda(@PathVariable Long id) {
        
        ItemVenda v = itemVendaService.buscaItemVenda(id);
        
        return new ResponseEntity(v, HttpStatus.OK);
    }
}
