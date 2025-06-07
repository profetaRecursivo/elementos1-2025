import estDat.estDatLin.*;
public class Ejercicio1{
    private ListaSE<Integer> camiones;
    private Pila<Integer> pila;
    private Cola<Integer> cola;

    /**
     * Ejercicio No. 1:
     * Se desea rotar el camino a un dato de la siguiente manera:
     * Una empresa quiere mandar sus pedidos en camiones en orden, la experiencia les enseñó a dejar libre una 
     * calle lateral para poder ordenar los camiones. 
     * 
     * La calle lateral es tan estrecha que no pueden pasar dos autos. Así, el camino que entra en último lugar por la calle lateral
     * debe salir necesariamente primero de la calle lateral. Debido a que los camiones se acercan, un camión no puede regresar 
     * y volver a ingresar a la calle lateral o a la calle de acceso.
     * 
     * Dada una lista de enteros, donde cada número representa al # de cada camión, indicar si estos pueden ir en orden o no.
     * Ejemplo:

    camiones = [5, 1, 2, 4, 3]

    res = si
     */
    public Ejercicio1(){
        camiones = new ListaSE<Integer>();
        pila = new Pila<Integer>();
        cola = new Cola<Integer>();
        camiones.insertar(5);
        camiones.insertar(1);
        camiones.insertar(2);
        camiones.insertar(4);
        camiones.insertar(3);
    }

    private void ordenadorCamiones(){
        while(!camiones.estaVacia()){
            int actual = camiones.acceder(0); 
            if(pila.estaVacia()){
                if(camiones.getSize() == 1){
                    cola.encolar(actual);
                    camiones.eliminarPos(0);
                }else{
                    if(actual > camiones.acceder(1)){
                        pila.push(actual);
                        camiones.eliminarPos(0);
                    }else{
                        cola.encolar(actual);
                        camiones.eliminarPos(0);
                    }
                }
            }else{
                if(camiones.getSize() == 1){
                    if(actual > pila.top()){
                        int remanente = pila.pop();
                        cola.encolar(remanente);
                        //camiones.eliminarPos(0);
                    }else{
                        cola.encolar(actual);
                        camiones.eliminarPos(0);
                        int remanente = pila.pop();
                        cola.encolar(remanente);
                        //camiones.eliminarPos(0);
                    }
                }else{
                    if(actual > camiones.acceder(1) && actual < pila.top()){
                        pila.push(actual);
                        camiones.eliminarPos(0);
                    }else if(actual < camiones.acceder(1) && actual < pila.top()){
                        cola.encolar(actual);
                        camiones.eliminarPos(0);
                    }
                }
            }
        }
    }

    public boolean sePuede(){
        boolean res = true;
        int aux = 0;
        ordenadorCamiones();
        while(!cola.estaVacia() && res){
            int actual = cola.decolar();
            if(actual > aux){
                aux = actual;
            }else{
                res = false;
            }
        }
        return res;
    }
}