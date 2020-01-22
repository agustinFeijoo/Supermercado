/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carritoJDBC2;


import modelo.*;
import negocio.CarritoNegocio;
import negocio.FacturaNegocio;
import negocio.ProductoNegocio;
import negocio.UsuarioNegocio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 *
 * @author xappia
 */
public class TestConnection {
    public static void main(String[] args) {
		
		Scanner sc =new Scanner(System.in);
		int entradaInt=0,idProducto=0,cantidad=0;
		String entrada;
		ProductoNegocio pn=new ProductoNegocio();
		Carrito carrito = null;
		Usuario usuario = null;
		List<Item> items=new ArrayList<Item>();
		Producto producto=new Producto(1,"Salchichas",99999);
		Item item=new Item(1,99999,producto);
		FacturaNegocio facturaNegocio=new FacturaNegocio();
		items.add(item);
		//Esto debería ser una vez que al usuario se le haya asignado un carrito
		//usuario=new Usuario(1,"Agustin","Feijoo","1111");
		
		CarritoNegocio carritoNegocio=new CarritoNegocio();
		
		
		UsuarioNegocio usuarioNegocio=new UsuarioNegocio();
		do{
			try {
				usuario =usuarioNegocio.ingresar();			
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
			if(usuario==null)
				System.out.println("Nombre o pass incorrectos");
		}while(usuario==null);
		
		
		System.out.println("Buen día "+usuario.getNombre()+" "+usuario.getApellido());
		
		try {
			carrito=carritoNegocio.crearOUsar(usuario.getIdUsuario());
		} catch (Exception e1) {
			e1.printStackTrace();
		}//Si creas uno te lo deja seteaado para usar 
		
		//carrito=new Carrito(1,usuario,items);
		//Opcion agregar carrito
		
		
		
		do{
			System.out.println("Que desea hacer \n 1 para ver productos \n 2 para ver carrito \n 3 para añadir al carrito \n 4 para sacar producto\n 5 para mostrar facturas \n 6 para comprar  \n 0 para cancelar");
			entrada=sc.nextLine();
			try {
				entradaInt=Integer.parseInt(entrada);				
			}catch(NumberFormatException ne) { 
				System.out.println("Error,solo pasar numeros");
			}
			
			switch(entradaInt) {
			case 1:
				try {
					pn.mostrarProductos();	
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
				
			
			break;
			
			case 2:
				try {
					carritoNegocio.mostrarItems(carrito.getIdCarrito());
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
				
			break;
			
			case 3:
				try {
					pn.mostrarProductos();	
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
				System.out.println("Que producto desea comprar?(solo id)");
				entrada=sc.nextLine();
				try {
					idProducto=Integer.parseInt(entrada);			
				}catch(NumberFormatException ne){
					System.out.println("Error,solo pasar numeros");
				}
				System.out.println("Que cantidad?");
				entrada=sc.nextLine();
				try {
					cantidad=Integer.parseInt(entrada);			
				}catch(NumberFormatException ne){
					System.out.println("Error,solo pasar numeros");
				}
				//Producto productoAnadir=pn.getProducto(idProducto);
				//if(productoAnadir!=null)
				try {
					carritoNegocio.anadirItem(carrito.getIdCarrito(),idProducto,cantidad);					
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
				//faltaría añadir la validación que no este en el carrito
				
			break;
			case 4: 
			//	pn.mostrarProductos();
				System.out.println("Que producto desea sacar?");
				try {
					idProducto=Integer.parseInt(entrada);				
				}catch(NumberFormatException ne){
					System.out.println("Error,solo pasar numeros");
				}
				carritoNegocio.sacar(idProducto);
			break;
			case 5:
				List<Factura> facturas=null;
				
				try {
					facturas=facturaNegocio.getFacturasByUser(usuario.getIdUsuario());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for(Factura f:facturas) {
					System.out.println(f.toString());
				}
				break;
			case 6:
				Factura factura=null;
				try {
					 factura=facturaNegocio.generarFactura(carrito.getIdCarrito(),usuario);
					 //falta deleteCarrito(idCarrito);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("En total es "+factura.getTotal());
			}
		}while((entradaInt!=6) && (entradaInt!=0));
		sc.close();
	}

	
	
}