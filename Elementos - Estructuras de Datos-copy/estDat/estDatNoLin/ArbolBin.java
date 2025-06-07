package estDat.estDatNoLin;
import estDat.estDatLin.*;
public class ArbolBin<T>{
    private T raiz;
    private ArbolBin<T> izq, der;

    public ArbolBin(){
        izq = der = null;
        raiz = null;
    }

    public boolean esVacia(){
        boolean res;
        res = (raiz == null);
        return res;
    }
    
    public ArbolBin<T> ancestroMasBajo(T x, T y){
        ArbolBin<T> res = null;
        return ancestroMasBajo(x, y, res);
    }
    
    private ArbolBin<T> ancestroMasBajo(T x, T y, ArbolBin<T> respuesta){
        ArbolBin<T> res = respuesta;
         if(!esVacia()){
            if(contiene(x, y)){
                //soy ancestro comun de x y de y
                res = this;
                res = izq.ancestroMasBajo(x, y, this);
                if(res == this){
                    res = der.ancestroMasBajo(x, y, this);
                }
            }
        }
        return res;
    }
    
    private boolean contiene(T x, T y){
        boolean res;
        res = contiene(x) && contiene(y);
        return res;
    }
    
    private boolean contiene(T x){
        boolean res;
        if(!esVacia()){
            if(x.equals(raiz)){
                res = true;
            }else{
                res = izq.contiene(x) || der.contiene(x);
            }
        }else{
            res = false;
        }
        return res;
    }
    
    public Lista<T> rutaAbajo(T x){
        Lista<T> ruta = new ListaDE<T>();
        ruta(x, ruta);
        ruta.invertir();
        return res;
    }
    
    private void ruta(T x, Lista<T> res){
        if(!esVacia()){
            res.insertar(raiz);
            if(izq.contiene(x)){
                izq.ruta(x, res);
            }else if (der.contiene(x)){
                der.ruta(x, res);
            }
        }
    }

    public void insertarAmplitud(T nDato){
        Cola<ArbolBin<T>> espera = new Cola<ArbolBin<T>>();
        espera.encolar(this);
        insertarAmplitud(nDato, espera);
    }
    //pasar
    /*
    public Lista<T> vistaInferior(){
        int nivel = conseguirNivel();
        int tam = nivel*2 + 1;
        Lista<T> res = new ListaDE<T>();
        res.reservar(tam);
        return vistaInferior(nivel, res);
    }
    
    private Lista<T> vistaInferior(int pos, Lista<T> res){
        if(!esVacia()){
            res.asignar(pos, raiz);
            //res[pos] =raiz;
            izq.vistaInferior(pos-1, res);
            der.vistaInferior(pos+1, res);
        }
    }*/
    

    private void insertarAmplitud(T nDato, Cola<ArbolBin<T>> espera){
        ArbolBin<T> arb;
        arb = espera.decolar();
        if(arb.esVacia()){
            arb.raiz = nDato;
            arb.izq = new ArbolBin<T>();
            arb.der = new ArbolBin<T>();
        }else{
            espera.encolar(arb.izq);
            espera.encolar(arb.der);
            insertarAmplitud(nDato, espera);
        }
    }

    public boolean vaciar(){
        boolean res;
        raiz = null;
        res = (raiz == null);
        return res;
    }

    public boolean insertarProfundidad(Lista<T> ruta, T nDato){
        boolean res;
        if(esVacia()){
            if(ruta.estaVacia()){
                raiz = nDato;
                izq = new ArbolBin<T>();
                der = new ArbolBin<T>();
                res = true;
            }else{
                res = false;
            }
        }else{
            if(ruta.estaVacia()){
                res = false;
            }else{
                T elemento = ruta.acceder(0);
                if(elemento.equals(raiz)){
                    ruta.eliminar(elemento);
                    if(izq.insertarProfundidad(ruta, nDato)){
                        res = true;
                    }else{
                        res = der.insertarProfundidad(ruta, nDato);
                    }
                }else{
                    res = false;
                }
            }
        }
        return res;
    }

    public String recorridoPreorden(){
        String res;
        res = "[" + recorridoPreorden(this);
        if(!esVacia()){
            res = res.substring(0, res.length()-2);
        }
        return res + "]";
    }

    private String recorridoPreorden(ArbolBin<T> actual){
        String res = "";
        if(!actual.esVacia()){
            res = actual.raiz + ", " + recorridoPreorden(actual.izq) + recorridoPreorden(actual.der);
        }
        return res;
    }

    public String recorridoInorden(){
        String res;
        res = "[" + recorridoInorden(this);
        if(!esVacia()){
            res = res.substring(0, res.length()-2);
        }
        return res + "]";
    }

    private String recorridoInorden(ArbolBin<T> actual){
        String res = "";
        if(!actual.esVacia()){
            res = recorridoInorden(actual.izq) + actual.raiz + ", " + recorridoInorden(actual.der);
        }
        return res;
    }

    public String recorridoPostorden(){
        String res;
        res = "[" + recorridoPostorden(this);
        if(!esVacia()){
            res = res.substring(0, res.length()-2);
        }
        return res + "]";
    }

    private String recorridoPostorden(ArbolBin<T> actual){
        String res = "";
        if(!actual.esVacia()){
            res = recorridoPostorden(actual.izq) + recorridoPostorden(actual.der) + actual.raiz + ", ";
        }
        return res;
    }

    public T acceder(T dato){
        T res;
        if(esVacia())
            res = null;
        else
        if(raiz.equals(dato))
            res = raiz;
        else{
            res = izq.acceder(dato);
            if(res == null)
                res = der.acceder(dato);
        }
        return res;
    }

    private boolean raizHoja(){
        return izq.esVacia() && der.esVacia();
    }

    private ArbolBin<T> busqueda(){
        ArbolBin<T> res;
        if(der.esVacia()){
            res = this;
        }else{
            res = der.busqueda();
        }
        return res;
    }

    public boolean InordenOrdenado(ArbolBin<Integer> actual){
        boolean res;
        if(!actual.esVacia()){
            if(!actual.raizHoja()){
                res = InordenOrdenado(actual.izq);
                if(res){
                    if(!actual.izq.esVacia() && actual.izq.raiz < actual.raiz){
                        res = InordenOrdenado(actual.der);
                        if(res){
                            if(!actual.der.esVacia() && actual.der.raiz > actual.raiz){
                                res = true;
                            }else{
                                res = false;
                            }
                        }else{
                            res = false;
                        }
                    }else{
                        res = false;
                    }
                }else{
                    res = false;
                }
            }else{
                res = true;
            }
        }else{
            res = true;
        }
        return res;
    }

    //Funciona para arboles con insercion por amplitud
    public boolean PreordenOrdenado(ArbolBin<Integer> actual){
        boolean res;
        if(!actual.esVacia()){
            if(!actual.raizHoja()){
                res = PreordenOrdenado(actual.izq);
                if(res){
                    if(!actual.izq.esVacia() && actual.izq.raiz > actual.raiz){
                        res = PreordenOrdenado(actual.der);
                        if(res){
                            if(!actual.der.esVacia() && actual.der.raiz > actual.izq.raiz && ((int)der.raiz >= actual.der.raiz || (int)izq.raiz <= actual.der.raiz)){
                                res = true;
                            }else{
                                res = false;
                            }
                        }else{
                            res = false;
                        }
                    }else{
                        res = false;
                    }
                }else{
                    res = false;
                }
            }else{
                res = true;
            }
        }else{
            res = true;
        }
        return res;
    }

    public ListaSE<ListaSE<T>> enlistarHojasHastaVaciarArb(){
        ListaSE<ListaSE<T>> listaGen = new ListaSE<ListaSE<T>>();
        enlistarHojasHastaVaciarArb(listaGen);
        return listaGen;
    }

    private ListaSE<ListaSE<T>> enlistarHojasHastaVaciarArb(ListaSE<ListaSE<T>> listaGen){
        if(!esVacia()){
            listaGen.insertar(enlistarHojas());
            anularHojas();
            enlistarHojasHastaVaciarArb(listaGen);
        }
        return listaGen;
    }

    public ListaSE<T> enlistarHojas(){
        ListaSE<T> lista = new ListaSE<T>();
        enlistarHojas(lista);
        return lista;
    }

    private void enlistarHojas(ListaSE<T> lista){
        if(!esVacia()){
            if(!raizHoja()){
                izq.enlistarHojas(lista);
                if(!lista.estaVacia()){
                    der.enlistarHojas(lista);
                }
            }else{
                lista.insertar(this.raiz);
            }
        }
    }

    public void anularHojas(){
        if(!esVacia()){
            if(!raizHoja()){
                izq.anularHojas();
                der.anularHojas();
            }else{
                this.raiz = null;
            }
        }
    }

    public int altura(){
        int res;
        if(!esVacia()){
            res = 1 + Math.max(izq.altura(), der.altura());
        }else{
            res = -1;
        }
        return res;
    }

    public int alturaIzq(){
        int res;
        if(!esVacia()){
            res = 1 + izq.altura();
        }else{
            res = -1;
        }
        return res;
    }

    public int alturaDer(){
        int res;
        if(!esVacia()){
            res = 1 + der.altura();
        }else{
            res = -1;
        }
        return res;
    }

    public T accederDato(T dato){
        T res = null;
        if(!esVacia()){
            if(!dato.equals(raiz)){
                res = izq.accederDato(dato);
                if(res == null){
                    res = der.accederDato(dato);
                }
            }else{
                res = raiz;
            }
        }
        return res;
    }

    public ListaSE<T> encontrarCamino(T dato){
        ListaSE<T> camino = new ListaSE<T>();
        if(!esVacia()){
            caminar(dato, camino);
        }
        return camino;
    }

    private void caminar(T dato, ListaSE<T> camino){
        if(!esVacia()){  
            if(!dato.equals(raiz)){
                izq.caminar(dato, camino);
                if(camino.estaVacia()){
                    der.caminar(dato, camino);
                    if(!camino.estaVacia()){
                        camino.insertarPos(0, raiz);
                    }
                }else{
                    camino.insertarPos(0, raiz);
                }
            }else{
                camino.insertarPos(0, raiz);
            }
        }
    }

    public ListaSE<ListaSE<T>> recogerHojasNivel(int nivel){
        ListaSE<ListaSE<T>> listaHojas = new ListaSE<ListaSE<T>>();
        recogerHojasNivel(listaHojas, nivel);
        return listaHojas;
    }

    private ListaSE<ListaSE<T>> recogerHojasNivel(ListaSE<ListaSE<T>> listaHojas, int nivel){
        if(!esVacia() && nivel >= 0){
            listaHojas.insertar(enlistarHojasNivel(nivel));
            recogerHojasNivel(listaHojas, nivel-1);
        }
        return listaHojas;
    }

    public ListaSE<T> enlistarHojasNivel(int nivel){
        ListaSE<T> lista = new ListaSE<T>();
        enlistarHojasNivel(lista, nivel);
        return lista;
    }

    private void enlistarHojasNivel(ListaSE<T> lista, int nivel){
        if(!esVacia()){
            if(nivel == 0 && raizHoja()){
                lista.insertar(raiz);
            }else{
                izq.enlistarHojasNivel(lista, nivel-1);
                der.enlistarHojasNivel(lista, nivel-1);
            }
        }
    }

    public void aplanarArbolDerecha(){
        if(!esVacia()){
            if(!raizHoja()){
                izq.aplanarArbolDerecha();
                der.aplanarArbolDerecha();
                izq.arbolMasDerecho2().der = der;
                der = izq;
                izq = new ArbolBin<T>();
            }
        }
    }

    public ArbolBin<T> arbolMasDerecho(){
        ArbolBin<T> res;
        if(der.esVacia()){
            res = this;
        }else{
            res = der.arbolMasDerecho();
        }
        return res;
    }

    public ArbolBin<T> arbolMasDerecho2(){
        ArbolBin<T> res;
        if(!esVacia()){
            if(!raizHoja()){
                res = der.arbolMasDerecho2();
            }else{
                res = this;
            }
        }else{
            res = null;
        }
        return res;
    }

    public void aplanarArbolIzquierda(){
        if(!esVacia()){
            if(!raizHoja()){
                izq.aplanarArbolIzquierda();
                der.aplanarArbolIzquierda();
                der.arbolMasIzquierdo().izq = izq;
                izq = der;
                der = new ArbolBin<T>();
            }
        }
    }

    public ArbolBin<T> arbolMasIzquierdo(){
        ArbolBin<T> res;
        if(!esVacia()){
            if(!raizHoja()){
                res = izq.arbolMasIzquierdo();
            }else{
                res = this;
            }
        }else{
            res = null;
        }
        return res;
    }

    public int contarDatosArbol(){
        int res = 0;
        if(!esVacia()){
            if(!raiz.equals(null)){
                res = 1 + izq.contarDatosArbol() + der.contarDatosArbol();
            }
        }
        return res;
    }

    public int posDato(T dato){
        int res = 0;
        T arbMasIzqRaiz = this.arbolMasIzquierdo().raiz;
        int exceso = posDato(arbMasIzqRaiz, 0);
        if(!esVacia()){
            res = Math.abs(posDato(dato, 0) - exceso);
        }else{
            res = Integer.MAX_VALUE;
        }
        return res;
    }

    private int posDato(T dato, int pos){
        int res;
        if(!esVacia()){
            if(dato.equals(raiz)){
                res = pos;
            }else{
                res = izq.posDato(dato, pos+1);
                if(res == Integer.MAX_VALUE){
                    res = der.posDato(dato, pos-1);
                }
            }
        }else{
            res = Integer.MAX_VALUE;
        }
        return res;
    }

    public int cantidadUnicaDescendencia(){
        int res;
        if(esVacia()){
            res = 0;
        }else{
            if(cumpleCondicion()){
                if(izq.esVacia()){
                    res = 1 + der.cantidadUnicaDescendencia();
                }else{
                    res = 1 + izq.cantidadUnicaDescendencia();
                }
            }else{
                res = izq.cantidadUnicaDescendencia() + der.cantidadUnicaDescendencia();
            }
        }
        return res;
    }

    private boolean cumpleCondicion(){
        return (izq.esVacia() && !der.esVacia()) || (der.esVacia() && !izq.esVacia());
    }

    public T eliminar(T dato){
        T res;
        if(esVacia())
            res = null;
        else
        if(raiz.equals(dato))
            if(raizHoja()){
                res = raiz;
                raiz = null;
                izq = der = null;
            }else{
                res = null;
            }
        else{
            res = izq.eliminar(dato);
            if(res == null)
                res = der.eliminar(dato);
        }
        return res;
    }

    public boolean contieneDato(T dato){
        boolean res;
        if(esVacia()){
            res = false;
        }else{
            if(raiz.equals(dato)){
                res = true;
            }else{
                res = izq.contieneDato(dato) || der.contieneDato(dato);
            }
        }
        return res;
    }

    /**
     * Ejercicio No. 2:
     * Realiza la operacion de invertir un arbol binario:
     * Ejemplo, si se tiene el siguiente arbol binario:
     * Antes:
     *        6
     *     2     8
     *   1    3
     *     4   
     * Despues:
     *        
     *     8     2
     *        3     1
     *           4
     */
    public void invertirRamasArbol(){
        ArbolBin<T> aux;
        if(!esVacia()){
            aux = der;
            der = izq;
            izq = aux;
            izq.invertirRamasArbol();
            der.invertirRamasArbol();
        }
    }

    /**
     * Ejercicio No. 3:
     * Se desea rotar el camino a un dato de la siguiente manera:
     * Si el dato es 7:
     * Antes:
     *        6
     *     4     2
     *   1    8
     *     3     7
     * Despues:
     *        7
     *     6     2
     *   1    4
     *     3     8
     */
    public void rotarRuta(T dato){
        ListaSE<ArbolBin<T>> listaArboles = new ListaSE<ArbolBin<T>>();
        ListaSE<T> raices = new ListaSE<T>();
        armarRuta(listaArboles, dato, raices);
        if(!listaArboles.estaVacia()){
            realizarCambio(listaArboles, raices);
        }
    }

    private void armarRuta(ListaSE<ArbolBin<T>> listaArboles, T dato, ListaSE<T> raices){
        if(!esVacia()){
            if(raiz.equals(dato)){
                listaArboles.insertarPos(0, this);
                raices.insertarPos(0, this.raiz);
            }else{
                izq.armarRuta(listaArboles, dato, raices);
                if(listaArboles.estaVacia()){
                    der.armarRuta(listaArboles, dato, raices); 
                }
                if(!listaArboles.estaVacia()){
                    listaArboles.insertarPos(0, this);
                    raices.insertarPos(0, this.raiz);
                }
            }
        }
    }

    private void realizarCambio(ListaSE<ArbolBin<T>> listaArboles, ListaSE<T> raices){
        T res = raices.eliminarPos(raices.getSize()-1);
        raices.insertarPos(0, res);
        ArbolBin<T> arb;
        T actualizado;
        while(!listaArboles.estaVacia()){
            arb = listaArboles.eliminarPos(0);
            actualizado = raices.eliminarPos(0);
            arb.raiz = actualizado;
        }
    }
}