package com.alura.jdbc.controller;

import com.alura.jdbc.DAO.CategoriaDao;
import com.alura.jdbc.DAO.ProductoDAO;
import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.modelo.Categoria;
import com.alura.jdbc.modelo.Producto;

import java.util.ArrayList;
import java.util.List;

public class CategoriaController {

    private CategoriaDao categoriaDao;


    public CategoriaController(){
        var factory = new ConnectionFactory();
        this.categoriaDao = new CategoriaDao(factory.recuperarConexion());
    }
	public List<Categoria> listar() {
		return categoriaDao.listar();
	}

    public List<Categoria> cargaReporte() {
        return this.categoriaDao.listarConProducto();
    }



}
