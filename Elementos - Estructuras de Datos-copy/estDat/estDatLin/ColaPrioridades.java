package estDat.estDatLin;
public class ColaPrioridades<T extends Comparable <T>> extends Cola<T>{
    public void encolar(T nDato){
        NodoDE<T> p = new NodoDE<T>();
        NodoDE<T> q = new NodoDE<T>();
        p.setDato(nDato);
        if(!estaVacia()){
            q = buscar(ini, nDato);
            if(q == null){
                p.setAnt(fin);
                fin.setSig(p);
                fin = p;
            }else if(q == ini){
                p.setSig(ini);
                ini.setAnt(p);
                ini = p;
            }else{
                NodoDE<T> r = q.getAnt();
                r.setSig(p);
                p.setAnt(r);
                p.setSig(q);
                q.setAnt(p);
            }
        }else{
            ini = p;
            fin = p;
        }
    }
    
    private NodoDE<T> buscar(NodoDE<T> inicio, T nDato){
        NodoDE<T> res;
        if(inicio == null){
            res = null;
        }else{
            if(nDato.compareTo(inicio.getDato()) > 0){
                res = buscar(inicio.getSig(), nDato);
            }else{
                res = inicio;
            }
        }
        return res;
    }
}