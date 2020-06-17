package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.conexao.ConexaoDb;
import sample.conexao.ConexaoTable;
import sample.model.Telas;

import java.sql.Connection;
import java.sql.Statement;

public class Main extends Application {

    private static Stage stage;
    private static Scene sceneLogin;
    private static Scene sceneCadastro;
    private static Scene sceneCampo;

    @Override
    public void start(Stage primaryStage) throws Exception{

        stage = primaryStage;

        Parent login = FXMLLoader.load(getClass().getResource("view/login.fxml"));
        sceneLogin = new Scene(login);

        Parent cadastro = FXMLLoader.load(getClass().getResource("view/cadastrar.fxml"));
        sceneCadastro = new Scene(cadastro);

        Parent campo = FXMLLoader.load(getClass().getResource("view/campo.fxml"));
        sceneCampo = new Scene(campo);


        primaryStage.setTitle("Campo Minado");
        primaryStage.setScene(sceneLogin);
        primaryStage.show();

        //Cria o banco de dados
        Connection connection = ConexaoDb.getConexao();

        Statement statement = connection.createStatement();
        statement.execute("CREATE DATABASE IF NOT EXISTS campominado");
        connection.close();

        //Cria tabela usuario
        Connection conn = ConexaoTable.getConexao();

        Statement stm = conn.createStatement();
        stm.execute("CREATE TABLE IF NOT EXISTS usuario ("
                + "id INT AUTO_INCREMENT PRIMARY KEY ,"
                + "nome VARCHAR(100) NOT NULL,"
                + "email VARCHAR(100) NOT NULL,"
                + "senha VARCHAR(100) NOT NULL )");
        conn.close();
    }

    public static void trocaTela(Telas telas){

        switch (telas){
            case LOGIN:
                stage.setScene(sceneLogin);
                break;
            case CADASTRO:
                stage.setScene(sceneCadastro);
                break;
            case CAMPO:
                stage.setScene(sceneCampo);
                break;
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
