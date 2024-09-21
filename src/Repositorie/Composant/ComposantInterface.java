package Repositorie.Composant;

import Domain.Composant;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ComposantInterface {

    public Composant add(Composant composant,int id) throws SQLException;
    public Composant update(Composant composant,String nom);
    public void delete(String nom);
    public List<Composant> getAll();
    public Optional<Composant> getById(int id);
    public boolean checkIfExist(int id);


}
