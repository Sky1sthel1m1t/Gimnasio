package Modelo;

public record ProporcionaAcceso(int suscripciones_id, int zonas_id) {

    public String[] getDatos(){
        return new String[]{suscripciones_id + "", zonas_id + ""};
    }

}
