package Repositorie.Project;

import Domain.Client;
import Domain.Project;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ProjectInterface {

        public Project add(Project project, Client client) throws SQLException;
        public Project update(Project project,String nom);
        public void delete(String nom);
        public List<Project> getAll();
        public Optional<Project> getById(int id);
        public boolean checkIfExist(int id);




    }

