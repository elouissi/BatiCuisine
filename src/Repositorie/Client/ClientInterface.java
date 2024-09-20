package Repositorie.Client;

import Domain.Client;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ClientInterface {


    public Client add(Client client) throws SQLException;
    public Client update(Client client,String nom);
    public void delete(String nom);
    public List<Client> getAll();
    public Optional<Client> getById(int id);
    public boolean checkIfExist(int id);




}
