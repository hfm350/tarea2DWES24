package entidades;

import java.util.Objects;

public class Ejemplar {
    private long id;
    private String nombre;
    private String planta;

    
    public Ejemplar(long id, String nombre, String planta) {
        this.id = id;
        this.nombre = nombre;
        this.planta = planta;
    }

    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPlanta() {
        return planta;
    }

    public void setPlanta(String planta) {
        this.planta = planta;
    }
    


	@Override
	public String toString() {
		return "Ejemplar [id=" + id + ", nombre=" + nombre + ", planta=" + planta + "]";
	}
    
    
    
    
}
