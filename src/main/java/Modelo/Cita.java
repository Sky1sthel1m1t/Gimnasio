package Modelo;

public record Cita(String fecha, int Suscriptor_Matricula, int Suscriptor_Clientes_CI,
                   int Suscriptor_Suscripciones_ID, int Empleados_CI, String hora,
                   double peso, String observaciones) {

    public String[] getDatos(){
        return new String[]{fecha, Suscriptor_Matricula + "", Suscriptor_Clientes_CI+ "",
                Suscriptor_Suscripciones_ID + "", Empleados_CI + "", hora, peso + "",
                observaciones};
    }

}
