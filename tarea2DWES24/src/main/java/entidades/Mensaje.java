package entidades;

import java.time.LocalDateTime;

public class Mensaje {

	private long id;
	private LocalDateTime fechahora;
	private String mensaje;
	private long idEjemplar;
	private long idPersona;
	
	public Mensaje() {
		
	}

	public Mensaje(long id, LocalDateTime fechahora, String mensaje, long idEjemplar, long idPersona) {
		super();
		this.id = id;
		this.fechahora = fechahora;
		this.mensaje = mensaje;
		this.idEjemplar = idEjemplar;
		this.idPersona = idPersona;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getFechahora() {
		return fechahora;
	}

	public void setFechahora(LocalDateTime fechahora) {
		this.fechahora = fechahora;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	

	public long getIdEjemplar() {
		return idEjemplar;
	}

	public void setIdEjemplar(long idEjemplar) {
		this.idEjemplar = idEjemplar;
	}

	public long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(long idPersona) {
		this.idPersona = idPersona;
	}

	@Override
	public String toString() {
		return "Mensaje [id=" + id + ", fechahora=" + fechahora + ", mensaje=" + mensaje + ", idEjemplar=" + idEjemplar
				+ ", idPersona=" + idPersona + "]";
	}

	
	
	
}