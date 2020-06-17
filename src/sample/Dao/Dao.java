package sample.Dao;

import sample.conexao.ConexaoTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dao {

    private Connection connection;

    public void inserir(String email, String name, String password) {

        String sql = "INSERT INTO usuario (email, nome, senha) VALUES (?, ?, ?)";

        try {
            PreparedStatement statement = this.getConnection().prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, name);
            statement.setString(3, password);
            statement.execute();
            this.getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean login(String email, String password){

        String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";

        try{
            PreparedStatement statement = this.getConnection().prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                return  true;
            }else {
                return  false;
            }

        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    private Connection getConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                return connection;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        connection = ConexaoTable.getConexao();
        return connection;
    }
}
