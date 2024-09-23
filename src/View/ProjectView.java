package View;

import Domain.Client;
import Domain.Devis;
import Domain.Project;
import Enum.EtatProjet;
import Service.ProjectService;

import java.sql.SQLException;
import java.util.Scanner;

public class ProjectView {
    Scanner scanner = new Scanner(System.in);
    ProjectService projectService = new ProjectService();
    ComposantView composantView = new ComposantView();
    DevisView devisView = new DevisView();
    public void saveProject( Client client) throws SQLException {
        System.out.println("Entrer le nom du projet :");
        String nom = scanner.nextLine();
        System.out.println("Choisir l'état du projet (Terminé, En cours, Annulé) :");
        Project project = new Project(nom, 0.0, 0.0, EtatProjet.En_cours);
        System.out.println(client.id);
        Project savedProject = projectService.saveProject(project, client);
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
        System.out.println("--- Calcul du coût total ---");
        Double materiauxTotale = composantView.calculerCoutTotalMateriaux(savedProject.getId());
        Double main_oeuvreTotale = composantView.calculeCoutTotaleMain_oeuvre(savedProject.getId());
        Double coutTotale = materiauxTotale + main_oeuvreTotale;

        System.out.println("Souhaitez-vous appliquer une TVA au projet ? (y/n) : ");
        String appliquerTVA = scanner.nextLine();
        double tauxTVA = 0.0;
        if (appliquerTVA.equalsIgnoreCase("y")) {
            System.out.println("Entrez le pourcentage de TVA (%) : ");
            tauxTVA = scanner.nextDouble();
            scanner.nextLine();
        }
        double margeBenef = 0.0;
        System.out.println("Souhaitez-vous appliquer une marge bénéficiaire au projet ? (y/n) : ");
        String appliquerMarge = scanner.nextLine();
        if (appliquerMarge.equalsIgnoreCase("y")) {
            System.out.println("Entrez le pourcentage de marge bénéficiaire (%) : ");
            margeBenef = scanner.nextDouble();
            scanner.nextLine();
        }
        double remise = 0.0 ;
        if (client.estProfessionnel){
            System.out.println("ce client est professionel est ce que vous voulez appliquer une remise(o/n)");
            String choix2 = scanner.nextLine();
            if (choix2.equalsIgnoreCase("o")){
                System.out.println("Entrez le remise (%) : ");
                remise = scanner.nextDouble();
                scanner.nextLine();
                System.out.println("- la marge beneficaire avant la remise : " + margeBenef + " %");
                System.out.println("- la marge beneficaire avant la remise : " + (margeBenef - remise)  + " €");
            }

        }
        System.out.println("souhaitez-vous appliquer une");
        double totalAvecTVA = coutTotale + (coutTotale * tauxTVA / 100);
        double marge = totalAvecTVA * (margeBenef / 100);
        double coutFinal = totalAvecTVA + marge;
        System.out.println("--- Résultat du Calcul ---");
        System.out.println("Nom du projet : " + savedProject.getNomProjet());
        System.out.println("Client : " + client.getNom());
        System.out.println("--- Détail des Coûts ---");
        System.out.println("1. Matériaux :");
        System.out.println("- Coût total des matériaux avant TVA : " + materiauxTotale + " €");
        System.out.println("- Coût total des matériaux avec TVA : " + (materiauxTotale + (materiauxTotale * tauxTVA / 100)) + " €");
        System.out.println("2. Main-d'œuvre :");
        System.out.println("- Coût total de la main-d'œuvre avant TVA : " + main_oeuvreTotale + " €");
        System.out.println("- Coût total de la main-d'œuvre avec TVA : " + (main_oeuvreTotale + (main_oeuvreTotale * tauxTVA / 100)) + " €");
        System.out.println("3. Coût total avant marge : " + totalAvecTVA + " €");
        System.out.println("4. Marge bénéficiaire (" + margeBenef + "%) : " + marge + " €");
        System.out.println("Coût total final du projet : " + coutFinal + " €");
        Devis devis = devisView.saveDevis(coutFinal,savedProject.getId());
        System.out.println("voulez-vous accepter le devis ? (y/n) :");
        String choix2 = scanner.nextLine();
        if (choix2.equalsIgnoreCase("y")){
            devisView.updateAccepte(devis);
        }


    }
}
