package Service;

 import Domain.Client;
 import Domain.Project;
 import Repositorie.Project.ProjectRepository;

 import java.sql.SQLException;
 import java.util.Scanner;

public class ProjectService {
    private Scanner scanner;

    ProjectRepository projectRepository = new ProjectRepository();

    public Project saveProject(Project project, Client client) throws SQLException {
       return projectRepository.add(project,client);
    }
}
