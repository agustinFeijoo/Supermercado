package dao;

import modelo.Carrito;
import modelo.Item;
import modelo.Producto;
import modelo.Usuario;
import negocio.UsuarioNegocio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author www.luv2code.com
 *
 */
public class CarritoDao {

	private Connection myConn;
	public void deleteAllData(){
		try {
			openConnection();
			Statement ps = myConn.createStatement();
			PreparedStatement resetTable=myConn.prepareStatement("ALTER TABLE carrito AUTO_INCREMENT = 1;");
			Statement psItem = myConn.createStatement();

			psItem.executeUpdate("delete from item",Statement.RETURN_GENERATED_KEYS);

			ps.executeUpdate("delete from carrito",Statement.RETURN_GENERATED_KEYS);
			resetTable.execute();
			//ResultSet resultSet = ps.getGeneratedKeys();

			ps.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public CarritoDao() {
		myConn=openConnection();
	}
	public List<Carrito> getAllCarritoUser(int idUsuario) throws SQLException{
		List<Carrito> list= new ArrayList<Carrito>();
		myConn=openConnection();
		
		ResultSet myRs = null;
		PreparedStatement myStmt = null;
		
		try {
			myStmt = myConn.prepareStatement("select * from carrito where idusuario= ?");
			myStmt.setInt(1, idUsuario);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Carrito tempUsuario= convertRowToCarrito(myRs);
				list.add(tempUsuario);
			}
		
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			close(myConn,myStmt, myRs);
		}
		return list;
		
	}
	public Carrito getCarritoByUser(int idCarrito,int idUsuario) {
		List<Carrito> carritosUsuario=null;
		try {
			carritosUsuario = getAllCarritoUser(idUsuario);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Carrito carrito=null;
		for(Carrito c:carritosUsuario) {
			if(c.getIdCarrito()==idCarrito)
				carrito=c;
		}
		return carrito;	
	}
	public Carrito agregarCarrito(int idUsuario) throws SQLException {
		myConn=openConnection();
		Carrito c=null;
		ResultSet myRs = null;
		PreparedStatement myStmt = null;
		int idCarrito;
		try {
			myStmt = myConn.prepareStatement("insert into carrito(idusuario) values(?)",Statement.RETURN_GENERATED_KEYS);
			myStmt.setInt(1, idUsuario);
			myStmt.execute();
			myRs=myStmt.getGeneratedKeys();
			
			  if(myRs.next()){
				  idCarrito=myRs.getInt(1);
				  c=getCarrito(idCarrito);
			  }
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally{
			close(myConn,myStmt, myRs);
		}
		return c;
	}

	public Carrito save(Carrito c) throws SQLException {
		myConn=openConnection();

		ResultSet myRs = null;
		PreparedStatement myStmt = null;
		int idCarrito;
		try {
			myStmt = myConn.prepareStatement("insert into carrito(idusuario) values(?)",Statement.RETURN_GENERATED_KEYS);
			myStmt.setInt(1, c.getUsuario().getIdUsuario());
			myStmt.execute();
			myRs=myStmt.getGeneratedKeys();

			if(myRs.next()){
				idCarrito=myRs.getInt(1);
				c=getCarrito(idCarrito);
			}

		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally{
			close(myConn,myStmt, myRs);
		}
		return c;
	}


	public boolean sacar(int idItem) {
		return true;
	}
	public List<Item> getItems(int idCarrito) throws Exception {
		List<Item> list = new ArrayList<Item>();
		myConn=openConnection();
		
		ResultSet myRs = null;
		PreparedStatement myStmt = null;
		
		try {
			myStmt = myConn.prepareStatement("select * from item where idcarrito=?");
			myStmt.setInt(1, idCarrito);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Item tempItem = convertRowToItem(myRs);
				list.add(tempItem);
			}
			
			
		
		}
		finally {
			CarritoDao.close(myConn,myStmt, myRs);
		}
		return list;
	}
	public boolean anadirItem(int idCarrito,int idProducto,int cantidad) throws SQLException {
		Carrito c=null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int iExectuteUpdate;
		boolean insertado=false;
		PreparedStatement carritoAlredyHasProduct;
		myConn=openConnection();
		try {
			//acá iría la validación de que el carrito no tenga otro producto igual anteriormente
			myStmt= myConn.prepareStatement("select * from producto where idproducto=?");
			myStmt.setInt(1, idProducto);
			myRs = myStmt.executeQuery();
			
			if(myRs.next()) {//espero que de false si no existe
		
				carritoAlredyHasProduct= myConn.prepareStatement("select * from item where idcarrito=? and idproducto=?");
				carritoAlredyHasProduct.setInt(1, idCarrito);
				carritoAlredyHasProduct.setInt(2, idProducto);
				myRs = myStmt.executeQuery();
				if(myRs.next()) { //el Usuario ya había agregado ese producto
					myStmt= myConn.prepareStatement("insert into item values(?,?,?)");
					myStmt.setInt(1, idCarrito);
					myStmt.setInt(2, idProducto);
					myStmt.setInt(3, cantidad);
					iExectuteUpdate = myStmt.executeUpdate();
					insertado=true;
					}else{
						System.out.println("Ya ingresaste ese producto!!");
					}
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {

			close(myConn,myStmt, myRs);
		}
		/*
			myStmt = myConn.prepareStatement("insert into item values ");//??,Statement.RETURN_GENERATED_KEYS()
			
			 
			  Resultset resultSet=myStmt.getGeneratedKeys();
			  if(resultSet.next()) {
				  obj.setBillID(resultSet.getInt(1));
			  }
			  */
		
		return insertado;
	}
	

	
	public List<Carrito> getAllCarrito() throws Exception {
		List<Carrito> list = new ArrayList<Carrito>();
		myConn=openConnection();
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from Carrito");
			
			while (myRs.next()) {
				Carrito tempCarrito = convertRowToCarrito(myRs);
				list.add(tempCarrito);
			}

			return list;		
		}
		finally {
			CarritoDao.close(myConn,myStmt, myRs);
		}
	}
	
	public List<Carrito> searchCarrito(String lastName) throws Exception {
		List<Carrito> list = new ArrayList<Carrito>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			lastName += "%";
			myStmt = myConn.prepareStatement("select * from carrito where last_name like ?");
			
			myStmt.setString(1, lastName);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Carrito tempCarrito = convertRowToCarrito(myRs);
				list.add(tempCarrito);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public Carrito getCarrito(int idCarrito) throws Exception {
		Carrito c= new Carrito();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			
			myStmt = myConn.prepareStatement("select * from Carrito where idCarrito=?");
			
			myStmt.setInt(1, idCarrito);
			
			myRs = myStmt.executeQuery();
			
			if(myRs.next()) {
			
				c = convertRowToCarrito(myRs);
			}
			while (myRs.next()) {
				
			}
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		finally {
			close(myConn,myStmt, myRs);
		}
		return c;
	}
	public List<Carrito> getCarritosByUser(int idUsuario) throws Exception {
		Carrito c= new Carrito();
		List<Carrito> carritos=new ArrayList<Carrito>();
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			
			myStmt = myConn.prepareStatement("select * from Carrito where idUsuario=?");
			
			myStmt.setInt(1, idUsuario);
			
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				c = convertRowToCarrito(myRs);
				carritos.add(c);
			}
			
		}
		finally {
			close(myConn,myStmt, myRs);
		}
		return carritos;
	}
	
	private Carrito convertRowToCarrito(ResultSet myRs) throws SQLException {
		
		int idCarrito = myRs.getInt("idcarrito");
		int idUsuario=myRs.getInt("idusuario");
		List<Item> items=null;
		try {
			items = getItems(idCarrito);
		} catch (Exception e) {
			e.printStackTrace();
		}
		UsuarioNegocio usuarioNegocio=new UsuarioNegocio(); 
		Usuario usuario=usuarioNegocio.getUsuario(idUsuario);
		
		Carrito tempCarrito = new Carrito(idCarrito,usuario,items);
		
		return tempCarrito;
	}
	private Item convertRowToItem(ResultSet myRs) throws SQLException {
		Producto p=new Producto();
		int idCarrito = myRs.getInt("idcarrito");
		int cantidad= myRs.getInt("cantidad");
		int idProducto= myRs.getInt("idproducto");
		Producto tempProducto=new Producto();
		ResultSet myRsProducto = null;
		PreparedStatement myStmt = null;
		
		try {
			myStmt = myConn.prepareStatement("select * from producto where idproducto= ?");
			myStmt.setInt(1, idProducto);
			
			myRsProducto = myStmt.executeQuery();
			
			if(myRsProducto.next()) {
				 tempProducto = convertRowToProducto(myRsProducto);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
			
			
			
			
		Item tempItem= new Item(idCarrito,cantidad,tempProducto);
		
		return tempItem;
	}

	
	private Producto convertRowToProducto(ResultSet myRs) throws SQLException {
	// TODO Auto-generated method stub
		
		int idProducto=myRs.getInt("idproducto");
		String nombre=myRs.getString("nombre");
		float precio=myRs.getFloat("precio");
		
		Producto producto=new Producto(idProducto,nombre,precio);
		
		
	return producto;
}




	private static void close(Connection myConn, Statement myStmt, ResultSet myRs)
			throws SQLException {

		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
			
		}
		
		if (myConn != null) {
			myConn.close();
		}
	}

	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);		
	}
	private Connection openConnection() {
		try {
			//La url de conexion no deberia estar harcodeada aca
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/carrito?serverTimezone=UTC","root","root");
		} catch (SQLException e) {
			throw new RuntimeException("No se puede establecer una conexion", e);
		}
	}

	public void eliminar(Carrito c) {
	}

	public List<Carrito> traer() {
		return null;
	}
}