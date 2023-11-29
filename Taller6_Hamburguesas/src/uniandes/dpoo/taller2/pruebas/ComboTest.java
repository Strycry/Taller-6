package uniandes.dpoo.taller2.pruebas;


import org.junit.Test;

import junit.framework.TestCase;
import uniandes.dpoo.taller2.procesamiento.*;


public class ComboTest extends TestCase{
	
	@Test
    public void testAgregarItemACombo() {
        Combo combo = new Combo(10, "Combo1");
        ProductoMenu productoMenu = new ProductoMenu("Hamburguesa", 10000);
        combo.agregarItemACombo(productoMenu);
        assertEquals(1, combo.getItemsCombo().size());
    }

    @Test
    public void testGetPrecio() {
        Combo combo = new Combo(10, "Combo2");
        ProductoMenu productoMenu1 = new ProductoMenu("Hamburguesa", 10000);
        ProductoMenu productoMenu2 = new ProductoMenu("Papas", 5000);
        combo.agregarItemACombo(productoMenu1);
        combo.agregarItemACombo(productoMenu2);
        assertEquals(13500, combo.getprecio());
    }

    @Test
    public void testGenerarTextoFactura() {
        Combo combo = new Combo(15, "Combo3");
        ProductoMenu productoMenu = new ProductoMenu("Hamburguesa", 10000);
        combo.agregarItemACombo(productoMenu);
        String expected = "Producto Combo3 8500";
        assertEquals(expected, combo.generarTextoFactura());
    }


}
