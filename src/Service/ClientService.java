package Service;

import Domain.Client;
import Repositorie.Client.ClientRepository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;

public class ClientService {
    private Scanner scanner;

    ClientRepository clientRepository = new ClientRepository();


    public Client saveClient(Client client) throws SQLException {
        if (!clientRepository.isNomExist(client.nom)){
       return clientRepository.add(client);
        }
        return null;
    }
    public Optional<Client> getClient(String nom) throws SQLException{
            return clientRepository.getByNom(nom);
    }

}
