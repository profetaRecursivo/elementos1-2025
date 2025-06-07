package estDat.estDatNoLin;
import estDat.estDatLin.*;
public class ArbolBB<T extends Comparable<T>>{
    private ArbolBB<T> izq;
    private T raiz;
    private ArbolBB<T> der;

    public ArbolBB(){
        raiz = null;
        izq = der = null;
    }

    public int contarNodosInternos(){
        int res = 0;
        if(!esVacia() && !esHoja()){
            res = 1 + izq.contarNodosInternos()+der.contarNodosInternos();
        }
        return res;
    }

    public int contarNodosHojas(){
        int res = 0;
        if(!esVacia()){
            if(esHoja()){
                res = 1;
            }else{
                res = izq.contarNodosHojas() + der.contarNodosHojas();
            }
        }
        return res;
    }

    public boolean esHoja(){
        return izq.esVacia() && der.esVacia();
    }

    //Arbol bien distribuido nodosInternos - nodosHojas
    public boolean bienDistribuido(){
        boolean res;
        if(esVacia()){
            res = true;
        }else{
            int internos = contarNodosInternos();
            int hojitas  = contarNodosHojas();
            if(Math.abs(internos - hojitas)<= 1){
                res = izq.bienDistribuido() && der.bienDistribuido();
            }else{
                res = false;
            }    
        }
        return res;
    }

    public boolean esVacia(){
        return raiz == null;
    }

    public void insertar(T dato){
        if(esVacia()){
            raiz = dato;
            izq = new ArbolBB<T>();
            der = new ArbolBB<T>();
        }else{
            if(raiz.compareTo(dato) > 0)
                izq.insertar(dato);
            else
                der.insertar(dato);
        }
    }

    public T eliminar(T dato){
        T elDato;
        ArbolBB<T> arb;
        if(esVacia())
            elDato = null;
        else
        if(dato.compareTo(raiz)<0)
            elDato = izq.eliminar(dato);
        else
        if(dato.compareTo(raiz) > 0)
            elDato = der.eliminar(dato);
        else{
            elDato = raiz;
            switch(estadoRaiz()){
                case 1: // hoja
                    raiz = null;
                    izq = null;
                    der = null;
                    break;
                case 2: // tiene solo HI
                    raiz = izq.raiz;
                    der = izq.der;
                    izq = izq.izq;
                    break;
                case 3: // tiene solo HD
                    raiz = der.raiz;
                    izq = der.izq;
                    der = der.der;
                    break;
                case 4: // tiene los dos
                    arb = der.descIzq();
                    raiz = arb.raiz;
                    arb.eliminar(raiz);
            }
        }
        return elDato;
    }

    //devuelve la descendencia mas izquierda
    private ArbolBB<T> descIzq(){
        ArbolBB<T> arb;
        if(izq.esVacia())
            arb = this;
        else
            arb = izq.descIzq();
        return arb;
    }

    private int estadoRaiz(){
        int estado;
        if(izq.esVacia())
            if(der.esVacia()) estado = 1;
            else              estado = 3;
        else
        if(der.esVacia()) estado = 2;
        else              estado = 4;
        return estado;
    }

    private boolean raizHoja(){
        return izq.esVacia() && der.esVacia();
    }

    //Arbol Parcialmente lleno cuando encuentro un arbol vacio
    //en el recorrido por amplitud no puede encontrar otro no vacio, si no false
    public boolean parcialmenteLleno(){
        Cola<ArbolBB<T>> cola = new Cola<ArbolBB<T>>();
        cola.encolar(this);
        boolean bandera = false;
        return parcialmenteLleno(cola, bandera);
    }

    private boolean parcialmenteLleno(Cola<ArbolBB<T>> cola, boolean bandera){
        ArbolBB<T> arb;
        boolean respuesta = true;
        if(!cola.estaVacia()){
            arb =cola.decolar();
            if(arb.esVacia()){
                bandera = true;
                respuesta = parcialmenteLleno(cola, bandera);
            }else{
                if(bandera){     
                    respuesta = false;
                }else{
                    cola.encolar(arb.izq);
                    cola.encolar(arb.der); 
                    respuesta = parcialmenteLleno(cola, bandera);
                }
            }
        }
        return respuesta;
    }
    
    //Recoger los datos en un intervalo
    public Lista<T> recogerIntervalo(T x, T y){
        ListaSE<T> lista = new ListaSE<T>();
        recogerIntervalo(lista, x, y);
        return lista;
    }

    private void recogerIntervalo(ListaSE<T>lista, T ini, T fin){
        if(!esVacia()){
            if(cumpleIntervalo(ini,fin)){
                lista.insertarPos(0, raiz);
            }
            
            if(raiz.compareTo(ini) > 0){
                izq.recogerIntervalo(lista ,ini, fin);
                if(raiz.compareTo(fin) < 0){
                    der.recogerIntervalo(lista, ini, fin);
                }
            }else{
                der.recogerIntervalo(lista, ini, fin);
            }
        }
    }

    private boolean cumpleIntervalo(T x, T y){
        return x.compareTo(raiz) <= 0 && y.compareTo(raiz) >= 0; 
    }

    //Camino  creo de raiz a dato
    public Lista<T> caminoA(T dato){
        Lista<T> res = new ListaSE();
        caminoA(dato, res);
        return res;
    }

    private boolean caminoA(T dato, Lista<T> res){
        boolean resu = false;
        if(!esVacia()){
            if(raiz.equals(dato)){
                res.insertar(raiz);
                resu = true;
            }else{
                resu = izq.caminoA(dato, res);
                if(!resu){
                    resu = der.caminoA(dato, res);
                }
                if(resu){
                    res.insertar(raiz);
                }
            }
        }
        return resu;
    }
    
    //Balancear una arbol
        public int cantidadDatos(){
        int res;
        if(esVacia()){
             res = 0;
        }else{
            res = 1+izq.cantidadDatos()+der.cantidadDatos();
        }
        return res;
    }
    
    public void balancearArbol(){
        int hi,hd;
        if(!esVacia()){
            hi = izq.cantidadDatos();
            hd = der.cantidadDatos();
            if(Math.abs(hi-hd) <= 1){
                izq.balancearArbol();
                der.balancearArbol();
            }else{
                if(hi > hd){
                    der.insertar(raiz);
                    raiz = izq.desDer().raiz;
                    izq.eliminar(raiz);
                    balancearArbol();
                }else{
                    izq.insertar(raiz);
                    raiz = der.desIzq().raiz;
                    der.eliminar(raiz);
                    balancearArbol();
                }
            }
        }
    }
    
    private ArbolBB<T> desDer(){
         ArbolBB<T> res;
         if(der.esVacia()){
             res = this;
         }else{
             res = der.desDer();
         }
         return res;
    }
    
    public ArbolBB<T> desIzq(){
        ArbolBB<T> res;
        if(izq.esVacia()){
            res = this;
        }else{
            res = izq.desIzq();
        }
        return res;
    }
}