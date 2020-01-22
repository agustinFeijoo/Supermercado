	package negocio;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.CarritoDao;
import dao.FacturaDao;
import modelo.Factura;
import modelo.Item;
import modelo.Usuario;

	public class FacturaNegocio {
		private FacturaDao dao;
		public FacturaNegocio(){
			try {
				this.dao =new FacturaDao();
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
		}
		
		/*
		public void mostrarFacturas(int idUsuario) throws Exception {
			
			List<Factura> facturas=dao.getFacturas(idUsuario);
				for(Factura i:facturas){
				System.out.println(i.toString());
			}
		}

			public Factura traerFactura(int idFactura) throws Exception{
				Factura c = dao.getFactura(idFactura);
				return c;
			}
		
		
			
			public void eliminar(int idFactura) {
				Factura c = dao.getFactura(idFactura);
				dao.eliminar(c);
			}
*/
			public List<Factura> getFacturasByUser(int idUsuario) throws SQLException {
				return dao.getFacturasByUser(idUsuario);
			}
			public Factura generarFactura(int idCarrito,Usuario usuario) throws SQLException {
				//Sumar el precio de todos los items
				CarritoDao carritoDao=new CarritoDao();
				LocalDate fecha=LocalDate.now();

				List<Item>items=new ArrayList<Item>();
				Factura f;
				float total=0;
				try {
					items=carritoDao.getItems(idCarrito);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for(Item i:items) {
					total=total+i.getCantidad()*i.getProducto().getPrecio();
				}
				f=anadir(idCarrito,usuario,total);
				return f;
			}
			
			public Factura anadir(int idCarrito,Usuario usuario,float total) throws SQLException{
				
				return dao.anadir(idCarrito,usuario,total);
			}


	}






