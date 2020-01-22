package dao;

import dataService.DataService;
import modelo.Carrito;
import modelo.Factura;
import modelo.Item;
import modelo.Usuario;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class UsuarioDaoTest {
    private UsuarioDao usuarioDao;
    private List<Item> itemsMock;
    private List<Factura> facturasMock;
    private Carrito carrito;
    private Usuario usuario;
    @Before
    public void setUp() {
        usuarioDao = new UsuarioDao();
        itemsMock= Mockito.mock(ArrayList.class);
        facturasMock=Mockito.mock(ArrayList.class);

        usuario = new Usuario(1,"Agustin","Feijoo","1111",facturasMock);


    }



    @After
    public void cleanDatabase(){
        DataService dataS = new DataService();
        dataS.deleteAllData();
    }

    @Test
    public void saveTest(){

        Usuario anotherUsuario=null;
        try{
            usuarioDao.save(usuario);
            anotherUsuario = usuarioDao.getUsuario(usuario.getIdUsuario());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        assertEquals(usuario.getNombre(), anotherUsuario.getNombre());
        assertEquals(usuario.getEmail(), anotherUsuario.getEmail());
        assertEquals(usuario.getContrasena(),anotherUsuario.getContrasena());
        assertEquals(usuario.getIdUsuario(),anotherUsuario.getIdUsuario());

    }
    @Test
    public void ingresar(){
        Usuario usuarioIngresado=null;
        try {
         usuarioIngresado=usuarioDao.ingresar(usuario.getNombre(),usuario.getContrasena());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals(usuario,usuarioIngresado);


    }
/*
    @Test
    public void updateTest(){

        carrito
        Dao.save(shoppingCart);

        ShoppingCart anotherShoppingCart = carritoDao.recover(shoppingCart.getShoppingCartID());

        assertEquals(shoppingCart.getShoppingCarUser().getUserName(), anotherShoppingCart.getShoppingCarUser().getUserName());
        assertEquals(shoppingCart.getShoppingCarUser().getUserMail(), anotherShoppingCart.getShoppingCarUser().getUserMail());
        assertEquals(shoppingCart.getShoppingCarUser().getUserPassword(), anotherShoppingCart.getShoppingCarUser().getUserPassword());

        shoppingCart.setShoppingCartPurchaseState(false);
        carritoDao.update(shoppingCart);

        anotherShoppingCart = carritoDao.recover(shoppingCart.getShoppingCartID());

        assertEquals(anotherShoppingCart.getShoppingCartPurchaseState(),shoppingCart.getShoppingCartPurchaseState());
    }
        */
}
