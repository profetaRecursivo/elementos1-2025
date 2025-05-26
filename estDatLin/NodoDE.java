package estDat.estDatLin;
public class NodoDE<T>{
    private NodoDE<T> ant;
    private T dato;
    private NodoDE<T> sig;
    
    public NodoDE(){
        ant = null;
        dato = null;
        sig = null;
    }
    
    public NodoDE<T> getAnt(){
        return ant;
    }
    
    public T getDato(){
        return dato;
    }
    
    public NodoDE<T> getSig(){
        return sig;
    }
    
    public void setAnt(NodoDE<T> nAnt){
        ant = nAnt;
    }
    
    public void setDato(T nDato){
        dato = nDato;
    }
    
    public void setSig(NodoDE<T> nSig){
        sig = nSig;
    }
}