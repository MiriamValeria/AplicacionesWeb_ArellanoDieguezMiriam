package mx.edu.utez.productosval;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import com.example.productosVal.config.SecurityConfig;

@SpringBootApplication
@Import(SecurityConfig.class)
public class ProductosValApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductosValApplication.class, args);
    }
}