package server;
import java.rmi.server.UnicastRemoteObject; 
import java.rmi.RemoteException;
import service.ICalculadora;
import java.rmi.registry.LocateRegistry;     
import java.rmi.registry.Registry;

/**
 *
 * @author otaviormc
 */
public class Server extends UnicastRemoteObject implements ICalculadora {
    
    public Server() throws RemoteException {
        super();
    }
    
    @Override
    public double adicao(double a, double b) throws RemoteException {
        return a + b;
    }
    
     @Override
    public double subtracao(double a, double b) throws RemoteException {
        return a - b;
    }
    
    public static void main(String[] args) {
        try {
            ICalculadora srv = new Server();
            
            Registry rg = LocateRegistry.createRegistry(PORTA);
            rg.bind((ICalculadora.NOME), srv);
            
            System.out.println("Servidor Iniciado.");
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }

   

} 
