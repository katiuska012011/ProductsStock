package com.alura.jdbc.factory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private DataSource dataSource;

    public ConnectionFactory() {
        var poolDatasource = new ComboPooledDataSource();
        poolDatasource.setJdbcUrl("jdbc:mysql://localhost/stock_control");
        poolDatasource.setUser("root");
        poolDatasource.setPassword("Sigef0101@");

        this.dataSource = poolDatasource;
    }

    public Connection recuperarConexion() {
        try {
            return this.dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
