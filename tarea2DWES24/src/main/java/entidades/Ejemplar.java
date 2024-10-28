package entidades;

import java.util.Objects;

public class Ejemplar {
    private int id;
    private String nombre;
    private Planta planta;

    
    public Ejemplar(int id, String nombre, Planta planta) {
        this.id = id;
        this.nombre = nombre;
        this.planta = planta;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Planta getPlanta() {
        return planta;
    }

    public void setPlanta(Planta planta) {
        this.planta = planta;
    }
    
    


	@Override
	public int hashCode() {
		return Objects.hash(id, nombre, planta);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ejemplar other = (Ejemplar) obj;
		return id == other.id && Objects.equals(nombre, other.nombre) && Objects.equals(planta, other.planta);
	}


	@Override
	public String toString() {
		return "Ejemplar [id=" + id + ", nombre=" + nombre + ", planta=" + planta + "]";
	}
    
    
    
    
}
