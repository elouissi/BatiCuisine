package Repositorie.Main_oeuvre;

import Domain.Main_oeuvre;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class Main_oeuvreRepository implements Main_oeuvreInterface{
    @Override
    public Main_oeuvre add(Main_oeuvre mainOeuvre) throws SQLException {
        return null;
    }

    @Override
    public Main_oeuvre update(Main_oeuvre mainOeuvre, String nom) {
        return null;
    }

    @Override
    public void delete(String nom) {

    }

    @Override
    public List<Main_oeuvre> getAll() {
        return null;
    }

    @Override
    public Optional<Main_oeuvre> getById(int id) {
        return Optional.empty();
    }

    @Override
    public boolean checkIfExist(int id) {
        return false;
    }
}
