package Repositorie.Main_oeuvre;

import Domain.Main_oeuvre;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Main_oeuvreInterface {
    public Main_oeuvre add(Main_oeuvre mainOeuvre) throws SQLException;
    public Main_oeuvre update(Main_oeuvre mainOeuvre,String nom);
    public void delete(String nom);
    public List<Main_oeuvre> getAll();
    public Optional<Main_oeuvre> getById(int id);
    public boolean checkIfExist(int id);
}
