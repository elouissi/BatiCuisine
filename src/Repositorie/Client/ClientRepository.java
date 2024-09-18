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
    public void ClientRepositoy() {

        conn = Connection_DB.getInstance().Connect_to_DB("GreenPulse","GreenPulse","");
    }


    @Override
    public Client add(Client client) throws SQLException {
        try {
            String query = "INSERT INTO clients (nom,adresse,telephone,estProfessionnel) Values (?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1,client.getNom());
            pstmt.setString(2,client.getAdresse());
            pstmt.setString(3,client.getTelephone());
            pstmt.setBoolean(4, client.estProfessionnel);
            pstmt.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);

        }
        return client;

     }

    @Override
    public Client update(Client client, int id) {
        Client updatedClient = null;
        try {
            String updateQuery = "UPDATE clients SET nom = ?, adresse = ?, telephone= ?,estProfessionnel=? WHERE id = ?";
            PreparedStatement updatePstmt = conn.prepareStatement(updateQuery);
            updatePstmt.setString(1, client.getNom());
            updatePstmt.setString(2, client.getAdresse());
            updatePstmt.setString(3, client.getTelephone());
            updatePstmt.setBoolean(4, client.estProfessionnel);
            updatePstmt.setInt(5, id);
            updatePstmt.executeUpdate();
            String selectQuery = "SELECT * FROM clients WHERE id = ?";
            PreparedStatement selectPstmt = conn.prepareStatement(selectQuery);
            selectPstmt.setInt(1, id);
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
    public Client delete(int id) {
        Client client = null;
        try {
            Optional<Client> optionalUser = getById(1);
            if (optionalUser.isPresent()) {
                client = optionalUser.get();
                String deleteQuery = "DELETE FROM clients WHERE id = ?";
                PreparedStatement deletePstmt = conn.prepareStatement(deleteQuery);
                deletePstmt.setInt(1, id);
                deletePstmt.executeUpdate();
            } else {
                System.out.println("client non trouvé");
            }
        } catch (Exception e) {
            System.out.println("Error deleting user: " + e);
        }
        return client;
    }

    @Override
    public List<Client> getAll() {
        List<Client> clients = new ArrayList<>();
        try {
            String query = "SELECT * FROM clients";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                clients.add(new Client(rs.getString("nom"), rs.getString("adresse"), rs.getString("telephone"), rs.getBoolean("estProfessionnel")));
            }
            return clients;

        } catch (Exception e) {
            System.out.println("Error getting users: " + e);
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

}
