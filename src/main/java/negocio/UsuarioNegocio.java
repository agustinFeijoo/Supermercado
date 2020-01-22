	package negocio;

	import dao.UsuarioDao;
import modelo.Usuario;

import java.sql.SQLException;
import java.util.Scanner;

	public class UsuarioNegocio {
		private UsuarioDao dao;
		public UsuarioNegocio(){
			try {
				this.dao =new UsuarioDao();
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
		}
		public boolean crearCuenta() {
			boolean ingresado=false;
			return ingresado;
		}
		public Usuario ingresar(){
			String name,pass = null;
			Scanner sc =new Scanner(System.in);
			
			Usuario u = null;
			
			
		
			
			System.out.println("Escriba su nombre de usuario o Si desea crear una nueva cuenta ingresar 0");
			name=sc.nextLine();
			if(name.equals("0")) {
				try {
					u=dao.crearCuenta();
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}else {
			System.out.println("Escriba su contrasena");
			pass=sc.nextLine();
			}
			if(u==null) {
				try {
					u =dao.ingresar(name,pass);
					}catch(Exception e) {
						System.out.println(e.getMessage());
					}
	
			}
			
			
			return u;
		}
		/*
		public void mostrarUsuarios(int idUsuario) throws Exception {
			
			List<Usuario> usuarios=dao.getUsuarios();
				for(Usuario i:Usuarios){
				System.out.println(i.toString());
			}
		}
		*/
			
			public Usuario traerUsuario(int idUsuario) throws Exception{
				Usuario c = dao.getUsuario(idUsuario);
				return c;
			}
			/*
			public List<Producto> getProductos(){
				List<Producto> productos;

			}
			*/
			public Usuario getUsuario(int id) {
				Usuario c = null;
				try {
					c = dao.getUsuario(id);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return c;
			}
			/*
			public void eliminar(int idUsuario) {
				Usuario c = dao.getUsuario(idUsuario);
				dao.eliminar(c);
			}
			*/
			/*
			public List<Usuario> traerUsuario() {
				return dao.traer();
			}

			public boolean anadir(int idUsuario,int idProducto,int cantidad) throws SQLException{
				
				return dao.anadir(idUsuario,idProducto,cantidad);
			}
	*/

	}






