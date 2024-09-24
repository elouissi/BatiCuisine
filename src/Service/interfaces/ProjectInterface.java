package Service.interfaces;

import Domain.Client;
import Domain.Project;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ProjectInterface {
    Project saveProject(Project project, Client client) throws SQLException;

    Project updateProject(Project project);

    List<Project> getAll();

    Optional<Project> searchByProject(int id);
}
