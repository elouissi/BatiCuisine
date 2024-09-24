package Service;

 import Domain.Client;
 import Domain.Project;
 import Repositorie.Project.ProjectRepository;
 import com.sun.source.tree.BreakTree;

 import java.sql.SQLException;
 import java.util.List;
 import java.util.Optional;
 import java.util.Scanner;

public class ProjectService {
    private Scanner scanner;

    ProjectRepository projectRepository = new ProjectRepository();

    public Project saveProject(Project project, Client client) throws SQLException {
       return projectRepository.add(project,client);
    }
    public Project updateProject(Project project){
        return projectRepository.update(project);
    }
    public List<Project> getAll(){
        return projectRepository.getAll();
    }
    public Optional<Project> searchByProject(int id){
        return projectRepository.getById(id);
    }

}
