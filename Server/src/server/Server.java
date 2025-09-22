package server;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import service.ISlotMachineService;
import java.util.*;

/**
 *
 * @author otaviormc
 */
public class Server extends UnicastRemoteObject implements ISlotMachineService {
        
    private Map<String, Double> balances = new HashMap<>();
    private Random random = new Random();
    
    public Server() throws RemoteException{
        super();
    }
    
    @Override
    public String connect(String name) throws RemoteException{
        String sessionId = UUID.randomUUID().toString();
        balances.put(sessionId, 100.0);
        return sessionId;
    }
    
    @Override 
    public double getBalance(String sessionId) throws RemoteException{
        return balances.getOrDefault(sessionId, 0.0);
    }
    
    @Override
    public String play(String sessionId, double bet) throws RemoteException{
        double balance = balances.getOrDefault(sessionId, 0.0);
        if (balance < bet) return "Saldo Insuficiente!";
        
        balance = balance - bet;
        
        String[] symbols = {"X", "7", "A"};
        String r1 = symbols[random.nextInt(symbols.length)];
        String r2 = symbols[random.nextInt(symbols.length)];
        String r3 = symbols[random.nextInt(symbols.length)];
        
        // Logica de premiacao
        double prize = (r1.equals(r2) && r2.equals(r3) ? bet * 5: 0);
        balance = balance + prize;
        balances.put(sessionId, balance);
        
        return String.format("%s | %s | %s -> Ganhou %.2f (Saldo: %.2f)", r1,r2,r3,prize, balance );
    }
    
    public static void main(String[] args) {
        try {
            Server srv = new Server();
            Registry rg = LocateRegistry.createRegistry(RMI_PORT);
            rg.bind((ISlotMachineService.RMI_SERVICE_NAME), srv);
            System.out.println("Servidor Iniciado.");
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }
    
}
