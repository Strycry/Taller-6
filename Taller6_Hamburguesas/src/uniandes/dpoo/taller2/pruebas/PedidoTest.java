package uniandes.dpoo.taller2.pruebas;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase; 
import uniandes.dpoo.taller2.procesamiento.*;

public class PedidoTest extends TestCase{
	private Pedido pedido;
	private Producto producto1;
	private Producto producto2;
	
	@Before
	public void setUp() throws Exception {
		pedido = new Pedido("Cliente Test", "Dirección Test");
		producto1 = new ProductoMenu("Producto1", 50000);
		producto2 = new ProductoMenu("Producto2", 60000);
	}
	
	@After
	public void tearDown() throws Exception {
		pedido = null;
		producto1 = null;
		producto2 = null;
	}
	
	@Test
	public void testAgregarProducto() {
		try {
			pedido.agregarProducto(producto1);
			assertEquals(1, pedido.getItemsPedido().size());
		} catch (PrecioExcedidoException e) {
			fail("No debería lanzar una excepción aquí.");
		}
	}
	
	@Test(expected = PrecioExcedidoException.class)
	public void testAgregarProductoPrecioExcedido() throws PrecioExcedidoException {
		pedido.agregarProducto(producto1);
		pedido.agregarProducto(producto2);
		// Debería lanzar una excepción ya que el precio total supera el límite.
	}
	
	@Test
	public void testGenerarTextoFactura() {
	    Restaurante restaurante = new Restaurante();
	    Pedido pedido = new Pedido("Cliente Test", "Dirección Test");
	    Producto producto1 = new ProductoMenu("Producto1", 50000);
	    Producto producto2 = new ProductoMenu("Producto2", 60000);

	    try {
	        pedido.agregarProducto(producto1);
	        pedido.agregarProducto(producto2);
	    } catch (PrecioExcedidoException e) {
	        fail("No se esperaba una excepción: " + e.getMessage());
	    }

	    // Se ajusta la generación del ID en el resultado esperado
	    String expected = "Id pedido: " + (pedido.getIdPedido() - 1) + "\n" +
	            "Nombre cliente: Cliente Test\n" +
	            "Direccion cliente: Dirección Test\n" +
	            "Items del pedido: \n" +
	            "\n" +
	            "Producto Producto1 50000\n" +
	            "\n" +
	            "Producto Producto2 60000\n" +
	            "Precio neto: 110000\n" +
	            "Precio iva pedido : 20900\n" +
	            "Precio total : 130900";

	    assertEquals(expected.trim(), pedido.generarTextoFactura().trim());
	}


	
	@Test
	public void testGuardarFactura() {
        Pedido pedido = new Pedido("Cliente Test", "Dirección Test");
        Producto producto1 = new ProductoMenu("Producto1", 50000);
        Producto producto2 = new ProductoMenu("Producto2", 60000);

        try {
            pedido.agregarProducto(producto1);
            pedido.agregarProducto(producto2);
        } catch (PrecioExcedidoException e) {
            fail("No debería lanzar excepción al agregar productos.");
        }

        File archivo = new File("./data/factura_test.txt");
        pedido.guardarFactura(archivo);

        assertTrue(archivo.exists());
        archivo.delete();  // Elimina el archivo creado para no dejar rastro
    }


}
