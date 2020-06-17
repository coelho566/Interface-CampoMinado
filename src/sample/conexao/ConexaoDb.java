package sample.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDb {

    public static Connection getConexao() {

        try {
            final String url = "jdbc:mysql://localhost:3306?verifyServerCertificate=false&useSSL=true";
            final String usuario = "root";
            final String senha = "root";

            return DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
