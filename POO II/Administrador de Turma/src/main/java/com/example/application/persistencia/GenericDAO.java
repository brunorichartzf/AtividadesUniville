package com.example.application.persistencia;

import java.sql.Connection;
import java.sql.SQLException;

public class GenericDAO {
    protected Connection conn() throws SQLException {
        return FabricaDeConexoes.get();
    }

}