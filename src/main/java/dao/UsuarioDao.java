package dao;

import modelo.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author www.luv2code.com
 *
 */
public class UsuarioDao {

	private Connection myConn;
	public void deleteAllData(){
		try {
			openConnection();
			Statement ps = myConn.createStatement();
			ps.executeUpdate("delete from usuario",Statement.RETURN_GENERATED_KEYS);
			PreparedStatement resetTable=myConn.prepareStatement("ALTER TABLE usuario AUTO_INCREMENT = 1");
			ResultSet resultSet = ps.getGeneratedKeys();
			resetTable.execute();

			ps.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

	}

	
	public UsuarioDao(){
		myConn=openConnection();
	}
	public Usuario getUsuario(int idUsuario) throws SQLException {
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Usuario tempUser=null;
		this.myConn=openConnection();
		try {
			myStmt=myConn.prepareStatement("select * from usuario where idusuario=?");
			myStmt.setInt(1, idUsuario);
			myRs=myStmt.executeQuery();
			if(myRs.next())
				tempUser=convertRowToUsuario(myRs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(myConn,myStmt,myRs);
		}
		return tempUser;
		
	}
	public Usuario crearCuenta() throws SQLException {
		Scanner sc = new Scanner(System.in);
		String apellido, email;
		Usuario u = null;
		int idUsuario;
		System.out.println("Escriba su nuevo nombre de usuario");
		String name = sc.nextLine();
		System.out.println("Escriba su nueva contrasena");
		String pass = sc.nextLine();
		System.out.println("Escriba su apellido");
		apellido = sc.nextLine();

		System.out.println("Escriba su email");
		email = sc.nextLine();
		u=save(name,apellido,pass,email);
		return u;
	}
	public Usuario save(Usuario u) throws SQLException {
		Usuario usuario=save(u.getNombre(),u.getApellido(),u.getContrasena(),u.getEmail());
		return usuario;
	}
	public Usuario save(String name,String apellido,String pass,String email) throws SQLException {
		myConn = openConnection();
		int idUsuario;
		ResultSet myRs = null;
		PreparedStatement myStmt = null;
		Usuario u=null;

		try {
			myStmt = myConn.prepareStatement("insert into Usuario(nombre,apellido,contrasena,email) values(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			myStmt.setString(1, name);
			myStmt.setString(2, apellido);
			myStmt.setString(3, pass);
			myStmt.setString(4, email);
			myStmt.execute();
			myRs=myStmt.getGeneratedKeys();

			if(myRs.next()){

				idUsuario=myRs.getInt(1);
				u=getUsuario(idUsuario);
			}else {
				System.out.println("Usuario no pudo ser creado");
			}

		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally{
			close(myConn,myStmt, myRs);
		}
		return u;


	}


	public Connection openConnection() {
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
	 * @throws SQLException 
	 */
	public Usuario ingresar(String name,String pass) throws SQLException {
		myConn=openConnection();
		//Connection myConn=DriverManager.getConnection("jdbc:mysql://localhost:3306/carrito?serverTimezone=UTC","root","root");
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Usuario tempUsuario=null;
		try {
			//name += "%";
			
			myStmt = myConn.prepareStatement("select * from Usuario where nombre like ? and contrasena like ?");
			myStmt.setString(1, name);
			myStmt.setString(2, pass);
			
			myRs = myStmt.executeQuery();
			
			if(myRs.next()) {
				 tempUsuario = convertRowToUsuario(myRs);
				
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			close(myConn,myStmt, myRs);
		}
		return tempUsuario;
	}
	private void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException("Error al cerrar la conexion", e);
		}
	}


	public List<Usuario> getAllUsuarios() throws Exception {
		List<Usuario> list = new ArrayList<Usuario>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from Usuario");
			
			while (myRs.next()) {
				Usuario tempUsuario = convertRowToUsuario(myRs);
				list.add(tempUsuario);
			}
			
			return list;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public List<Usuario> searchUsuarios(String lastName) throws Exception {
		List<Usuario> list = new ArrayList<Usuario>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			lastName += "%";
			myStmt = myConn.prepareStatement("select * from Usuario where last_name like ?");
			
			myStmt.setString(1, lastName);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Usuario tempUsuario = convertRowToUsuario(myRs);
				list.add(tempUsuario);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public Usuario convertRowToUsuario(ResultSet myRs) throws SQLException {
		
		int idUsuario = myRs.getInt("idusuario");
		//No funciona y no encontre el motivo
		String nombre= myRs.getString("nombre");
		String apellido=myRs.getString("apellido");
		String constrasena=myRs.getString("contrasena");
		String email=myRs.getString("email");
		Usuario tempUsuario = new Usuario(idUsuario,nombre,apellido,constrasena,email);
		
		return tempUsuario;
	}

	
	public static void close(Connection myConn, Statement myStmt, ResultSet myRs)
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

}
