package Modelo;

public record Incidente(String fecha, int Empleados_CI,
                        int Suscriptor_Clientes_CI, int Suscriptor_Matricula,
                        int Suscriptor_Suscripciones_ID, String razon) {

    public String[] getDatos(){
        return new String[]{fecha, Empleados_CI + "", Suscriptor_Clientes_CI + "",
        Suscriptor_Matricula + "", Suscriptor_Suscripciones_ID + "", razon};
    }

}
