package negocio;

import dao.CarritoDao;
import modelo.Carrito;
import modelo.Item;
import modelo.Producto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarritoNegocio {
	private CarritoDao dao;
	int entradaInt;

	public CarritoNegocio()  {
		if(dao==null)
			this.dao =new CarritoDao();
	}




	public Carrito crearOUsar(int idUsuario)throws Exception {
		Scanner sc=new Scanner(System.in);
		int entradaInt=0,idCarrito=0;
		String entrada;
		Carrito carrito=null;
		while(entradaInt!=1 && entradaInt!=2) {
			System.out.println("Desea:\n "
					+ "1:agregar un carrito o 2:utilizar uno ya existente?");
			entrada=sc.nextLine();
			try {
				entradaInt=Integer.parseInt(entrada);				
			}catch(NumberFormatException ne) {
				System.out.println("Error,solo pasar numeros");
			}
		}
		if(entradaInt==1) 
			carrito=anadirCarrito(idUsuario);
		else
			if(entradaInt==2) {
				while(carrito==null) {
					mostrarCarritos(idUsuario);
					System.out.println("Ingrese el id del carrito que va a utilizar o ingrese 0 para insertar");
					entrada=sc.nextLine();
					try {
						idCarrito=Integer.parseInt(entrada);				
					}catch(NumberFormatException ne) {
						System.out.println("Error,solo pasar numeros");
					}
					if(idCarrito==0)
						carrito=anadirCarrito(idUsuario);
					else
						carrito=dao.getCarritoByUser(idCarrito,idUsuario);//El usuario debería poder agarrar carritos que contengan su idUsuario
					
				}
			}
		return carrito;
	}
	public void mostrarCarritos(int idUsuario) {
		List<Carrito> carritos = null;
		try {
			carritos = dao.getAllCarritoUser(idUsuario);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Carrito c:carritos) {
			System.out.println(c.toString());
		}


	}
	

	public void mostrarItems(int idCarrito) throws Exception {

		List<Item> items=dao.getItems(idCarrito);
		for(Item i:items){
			System.out.println(i.toString());
		}
	}
	public boolean sacar(int idItem) {
		dao.sacar(idItem);
		return true;
	}

	public Carrito traerCarrito(int idCarrito) throws Exception{
		Carrito c = dao.getCarrito(idCarrito);
		return c;
	}

	public List<Producto> getProductos(){
		List<Producto> productos=new ArrayList<Producto>();
		return productos;
	}

	public Carrito  getCarrito(int id) throws Exception {
		Carrito c = dao.getCarrito(id);
		return c;
	}

	public void eliminar(int idCarrito) {
		Carrito c = null;
		try {
			c = dao.getCarrito(idCarrito);
		} catch (Exception e) {
			e.printStackTrace();
		}
		dao.eliminar(c);
	}

	public List<Carrito> traerCarrito() {
		return dao.traer();
	}
	public boolean anadirItem(int idCarrito,int idProducto,int cantidad) throws SQLException{
		return dao.anadirItem(idCarrito,idProducto,cantidad);
	}

	public Carrito anadirCarrito(int idUsuario) throws SQLException {
		return dao.agregarCarrito(idUsuario);
		
	}


}



