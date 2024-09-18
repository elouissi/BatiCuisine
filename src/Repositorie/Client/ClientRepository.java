package Repositorie.Client;

import Config.Connection_DB;
import Domain.Client;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class ClientRepository implements ClientInterface {
    private Connection conn;
    public void ClientRepositoy() {

        conn = Connection_DB.getInstance().Connect_to_DB("GreenPulse","GreenPulse","");
    }


    @Override
    public Client add(Client client) {
        return null;
    }

    @Override
    public Client update(Client client, int id) {
        return null;
    }

    @Override
    public Client delete(int id) {
        return null;
    }

    @Override
    public List<Client> getAll() {
        return null;
    }

    @Override
    public Optional<Client> getById(int id) {
        return Optional.empty();
    }

    @Override
    public boolean checkIfExist(int id) {
        return false;
    }

}
