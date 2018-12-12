/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.pi.controller;

import com.example.pi.model.Carrinho;
import com.example.pi.model.Cliente;
import com.example.pi.services.CarrinhoService;
import com.example.pi.services.ClienteService;
import com.example.pi.services.ItemCarrinhoService;
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
 * @author dp
 */
@RestController // especifica que essa classe vai recebe aquisições HTTP
@RequestMapping(value = "/cliente")
public class ClienteController {
    

    @Autowired // informa que essa classe interliga com a ClienteService
    ClienteService clienteService; // com isso n precisa fazer instancia da classe ClienteService
    
    @Autowired
    CarrinhoService carrinhoService;
    
    @Autowired
    ItemCarrinhoService itemCarrinhoService;
    
    public static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    
    @RequestMapping(method = RequestMethod.POST, //RequestMapping: especifica o tipo de HTTP que essa classe vai responde,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    /* nesse caso vai responde a aquisições post(SALVAR), do tipo post */
    ResponseEntity cadastrarCliente(@RequestBody Cliente cli) { //RequestBody: pega o JSON e transforma em objeto

        clienteService.cadastrarCliente(cli);

        return new ResponseEntity(HttpStatus.CREATED);

    }

    @RequestMapping(method = RequestMethod.DELETE,
            value = "/{id}/admim")
    ResponseEntity removerCliente(@PathVariable Long id) {

        clienteService.excluirCliente(id);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    ResponseEntity editarCliente(@RequestBody Cliente cli) {

        clienteService.editarCliente(cli);

        return new ResponseEntity(HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Cliente> mostraCliente(@PathVariable Long id) {

        Cliente cl = clienteService.buscaCliente(id);

        return new ResponseEntity(cl, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST, 
            consumes = MediaType.APPLICATION_JSON_VALUE, value = "/autenticar")
    public ResponseEntity autenticar(@RequestBody Cliente cli){
        
        Cliente cliAuten = clienteService.validarCliente(cli);
                
        if(cliAuten == null || cliAuten.getNome().equals("") || cliAuten.getSenha().equals("")){
            return new ResponseEntity<>(cliAuten, HttpStatus.FORBIDDEN);
        }
            Carrinho carri = veriticarCarrinho(cliAuten);
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setSubject(cliAuten.getNome());
        jwtBuilder.claim("id_cliente", cliAuten.getId());
        jwtBuilder.claim("id_carrinho", carri.getId());
        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 1000));
        jwtBuilder.signWith(key); 
        
        String token = jwtBuilder.compact(); 
        
        HttpHeaders hearders = new HttpHeaders();
        
        hearders.add("Authorization", "Bearer " + token);
        
        return new ResponseEntity<>(hearders, HttpStatus.OK);
    }
    
    public Carrinho veriticarCarrinho(Cliente cliAutent){
         Carrinho carri = carrinhoService.verificarCarrinhoId_Cli(cliAutent); 
       
        if(carri == null){
            carri = new Carrinho();
            carri.setCli(cliAutent);
            carrinhoService.restaurarTempoExpiração(carri);
            carri = carrinhoService.casdastrarNovoCarrinho(carri);
        }else{
            if(carri.getExpireTime().before(new Date(System.currentTimeMillis())) ){ // se a data de expirção for 
                System.out.println("LIMPAR CARRINHO EXPIRADO");
                itemCarrinhoService.limparCarrinho(carri);
               carrinhoService.restaurarTempoExpiração(carri);
            }
        }
        
        return carri;
    }
}
