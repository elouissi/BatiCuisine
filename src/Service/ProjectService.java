package Service;

 import Domain.Project;
 import Repositorie.Project.ProjectRepository;

 import java.sql.SQLException;
 import java.util.Scanner;

public class ProjectService {
    private Scanner scanner;

    ProjectRepository projectRepository = new ProjectRepository();

    public void saveProject(Project project, int clientId) throws SQLException {
        projectRepository.add(project,clientId);
    }
}
