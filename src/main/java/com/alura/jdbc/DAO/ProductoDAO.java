package com.alura.jdbc.DAO;

import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.modelo.Categoria;
import com.alura.jdbc.modelo.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    private Connection con;

    public ProductoDAO(Connection con) {
        this.con = con;
    }

    public void guardar(Producto producto) {

        try {
            final PreparedStatement statement = con.prepareStatement("INSERT INTO producto(nombre, descripcion, cantidad, categoria_id) "
                    + "VALUES(?, ? ,?, ?)", Statement.RETURN_GENERATED_KEYS);

            try (statement) {
                statement.setString(1, producto.getNombre());
                statement.setString(2, producto.getDescripcion());
                statement.setInt(3, producto.getCantidad());
                statement.setInt(4, producto.getCategoriaId());
                statement.execute();
                final ResultSet resultSet = statement.getGeneratedKeys();
                try (resultSet) {
                    while (resultSet.next()) {
                        producto.setId(resultSet.getInt(1));
                        System.out.println(String.format("Fue insertido el producto %s ", producto.toString()));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }


    public List<Producto> listar() {
        List<Producto> resultado = new ArrayList<>();
        try {
            final PreparedStatement statement = con.prepareStatement("SELECT id, nombre, descripcion, cantidad FROM producto");
            try (statement) {
                statement.execute();
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    Producto producto = new Producto(resultSet.getInt("id"),
                            resultSet.getString("nombre"), resultSet.getString("descripcion"), (resultSet.getInt("cantidad")));
                    resultado.add(producto);
                }
            }
            return resultado;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public int eliminar(Integer id) {
        try {
            final PreparedStatement statement = con.prepareStatement("DELETE FROM producto where id = ?");
            try (statement) {
                statement.setInt(1, id);
                statement.execute();
                return statement.getUpdateCount();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public int modificar(String nombre, String descripcion, Integer cantidad, Integer id) {
        try {
            PreparedStatement statement = con.prepareStatement("UPDATE producto SET nombre = ? , descripcion = ?, cantidad = ? where id = ?");
            try (statement) {
                statement.setString(1, nombre);
                statement.setString(2, descripcion);
                statement.setInt(3, cantidad);
                statement.setInt(4, id);
                statement.execute();
                return statement.getUpdateCount();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Producto> listar(Integer id){
        List<Producto> resultado = new ArrayList<>();
        try {
            final PreparedStatement statement = con.prepareStatement("SELECT id, nombre, descripcion, cantidad FROM producto WHERE categoria_id = ?");
            try (statement) {
                statement.setInt(1, id);
                statement.execute();
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    Producto producto = new Producto(resultSet.getInt("id"),
                            resultSet.getString("nombre"), resultSet.getString("descripcion"), (resultSet.getInt("cantidad")));
                    resultado.add(producto);
                }
            }
            return resultado;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

}
