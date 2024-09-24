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
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║     Ajout d'un nouveau composant       ║");
        System.out.println("╚════════════════════════════════════════╝");

        System.out.print("→ Veuillez entrer le nom du composant : ");
        String nom = scanner.nextLine();

        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║     Veuillez choisir le type du composant :    ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println(" 1 - Matériel");
        System.out.println(" 2 - Main-d'œuvre");

        int choix = scanner.nextInt();
        scanner.nextLine();

        if (choix == 1) {
            System.out.print("→ Veuillez entrer la quantité de matériel (en m²) : ");
            Double quantite = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("→ Veuillez entrer le coût unitaire (€/m²) : ");
            Double coutUnitaire = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("→ Veuillez entrer le coût du transport (€) : ");
            Double coutTransport = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("→ Veuillez entrer le coefficient de qualité : ");
            Double coefficient = scanner.nextDouble();
            scanner.nextLine();

            Materiaux materiaux = new Materiaux(nom, "matériaux", 0, coutUnitaire, quantite, coutTransport, coefficient);
            composantService.saveComposant(materiaux, idProject);

            System.out.println("✔️ Matériau ajouté avec succès.");
        } else if (choix == 2) {
            System.out.print("→ Veuillez entrer le taux horaire (€/h) : ");
            Double taux = scanner.nextDouble();
            scanner.nextLine();

            double heure = CheckInput.readDouble("→ Veuillez entrer le nombre d'heures de travail : ");

            System.out.print("→ Veuillez entrer la productivité : ");
            Double productivite = scanner.nextDouble();
            scanner.nextLine();
            if (productivite > 1.00){
                Main_oeuvre mainOeuvre = new Main_oeuvre(nom, "main_oeuvre", 0, taux, heure, productivite,"Ouvrier_spécialisé");
                composantService.saveComposant(mainOeuvre, idProject);


            } else if (productivite <= 1.00) {
                Main_oeuvre mainOeuvre = new Main_oeuvre(nom, "main_oeuvre", 0, taux, heure, productivite," Ouvrier de base");
                composantService.saveComposant(mainOeuvre, idProject);

            }


            System.out.println("✔️ Main-d'œuvre ajoutée avec succès.");
        } else {
            System.out.println("❌ Choix invalide. Veuillez réessayer.");
        }

        System.out.println("══════════════════════════════════════════");
    }
    public double calculerCoutTotalMateriaux(int projectId) {
        return composantService.calculerCoutTotalMateriaux(projectId);
    }
    public Double calculeCoutTotaleMain_oeuvre(int projectId){
        return composantService.calculeCoutTotaleMain_oeuvre(projectId);
    }



}
