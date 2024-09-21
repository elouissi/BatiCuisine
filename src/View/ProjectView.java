package View;

import Domain.Project;
import Enum.EtatProjet;
import Service.ProjectService;

import java.sql.SQLException;
import java.util.Scanner;

public class ProjectView {
    Scanner scanner = new Scanner(System.in);
    ProjectService projectService = new ProjectService();
    public void saveProject(int clientId) throws SQLException {
        System.out.println("entrer le nom du projet :");
        String nom = scanner.nextLine();
        System.out.println("entrer la marge beneficiare :");
        Double margeBenif = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("choisir l'etet du project (Terminé,En cours, Annulé)");
        EtatProjet etatProject = EtatProjet.valueOf(scanner.nextLine());
        System.out.println(etatProject);
        Project project = new Project(nom,margeBenif,0.0,etatProject);
        System.out.println(project);
        projectService.saveProject(project,clientId);
        System.out.println("voulez vous ajouter des composants(o/n)");
        String choix = scanner.nextLine();
        while (choix == "y"){


        }






    }

}
