package Repositorie.Devis;

import Domain.Devis;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DevisInterface {
    public Devis add(Devis devis, int id) throws SQLException;
    public Devis update(Devis devis,int id);
    public void delete(String nom);
    public List<Devis> getAll();
    public Optional<Devis> getById(int id);
    public boolean checkIfExist(int id);
}
