package mx.edu.utez.productosval.repository;

import mx.edu.utez.productosval.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}