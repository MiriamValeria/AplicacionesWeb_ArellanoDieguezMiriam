package com.example.productosVal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.productosVal.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}