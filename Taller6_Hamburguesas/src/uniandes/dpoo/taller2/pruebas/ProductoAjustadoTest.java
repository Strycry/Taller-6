package uniandes.dpoo.taller2.pruebas;

import org.junit.Test;

import junit.framework.TestCase;
import uniandes.dpoo.taller2.procesamiento.Ingrediente;
import uniandes.dpoo.taller2.procesamiento.ProductoAjustado;
import uniandes.dpoo.taller2.procesamiento.ProductoMenu;

public class ProductoAjustadoTest extends TestCase{
	@Test
    public void testAñadirIngrediente() {
        ProductoMenu base = new ProductoMenu("Hamburguesa", 10000);
        ProductoAjustado producto = new ProductoAjustado(base);
        Ingrediente ingrediente = new Ingrediente("Queso", 2000);
        producto.añadirIngrediente(ingrediente);
        assertTrue(producto.generarTextoFactura().contains("Agregados"));
        assertTrue(producto.generarTextoFactura().contains(ingrediente.getNombre()));
    }

    @Test
    public void testEliminarIngrediente() {
        ProductoMenu base = new ProductoMenu("Hamburguesa", 10000);
        ProductoAjustado producto = new ProductoAjustado(base);
        Ingrediente ingrediente = new Ingrediente("Queso", 2000);
        producto.eliminarIngrediente(ingrediente);
        assertTrue(producto.generarTextoFactura().contains("Eliminados"));
        assertTrue(producto.generarTextoFactura().contains(ingrediente.getNombre()));
    }

    @Test
    public void testSinIngredientesAgregados() {
        ProductoMenu base = new ProductoMenu("Hamburguesa", 10000);
        ProductoAjustado producto = new ProductoAjustado(base);
        assertTrue(producto.sinIngredientesAgregados());
    }

    @Test
    public void testConIngredientesAgregados() {
        ProductoMenu base = new ProductoMenu("Hamburguesa", 10000);
        ProductoAjustado producto = new ProductoAjustado(base);
        Ingrediente ingrediente = new Ingrediente("Queso", 2000);
        producto.añadirIngrediente(ingrediente);
        assertFalse(producto.sinIngredientesAgregados());
    }

    @Test
    public void testSinIngredientesQuitados() {
        ProductoMenu base = new ProductoMenu("Hamburguesa", 10000);
        ProductoAjustado producto = new ProductoAjustado(base);
        assertTrue(producto.sinIngredientesQuitados());
    }

    @Test
    public void testConIngredientesQuitados() {
        ProductoMenu base = new ProductoMenu("Hamburguesa", 10000);
        ProductoAjustado producto = new ProductoAjustado(base);
        Ingrediente ingrediente = new Ingrediente("Queso", 2000);
        producto.eliminarIngrediente(ingrediente);
        assertFalse(producto.sinIngredientesQuitados());
    }

    @Test
    public void testGetPrecio() {
        ProductoMenu base = new ProductoMenu("Hamburguesa", 10000);
        ProductoAjustado producto = new ProductoAjustado(base);
        Ingrediente ingrediente1 = new Ingrediente("Queso", 2000);
        Ingrediente ingrediente2 = new Ingrediente("Tomate", 1000);
        producto.añadirIngrediente(ingrediente1);
        producto.añadirIngrediente(ingrediente2);
        assertEquals(13000, producto.getprecio());
    }

}

