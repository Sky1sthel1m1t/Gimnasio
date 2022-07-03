package Modelo;

public record Zona(int ID, String Descripcion, String Nombre) {
    @Override
    public String toString() {
        return Nombre + " - " + Descripcion;
    }
}
