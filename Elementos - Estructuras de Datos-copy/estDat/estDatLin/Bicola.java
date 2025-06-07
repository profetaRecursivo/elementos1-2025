package estDat.estDatLin;
public class Bicola<T> extends Cola<T>{
    public T salir(){
        T res;
        if(!estaVacia()){
            res = fin.getDato();
            fin = fin.getAnt();
            if(fin == null){
                ini = null;
            }else{
                fin.setSig(null);
            }
        }else{
            res = null;
        }
        return res;
    }
    
    public void meter(T nDato){
        if(estaVacia()){
            NodoDE<T> p = new NodoDE<T>();
            p.setDato(nDato);
            ini = p;
            fin = p;
        }else{
            NodoDE<T> p = new NodoDE<T>();
            p.setDato(nDato);
            ini.setAnt(p);
            p.setSig(ini);
            ini = p;
        }
    }
}