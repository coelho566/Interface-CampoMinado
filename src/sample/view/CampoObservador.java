package sample.view;

import sample.model.Campo;
import sample.model.CampoStatus;

public interface CampoObservador {

     void eventoOcorreu(Campo campo, CampoStatus status);
}
