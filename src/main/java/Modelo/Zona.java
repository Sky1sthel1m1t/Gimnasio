package Modelo;

public record Zona(int ID, String Descripcion, String Nombre) {

    public String[] getDatos(){
        return new String[]{ID + "", Descripcion, Nombre};
    }

    @Override
    public String toString() {
        return Nombre + " - " + Descripcion;
    }
}
