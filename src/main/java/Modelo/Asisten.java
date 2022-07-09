package Modelo;

public record Asisten(String Clases_Fecha, int Clases_Horarios_ID, int Clases_Zonas_ID,
                      int suscriptorMatricula, int suscriptorClientesCI, int suscriptorSuscripcionesID) {

    public String[] getDatos(){
            return new String[]{Clases_Fecha, Clases_Horarios_ID + "", Clases_Zonas_ID + "", suscriptorMatricula + "",
            suscriptorClientesCI + "", suscriptorSuscripcionesID + ""};
        }
}
