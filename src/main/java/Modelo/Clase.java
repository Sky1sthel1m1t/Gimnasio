package Modelo;

public record Clase(String Fecha, int Zonas_ID, int Horarios_ID) {

    public String[] getDatos(){
        return new String[]{Fecha, Zonas_ID + "", Horarios_ID + ""};
    }

}
