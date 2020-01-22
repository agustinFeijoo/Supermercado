package dao;

import modelo.Carrito;
import modelo.Factura;
import modelo.Usuario;
import negocio.CarritoNegocio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author www.luv2code.com
 *
 */
public class FacturaDao {

	private Connection myConn;

	
	public FacturaDao(){
		myConn=openConnection();
	}
	public void deleteAllData(){
		try {
			openConnection();
			Statement ps = myConn.createStatement();
			ps.executeUpdate("delete from Factura",Statement.RETURN_GENERATED_KEYS);
			PreparedStatement resetTable=myConn.prepareStatement("ALTER TABLE carrito AUTO_INCREMENT = 1;");
			resetTable.execute();
			ResultSet resultSet = ps.getGeneratedKeys();
			if (resultSet.next()) {
				//aProduct.setIdProducto(resultSet.getInt(1));//creo que es inecesario
			}
			ps.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private Connection openConnection() {
		try {
			//La url de conexion no deberia estar harcodeada aca
			//Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/carrito?serverTimezone=UTC","root","root");
			//return DriverManager.getConnection("jdbc:mysql://localhost:3306/carrito?user=root&password=root");
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


	public List<Factura> getAllFacturas() throws Exception {
		List<Factura> list = new ArrayList<Factura>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from Factura");
			
			while (myRs.next()) {
				Factura tempFactura = convertRowToFactura(myRs);
				list.add(tempFactura);
			}
			
			return list;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	public List<Factura> getFacturasByUser(int idUsuario) throws SQLException{
		CallableStatement myStmt=null;
		ResultSet myRs = null;
		List<Factura> facturasUsuario=new ArrayList<Factura>();
		try {
			myStmt=myConn.prepareCall("{call getFacturasByClient(?)}");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		myStmt.setInt(1, idUsuario);
		myStmt.execute();
		myRs=myStmt.getResultSet();
		while(myRs.next()) {
			facturasUsuario.add(convertRowToFactura(myRs));
			
		}
		return facturasUsuario;
	}
	public List<Factura> searchFacturas(String lastName) throws Exception {
		List<Factura> list = new ArrayList<Factura>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			lastName += "%";
			myStmt = myConn.prepareStatement("select * from Factura where last_name like ?");
			
			myStmt.setString(1, lastName);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Factura tempFactura = convertRowToFactura(myRs);
				list.add(tempFactura);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	/*
	private List<Factura> convertRowToFactura(ResultSet myRs) throws SQLException{
		
	}
	*/
	private Factura convertRowToFactura(ResultSet myRs) throws SQLException {
		CarritoNegocio carritoNegocio=new CarritoNegocio();
		int id = myRs.getInt("idFactura");
		float total=myRs.getFloat("total");
		Date fecha=myRs.getDate("fecha");
		int idCarrito=myRs.getInt("idcarrito");
		int idUsuario=myRs.getInt("idusuario");
		Carrito c=null;
		try {
			c=	carritoNegocio.getCarrito(idCarrito);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Factura tempFactura = new Factura(id,total,fecha,c);
		
		return tempFactura;
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

	public Factura anadir(int idCarrito, Usuario usuario, float total) throws SQLException {
		// TODO Auto-generated method stub
		myConn=openConnection();
		Factura f=null;
		ResultSet myRs = null;
		PreparedStatement myStmt = null;
		int idFactura;
		try {
			myStmt = myConn.prepareStatement("insert into factura(total,fecha,idcarrito,idusuario) values(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			myStmt.setFloat(1, total);
			myStmt.setDate(2, Date.valueOf(java.time.LocalDate.now()));
			myStmt.setFloat(3, idCarrito);
			myStmt.setFloat(4, usuario.getIdUsuario());
			myStmt.execute();
			myRs=myStmt.getGeneratedKeys();
			
			  if(myRs.next()){
				  
				  idFactura=myRs.getInt(1);
				  f=getFactura(idFactura);
			  }
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally{
			close(myConn,myStmt, myRs);
		}
		return f;
		
	}

	private Factura getFactura(int idFactura) throws SQLException {
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Factura tempFactura=null;
		try {
			
			myStmt = myConn.prepareStatement("select * from Factura where idfactura=?");
			
			myStmt.setInt(1, idFactura);
			
			myRs = myStmt.executeQuery();
			
			if(myRs.next()) {
				tempFactura = convertRowToFactura(myRs);
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			close(myConn,myStmt, myRs);
		}
		return tempFactura;
	}
	


}
