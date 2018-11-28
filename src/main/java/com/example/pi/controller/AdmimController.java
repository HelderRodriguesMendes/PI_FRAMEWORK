/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.pi.controller;

import com.example.pi.model.Admim;
import com.example.pi.services.AdmimService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
 * @author helde
 */
@RestController
@RequestMapping(value = "/admim")
public class AdmimController {
    
    @Autowired
    AdmimService admimService;
    
    public final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    
     @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity CadastrarAdmim(@RequestBody Admim adm){
        admimService.cadastraAdmim(adm);
        
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}/admim")
    ResponseEntity removerAdmim(@PathVariable Long id){
        admimService.excluirAdmim(id);
        
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    ResponseEntity editarAdmim(@RequestBody Admim id){
        admimService.editarAdmim(id);
        
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity mostrarAdmim(@PathVariable Long id){
        
        Admim adm = admimService.bauscarAdmim(id);
        
        return new ResponseEntity(adm, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/autenticar")
    public ResponseEntity Autenticar(@RequestBody Admim adm){
        Admim admAutenti  = admimService.validarAdmim(adm);
        
        if(admAutenti == null || admAutenti.getNome().equals("") || admAutenti.getSenha().equals("")){
            return new ResponseEntity<>(admAutenti, HttpStatus.FORBIDDEN);
        }
        
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.claim("admim", true);
        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 100));
        jwtBuilder.signWith(key);
        
        String token = jwtBuilder.compact();
        
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + token);
        
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }
}
