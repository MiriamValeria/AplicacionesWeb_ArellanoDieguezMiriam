package mx.edu.utez.productosval.repository;

import mx.edu.utez.productosval.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}