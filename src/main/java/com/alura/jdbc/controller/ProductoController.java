package com.alura.jdbc.controller;

import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.modelo.Categoria;
import com.alura.jdbc.modelo.Producto;
import com.alura.jdbc.DAO.ProductoDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoController {

     private ProductoDAO productoDAO;

    public ProductoController() {
        var factory = new ConnectionFactory();
        this.productoDAO = new ProductoDAO(factory.recuperarConexion());
    }

    public int modificar(String nombre, String descripcion, Integer cantidad, Integer id) {
        return productoDAO.modificar(nombre, descripcion, cantidad, id);
    }

    public int eliminar(Integer id) {
        return productoDAO.eliminar(id);
    }

    public List<Producto> listar() throws SQLException {
        return productoDAO.listar();
    }

    public void guardar(Producto producto, Integer categoriaID) {
        producto.setCategoriaId(categoriaID);
        productoDAO.guardar(producto);
    }
    public List<Producto> listar(Categoria categoria){
        return productoDAO.listar(categoria.getId());
    }

}
