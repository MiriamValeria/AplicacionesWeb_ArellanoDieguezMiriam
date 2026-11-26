package com.example.productosVal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.productosVal.dto.ProductRequest;
import com.example.productosVal.dto.ProductResponse;
import com.example.productosVal.model.Producto;
import com.example.productosVal.service.ProductoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        List<ProductResponse> listResponse = service.getAll().stream()
                .map(this::toResponse)
                .toList();
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long id) {
        Optional<Producto> product = service.findById(id);
        return product.map(value -> ResponseEntity.ok(toResponse(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductResponse> save(@Valid @RequestBody ProductRequest producto) {
        Producto productSaved = service.save(toEntity(producto));
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(productSaved));
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
    public ResponseEntity<ProductResponse> update(@PathVariable Long id, @Valid @RequestBody ProductRequest producto) {
        Optional<Producto> updated = service.update(id, toEntity(producto));
        return updated.map(value -> ResponseEntity.ok(toResponse(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private ProductResponse toResponse(Producto producto) {
        return new ProductResponse(producto.getId(), producto.getName(), producto.getPrice(), producto.getStock());
    }

    private Producto toEntity(ProductRequest request) {
        Producto producto = new Producto();
        producto.setName(request.name());
        producto.setPrice(request.price());
        producto.setStock(request.stock());
        return producto;
    }
}
