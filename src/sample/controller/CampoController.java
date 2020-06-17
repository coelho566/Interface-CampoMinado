package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.model.Tabuleiro;
import sample.view.TabuleiroGrid;

public class CampoController {

    @FXML
    private TextField coluna;

    @FXML
    private TextField linha;

    @FXML
    private TextField numBomba;

    @FXML
    protected void btnActive(ActionEvent event){

        int col = Integer.parseInt(coluna.getText());
        int row = Integer.parseInt(linha.getText());
        int bomba = Integer.parseInt(numBomba.getText());

        gerarCampo(col, row, bomba);
    }

    private void gerarCampo(int coluna, int linha, int minas){
        Tabuleiro tabuleiro = new Tabuleiro(linha, coluna, minas);

        Parent campo = new TabuleiroGrid(tabuleiro);

        Stage stage = new Stage();
        stage.setScene(new Scene(campo));
        stage.setTitle("Campo Minado");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
}
