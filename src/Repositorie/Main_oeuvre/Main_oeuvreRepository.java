package Repositorie.Main_oeuvre;

import Config.Connection_DB;
import Domain.Main_oeuvre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class Main_oeuvreRepository implements Main_oeuvreInterface{
    private Connection conn;

    public Main_oeuvreRepository() {
        this.conn = Connection_DB.getInstance().Connect_to_DB();
    }
    @Override
    public Main_oeuvre add(Main_oeuvre mainOeuvre ,int id) throws SQLException {
        try {
        String query = "INSERT INTO main_oeuvre (nom,typecomposant,tauxtva,project_id,tauxhoraire, heurestravail, productiviteouvrier) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, mainOeuvre.getName());
        pstmt.setString(2, mainOeuvre.getTypeComposant());
        pstmt.setDouble(3, mainOeuvre.getTauxTVA());
        pstmt.setInt(4, id);
        pstmt.setDouble(5, mainOeuvre.getTauxHoraire());
        pstmt.setDouble(6, mainOeuvre.getHeuresTravail());
        pstmt.setDouble(7, mainOeuvre.getProductiviteOuvrier());
         int affectedRows = pstmt.executeUpdate();
        if (affectedRows > 0) {
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                mainOeuvre.id = generatedKeys.getInt(1);
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
        return mainOeuvre;
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
