package modelo;

import java.util.Date;

public class Factura {
	private int idFactura;
	private float total;
	private Usuario usuario;
	private Date fecha;
	private Carrito carrito;
	public Factura() {
		
	}
	public Factura(int idFactura,float total,Date fecha,Carrito carrito,Usuario usuario) {
		this.idFactura = idFactura;
		this.total = total;
		this.usuario = usuario;
		this.fecha=fecha;
		this.carrito=carrito;
	}
	
	public Factura(int idFactura, float total, Date fecha) {
		super();
		this.idFactura = idFactura;
		this.total = total;
		this.fecha = fecha;
	}
	public Factura(int idFactura, float total, Date fecha,Carrito carrito) {
		super();
		this.idFactura = idFactura;
		this.total = total;
		this.fecha = fecha;
		this.carrito=carrito;
	}
	public Factura(int idFactura, float total, Usuario usuario,Date fecha) {
		super();
		this.idFactura = idFactura;
		this.total = total;
		this.usuario = usuario;
		this.fecha=fecha;
	}
	
	public Carrito getCarrito() {
		return carrito;
	}
	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}
	public int getIdFactura() {
		return idFactura;
	}
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	@Override
	public String toString() {
		return "Factura [idFactura=" + idFactura + ", total=" + total + ", fecha=" + fecha
				+ ", carrito=" + carrito + "]";
	}

	
	
}
