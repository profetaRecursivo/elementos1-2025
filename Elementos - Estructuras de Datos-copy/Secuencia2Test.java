

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import estDat.estDatLin.*;
public class Secuencia2Test
{
    /**
     * Default constructor for test class Secuencia2Test
     */
    private Lista<Integer> secuencia;
    private Secuencia2 solver;
    public Secuencia2Test()
    {
    }

    @BeforeEach
    public void setUp()
    {
         solver = new Secuencia2();   
    }

    
    @AfterEach
    public void tearDown()
    {
    }
    
    @Test
    public void test1(){
        secuencia = new ListaDE<Integer>();
        secuencia.insertar(2);
        secuencia.insertar(2);
        secuencia.insertar(4);
        secuencia.insertar(2);
        secuencia.insertar(2);
        secuencia.insertar(4);
        secuencia.insertar(4);
        secuencia.insertar(8);
        secuencia.insertar(16);
        secuencia.insertar(2);
        secuencia.insertar(2);
        secuencia.insertar(2);
        Lista<Integer> resDeseada = new ListaDE<Integer>();
        resDeseada.insertar(16);
        resDeseada.insertar(4);
        resDeseada.insertar(8);
        resDeseada.insertar(16);
        resDeseada.insertar(4);
        resDeseada.insertar(2);
        Lista<Integer> nuestraRes = solver.reducir(secuencia);
        assertEquals(resDeseada, nuestraRes);
    }
}
