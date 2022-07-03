package Modelo;

public record Horario(Integer ID, String Dia, String HoraInicio, String HoraFinal, int Empleados_CI) {

    public String[] getDatos(){
        return new String[]{ID + "",Dia,HoraInicio,HoraFinal,Empleados_CI + ""};
    }

}
