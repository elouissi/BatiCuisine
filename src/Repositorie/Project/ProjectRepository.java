package Repositorie.Project;

import Config.Connection_DB;
import Domain.Client;
import Domain.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import Enum.EtatProjet;

public class ProjectRepository implements ProjectInterface {
    private Connection conn;
    public ProjectRepository() {
        this.conn = Connection_DB.getInstance().Connect_to_DB();
    }


    @Override
    public Project add(Project project, Client client) throws SQLException {
        try {
            String query = "INSERT INTO projects (nomproject, margebeneficiaire, couttotal, etatproject, clientid) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, project.getNomProjet());
            pstmt.setDouble(2, project.getMargeBeneficiaire());
            pstmt.setDouble(3, project.getCoutTotal());
            pstmt.setObject(4, project.getEtatProjet(), java.sql.Types.OTHER);
            pstmt.setInt(5,client.getId());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0){
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    project.id = generatedKeys.getInt(1);
                }
                client.addProjectToList(project);


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return project;
    }

    @Override
    public Project update(Project project, String nom) {
        try {
            String query = "UPDATE projects SET nomproject = ?, margebeneficiaire = ?, couttotal = ?, etatproject = ? WHERE nomproject = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, project.getNomProjet());
            pstmt.setDouble(2, project.getMargeBeneficiaire());
            pstmt.setDouble(3, project.getCoutTotal());
            pstmt.setObject(4, project.getEtatProjet(), java.sql.Types.OTHER);
            pstmt.setString(5, nom);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                return project;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void delete(String nom)     {
        try {
            String query = "DELETE FROM projects WHERE nomproject = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nom);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("Le projet avec le nom " + nom + " n'existe pas.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Project> getAll()     {
        List<Project> projects = new ArrayList<>();
        try {
            String query = "SELECT * FROM projects";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Project project = new Project();
                project.setId(rs.getInt("id"));
                project.setNomProjet(rs.getString("nomproject"));
                project.setMargeBeneficiaire(rs.getDouble("margebeneficiaire"));
                project.setCoutTotal(rs.getDouble("couttotal"));
                project.setEtatProjet(EtatProjet.valueOf(rs.getString("etatproject")));
                 projects.add(project);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return projects;
    }

    @Override
    public Optional<Project> getById(int id)  {
        Project project = null;
        try {
            String query = "SELECT * FROM projects WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                project = new Project();
                project.setId(rs.getInt("id"));
                project.setNomProjet(rs.getString("nomproject"));
                project.setMargeBeneficiaire(rs.getDouble("margebeneficiaire"));
                project.setCoutTotal(rs.getDouble("couttotal"));
                project.setEtatProjet(EtatProjet.valueOf(rs.getString("etatproject")));
             }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(project);
    }


    @Override
    public boolean checkIfExist(int id) {
        return false;
    }
}
