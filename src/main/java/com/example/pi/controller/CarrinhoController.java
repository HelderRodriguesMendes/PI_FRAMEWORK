/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.pi.controller;

import com.example.pi.model.Carrinho;
import com.example.pi.model.ItemCarrinho;
import com.example.pi.model.ItemVenda;
import com.example.pi.model.Produto;
import com.example.pi.model.Venda;
import com.example.pi.services.CarrinhoService;
import com.example.pi.services.ItemCarrinhoService;
import com.example.pi.services.ProdutoService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
@RequestMapping(value = "/carrinho/clienteAut")
public class CarrinhoController {

    @Autowired
    CarrinhoService carrinhoService;

    @Autowired
    ItemCarrinhoService itemCarrinhoService;

    @Autowired
    ProdutoService produtoService;

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
    
    
    
    
    
    
    
    

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Carrinho> mostraCarrinho(@RequestHeader HttpHeaders headrs) {
        long id;
        try {
             id = (carrinhoService.carrinhoToken(headrs));
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Carrinho car = carrinhoService.buscaCrrinho(id);

        return new ResponseEntity(car, HttpStatus.OK);
    }

    
    
    
    
    
    
    
    
    
    
    
    
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity adicionarProdutoNoCarrinho(@RequestBody Produto pro, @RequestHeader HttpHeaders headrs) {
        Carrinho carri;
        Produto produtoNoEstoque = produtoService.buscaProduto(pro.getId());
        
        long idCarrrinho;
        try {
             idCarrrinho = (carrinhoService.carrinhoToken(headrs));
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
            carri = carrinhoService.buscaCrrinho(idCarrrinho);

        ItemCarrinho produtosNoCarrinho = itemCarrinhoService.verificarProdutoNoCarrinho(carri, pro);

        if (produtosNoCarrinho == null) {
            if (pro.getQuantidade() <= produtoNoEstoque.getQuantidade()) {
                ItemCarrinho itemC = new ItemCarrinho();

                itemC.setProduto(pro);
                itemC.setQuantidade(pro.getQuantidade());
                itemC.setCarrinho(carri);
                itemCarrinhoService.cadastrarItemCarrinho(itemC);

                carrinhoService.restaurarTempoExpiração(carri);
                carrinhoService.cadastrarCarrinho(carri);
            } else {
                return new ResponseEntity("A quantidade desajada é inferior a que contem no estoque", HttpStatus.BAD_REQUEST);
            }
        } else {
            if ((produtosNoCarrinho.getQuantidade() + pro.getQuantidade()) <= produtoNoEstoque.getQuantidade()) {
                produtosNoCarrinho.setQuantidade(produtosNoCarrinho.getQuantidade() + pro.getQuantidade());

                itemCarrinhoService.cadastrarItemCarrinho(produtosNoCarrinho);
            }
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
