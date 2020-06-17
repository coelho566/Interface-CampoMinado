package sample.view;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import sample.model.Tabuleiro;

public class TabuleiroGrid extends GridPane {

    public TabuleiroGrid(Tabuleiro tabuleiro){

        int coluna = tabuleiro.getColunas();
        int linha = tabuleiro.getLinhas();

        setAlignment(Pos.CENTER);

        for (int i = 0; i < coluna; i++){
            getColumnConstraints().add(cc());
        }

        for (int i = 0; i < linha; i++){
            getRowConstraints().add(rc());
        }

        tabuleiro.paraCada(c -> add(new BotaoCampo(c),
                c.getColuna(), c.getLinha()));

        tabuleiro.registrarObservador(e ->{
            if(e.isGanhou()){
                Alert ganhou = new Alert(Alert.AlertType.INFORMATION);
                ganhou.setTitle("Ganhou");
                ganhou.setHeaderText("Parabéns! Você é demais");
                ganhou.setContentText("Jogar novamente");
                ganhou.show();
            }else{
                Alert perdeu = new Alert(Alert.AlertType.WARNING);
                perdeu.setTitle("Perdeu");
                perdeu.setHeaderText("Você não é derrota quando perde. Você é derrotado quando desiste.");
                perdeu.setContentText("Tente outra vez");
                perdeu.show();
            }
        });
    }

    private ColumnConstraints cc(){
        ColumnConstraints cc = new ColumnConstraints();
        cc.setMinWidth(27);
        cc.setFillWidth(true);

        return cc;
    }

    private RowConstraints rc(){
        RowConstraints rc = new RowConstraints();
        rc.setMinHeight(27);
        rc.setFillHeight(true);

        return rc;
    }
}
