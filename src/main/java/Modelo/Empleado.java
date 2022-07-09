package Modelo;

public record Empleado(int CI, String nombre, String apellidos, String dtContratacion,
                       double sueldo, String HoraEntrada, String HoraSalida,
                       String claseEmpleado, String tipo) {

    public String[] getDatos(){
        return new String[]{CI + "", nombre, apellidos, dtContratacion, sueldo + "",
                HoraEntrada, HoraSalida,claseEmpleado, tipo};
    }

    @Override
    public String toString() {
        return nombre + " " + apellidos + " - " + claseEmpleado;
    }
}
