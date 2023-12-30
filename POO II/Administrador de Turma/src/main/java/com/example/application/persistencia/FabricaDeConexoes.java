package com.example.application.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class FabricaDeConexoes {
    private final static String URL = "jdbc:sqlite:adm_turma.sqlite";
    public static Connection get() throws SQLException {
        return DriverManager.getConnection(URL);
    }

}
