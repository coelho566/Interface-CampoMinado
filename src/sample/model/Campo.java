package sample.model;

import sample.view.CampoObservador;

import java.util.ArrayList;
import java.util.List;

public class Campo {

    private int coluna;
    private int linha;

    private boolean minado = false;
    private boolean aberto = false;
    private boolean marcado = false;

    private List<Campo> vizinhos = new ArrayList<Campo>();
    private List<CampoObservador> observadores = new ArrayList<>();

    public Campo(int linha, int coluna){
        this.linha = linha;
        this.coluna = coluna;
    }

    public void registrarObservador(CampoObservador observador){
        observadores.add(observador);
    }

    public void notificarObservadores(CampoStatus status){
        observadores
                .forEach(o -> o.eventoOcorreu(this, status));
    }

    public boolean adicionarVizinho(Campo vizinho) {
        boolean linhaDiferente = linha != vizinho.linha;
        boolean colunaDiferente = coluna != vizinho.coluna;
        boolean diagonal = linhaDiferente && colunaDiferente;

        int deltaLinha = Math.abs(linha - vizinho.linha);
        int deltaColuna = Math.abs(coluna - vizinho.coluna);
        int deltaGeral = deltaColuna + deltaLinha;

        if(deltaGeral == 1 && !diagonal) {
            vizinhos.add(vizinho);
            return true;
        }else if(deltaGeral == 2 && diagonal) {
            vizinhos.add(vizinho);
            return true;
        }else {
            return false;
        }
    }

    public void alternarMarcacao(){
        if(!aberto){
            marcado = !marcado;

            if(marcado){
                this.notificarObservadores(CampoStatus.MARCAR);
            }else{
                this.notificarObservadores(CampoStatus.DESMARCAR);
            }
        }
    }
    public boolean abrir(){
        if(!aberto && !marcado){
            if(minado){
                this.notificarObservadores(CampoStatus.EXPLODIR);
                return true;
            }
            this.setAberto(true);

            if (vizinhancaSegura()){
                vizinhos.forEach(v -> v.abrir());
            }
            return  true;
        }else {
            return false;
        }
    }

    public  boolean vizinhancaSegura(){
        return vizinhos.stream().noneMatch(v -> v.minado);
    }

    public void minar(){
        minado = true;
    }

    public boolean isMinado(){
        return minado;
    }

    public boolean isMarcado(){
        return marcado;
    }

    public void setAberto(boolean aberto) {
        this.aberto = aberto;
        if(aberto){
            this.notificarObservadores(CampoStatus.ABRIR);
        }
    }

    public boolean isAberto(){
        return aberto;
    }

    public boolean isFechado(){
        return !isAberto();
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    //Objetivo do jogo
    public boolean objetivoAlcancado(){
        boolean desvendado = !minado && aberto;
        boolean protegido = minado && marcado;
        return desvendado || protegido;
    }

    //Conta as minas dos campos ao redor
    public int minasNaVizinhanca(){
        return (int) vizinhos.stream().filter(v -> v.minado).count();
    }

    //Reinicia o jogo
    public void reiniciar(){
        aberto = false;
        minado = false;
        marcado = false;
        notificarObservadores(CampoStatus.REINICIAR);
    }


}
