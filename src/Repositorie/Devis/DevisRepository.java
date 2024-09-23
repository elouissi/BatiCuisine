package Repositorie.Devis;

import Config.Connection_DB;
import Domain.Client;
import Domain.Devis;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class DevisRepository implements DevisInterface{
    private Connection conn;
    public DevisRepository() {
       this.conn =  Connection_DB.getInstance().Connect_to_DB();
    }
    @Override
    public Devis add(Devis devis, int id) throws SQLException {
        try {
            String query = "INSERT INTO devis (montantestimate, dateemission, datevalidite,accepte, projectid) VALUES (?, ?, ?, ?,?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setDouble(1, devis.getMontantEstime());
            pstmt.setDate(2, Date.valueOf(devis.dateEmission));
            pstmt.setDate(3, Date.valueOf(devis.getDateValidite()));
            pstmt.setBoolean(4, devis.isAccepte());
            pstmt.setInt(5, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    devis.id = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return devis;
    }

    @Override
    public Devis update(Devis devis, int id) {
        Devis updatedDevis = null;
        try {
            String updateQuery = "UPDATE devis SET montantestimate = ?, dateemission = ?, datevalidite= ?,accepte=? WHERE id = ?";
            PreparedStatement updatePstmt = conn.prepareStatement(updateQuery);
            updatePstmt.setDouble(1, devis.getMontantEstime());
            updatePstmt.setDate(2, Date.valueOf(devis.getDateEmission()));
            updatePstmt.setDate(3, Date.valueOf(devis.getDateValidite()));
            updatePstmt.setBoolean(4, devis.isAccepte());
            updatePstmt.setInt(5, id);
            updatePstmt.executeUpdate();
            String selectQuery = "SELECT * FROM devis WHERE id = ?";
            PreparedStatement selectPstmt = conn.prepareStatement(selectQuery);
            selectPstmt.setInt(1, id);
            ResultSet rs = selectPstmt.executeQuery();

            if (rs.next()) {
                updatedDevis = new Devis(rs.getDouble("montantestimate"), rs.getDate("dateemission").toLocalDate(), rs.getDate("datevalidite").toLocalDate(), rs.getBoolean("accepte"));
            }

        } catch (Exception e) {
            System.out.println("Error updating user: " + e);
        }
        return updatedDevis;    }

    @Override
    public void delete(String nom) {

    }

    @Override
    public List<Devis> getAll() {
        return null;
    }

    @Override
    public Optional<Devis> getById(int id) {
        return Optional.empty();
    }

    @Override
    public boolean checkIfExist(int id) {
        return false;
    }
}
