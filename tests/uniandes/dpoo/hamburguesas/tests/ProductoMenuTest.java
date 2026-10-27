package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import uniandes.dpoo.hamburguesas.mundo.*;

class ProductoMenuTest {

	@Test
	void testGetNombre() {

        ProductoMenu producto = new ProductoMenu("Hamburguesa de pollo", 20000);

        String nombreQueDa = producto.getNombre();

        assertEquals("Hamburguesa de pollo", nombreQueDa);
    };
	
    @Test
	void testGetPrecio() {

        ProductoMenu producto = new ProductoMenu("Hamburguesa de pollo", 20000);

        int precioQueDa = producto.getPrecio();

        assertEquals(20000, precioQueDa);
    };
    @Test
	void testGenerarTextoFactura() {

        ProductoMenu producto = new ProductoMenu("Hamburguesa de pollo", 20000);

        String factura = producto.generarTextoFactura();
        
        String esperado = "Hamburguesa de pollo\n"
                + "            20000\n";

        assertEquals(esperado, factura);
    };
    }


