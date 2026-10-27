package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;


class PedidoTest {
	
	@TempDir
    Path carpetaTemporal;

	@Test
	void testGetIdPedido() {
		Pedido pedido = new Pedido("Isabella", "Calle 88");

	 assertTrue(pedido.getIdPedido() >= 0);
		}
;
@Test
void testGetNombreCliente() {
    Pedido pedido = new Pedido("Isabella", "Calle 88");
    assertEquals("Isabella", pedido.getNombreCliente());
}

@Test
void testPrecioTotalPedidoVacio() {
    Pedido pedido = new Pedido("Isabella", "Calle 88");
    assertEquals(0, pedido.getPrecioTotalPedido());
}

@Test
void testPrecioTotalConUnProducto() {
    Pedido pedido = new Pedido("Isabella", "Calle 80");
    ProductoMenu hamburguesa =
            new ProductoMenu("Hamburguesa", 20000);

    pedido.agregarProducto(hamburguesa);

    assertEquals(23800, pedido.getPrecioTotalPedido());
}

@Test
void testPrecioTotalConVariosProductos() {
    Pedido pedido = new Pedido("Isabella", "Calle 80");

    pedido.agregarProducto(new ProductoMenu("Hamburguesa", 10000));
    pedido.agregarProducto(new ProductoMenu("Papas", 5000));

    assertEquals(17850, pedido.getPrecioTotalPedido());
}

@Test
void testGenerarTextoFactura() {
    Pedido pedido = new Pedido("Isabella", "Calle 20");
    pedido.agregarProducto(new ProductoMenu("Hamburguesa", 10000));

    String factura = pedido.generarTextoFactura();

    assertTrue(factura.contains("Isabella"));
    assertTrue(factura.contains("Calle 20"));
    assertTrue(factura.contains("Precio Neto:  10000"));
    assertTrue(factura.contains("IVA:          1900"));
    assertTrue(factura.contains("Precio Total: 11900"));
}

@Test
void testGuardarFactura() throws Exception {
    Pedido pedido = new Pedido("Isabella", "Calle 20");
    pedido.agregarProducto(new ProductoMenu("Hamburguesa", 10000));

    File archivo = carpetaTemporal.resolve("factura.txt").toFile();

    pedido.guardarFactura(archivo);

    String contenido = Files.readString(archivo.toPath());

    assertTrue(contenido.contains("Isabella"));
    assertTrue(contenido.contains("Precio Total: 11900"));
}

@Test
void testIdsConsecutivos() {
    Pedido pedido1 = new Pedido("Cliente 1", "Dirección 1");
    Pedido pedido2 = new Pedido("Cliente 2", "Dirección 2");

    assertEquals(pedido1.getIdPedido() + 1, pedido2.getIdPedido());
}
@Test
void testPedidoConProductoAjustado() {
    Pedido pedido = new Pedido("Isabella", "Calle 20");

    ProductoMenu hamburguesa =
            new ProductoMenu("Hamburguesa", 20000);

    ProductoAjustado ajustado =
            new ProductoAjustado(hamburguesa);

    ajustado.agregarIngrediente(
            new Ingrediente("Queso", 2000)
    );

    pedido.agregarProducto(ajustado);

    assertEquals(26180, pedido.getPrecioTotalPedido());
}

}
