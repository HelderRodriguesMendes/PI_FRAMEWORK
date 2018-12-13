/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.pi.controller;

import com.example.pi.model.Carrinho;
import com.example.pi.model.ItemCarrinho;
import com.example.pi.model.ItemVenda;
import com.example.pi.model.Venda;
import com.example.pi.services.CarrinhoService;
import com.example.pi.services.ItemCarrinhoService;
import com.example.pi.services.VendaService;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
    
    @Autowired
    CarrinhoService carrinhoService;

    @Autowired
    ItemCarrinhoService itemCarrinhoService;
    
    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, value = "/admim")
    ResponseEntity cadastrarVenda(@RequestBody Venda venda) {

        vendaService.cadastrarVenda(venda);

        return new ResponseEntity(HttpStatus.CREATED);

    }
    
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}/admim")
    ResponseEntity removerVenda(@PathVariable Long id) {

        vendaService.excluirVenda(id);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/admim")
    ResponseEntity alterarVenda(@RequestBody Venda venda) {
        
        vendaService.alterarVenda(venda);
        
        return new ResponseEntity(HttpStatus.OK);
        
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Venda> mostraVenda(@PathVariable Long id) {
        
        Venda v = vendaService.buscaVenda(id);
        
        return new ResponseEntity(v, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/clienteAut")
    ResponseEntity FinalizarVenda(@RequestHeader HttpHeaders headrs){
        long idCarrinho;
        try {
             idCarrinho = carrinhoService.carrinhoToken(headrs);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        Carrinho car = carrinhoService.buscaCrrinho(idCarrinho);
        Venda venda = new Venda();
        ArrayList<ItemVenda> listaItens = new ArrayList<ItemVenda>();
        
        double total = 0;
        for(ItemCarrinho ic : car.getItens()){
            ItemVenda iv = new ItemVenda();
            iv.setCusto(ic.getProduto().getCusto());
            iv.setProduto(ic.getProduto());
            iv.setQuantidade(ic.getQuantidade());
            iv.setValor(ic.getProduto().getPreco());
            iv.setVenda(venda);
            listaItens.add(iv); 
            total += iv.getValor() * iv.getQuantidade();
        }
        venda.setItenVenda(listaItens);
        venda.setValor(total);
        venda.setData(new Date());
        vendaService.cadastrarVenda(venda);
        itemCarrinhoService.limparCarrinho(car);
        
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
}
