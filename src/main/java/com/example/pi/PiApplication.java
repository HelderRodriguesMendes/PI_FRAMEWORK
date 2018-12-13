package com.example.pi;

import com.example.pi.properties.FileStorageProperties;
import com.example.pi.settings.AdmimFiltro;
import com.example.pi.settings.ClienteFiltro;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class PiApplication {
    
    @Bean
    public FilterRegistrationBean filtroCli(){
        FilterRegistrationBean fr = new FilterRegistrationBean();
        fr.setFilter(new ClienteFiltro());
        fr.addUrlPatterns("/clienteAut/*");
        return fr;
    }
    
    @Bean
    public FilterRegistrationBean filtroAdmim(){
        FilterRegistrationBean fr = new FilterRegistrationBean();
        fr.setFilter(new AdmimFiltro());
        fr.addUrlPatterns("/admimAut/*");
        return fr;
    }

	public static void main(String[] args) {
		SpringApplication.run(PiApplication.class, args);
	}
}
