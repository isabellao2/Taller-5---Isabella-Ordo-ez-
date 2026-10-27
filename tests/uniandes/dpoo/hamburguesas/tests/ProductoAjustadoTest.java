package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;


import org.junit.jupiter.api.Test;

class ProductoAjustadoTest {

	@Test
    void testGetNombre() {
        ProductoMenu base = new ProductoMenu("Hamburguesa sencilla", 12000);
        ProductoAjustado ajustado = new ProductoAjustado(base);

        assertEquals("Hamburguesa sencilla", ajustado.getNombre());
    }

    @Test
    void testGetPrecioSinAjustes() {
        ProductoMenu base = new ProductoMenu("Hamburguesa sencilla", 12000);
        ProductoAjustado ajustado = new ProductoAjustado(base);

        assertEquals(12000, ajustado.getPrecio());
    }

    @Test
    void testGetPrecioConIngredientesAgregados() {
        ProductoMenu base = new ProductoMenu("Hamburguesa sencilla", 12000);
        ProductoAjustado ajustado = new ProductoAjustado(base);
        Ingrediente queso = new Ingrediente("Queso", 2000);
        Ingrediente tocineta = new Ingrediente("Tocineta", 3000);

        ajustado.agregarIngrediente(queso);
        ajustado.agregarIngrediente(tocineta);

        assertEquals(17000, ajustado.getPrecio());
    }

    @Test
    void testGenerarTextoFactura() {
        ProductoMenu base = new ProductoMenu("Hamburguesa sencilla", 12000);
        ProductoAjustado ajustado = new ProductoAjustado(base);

        String texto = ajustado.generarTextoFactura();

        assertTrue(texto.contains("Hamburguesa sencilla"));
        assertTrue(texto.contains("12000"));
    }

    @Test
    void testGenerarTextoFacturaConIngredientes() {
        ProductoMenu base = new ProductoMenu("Hamburguesa sencilla", 12000);
        ProductoAjustado ajustado = new ProductoAjustado(base);

        Ingrediente queso = new Ingrediente("Queso", 2000);
        Ingrediente tomate = new Ingrediente("Tomate", 1000);

        ajustado.agregarIngrediente(queso);
        ajustado.eliminarIngrediente(tomate);

        String texto = ajustado.generarTextoFactura();

        assertTrue(texto.contains("+Queso"));
        assertTrue(texto.contains("2000"));
        assertTrue(texto.contains("-Tomate"));
        assertTrue(texto.contains("14000"));
    }
}



