package View;

import Domain.Project;
import Enum.EtatProjet;
import Service.ProjectService;

import java.sql.SQLException;
import java.util.Scanner;

public class ProjectView {
    Scanner scanner = new Scanner(System.in);
    ProjectService projectService = new ProjectService();
    ComposantView composantView = new ComposantView();
    public void saveProject(int clientId) throws SQLException {
        System.out.println("Entrer le nom du projet :");
        String nom = scanner.nextLine();
        System.out.println("Entrer la marge bénéficiaire :");
        Double margeBenif = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Choisir l'état du projet (Terminé, En cours, Annulé) :");
        EtatProjet etatProject = EtatProjet.valueOf(scanner.nextLine());
        Project project = new Project(nom, margeBenif, 0.0, etatProject);
        Project savedProject = projectService.saveProject(project, clientId);
        System.out.println("Projet ajouté avec succès : " + savedProject.getNomProjet());
        System.out.println("Voulez-vous ajouter des composants au projet ? (o/n)");
        String choix = scanner.nextLine();
         while (choix.equalsIgnoreCase("o")) {
            composantView.saveComposant(savedProject.getId());
            System.out.println("Composant ajouté avec succès.");
            System.out.println("Voulez-vous ajouter un autre composant ? (o/n)");
            choix = scanner.nextLine();
        }
        System.out.println("Tous les composants ont été ajoutés.");
    }

}
