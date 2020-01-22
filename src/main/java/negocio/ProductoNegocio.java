package negocio;

import java.util.List;

import dao.ProductoDao;
import modelo.Producto;

public class ProductoNegocio {
	private ProductoDao dao;
	
	
	public ProductoNegocio() {
		try {
		this.dao =new ProductoDao();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public Producto getProducto(int idProducto) {
		Producto p=dao.getProducto(idProducto);
		return p;
	}
	public void mostrarProductos() throws Exception {
		
		List<Producto>productos=dao.getAllProductos();
			for(Producto p:productos){
			System.out.println(p.toString());
		}
	}
	/*
	public Producto getProducto(int idProducto) {
		return dao.getProducto(idProducto);
	}
	*/
}
