/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.pi.services;

import com.example.pi.model.Imagem;
import com.example.pi.model.Produto;
import com.example.pi.properties.FileStorageProperties;
import com.example.pi.repository.ImagemRepository;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author dp
 */
@Service
public class ImagemService {
    
    private final Path fileStorageLocation;
    
    @Autowired
    private ImagemRepository imagemRepository;
    
    public ImagemService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException e) {
            System.out.println("Não foi possível criar o diretório raiz de uploads" + e);
        }
    }
    
    public String generatePath() {
        
        String path;
        
        LocalDateTime dateTime;
        dateTime = LocalDateTime.now();
        path = "/" + dateTime.getYear() + "/" + dateTime.getMonth() + "/" + dateTime.getDayOfMonth() + "/";
        
        return path;
        
    }
    
    public String generateUUIDandExtesion(MultipartFile file) throws IOException {
        
        String fullname = file.getOriginalFilename();
        String ext = "";
        
        int i = fullname.lastIndexOf('.');
        
        if (i == -1) {
            throw new IOException("Arquivo sem extensão válida");
        }
        
        if (i >= 0) {
            ext = fullname.substring(i);
        }
        
        return UUID.randomUUID() + ext;
        
    }
    
    public void salvarImagem(MultipartFile file, Produto produto) throws IOException {
        
        String relativePath = generatePath() + generateUUIDandExtesion(file);
        Path absolutePath;
        
        absolutePath = Paths.get(fileStorageLocation.toString() + relativePath).toAbsolutePath().normalize();
        
        try {
            Files.createDirectories(absolutePath);
            Files.copy(file.getInputStream(), absolutePath, StandardCopyOption.REPLACE_EXISTING);
            
            Imagem img = new Imagem();
            img.setPatch(relativePath);
            img.setProduto(produto);
            
            imagemRepository.save(img);
            
        } catch (IOException e) {
            
            System.out.println("Erro: " + e);
            
        }
        
    }
    
    public Resource consultarImagem(Long id) throws FileNotFoundException {
        
        Imagem imagem = imagemRepository.findById(id).get();
        
        try {
            
            if (imagem == null) {
                
                throw new FileNotFoundException("File not found");
                
            }
            
            Path imagemPath = Paths.get(fileStorageLocation.toString() + imagem.getPatch()).toAbsolutePath().normalize();
            Path fullPath = this.fileStorageLocation.resolve(imagemPath).normalize();
            
            Resource resource = new UrlResource(fullPath.toUri());
            
            if (resource.exists()) {
                return resource;
            }
            
        } catch (NoSuchElementException | MalformedURLException e) {
            
            throw new FileNotFoundException("File not found");
        }
        
        throw new FileNotFoundException("File not found");
    }
    
}
