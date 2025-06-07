package estDat.estDatNoLin;
import estDat.estDatLin.*;
public class ArbolGen
{
    private char dato;
    private ArbolGen prim, sec, ter, cuarto;
    
    public ArbolGen(){
        dato = ' ';
        prim = sec = ter = cuarto = null;
    }
    
    public boolean vacio(){
        return dato == ' ';
    }
    
    public void eliminar(){
        Lista<Character> lista = new ListaDE<Character>();
        eliminar(lista);
    }
    
    public boolean eliminar(Lista<Character> lista){
        boolean res;
        if(!vacio()){
            if(losUltimossonATA(lista)){
                res = true;
            }else{
                lista.insertar(dato);
                boolean a = prim.eliminar(lista);
                if(a){
                    prim = new ArbolGen();
                }
                boolean b = sec.eliminar(lista);
                if(b){
                    sec = new ArbolGen();
                }
                boolean c = sec.eliminar(lista);
                if(c){
                    sec = new ArbolGen();
                }
                boolean d = sec.eliminar(lista);
                if(d){
                    sec = new ArbolGen();
                }
                res = a && b && c && d;
                if(esHoja()){
                    res = false;
                }
                lista.eliminarUltimo();
            }
        }else{
            res = true;
        }
        return res;
    }
}
