package entidades;

import java.util.Objects;

public class Persona {
    private int id;
    private String nombre;
    private String email;
    private Credenciales credenciales;

    public Persona(int id, String nombre, String email, Credenciales credenciales) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.credenciales = credenciales;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Credenciales getCredenciales() {
        return credenciales;
    }

    public void setCredenciales(Credenciales credenciales) {
        this.credenciales = credenciales;
    }

	@Override
	public int hashCode() {
		return Objects.hash(credenciales, email, id, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(credenciales, other.credenciales) && Objects.equals(email, other.email) && id == other.id
				&& Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", email=" + email + ", credenciales=" + credenciales + "]";
	}
    
    
}

