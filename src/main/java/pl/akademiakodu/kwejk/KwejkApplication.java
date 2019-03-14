package pl.akademiakodu.kwejk;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;


import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.SessionScope;
import pl.akademiakodu.kwejk.storage.StorageProperties;
import pl.akademiakodu.kwejk.storage.StorageService;


@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class KwejkApplication {

	public static void main(String[] args) {
		SpringApplication.run(KwejkApplication.class, args);
	}

	@Bean
	//@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll(); //zakomentowałam, żeby nie kasowało mi innych gifów, które mają być zawsze
			storageService.init();
			storageService.loadAll();
		};
	}
}
