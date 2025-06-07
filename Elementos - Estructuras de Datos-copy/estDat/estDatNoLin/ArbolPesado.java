package estDat.estDatNoLin;
import estDat.estDatLin.*;
public class ArbolPesado<T extends Comparable <T>>{
    private T raiz;
    private ArbolPesado<T> izq, der;

    public ArbolPesado(){
        raiz = null;
        der = izq = null;
    }

    public boolean esVacia(){
        return (raiz == null);
    }

    public void insertarAmplitud(T nDato){
        Cola<ArbolPesado<T>> espera = new Cola<ArbolPesado<T>>();
        espera.encolar(this);
        insertarAmplitud(nDato, espera);
        modificar();
    }

    private void insertarAmplitud(T nDato, Cola<ArbolPesado<T>> espera){
        ArbolPesado<T> arb;
        arb = espera.decolar();
        if(arb.esVacia()){
            arb.raiz = nDato;
            arb.izq = new ArbolPesado<T>();
            arb.der = new ArbolPesado<T>();
        }else{
            espera.encolar(arb.izq);
            espera.encolar(arb.der);
            insertarAmplitud(nDato, espera);
        }
    }

    private boolean raizHoja(){
        boolean res;
        res = (izq.esVacia()) && (der.esVacia());
        return res;
    }

    private void modificar(){
        if(!esVacia()){
            if(!raizHoja()){
                izq.modificar();
                der.modificar();
                this.ordenar();
            }
        }
    }

    private void ordenar(){
        T aux;
        if(der.esVacia()){
            if(izq.raiz.compareTo(raiz) > 0){
                aux = izq.raiz;
                izq.raiz = raiz;
                raiz = aux;
            }
        }else{
            if(der.raiz.compareTo(raiz) > 0){
                aux = der.raiz;
                der.raiz = raiz;
                raiz = aux;
            }else{
                if(izq.raiz.compareTo(raiz) > 0){
                    aux = izq.raiz;
                    izq.raiz = raiz;
                    raiz = aux;
                }
            }
        }
    }

    public String recorrerPreorden(){
        String res;
        res = recorrerPreorden(this);
        return res;
    }

    private String recorrerPreorden(ArbolPesado<T> actual){
        String res;
        if(!actual.esVacia()){
            res = "" + actual.raiz + " " + recorrerPreorden(actual.izq) 
            + recorrerPreorden(actual.der);
        }else{
            res = "";
        }
        return res;
    }

    public void modificar2(){
        if(!esVacia()){
            if(!raizHoja()){
                izq.modificar();
                der.modificar();
                this.ordenarPosible();
            }
        }
    }

    private void ordenarPosible(){
        T aux;
        if(der.esVacia()){
            if(izq.raiz.compareTo(raiz) > 0){
                aux = izq.raiz;
                izq.raiz = raiz;
                raiz = aux;
            }
        }else{
            if(der.raiz.compareTo(raiz) > 0){
                aux = der.raiz;
                der.raiz = raiz;
                raiz = aux;
            }else{
                if(izq.raiz.compareTo(raiz) > 0){
                    aux = izq.raiz;
                    izq.raiz = raiz;
                    raiz = aux;
                }
            }
        }
    }
}
