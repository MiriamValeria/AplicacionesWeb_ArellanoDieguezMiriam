package mx.edu.utez.productosval.service;

import mx.edu.utez.productosval.dto.StockDTO;
import mx.edu.utez.productosval.exception.CustomException;
import mx.edu.utez.productosval.exception.InsufficientStockException;
import mx.edu.utez.productosval.model.Category;
import mx.edu.utez.productosval.model.Producto;
import mx.edu.utez.productosval.repository.CategoryRepository;
import mx.edu.utez.productosval.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    private final ProductoRepository repo;
    private final CategoryRepository repoCategory;

    public ProductoService(ProductoRepository repo, CategoryRepository repoCategory) {
        this.repo = repo;
        this.repoCategory = repoCategory;
    }

    public List<Producto> getAll() {
        return repo.findAll();
    }

    public Optional<Producto> findById(Long id) {
        return repo.findById(id);
    }

    public Producto save(Producto producto, long categoryId) {
        Category category = repoCategory.findById(categoryId).orElseThrow();
        producto.setCategory(category);
        return repo.save(producto);
    }

    public Optional<Producto> update(long id, Producto producto) {
        Optional<Producto> optional = repo.findById(id);
        if (optional.isEmpty()) {
            return Optional.empty();
        }
        Producto productoDB = optional.get();
        productoDB.setName(producto.getName());
        productoDB.setPrice(producto.getPrice());
        productoDB.setStock(producto.getStock());
        return Optional.of(repo.save(productoDB));
    }

    public boolean delete(long id) {
        if (!repo.existsById(id)) {
            return false;
        }
        repo.deleteById(id);
        return true;
    }

    // Nuevo método para actualizar stock
    public Producto actualizarStock(long id, StockDTO dto) {
        Producto producto = repo.findById(id)
                .orElseThrow(() -> new CustomException("Producto no encontrado con el id: " + id));

        String palabraClave = dto.getPalabraClave().trim().toLowerCase();

        if (palabraClave.equals("add") || palabraClave.equals("agregar")) {
            producto.setStock(producto.getStock() + dto.getCantidad());
        } else if (palabraClave.equals("remove") || palabraClave.equals("restar") || palabraClave.equals("remover")) {
            if (producto.getStock() < dto.getCantidad()) {
                throw new InsufficientStockException("No hay suficientes productos al momento de remover");
            }
            producto.setStock(producto.getStock() - dto.getCantidad());
        } else {
            throw new IllegalArgumentException("Palabra clave no válida. Usa 'add' o 'remove'");
        }

        return repo.save(producto);
    }
}