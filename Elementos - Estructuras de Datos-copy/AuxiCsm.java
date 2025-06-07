import estDat.estDatLin.*;
public class AuxiCsm{
    public boolean AutitosOrdenados(Cola<Integer> cola){
        Pila<Integer> abajo,derecha;
        abajo = new Pila<Integer>();
        derecha = new Pila<Integer>();
        boolean sePuede = true;
        if(!cola.estaVacia()){
            Integer dato = cola.decolar();
            while(!cola.estaVacia() && sePuede){
                if(dato > cola.top()){
                    if(!abajo.estaVacia()){
                        if(dato > abajo.top()){
                            sePuede = false;
                        }else{
                            abajo.push(dato);
                        }
                    }else{
                        abajo.push(dato);
                    }
                }else{
                    if(!derecha.estaVacia()){
                        if(dato > derecha.top()){
                            derecha.push(dato);
                        }else{
                            derecha.push(dato);
                            sePuede = false;
                        }
                    }else{
                        derecha.push(dato);
                    }
                }
                dato = cola.decolar();
            }

            if(!abajo.estaVacia()){
                if(dato > abajo.top()){
                    sePuede = false;
                }
            }

            if(!derecha.estaVacia()){
               if(dato < derecha.top()){
                  sePuede = false;
                }
            }
        }
        return sePuede;
    } 
}