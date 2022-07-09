package Modelo;

public record Suscriptor(int Matricula, int Clientes_CI, int Suscripciones_id,
                         int Empleados_CI, String dtPrimeraInscripcion,
                         String dtInicioSuscripcion, String dtFinalSuscripcion) {

    public Suscriptor(int clientes_CI, int suscripciones_id, int empleados_CI) {
        this(0, clientes_CI, suscripciones_id, empleados_CI, null, null, null);
    }

    public String[] getDatos(){
        return new String[]{Matricula + "", Clientes_CI + "", Suscripciones_id + "", Empleados_CI + "",
                dtPrimeraInscripcion, dtInicioSuscripcion, dtFinalSuscripcion};
    }
}
