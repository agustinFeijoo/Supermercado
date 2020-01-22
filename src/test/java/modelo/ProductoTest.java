package modelo;

import static org.junit.Assert.assertEquals;
import modelo.Producto;
import org.junit.Before;
import org.junit.Test;

public class ProductoTest {

    private Producto producto;

    @Before
    public void setUp(){
        producto = new Producto(1,"lavandina",(float) 45);

    }
    @Test
    public void constructorAndGettersTest(){

        assertEquals(producto.getIdProducto(), 1);

        assertEquals(producto.getNombre(),"lavandina");

        assertEquals(producto.getPrecio(), 45, 0);
    }

    @Test
    public void settersTest(){

        producto.setNombre("jabon");
        producto.setIdProducto(1);
        producto.setPrecio((float) 90);
        

        assertEquals(producto.getNombre(),"jabon");
        assertEquals(producto.getPrecio(), 90, 0);
        assertEquals(producto.getIdProducto(),1);
        
    }

    @Test
    public void toStringTest(){
       assertEquals(producto.toString(),"Producto [idProducto=1, nombre=lavandina, precio=45.0]");


    }
}
