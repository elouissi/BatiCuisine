package Service;

import Repositorie.Client.ClientRepository;

import java.util.HashMap;
import java.util.Scanner;

public class ClientService {
    private Scanner scanner;

    ClientRepository userRepositoy = new ClientRepository();
    public ClientService(Scanner scanner) {
         this.scanner = scanner;
    }

}
