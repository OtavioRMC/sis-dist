package client;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;
import service.ISlotMachineService;
/**
 *
 * @author otaviormc
 */
public class Client {

   public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
            ISlotMachineService service = (ISlotMachineService) registry.lookup(ISlotMachineService.RMI_SERVICE_NAME);

            Scanner sc = new Scanner(System.in);
            System.out.print("Digite seu nome: ");
            String name = sc.nextLine();

            String sessionId = service.connect(name);
            System.out.println("Conectado! Saldo inicial: " + service.getBalance(sessionId));

            while (true) {
                System.out.print("Digite aposta (ou 0 para sair): ");
                double bet = sc.nextDouble();
                if (bet == 0) break;
                String result = service.play(sessionId, bet);
                System.out.println(result);
            }

            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

    
