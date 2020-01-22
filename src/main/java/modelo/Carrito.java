package modelo;

import java.util.List;

public class Carrito{
	private Usuario usuario;
	private List<Item> items;
	private int idCarrito;
	
	
	public Carrito() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Carrito(int idCarrito,Usuario usuario, List<Item> items) {
		super();
		this.usuario = usuario;
		this.items = items;
		this.idCarrito = idCarrito;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public int getIdCarrito() {
		return idCarrito;
	}
	public void setIdCarrito(int id) {
		this.idCarrito = id;
	}
	@Override
	public String toString() {
		return "Carrito [usuario=" + usuario + ", items=" + items + ", idCarrito=" + idCarrito + "]";
	}
	
	
	
	
	
	
	
}
