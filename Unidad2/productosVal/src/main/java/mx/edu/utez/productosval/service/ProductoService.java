package mx.edu.utez.productosval.service;

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

    public ProductoService(ProductoRepository repo , CategoryRepository repoCategory){
        this.repo = repo;
        this.repoCategory= repoCategory;
    }

    public List<Producto> getAll(){
        return repo.findAll();
    }

    public Optional<Producto> findById(Long id){
        return repo.findById(id);
    }

    public Producto save(Producto producto,long categoryId){
        Category category = repoCategory.findById(categoryId).orElseThrow();
        producto.setCategory(category);
        return repo.save(producto);
    }

    public Optional<Producto> update(long id, Producto producto){
        Optional<Producto> optional = repo.findById(id);
        if(optional.isEmpty()){
            return Optional.empty();
        }
        Producto productoDB = optional.get();
        productoDB.setName(producto.getName());
        productoDB.setPrice(producto.getPrice());
        productoDB.setStock(producto.getStock());
        return Optional.of(repo.save(productoDB));
    }

    public boolean delete(long id){
        if(!repo.existsById(id)){
            return false;
        }
        repo.deleteById(id);
        return true;
    }
}