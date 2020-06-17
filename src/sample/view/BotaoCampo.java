package sample.view;

import javafx.scene.control.Button;
import sample.model.Campo;
import sample.model.CampoStatus;


public class BotaoCampo extends Button implements CampoObservador {

    private Campo campo;

    public  BotaoCampo(Campo campo){
        this.campo = campo;

        setMinHeight(27);
        setMinWidth(27);
        setStyle("-fx-border-color: rgba(184,184,184)");
        setStyle("-fx-background-color: rgb(171,171,171)");
        setStyle("-fx-border-radius: 0");


        this.setOnMousePressed(e -> {
            if (e.getButton().toString().equalsIgnoreCase("PRIMARY")){
                campo.abrir();
            }else {
                campo.alternarMarcacao();
            }
        });

        campo.registrarObservador(this);
    }

    @Override
    public void eventoOcorreu(Campo campo, CampoStatus status) {
        switch (status){
            case ABRIR:
                aplicarEstiloAbrir();
                break;
            case MARCAR:
                aplicarEsiloMarcar();
                break;
            case EXPLODIR:
                aplicarEstiloExplodir();
                break;
            default:
                aplicarEstiloPadrao();
        }
    }

    private void aplicarEstiloPadrao() {
        setStyle("-fx-background-color: rgb(171,171,171)");
        setStyle("-fx-border-radius: 0");
        setText("");
    }

    private void aplicarEstiloExplodir() {
        setStyle("-fx-background-color: rgba(189, 66, 68)");
        setTextFill(javafx.scene.paint.Paint.valueOf("white"));
        setText("X");
    }

    private void aplicarEsiloMarcar() {
        setStyle("-fx-background-color: rgba(8, 179, 247)");
        setText("M");
    }

    private void aplicarEstiloAbrir() {

        setStyle("-fx-background-color: rgba(184, 184, 184)");

        if(campo.isMinado()){
            setStyle("-fx-background-color: rgba(189, 66, 68)");
            return;
        }

        switch (campo.minasNaVizinhanca()){
            case 1:
                setTextFill(javafx.scene.paint.Paint.valueOf("green"));
                break;
            case 2:
                setTextFill(javafx.scene.paint.Paint.valueOf("blue"));
                break;
            case 3:
                setTextFill(javafx.scene.paint.Paint.valueOf("yellow"));
                break;
            case 4:
            case 5:
            case 6:
                setTextFill(javafx.scene.paint.Paint.valueOf("red"));
                break;
            default:
                setTextFill(javafx.scene.paint.Paint.valueOf("pink"));

        }

        String valor = !campo.vizinhancaSegura() ? campo.minasNaVizinhanca() + "" : "";
        this.setText(valor);

    }

}
