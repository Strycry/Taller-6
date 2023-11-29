package uniandes.dpoo.taller2.pruebas;

import org.junit.Test;

import junit.framework.TestCase;
import uniandes.dpoo.taller2.procesamiento.ProductoMenu;

public class ProductoMenuTest extends TestCase{
	@Test
    public void testGetPrecio() {
        ProductoMenu producto = new ProductoMenu("Hamburguesa", 10000);
        assertEquals(10000, producto.getprecio());
    }

    @Test
    public void testGetNombre() {
        ProductoMenu producto = new ProductoMenu("Hamburguesa", 10000);
        assertEquals("Hamburguesa", producto.getnombre());
    }

    @Test
    public void testGenerarTextoFactura() {
        ProductoMenu producto = new ProductoMenu("Hamburguesa", 10000);
        String factura = producto.generarTextoFactura();
        assertEquals("Producto Hamburguesa 10000", factura);
    }

}
