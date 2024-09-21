package Repositorie.Composant;

import Config.Connection_DB;
import Domain.Composant;
import Domain.Main_oeuvre;
import Domain.Materiaux;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ComposantRepository implements ComposantInterface {
    private Connection conn;

    public void ComposantRepository() {
        this.conn = Connection_DB.getInstance().Connect_to_DB();
    }

    @Override
    public Composant add(Composant composant, int id) throws SQLException {
        try {
            String query = "INSERT INTO composants (nom, typecomposant, tauxtva, project_id) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, composant.getName());
            pstmt.setString(2, composant.getTypeComposant());
            pstmt.setDouble(3, composant.getTauxTVA());
            pstmt.setInt(4, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    composant.id = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return composant;
    }

    @Override
    public Composant update(Composant composant, String nom) {
        try {
            String query = "UPDATE composants SET nom = ?, typecomposant = ?, tauxtva = ? WHERE nom = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, composant.getName());
            pstmt.setString(2, composant.getTypeComposant());
            pstmt.setDouble(3, composant.getTauxTVA());
            pstmt.setString(4, nom);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                return composant;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void delete(String nom)  {
        try {
            String query = "DELETE FROM composants WHERE nom = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nom);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Composant> getAll()  {
        List<Composant> composants = new ArrayList<>();
        try {
            String query = "SELECT * FROM composants";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                 Composant composant = createComposantFromResultSet(rs);
                composants.add(composant);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return composants;
    }

    @Override
    public Optional<Composant> getById(int id) {
        Composant composant = null;
        try {
            String query = "SELECT * FROM composants WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                composant = createComposantFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(composant);
    }

    @Override
    public boolean checkIfExist(int id)  {
        try {
            String query = "SELECT 1 FROM composants WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Composant createComposantFromResultSet(ResultSet rs) throws SQLException {
        String typeComposant = rs.getString("typecomposant");

        if (typeComposant.equals("Materiaux")) {
            Materiaux materiaux = new Materiaux();
            materiaux.setName(rs.getString("nom"));
            materiaux.setTauxTVA(rs.getDouble("tauxtva"));
             return materiaux;
        } else if (typeComposant.equals("MainOeuvre")) {
            Main_oeuvre mainOeuvre = new Main_oeuvre();
            mainOeuvre.setName(rs.getString("nom"));
            mainOeuvre.setTauxTVA(rs.getDouble("tauxtva"));
             return mainOeuvre;
        } else {
            throw new RuntimeException("Type de composant inconnu: " + typeComposant);
        }
    }
}
