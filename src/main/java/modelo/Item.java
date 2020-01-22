package modelo;

public class Item {
	private int id;
	private int cantidad;
	private Producto producto;
	
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Item(int id, int cantidad, Producto producto) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.producto = producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Item [id=" + id + ", cantidad=" + cantidad + ", producto=" + producto + "]";
	}
	
	
}
