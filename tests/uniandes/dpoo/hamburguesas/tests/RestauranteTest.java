package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.File;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertNotNull;


import uniandes.dpoo.hamburguesas.mundo.Restaurante;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.IngredienteRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.ProductoFaltanteException;


public class RestauranteTest {
	
	@Test
	void testCargarInformacionRestaurante() throws Exception {
	    Restaurante restaurante = new Restaurante();

	    File ingredientes = new File("data/ingredientes.txt");
	    File menu = new File("data/menu.txt");
	    File combos = new File("data/combos.txt");

	    restaurante.cargarInformacionRestaurante(ingredientes, menu, combos);

	    assertEquals(15, restaurante.getIngredientes().size());
	    assertEquals(22, restaurante.getMenuBase().size());
	    assertEquals(4, restaurante.getMenuCombos().size());
	}

    @Test
    void testRestauranteVacio() {
        Restaurante restaurante = new Restaurante();

        assertNull(restaurante.getPedidoEnCurso());
        assertEquals(0, restaurante.getPedidos().size());
        assertEquals(0, restaurante.getIngredientes().size());
        assertEquals(0, restaurante.getMenuBase().size());
        assertEquals(0, restaurante.getMenuCombos().size());
    }
    @Test
    void testIniciarPedido() throws Exception {
        Restaurante restaurante = new Restaurante();

        restaurante.iniciarPedido("Isabella", "Calle 45");

        assertNotNull(restaurante.getPedidoEnCurso());
        assertEquals("Isabella",
                restaurante.getPedidoEnCurso().getNombreCliente());
    }
    @Test
    void testIniciarPedidoCuandoYaExisteUno() throws Exception {
        Restaurante restaurante = new Restaurante();

        restaurante.iniciarPedido("Isabella", "Calle 50");

        org.junit.jupiter.api.Assertions.assertThrows(
            uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException.class,
            () -> restaurante.iniciarPedido("Juan", "Calle 100")
        );
    }
    @Test
    void testNoIniciarDosPedidos() throws Exception {
        Restaurante restaurante = new Restaurante();

        restaurante.iniciarPedido("Isabella", "Calle 88");

        Pedido primerPedido = restaurante.getPedidoEnCurso();

        assertNotNull(primerPedido);

        try {
            restaurante.iniciarPedido("	Laura", "Calle 20");
        } catch (Exception e) {
            assertEquals(primerPedido, restaurante.getPedidoEnCurso());
        }
    }
    @TempDir
    Path carpetaTemporal;
    
    @Test
    void testCerrarYGuardarPedido() throws Exception {
        Restaurante restaurante = new Restaurante();

        File carpetaFacturas = new File("./facturas");
        carpetaFacturas.mkdirs();

        restaurante.iniciarPedido("Isabella", "Calle 88");

        int id = restaurante.getPedidoEnCurso().getIdPedido();

        restaurante.cerrarYGuardarPedido();

        assertNull(restaurante.getPedidoEnCurso());

        File factura = new File("./facturas/factura_" + id + ".txt");
        assertTrue(factura.exists());
    }
    @Test
    void testCerrarSinPedido() throws Exception {
        Restaurante restaurante = new Restaurante();

        boolean huboExcepcion = false;

        try {
            restaurante.cerrarYGuardarPedido();
        } catch (Exception e) {
            huboExcepcion = true;
        }

        assertTrue(huboExcepcion);
        assertNull(restaurante.getPedidoEnCurso());
    }
    @Test
    void testGetListasRestaurante() {
        Restaurante restaurante = new Restaurante();

        restaurante.getIngredientes().add(new Ingrediente("Queso", 2000));
        restaurante.getMenuBase().add(new ProductoMenu("Hamburguesa", 20000));

        assertEquals(1, restaurante.getIngredientes().size());
        assertEquals(1, restaurante.getMenuBase().size());
    }
    @Test
    void testIngredienteRepetido() throws Exception {
        Restaurante restaurante = new Restaurante();

        File ingredientes = new File("dataTest/ingredientesRepetidos.txt");
        File menu = new File("dataTest/menu.txt");
        File combos = new File("dataTest/combos.txt");

        boolean huboExcepcion = false;

        try {
            restaurante.cargarInformacionRestaurante(ingredientes, menu, combos);
        } catch (Exception e) {
            huboExcepcion = true;
        }

        assertTrue(huboExcepcion);
    }
    @Test
    void testProductoRepetido() throws Exception {
        Restaurante restaurante = new Restaurante();

        File ingredientes = new File("dataTest/ingredientes.txt");
        File menu = new File("dataTest/menuRepetido.txt");
        File combos = new File("dataTest/combos.txt");

        boolean huboExcepcion = false;

        try {
            restaurante.cargarInformacionRestaurante(ingredientes, menu, combos);
        } catch (Exception e) {
            huboExcepcion = true;
        }

        assertTrue(huboExcepcion);
    }
    @Test
    void testProductoFaltanteEnCombo() throws Exception {
        Restaurante restaurante = new Restaurante();

        File ingredientes = new File("dataTest/ingredientes.txt");
        File menu = new File("dataTest/menu.txt");
        File combos = new File("dataTest/comboProductoFalta.txt");

        boolean huboExcepcion = false;

        try {
            restaurante.cargarInformacionRestaurante(ingredientes, menu, combos);
        } catch (Exception e) {
            huboExcepcion = true;
        }

        assertTrue(huboExcepcion);
    }

    @Test
    void testComboRepetido() throws Exception {
        Restaurante restaurante = new Restaurante();

       
        boolean huboExcepcion = false;

        try {
            restaurante.cerrarYGuardarPedido();
        } catch (Exception e) {
            huboExcepcion = true;
        }

        assertTrue(huboExcepcion);
        assertNull(restaurante.getPedidoEnCurso());
    }
 }