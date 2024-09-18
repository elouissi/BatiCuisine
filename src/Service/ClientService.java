package Service;

import Domain.Client;
import Repositorie.Client.ClientRepository;

import java.util.HashMap;
import java.util.Scanner;

public class ClientService {
    private Scanner scanner;

    ClientRepository clientRepository = new ClientRepository();


    public void addClient(String nom, String adresse, String telephone,boolean estProfessionnel) {

        Client client = new Client(nom,adresse,telephone,estProfessionnel);
        clientRepository.add(client);
    }
}
