package entidades;

import java.util.Objects;

public class Seguimiento {
    private Persona persona;
    private Ejemplar ejemplar;

    public Seguimiento(Persona persona, Ejemplar ejemplar) {
        this.persona = persona;
        this.ejemplar = ejemplar;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Ejemplar getEjemplar() {
        return ejemplar;
    }

    public void setEjemplar(Ejemplar ejemplar) {
        this.ejemplar = ejemplar;
    }

	@Override
	public int hashCode() {
		return Objects.hash(ejemplar, persona);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seguimiento other = (Seguimiento) obj;
		return Objects.equals(ejemplar, other.ejemplar) && Objects.equals(persona, other.persona);
	}

	@Override
	public String toString() {
		return "Seguimiento [persona=" + persona + ", ejemplar=" + ejemplar + "]";
	}
    
    
}

