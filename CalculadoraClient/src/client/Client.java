package client;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import service.ICalculadora;

/**
 *
 * @author otaviormc
 */
public class Client {
    public static void main(String[] args) {
        
        try {
            Registry srv = LocateRegistry.getRegistry(
                    ICalculadora.URL, ICalculadora.PORTA
            );
            
            // Descoberta dos métodos Disponíveis
            ICalculadora op = (ICalculadora) srv.lookup(ICalculadora.NOME);
            
            System.out.println("Soma: " + op.adicao(10,20));
            System.out.println("Subtracao: " + op.subtracao(20, 10));
        } catch (Exception e) {
             System.out.println("ERRO: " + e.getMessage());
        }
    }
}
