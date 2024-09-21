package Repositorie.Materiaux;

import Config.Connection_DB;
import Domain.Materiaux;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MateriauxRepository implements MateriauxInterface {
    private Connection conn;

    public MateriauxRepository() {
        this.conn = Connection_DB.getInstance().Connect_to_DB();
    }

    @Override
    public Materiaux add(Materiaux materiaux,int id)  {
        try {
            String query = "INSERT INTO materiaux (nom,typecomposant,tauxtva,project_id,coutUnitaire, quantite, coutTransport,coefficientqualite) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
             pstmt.setString(1, materiaux.getName());
            pstmt.setString(2, materiaux.getTypeComposant());
            pstmt.setDouble(3, materiaux.getTauxTVA());
            pstmt.setInt(4, id);
            pstmt.setDouble(5, materiaux.getCoutUnitaire());
            pstmt.setDouble(6, materiaux.getQuantite());
            pstmt.setDouble(7, materiaux.getCoutTransport());
            pstmt.setDouble(8,materiaux.getCoefficientQualite());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    materiaux.id = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return materiaux;
    }

    @Override
    public Materiaux update(Materiaux materiaux, String nom)  {
        try {
            String query = "UPDATE materiaux SET coutUnitaire = ?, quantite = ?, coutTransport = ?, coefficientqualite = ? WHERE nom = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setDouble(1, materiaux.getCoutUnitaire());
            pstmt.setDouble(2, materiaux.getQuantite());
            pstmt.setDouble(3, materiaux.getCoutTransport());
            pstmt.setDouble(4,materiaux.getCoefficientQualite());
            pstmt.setString(5, nom);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                return materiaux;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void delete(String nom)  {
        try {
            String query = "DELETE FROM materiaux WHERE nom = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nom);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Materiaux> getAll()  {
        List<Materiaux> materiauxList = new ArrayList<>();
        try {
            String query = "SELECT * FROM materiaux";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Materiaux materiaux = createMateriauxFromResultSet(rs);
                materiauxList.add(materiaux);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return materiauxList;
    }

    @Override
    public Optional<Materiaux> getById(int id)  {
        Materiaux materiaux = null;
        try {
            String query = "SELECT * FROM materiaux WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                materiaux = createMateriauxFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(materiaux);
    }

    @Override
    public boolean checkIfExist(int id) {
        try {
            String query = "SELECT 1 FROM materiaux WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Materiaux createMateriauxFromResultSet(ResultSet rs) throws SQLException {
        Materiaux materiaux = new Materiaux();
        materiaux.setId(rs.getInt("id"));
        materiaux.setCoutUnitaire(rs.getDouble("coutUnitaire"));
        materiaux.setQuantite(rs.getDouble("quantite"));
        materiaux.setCoutTransport(rs.getDouble("coutTransport"));
        materiaux.setCoefficientQualite(rs.getDouble("coefficientQualite"));
         return materiaux;
    }
}
