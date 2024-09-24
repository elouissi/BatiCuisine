package Service;

import Domain.Client;
import Repositorie.Client.ClientRepository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ClientService implements Service.interfaces.ClientInterface {
    private Scanner scanner;

    ClientRepository clientRepository = new ClientRepository();


    @Override
    public Client saveClient(Client client) throws SQLException {
        if (!clientRepository.isNomExist(client.nom)){return clientRepository.add(client);}
        return null;
    }
    @Override
    public Optional<Client> getClient(String nom) throws SQLException{
            return clientRepository.getByNom(nom);
    }
    @Override
    public Client updateClient(Client client, String name) throws SQLException {
        Optional<Client> cl = getClient(client.nom);
        if(cl.isPresent())return null;else return clientRepository.update(client , name);
    }
    @Override
    public void deleteClient(String nom){
        clientRepository.delete(nom);
    }
    @Override
    public List<Client> getAll(){
        return clientRepository.getAll();
    }



}
