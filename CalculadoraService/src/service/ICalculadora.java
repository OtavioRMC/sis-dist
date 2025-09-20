package service;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author otaviormc
 */
public interface ICalculadora extends Remote  {
    
    public static final int PORTA = 1099;
    public static final String URL = "127.0.0.1";
    public final String NOME = "Calculadora";
    
    // Métodos 
    public abstract double adicao(double a, double b) throws RemoteException;
    public abstract double subtracao(double a, double b) throws RemoteException;
   
    
}


