package service;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author otaviormc
 */
public interface ISlotMachineService extends Remote {
    public static final int RMI_PORT = 1099;
    public static final String RMI_URL = "127.0.0.1";    
    public static String RMI_SERVICE_NAME = "SlotMachineService";
    
    String connect(String username) throws RemoteException;
    String play(String sessionId, double bet) throws RemoteException;
    double getBalance(String sessionId) throws RemoteException;
    
}
