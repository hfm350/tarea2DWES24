package entidades;

import java.util.Objects;

public class Credenciales {
	private long id;
	private String usuario;
	private String password;
	private Long idPersona;

	public Credenciales(long id, String usuario, String password, Long idPersona) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.password = password;
		this.idPersona = idPersona;
	}

	public Credenciales(String usuario, String password) {
		super();
		this.usuario = usuario;
		this.password = password;

	}

	public Credenciales() {
		
	}

	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getPersona() {
		return idPersona;
	}

	public void setPersona(Long idPersona) {
		this.idPersona = idPersona;
	}

	@Override
	public String toString() {
		return "Credenciales [id=" + id + ", usuario=" + usuario + ", password=" + password + ", idPersona=" + idPersona
				+ "]";
	}

}
