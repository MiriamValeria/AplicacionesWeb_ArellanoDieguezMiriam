package com.example.productosVal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.productosVal.model.Producto;
import com.example.productosVal.repository.ProductoRepository;

@Service
public class ProductoService {

    private ProductoRepository repo;

    public ProductoService(ProductoRepository repo) {
        this.repo = repo;
    }

    public List<Producto> getAll() {
        return repo.findAll();
    }

    public Optional<Producto> findById(Long id) {
        return repo.findById(id);
    }

    public Producto save(Producto producto) {
        return repo.save(producto);
    }

    public Optional<Producto> update(long id, Producto producto) {
        Optional<Producto> optional = repo.findById(id);
        if (optional.isEmpty()) {
            return Optional.empty();
        }
        Producto productoDb = optional.get();

        productoDb.setName(producto.getName());
        productoDb.setPrice(producto.getPrice());
        productoDb.setStock(producto.getStock());

        return Optional.of(repo.save(productoDb));
    }

    public boolean delete(Long id) {
        if (!repo.existsById(id)) {
            return false;
        }
        repo.deleteById(id);
        return true;
    }


}
