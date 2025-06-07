import estDat.estDatLin.*;
public class Emparejamiento
{
    public boolean emparejan(Lista<Character> secuencia){
        boolean res = true;
        Pila<Character> pila = new Pila<Character>();
        int tam = secuencia.getSize();
        for(int i = 0; i<tam; i++){
            if(secuencia.acceder(i).equals('(')){
                pila.push('(');
            }else{
                if(!pila.estaVacia()){
                    pila.pop();
                }else{
                    res = false;
                }
            }
        }
        if(!pila.estaVacia()){
            res = false;
        }
        return res;
    }
}
