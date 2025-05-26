package estDat;
import estDat.estDatLin.*;

public class Secuencia
{
    public Pila<Integer> fusionar(Lista<Integer> secuencia){
        Pila<Integer> pila = new Pila<Integer>();
        int n = secuencia.getSize();
        for(int i = 0; i<n; i++){
            int fusion = secuencia.acceder(i);
            while(!pila.estaVacia() && fusion == pila.top()){
                fusion = fusion + pila.top();
                pila.pop();
            }
            pila.push(fusion);
        }
        //el resultado sale al reves, por que es una pila, pueden voltearlo y devolver una lista :)
        return pila;
    }
}
