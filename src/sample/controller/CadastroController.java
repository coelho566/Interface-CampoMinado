package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import sample.Dao.Dao;
import sample.Main;
import sample.model.Telas;
import sample.service.CriptografaDados;

public class CadastroController {

    @FXML
    private TextField email;

    @FXML
    private TextField nome;

    @FXML
    private TextField senha;

    @FXML
    protected void cadastrar(ActionEvent event){

        Dao dao = new Dao();
        CriptografaDados criptografa = new CriptografaDados();

        String mail = criptografa.criptografia(email.getText());
        String name = criptografa.criptografia(nome.getText());
        String password = criptografa.criptografia(senha.getText());

        if(!dao.login(mail, password)) {
            if (mail != null && name != null && password != null) {
                dao.inserir(mail, name, password);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Atenção");
                alert.setHeaderText("Usuário cadastrado com sucesso!");
                alert.setContentText("Seus dados estão bem protegidos! Bom jogo");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Atenção");
                alert.setHeaderText("Atenção! Campo vazio");
                alert.setContentText("Por favor preencha todos os campos");
                alert.show();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atenção");
            alert.setHeaderText("Atenção! email já existe");
            alert.setContentText("Faça cadastro com outro email");
            alert.show();
        }
    }

    @FXML
    protected void logar(ActionEvent event){

       Dao dao = new Dao();
       CriptografaDados criptografaDados = new CriptografaDados();

        String mail = criptografaDados.criptografia(email.getText());
       String password = criptografaDados.criptografia(senha.getText());

        if(dao.login(mail, password)){
            Main.trocaTela(Telas.CAMPO);
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Atenção");
            alert.setHeaderText("Atenção! Usuário ou senha inválidas");
            alert.setContentText("Digite os campos corretos ou faça seu cadastro");
            alert.show();
        }
    }

    @FXML
    protected void cadastro(ActionEvent event){
        Main.trocaTela(Telas.CADASTRO);
    }

    @FXML
    protected void voltar(ActionEvent event){
        Main.trocaTela(Telas.LOGIN);
    }
}
