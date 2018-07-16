package br.com.bmsti.produto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import br.com.bmsti.produto.config.JpaConfig;

@Import(JpaConfig.class)
@SpringBootApplication(scanBasePackages={"br.com.bmsti.produto"})
public class ProdutoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProdutoApplication.class, args);
	}
}
