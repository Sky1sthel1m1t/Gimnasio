package Modelo;

public record Suscripcion(int ID, Double Precio, String Nombre) {

    public String[] getDatos(){
        return new String[]{ID + "", Precio + "", Nombre};
    }

    @Override
    public String toString() {
        return Nombre + " - " + Precio;
    }
}
