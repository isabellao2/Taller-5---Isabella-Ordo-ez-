package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;




class ComboTest {

	@Test
	void testGetNombre() {
		ArrayList <ProductoMenu> productos  = new ArrayList <ProductoMenu>();
		productos.add(new ProductoMenu("Hamburguesa mini", 15000));
        
		Combo combo = new Combo ("Combo mini", 0.10, productos);
				
		assertEquals("Combo mini", combo.getNombre());

		;
	}
	@Test
	void testGetPrecioNoDescuento() {
		ArrayList <ProductoMenu> productos  = new ArrayList <ProductoMenu>();
		productos.add(new ProductoMenu("Hamburguesa mini", 15000));
		productos.add(new ProductoMenu("Porcion nuggets", 9000));
        
		Combo combo = new Combo ("Combo mini", 0.0, productos);
				
		assertEquals(24000, combo.getPrecio());

		;
	}
	@Test
	void testGetPrecioDescuento() {
		ArrayList <ProductoMenu> productos  = new ArrayList <ProductoMenu>();
		productos.add(new ProductoMenu("Hamburguesa mini", 15000));
		productos.add(new ProductoMenu("Porcion nuggets", 9000));
        
		Combo combo = new Combo ("Combo mini", 0.15, productos);
				
		assertEquals(20400, combo.getPrecio());

		;
	}
	 @Test
	    void testGenerarTextoFactura() {
	        ArrayList<ProductoMenu> productos = new ArrayList<ProductoMenu>();
	        productos.add(new ProductoMenu("Hamburguesa grande", 20000));

	        Combo combo = new Combo("Combo especial", 0.08, productos);

	        String texto = combo.generarTextoFactura();

	        assertTrue(texto.contains("Combo especial"));
	        assertTrue(texto.contains("Descuento: 0.08"));
	        assertTrue(texto.contains("18400"));
	    }
	 @Test
		void testComboMasDosElementos() {
			ArrayList <ProductoMenu> productos  = new ArrayList <ProductoMenu>();
			productos.add(new ProductoMenu("Hamburguesa grande", 20000));
			productos.add(new ProductoMenu("Porcion nuggets", 9000));
			productos.add(new ProductoMenu("Hamburguesa mini", 15000));
			productos.add(new ProductoMenu("Porcion papas", 7000));
	        
			Combo combo = new Combo ("Combo maxi", 0.10, productos);
					
			assertEquals(45900, combo.getPrecio());

			;
		}
	

}

