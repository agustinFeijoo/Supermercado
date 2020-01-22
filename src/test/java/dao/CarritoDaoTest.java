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

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class CarritoDaoTest {
    private CarritoDao carritoDao;
    private List<Item> itemsMock;
    private List<Factura> facturasMock;
    private Carrito carrito;
    @Before

    public void setUp() {
        carritoDao = new CarritoDao();
        itemsMock= Mockito.mock(ArrayList.class);
        facturasMock=Mockito.mock(ArrayList.class);

        Usuario usuario = new Usuario(1,"Agustin","Feijoo","1111",facturasMock);

        carrito = new Carrito(1, usuario, itemsMock);

    }



    @After
    public void cleanDatabase(){
        DataService dataS = new DataService();
        dataS.deleteAllData();
    }

    @Test
    public void saveTest(){

         Carrito anotherShoppingCart=null;
        try{
            carritoDao.save(carrito);
            anotherShoppingCart = carritoDao.getCarrito(carrito.getIdCarrito());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        assertEquals(carrito.getUsuario().getNombre(), anotherShoppingCart.getUsuario().getNombre());
        assertEquals(carrito.getUsuario().getEmail(), anotherShoppingCart.getUsuario().getEmail());
        assertEquals(carrito.getUsuario().getContrasena(), carrito.getUsuario().getContrasena());

    }

/*
    @Test
    public void updateTest(){

        carritoDao.save(shoppingCart);

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
