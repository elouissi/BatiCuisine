package View;

import Domain.Main_oeuvre;
import Domain.Materiaux;
import Service.ComposantService;
import Utils.CheckInput;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ComposantView {
    Scanner scanner = new Scanner(System.in);
    ComposantService composantService = new ComposantService();

    public void saveComposant(int idProject) throws SQLException {
        System.out.println("//=======================================//");
        System.out.println("//     Ajout d'un nouveau composant      //");
        System.out.println("//=======================================//");


        String nom = CheckInput.readString("→ Veuillez entrer le nom du composant : ");

        System.out.println("//=======================================//");
        System.out.println("//Veuillez choisir le type du composant ://");
        System.out.println("//=======================================//");
        System.out.println(" 1 - Materiel");
        System.out.println(" 2 - Main-d'œuvre");


        int choix = CheckInput.readInt("choix:");

        if (choix == 1) {

            double quantite = CheckInput.readDouble("-> Veuillez entrer la quantite de materiel (en m²) : ");

            double coutUnitaire = CheckInput.readDouble("-> Veuillez entrer le coût unitaire (€/m²) : ");

            double coutTransport = CheckInput.readDouble("-> Veuillez entrer le coût du transport (€) : ");

            double coefficient = CheckInput.readDouble("-> Veuillez entrer le coefficient de qualite  (1.0 = standard, > 1.0 = haute qualité) : ");


            Materiaux materiaux = new Materiaux(nom, "matériaux", 0, coutUnitaire, quantite, coutTransport, coefficient);
            composantService.saveComposant(materiaux, idProject);

            System.out.println(" Materiau ajoute avec succès.");
        } else if (choix == 2) {

            double taux = CheckInput.readDouble(" Veuillez entrer le taux horaire (€/h) : ");

            double heure = CheckInput.readDouble(" Veuillez entrer le nombre d'heures de travail : ");

            double productivite = CheckInput.readDouble("-> Veuillez entrer la productivite : ");

            if (productivite > 1.00){

                Main_oeuvre mainOeuvre = new Main_oeuvre(nom, "main_oeuvre", 0, taux, heure, productivite,"Ouvrier_spécialisé");
                composantService.saveComposant(mainOeuvre, idProject);

            } else if (productivite <= 1.00) {

                Main_oeuvre mainOeuvre = new Main_oeuvre(nom, "main_oeuvre", 0, taux, heure, productivite," Ouvrier de base");
                composantService.saveComposant(mainOeuvre, idProject);

            }


            System.out.println(" Main-d'œuvre ajoutee avec succès.");
        } else {
            System.out.println(" Choix invalide. Veuillez reessayer.");
        }

        System.out.println("//=======================================//");
    }
    public double calculerCoutTotalMateriaux(int projectId) {
        return composantService.calculerCoutTotalMateriaux(projectId);
    }
    public Double calculeCoutTotaleMain_oeuvre(int projectId){
        return composantService.calculeCoutTotaleMain_oeuvre(projectId);
    }



}
