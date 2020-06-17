package sample.service;

import java.util.Base64;

public class CriptografaDados {

    public CriptografaDados() {
    }

    public String criptografia(String dados){
        return new String(Base64.getEncoder().encode(dados.getBytes()));
    }

    public String descriptografa(String dados){
        return new String(Base64.getDecoder().decode(dados.getBytes()));
    }
}
