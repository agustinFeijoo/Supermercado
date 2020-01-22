package dataService;


import dao.CarritoDao;
import dao.FacturaDao;
import dao.ProductoDao;
import dao.UsuarioDao;
import modelo.Factura;
import modelo.Producto;
import modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class DataService{

    private ProductoDao productDao;
    private UsuarioDao usuarioDao;
    private FacturaDao facturaDao;
    private CarritoDao carritoDao;
    //private ItemDao itemDao;

    public DataService(/*ProductDao aProductDao, UsuarioDao aUsuarioDao, BillDao aBillDao*/){
        /*todo quitar comentario*/
        this.productDao = new ProductoDao();
        this.usuarioDao = new UsuarioDao();
        this.facturaDao = new FacturaDao();
        this.carritoDao = new CarritoDao();
       // this.itemDao = new ItemDao();
    }


    public void createInitialDataSet() {
        List<Factura> facturas=new ArrayList<Factura>();

        //Factura f1=new Factura(3,);

        Producto product1 = new Producto(4, "CIF", (float)100);
        Producto product2 = new Producto(5, "Mr Musculo", (float)150);
        Producto product3 = new Producto(6, "Ayudin", (float)200);
        Producto product4 = new Producto(7, "CIF", (float)50);
        Producto product5 = new Producto(8, "Mr Musculo", (float)40);
        Producto product6 = new Producto(9, "Ayudin", (float)30);
        Producto product7 = new Producto(10, "Magistral", (float)80);
        Producto product8 = new Producto(11, "Ace", (float)70);
        Producto product9 = new Producto(12, "Magistral", (float)20);
        Producto product10 = new Producto(13, "Ace", (float)25);

        try {
            productDao.save(product1);
            productDao.save(product2);
            productDao.save(product3);
            productDao.save(product4);
            productDao.save(product5);
            productDao.save(product6);
            productDao.save(product7);
            productDao.save(product8);
            productDao.save(product9);
            productDao.save(product10);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
       // BillGenerator facturaGenerator = new BillGenerator();

        Usuario usuario1 = new Usuario(3,"Fran","spinelli", "123", "fran@example.com" );
        Usuario usuario2 = new Usuario(4,"Agus","feijoo", "1111",facturas);
        Usuario usuario3 = new Usuario(5,"Tomy","Lopez", "123", "tomy@example.com");
        try {
            usuarioDao.save(usuario1);
            usuarioDao.save(usuario2);
            usuarioDao.save(usuario3);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }


    public void deleteAllData() {


        this.facturaDao.deleteAllData();
        this.carritoDao.deleteAllData();//tambien borro items
        this.usuarioDao.deleteAllData();

        //this.itemDao.deleteAllData();
        this.productDao.deleteAllData();
    }
}
