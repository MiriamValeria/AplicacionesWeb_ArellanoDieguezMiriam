package mx.edu.utez.productosval.service;

import mx.edu.utez.productosval.model.Category;
import mx.edu.utez.productosval.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Optional<Category> findById(Long id) {
        return repository.findById(id);
    }

    public Category save(Category category) {
        return repository.save(category);
    }

    public Optional<Category> update(Long id, Category category) {
        if (!repository.existsById(id)) {
            return Optional.empty();
        }
        category.setId(id);
        return Optional.of(repository.save(category));
    }

    public boolean delete(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
}