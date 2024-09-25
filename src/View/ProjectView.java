package View;

import Domain.Client;
import Domain.Composant;
import Domain.Devis;
import Domain.Project;
import Enum.EtatProjet;
import Service.ComposantService;
import Service.ProjectService;
import Utils.CheckInput;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ProjectView {
    Scanner scanner = new Scanner(System.in);
    ProjectService projectService = new ProjectService();
    ComposantView composantView = new ComposantView();
    ComposantService composantService = new ComposantService();
    DevisView devisView = new DevisView();
    public void saveProject( Client client) throws SQLException {
        System.out.println("//==============================================================================//");
        System.out.println("//                             Création d'un Nouveau Projet                     //");
        System.out.println("//==============================================================================//");

        String nom = CheckInput.readString(" Entrez le nom du projet :");

        Project project = new Project(nom, 0.0, 0.0, EtatProjet.En_cours);

        client.addProjectToList(project);

        System.out.println(client.getListProject());

        Project savedProject = projectService.saveProject(project, client);

        System.out.println(" Projet '" + savedProject.getNomProjet() + "' ajouté avec succès !");

        String choix = CheckInput.readString("Voulez-vous ajouter des composants au projet ? (o/n)");

        while (choix.equalsIgnoreCase("o")) {
            composantView.saveComposant(savedProject.getId());
            System.out.println(" Composant ajouté avec succès.");
             choix = CheckInput.readString("Voulez-vous ajouter un autre composant ? (o/n)");
        }

        System.out.println(" Tous les composants ont été ajoutés.");
        System.out.println(" --- Calcul du coût total du projet ---");
        Double materiauxTotale = composantView.calculerCoutTotalMateriaux(savedProject.getId());
        Double main_oeuvreTotale = composantView.calculeCoutTotaleMain_oeuvre(savedProject.getId());
        Double coutTotale = materiauxTotale + main_oeuvreTotale;


        String appliquerTVA = CheckInput.readString(" Souhaitez-vous appliquer une TVA au projet ? (o/n) : ");
        double tauxTVA = 0.0;
        if (appliquerTVA.equalsIgnoreCase("o")) {
            tauxTVA = CheckInput.readDouble("Entrez le pourcentage de TVA (%) : ");
            List<Composant> composants = composantService.findComposantsByProjectId(savedProject.getId());
            composants.forEach(System.out::println);

            for (Composant composant : composants) {
                composantService.updateTvaForComposant(composant.getId(), tauxTVA);
            }
        }
        double margeBenef = 0.0;

         String appliquerMarge = CheckInput.readString(" Souhaitez-vous appliquer une marge bénéficiaire au projet ? (o/n) : ");

        if (appliquerMarge.equalsIgnoreCase("o")) {

            margeBenef = CheckInput.readDouble("Entrez le pourcentage de marge bénéficiaire (%) : ");

        }
        double remise = 0.0 ;
        if (client.estProfessionnel){

             String choix2 = CheckInput.readString(" Ce client est professionnel. Souhaitez-vous appliquer une remise ? (o/n)");

            if (choix2.equalsIgnoreCase("o")){

                remise = CheckInput.readDouble("Entrez le remise (%) : ");
                 System.out.println(" - La marge bénéficiaire avant remise : " + margeBenef + " %");
                System.out.println(" - La marge bénéficiaire après remise : " + (margeBenef - (margeBenef * remise/100)) + " €");
                margeBenef = (margeBenef - (margeBenef * remise/100));
            }

        }
         double totalAvecTVA = coutTotale + (coutTotale * tauxTVA / 100);
        double marge = totalAvecTVA * (margeBenef / 100);
        double coutFinal = totalAvecTVA + marge;

        System.out.println(" --- Résultat du Calcul ---");
        System.out.println(" Nom du projet : " + savedProject.getNomProjet());
        System.out.println(" Client : " + client.getNom());
        System.out.println(" --- Détail des Coûts ---");
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
        System.out.println(devis);
         Devis devis1 = devisView.updateAccepte(devis);
        System.out.println(devis1);
        if (!devis1.accepte){
            savedProject.setEtatProjet(EtatProjet.Annulé);

        }else {
            savedProject.setEtatProjet(EtatProjet.Terminé);

        }
        savedProject.setCoutTotal(coutFinal);
        savedProject.setMargeBeneficiaire(margeBenef);
         Project project1 = projectService.updateProject(savedProject);
        System.out.println("ID du projet à mettre à jour: " + savedProject.getId());
        System.out.println(project1);




    }
    public void getAll(){
             System.out.println("//==============================================================================//");
            System.out.println("//                        Liste de Tous les Projets Existants                    //");
            System.out.println("//==============================================================================//");

            List<Project> projects = projectService.getAll();

            if (projects.isEmpty()) {
                System.out.println(" Aucun projet n'a été trouvé.");
            } else {
                System.out.println("Nombre total de projets : " + projects.size());
                List<Project> sortedProject = projects.stream()
                        .sorted(Comparator.comparing(Project::getCoutTotal).reversed())
                        .collect(Collectors.toList());

                sortedProject.stream().forEach(project -> {
                    System.out.println("==============================================================================//");
                    System.out.println(" Nom du Projet : " + project.getNomProjet());
                    System.out.println(" Coût Total : " + project.getCoutTotal() + " €");
                    System.out.println(" État du Projet : " + project.getEtatProjet());
                    System.out.println("==============================================================================//");
                });
            }

            System.out.println(" Fin de la liste des projets.");
        }


    public void searchProject() {
        System.out.println("//=======================================//");
        System.out.println("//      Recherche de Projet par ID        //");
        System.out.println("//=======================================//");

        System.out.print("→ Veuillez entrer l'ID du projet : ");
        int idProject = scanner.nextInt();
        scanner.nextLine();

        Optional<Project> projectOptional = projectService.searchByProject(idProject);
        System.out.println();

        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();

            System.out.println("//==============================================================================//");
            System.out.println("//                            --- Détails du Projet ---                         //");
            System.out.println("//==============================================================================//");
            System.out.println("   Nom du projet         : " + project.getNomProjet());
            System.out.println("   Marge bénéficiaire    : " + project.getMargeBeneficiaire() + " %");
            System.out.println("   Coût total            : " + project.getCoutTotal() + " €");
            System.out.println("   État du projet        : " + project.getEtatProjet());
            System.out.println("==============================================================================//");
        } else {
            System.out.println("//=======================================//");
            System.out.println("//   Le projet que vous cherchez est       //");
            System.out.println("//                introuvable              //");
            System.out.println("//=======================================//");
        }
    }

}
