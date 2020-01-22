package dao;

import modelo.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author www.luv2code.com
 *
 */
public class ProductoDao {

	private Connection myConn;

	
	public ProductoDao() {
		myConn=openConnection();
	}

	public void deleteAllData(){
	    try {
            openConnection();
            Statement ps = myConn.createStatement();
            ps.executeUpdate("delete from producto",Statement.RETURN_GENERATED_KEYS);
			PreparedStatement resetTable=myConn.prepareStatement("ALTER TABLE carrito AUTO_INCREMENT = 1;");
            ResultSet resultSet = ps.getGeneratedKeys();
			resetTable.execute();

            ps.close();
        }catch(Exception e) {
        	System.out.println(e.getMessage());
        }

	}

	public void save(Producto aProduct)throws Exception {
			openConnection();
			PreparedStatement ps = myConn.prepareStatement("INSERT INTO product (nombre, precio) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, aProduct.getNombre());
			ps.setFloat(2, aProduct.getPrecio());
			ps.execute();

			ResultSet resultSet = ps.getGeneratedKeys();
			if (resultSet.next()){
				//aProduct.setIdProducto(resultSet.getInt(1));//creo que es inecesario
			}
			ps.close();
		}

	private Connection openConnection() {
		try {
			//La url de conexion no deberia estar harcodeada aca
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/carrito?serverTimezone=UTC","root","root");
		} catch (SQLException e) {
			throw new RuntimeException("No se puede establecer una conexion", e);
		}
	}

	/**
	 * Cierra una conexion con la base de datos (libera los recursos utilizados por la misma)
	 * @param connection - la conexion a cerrar.
	 */
	private void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException("Error al cerrar la conexion", e);
		}
	}
	public Producto getProducto(int idProducto) {
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Producto tempProducto=null;
		

		try {
			
			myStmt = myConn.prepareStatement("select * from producto where idproducto=?");
			
			myStmt.setInt(1, idProducto);
			
			myRs = myStmt.executeQuery();
			
			if (myRs.next()) {
				 tempProducto = convertRowToProducto(myRs);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
			
			return tempProducto;
	}
	

	public List<Producto> getAllProductos() throws Exception {
		List<Producto> list = new ArrayList<Producto>();
		myConn=openConnection();
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from producto");
			
			while (myRs.next()) {
				Producto tempProducto = convertRowToProducto(myRs);
				list.add(tempProducto);
			}
			
			return list;		
		}
		finally {
			close(myConn,myStmt, myRs);
		}
	}
	
	public List<Producto> searchProductos(String lastName) throws Exception {
		List<Producto> list = new ArrayList<Producto>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			lastName += "%";
			myStmt = myConn.prepareStatement("select * from producto where last_name like ?");
			
			myStmt.setString(1, lastName);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Producto tempProducto = convertRowToProducto(myRs);
				list.add(tempProducto);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	private Producto convertRowToProducto(ResultSet myRs) throws SQLException {
		
		int id = myRs.getInt("idproducto");
		String nombre= myRs.getString("nombre");
		float precio=myRs.getFloat("precio");
		
		Producto tempProducto = new Producto(id,nombre,precio);
		
		return tempProducto;
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

	public static void main(String[] args) throws Exception {
		
		ProductoDao Dao = new ProductoDao();
		System.out.println(Dao.searchProductos("thom"));

		System.out.println(Dao.getAllProductos());
	}
}
