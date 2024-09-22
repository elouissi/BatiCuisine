package Repositorie.Client;

import Config.Connection_DB;
import Domain.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientRepository implements ClientInterface {
    private Connection conn;
    public ClientRepository() {
         this.conn = Connection_DB.getInstance().Connect_to_DB();
    }



    @Override
    public Client add(Client client) throws SQLException {
        try {
            String query = "INSERT INTO clients (nom, adresse, telephone, estProfessionnel) VALUES (?, ?, ?, ?)";
             PreparedStatement pstmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, client.getNom());
            pstmt.setString(2, client.getAdresse());
            pstmt.setString(3, client.getTelephone());
            pstmt.setBoolean(4, client.isEstProfessionnel());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                 ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    client.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("L'ajout du client a échoué, aucun ID généré.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return client;
    }
    @Override
    public Client update(Client client, String nom) {
        Client updatedClient = null;
        try {
            String updateQuery = "UPDATE clients SET nom = ?, adresse = ?, telephone= ?,estProfessionnel=? WHERE nom = ?";
            PreparedStatement updatePstmt = conn.prepareStatement(updateQuery);
            updatePstmt.setString(1, client.getNom());
            updatePstmt.setString(2, client.getAdresse());
            updatePstmt.setString(3, client.getTelephone());
            updatePstmt.setBoolean(4, client.estProfessionnel);
            updatePstmt.setString(5, nom);
            updatePstmt.executeUpdate();
            String selectQuery = "SELECT * FROM clients WHERE nom = ?";
            PreparedStatement selectPstmt = conn.prepareStatement(selectQuery);
            selectPstmt.setString(1, nom);
            ResultSet rs = selectPstmt.executeQuery();

            if (rs.next()) {
                updatedClient = new Client(rs.getString("nom"), rs.getString("adresse"), rs.getString("telephone"), rs.getBoolean("estProfessionnel"));
            }

        } catch (Exception e) {
            System.out.println("Error updating user: " + e);
        }
        return updatedClient;
    }

    @Override
    public void delete(String nom) {
         try {
                String deleteQuery = "DELETE FROM clients WHERE nom = ?";
                PreparedStatement deletePstmt = conn.prepareStatement(deleteQuery);
                deletePstmt.setString(1, nom);
                deletePstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error deleting client: " + e);
        }
     }

    @Override
    public List<Client> getAll() {
        List<Client> clients = new ArrayList<>();
        try {
            String query = "SELECT * FROM clients";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                clients.add(new Client(rs.getInt("id"),rs.getString("nom"), rs.getString("adresse"), rs.getString("telephone"), rs.getBoolean("estProfessionnel")));
            }
            return clients;

        } catch (Exception e) {
            System.out.println("Error getting clients: " + e);
        }
        return clients;    }

    @Override
    public Optional<Client> getById(int id) {
        Client client = null;
        try {
            String query = "SELECT * FROM clients WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                client = new Client(rs.getString("nom"), rs.getString("adresse"), rs.getString("telephone"), rs.getBoolean("estProfessionnel"));
            }
        } catch (Exception e) {
            System.out.println("Error getting user: " + e);
        }
        return Optional.ofNullable(client);
     }
    public Optional<Client> getByNom(String nom) {
        Client client = null;
        try {
            String query = "SELECT * FROM clients WHERE nom = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nom);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                 int id = rs.getInt("id");
                String adresse = rs.getString("adresse");
                String telephone = rs.getString("telephone");
                boolean estProfessionnel = rs.getBoolean("estProfessionnel");

                client = new Client(id, nom, adresse, telephone, estProfessionnel);
            }
        } catch (Exception e) {
            System.out.println("Error getting client by name: " + e.getMessage());
        }
        return Optional.ofNullable(client);
    }


    @Override
    public boolean checkIfExist(int id) {
        boolean exists = false;
        try {
            String query = "SELECT 1 FROM clients WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            exists = rs.next();
        } catch (SQLException e) {
            System.out.println("Error checking if client exists: " + e);
        }
        return exists;
    }
    public boolean isNomExist(String nom) throws SQLException {
        String query = "SELECT COUNT(*) FROM clients WHERE nom = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, nom);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
        return false;
    }



}
