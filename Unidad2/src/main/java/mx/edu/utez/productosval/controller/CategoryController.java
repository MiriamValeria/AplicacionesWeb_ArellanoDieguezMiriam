package mx.edu.utez.productosval.controller;

import jakarta.validation.Valid;
import mx.edu.utez.productosval.dto.CategoryRequest;
import mx.edu.utez.productosval.dto.CategoryResponse;
import mx.edu.utez.productosval.exception.CustomException;
import mx.edu.utez.productosval.model.Category;
import mx.edu.utez.productosval.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> findAll() {
        List<CategoryResponse> responses = new ArrayList<>();
        for (Category category : service.findAll()) {
            responses.add(toResponse(category));
        }
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getById(@PathVariable Long id) {
        Category category = service.findById(id)
                .orElseThrow(() -> new CustomException("Categoría no encontrada con el ID: " + id));
        return ResponseEntity.ok(toResponse(category));
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> save(@Valid @RequestBody CategoryRequest request) {
        Category categorySaved = service.save(toEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(categorySaved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable Long id, @Valid @RequestBody CategoryRequest request) {
        Category categoryUpdated = service.update(id, toEntity(request))
                .orElseThrow(() -> new CustomException("No se pudo actualizar. Categoría no encontrada con el ID: " + id));
        return ResponseEntity.ok(toResponse(categoryUpdated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = service.delete(id);
        if (!deleted) {
            throw new CustomException("No se pudo eliminar. Categoría no encontrada con el ID: " + id);
        }
        return ResponseEntity.noContent().build();
    }

    private CategoryResponse toResponse(Category category) {
        return new CategoryResponse(category.getId(), category.getName());
    }

    private Category toEntity(CategoryRequest request) {
        Category category = new Category();
        category.setName(request.name());
        return category;
    }
}