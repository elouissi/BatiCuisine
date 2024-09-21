package Repositorie.Materiaux;

import Domain.Materiaux;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface MateriauxInterface {
    public Materiaux add(Materiaux materiaux) throws SQLException;
    public Materiaux update(Materiaux materiaux,String nom);
    public void delete(String nom);
    public List<Materiaux> getAll();
    public Optional<Materiaux> getById(int id);
    public boolean checkIfExist(int id);
}
