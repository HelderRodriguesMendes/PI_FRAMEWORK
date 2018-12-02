/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.pi.controller;

import com.example.pi.model.Categoria;
import com.example.pi.services.CategoriaService;
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
@RequestMapping(value = "/categoria/admimAut")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, value = "/admim")
    ResponseEntity cadastrarCategoria(@RequestBody Categoria categoria) {

        categoriaService.cadastrarCategoria(categoria);

        return new ResponseEntity(HttpStatus.CREATED);

    }

    @RequestMapping(method = RequestMethod.DELETE,
            value = "/{id}/admim")
    ResponseEntity removerCategoria(@PathVariable Long id) {

        categoriaService.excluirCategoria(id);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/admim")
    ResponseEntity editarCategoria(@RequestBody Categoria categoria) {

        categoriaService.editarCategoria(categoria);

        return new ResponseEntity(HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Categoria> mostraCategoria(@PathVariable Long id) {

       Categoria ca = categoriaService.buscaCategoria(id);

       return new ResponseEntity(ca, HttpStatus.OK);
    }

}
