package sebas.lab.testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class GestionBibliotecaTest {

    @Test
    public void testPrecioSinDescuento() {
        GestionBiblioteca gestion = new GestionBiblioteca();
        double precioBase = 100.0;
        double descuento = 0.0; 
        double esperado = 100.0;
        double resultado = gestion.calcularPrecioConDescuento(precioBase, descuento);
        assertEquals(esperado, resultado, 1e-6);
    }

    @Test
    public void testPrecioCon50PorcientoDescuento() {
        GestionBiblioteca gestion = new GestionBiblioteca();
        double precioBase = 200.0;
        double descuento = 50.0; 
        double esperado = 100.0; 
        double resultado = gestion.calcularPrecioConDescuento(precioBase, descuento);
        assertEquals(esperado, resultado, 1e-6);
    }

    @Test
    public void testPrecioCon100PorcientoDescuento() {
        GestionBiblioteca gestion = new GestionBiblioteca();
        double precioBase = 75.5;
        double descuento = 100.0;
        double esperado = 0.0;
        double resultado = gestion.calcularPrecioConDescuento(precioBase, descuento);
        assertEquals(esperado, resultado, 1e-6);
    }

    @Test
    public void testLibroNoDisponible() {
        GestionBiblioteca gestion = new GestionBiblioteca();
        String titulo = "Don Quijote";
        
        assertFalse(gestion.estaDisponible(titulo));
    }

    @Test
    public void testLibroDisponibleDespuesDeAgregarlo() {
        GestionBiblioteca gestion = new GestionBiblioteca();
        String titulo = "Cien Años de Soledad";
        
        boolean agregado = gestion.agregarLibro(titulo);
        assertTrue(agregado);
        assertTrue(gestion.estaDisponible(titulo));
    }

    @Test
    public void testAgregarLibroExitosamente() {
        GestionBiblioteca gestion = new GestionBiblioteca();
        String titulo = "El Principito";
        
        boolean agregado = gestion.agregarLibro(titulo);
        assertTrue("El libro se agrego exitosamente", agregado);
        
        assertTrue(gestion.estaDisponible(titulo));
    }

    @Test
    public void testAgregarLibroDuplicadoRetornaFalse() {
        GestionBiblioteca gestion = new GestionBiblioteca();
        String titulo = "1984";
        
        assertTrue(gestion.agregarLibro(titulo));
        
        assertFalse("El libro esta duplicado", gestion.agregarLibro(titulo));
    }

    @Test
    public void testCategoriaPrincipianteCon0Libros() {
        GestionBiblioteca gestion = new GestionBiblioteca();
        int librosLeidos = 0;
        String esperado = "Principiante";
        String resultado = gestion.obtenerCategoriaLector(librosLeidos);
        assertEquals(esperado, resultado);
    }

    @Test
    public void testCategoriaIntermedioCon5Libros() {
        GestionBiblioteca gestion = new GestionBiblioteca();
        int librosLeidos = 5;
        String esperado = "Intermedio";
        String resultado = gestion.obtenerCategoriaLector(librosLeidos);
        assertEquals(esperado, resultado);
    }

    @Test
    public void testCategoriaAvanzadoCon25Libros() {
        GestionBiblioteca gestion = new GestionBiblioteca();
        int librosLeidos = 25;
        String esperado = "Avanzado";
        String resultado = gestion.obtenerCategoriaLector(librosLeidos);
        assertEquals(esperado, resultado);
    }

    @Test
    public void testObtenerLibrosDisponiblesNuncaRetornaNull() {
        GestionBiblioteca gestion = new GestionBiblioteca();
        assertNotNull("Nunca se retorna null", gestion.obtenerLibrosDisponibles());
    }

    @Test
    public void testObtenerLibrosDisponiblesListaVaciaInicialmente() {
        GestionBiblioteca gestion = new GestionBiblioteca();
        assertTrue("La lista de libros esta vacía", gestion.obtenerLibrosDisponibles().isEmpty());
    }
}
