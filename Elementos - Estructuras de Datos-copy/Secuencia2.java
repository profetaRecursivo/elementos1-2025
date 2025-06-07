import estDat.estDatLin.*;
public class Secuencia2
{
    public Lista<Integer> reducir(Lista<Integer> secuencia){
        Lista<Integer> res = new ListaDE<Integer>();
        int tam = secuencia.getSize();
        Pila<Integer> pila = new Pila<Integer>();
        for(int i = 0; i<tam; i++){
            int fusion = secuencia.acceder(i);
            while(!pila.estaVacia() && pila.top().equals(fusion)){
                fusion = fusion + pila.top();
                pila.pop();
            }
            pila.push(fusion);
        }
        while(!pila.estaVacia()){
            res.insertar(pila.pop());
        }
        return res;
    }
}
