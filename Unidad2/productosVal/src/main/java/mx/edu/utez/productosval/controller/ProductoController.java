package mx.edu.utez.productosval.controller;

import jakarta.validation.Valid;
import mx.edu.utez.productosval.dto.ProductoRequest;
import mx.edu.utez.productosval.dto.ProductoResponse;
import mx.edu.utez.productosval.model.Producto;
import mx.edu.utez.productosval.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProductoResponse>> findAll() {
        List<ProductoResponse> listResponse = new ArrayList<>();
        for (Producto p : service.getAll()) {
            listResponse.add(toResponse(p));
        }
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponse> getById(@PathVariable Long id) {
        Optional<Producto> producto = service.findById(id);
        if (producto.isPresent()) {
            return ResponseEntity.ok(toResponse(producto.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ProductoResponse> save(@Valid @RequestBody ProductoRequest producto) {
        Producto productoSaved = service.save(toEntity(producto), producto.categoryId());
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(productoSaved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = service.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponse> update(@PathVariable Long id, @Valid @RequestBody ProductoRequest producto) {
        Optional<Producto> optional = service.update(id, toEntity(producto));
        if (optional.isPresent()) {
            return ResponseEntity.ok(toResponse(optional.get()));
        }
        return ResponseEntity.notFound().build();
    }

    private ProductoResponse toResponse(Producto producto) {
        Long categoryId = (producto.getCategory() != null) ? producto.getCategory().getId() : null;
        String categoryName = (producto.getCategory() != null) ? producto.getCategory().getName() : null;

        return new ProductoResponse(
                producto.getId(),
                producto.getName(),
                producto.getPrice(),
                producto.getStock(),
                categoryId,
                categoryName
        );
    }

    private Producto toEntity(ProductoRequest request) {
        Producto producto = new Producto();
        producto.setName(request.name());
        producto.setPrice(request.price());
        producto.setStock(request.stock());
        return producto;
    }
}