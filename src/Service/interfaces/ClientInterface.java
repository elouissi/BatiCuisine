package Service.interfaces;

import Domain.Client;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ClientInterface {
    Client saveClient(Client client) throws SQLException;

    Optional<Client> getClient(String nom) throws SQLException;

    Client updateClient(Client client, String name) throws SQLException;

    void deleteClient(String nom);

    List<Client> getAll();
}
