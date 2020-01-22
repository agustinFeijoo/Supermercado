package modelo;

import java.util.List;

public class Usuario {
	private String nombre;
	private String apellido;
	private String contrasena;
	private int idUsuario;
	private List<Factura> facturas;
	private String email;
	
	
	
	public Usuario(int idUsuario,String nombre, String apellido, String contrasena, List<Factura> facturas,
			String email) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.contrasena = contrasena;
		this.idUsuario = idUsuario;
		this.facturas = facturas;
		this.email = email;
	}

	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Usuario(int idUsuario,String nombre, String apellido, String contrasena, List<Factura> facturas) {
			super();
		this.idUsuario=idUsuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.contrasena = contrasena;
		this.facturas = facturas;
	}
	public Usuario(int idUsuario,String nombre, String apellido, String contrasena, String email) {
		super();
		this.idUsuario=idUsuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.contrasena = contrasena;
		this.email=email;
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	

	public String getemail() {
		return email;
	}

	public void setemail(String email) {
		this.email = email;
	}

	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", apellido=" + apellido + ", contrasena=" + contrasena + ", idUsuario="
				+ idUsuario + ", facturas=" + facturas + "]";
	}
	
	
	
}
