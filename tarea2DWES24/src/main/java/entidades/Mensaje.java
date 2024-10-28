package entidades;

import java.time.LocalDateTime;
import java.util.Objects;

public class Mensaje {
    private int id;
    private LocalDateTime fechaHora;
    private String mensaje;
    private Ejemplar idEjemplar;

    
    public Mensaje(int id, LocalDateTime fechaHora, String mensaje, Ejemplar ejemplar) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.mensaje = mensaje;
        this.idEjemplar = ejemplar;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Ejemplar getEjemplar() {
        return idEjemplar;
    }

    public void setEjemplar(Ejemplar ejemplar) {
        this.idEjemplar = ejemplar;
    }


	@Override
	public int hashCode() {
		return Objects.hash(fechaHora, id, idEjemplar, mensaje);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mensaje other = (Mensaje) obj;
		return Objects.equals(fechaHora, other.fechaHora) && id == other.id
				&& Objects.equals(idEjemplar, other.idEjemplar) && Objects.equals(mensaje, other.mensaje);
	}


	@Override
	public String toString() {
		return "Mensaje [id=" + id + ", fechaHora=" + fechaHora + ", mensaje=" + mensaje + ", idEjemplar=" + idEjemplar
				+ "]";
	}
    
    
}

