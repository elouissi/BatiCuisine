package View;

import Domain.Main_oeuvre;
import Domain.Materiaux;
import Service.ComposantService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ComposantView {
    Scanner scanner = new Scanner(System.in);
    ComposantService composantService = new ComposantService();

    public void saveComposant(int idProject) throws SQLException {
        System.out.println("s'il vous entrer le nom du composant : ");
        String nom = scanner.nextLine();
        System.out.println("s'il vous plait choisissez le type du composant:");
        System.out.println("1-mateiel\n 2-main d'oeuvre");
        int choix =scanner.nextInt();
        scanner.nextLine();
        if (choix == 1){
            System.out.println("s'il vous entrer la quantité du material : ");
            Double quantite = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("s'il vous entrer le cout Unitaire  : ");
            Double coutUnitaire = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("s'il vous entrer le cout du transport : ");
            Double coutTransport = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("s'il vous entrer le coefficient du qualité : ");
            Double coefficient = scanner.nextDouble();
            scanner.nextLine();
            Materiaux materiaux = new Materiaux(nom,"materiaux",0,coutUnitaire,quantite,coutTransport,coefficient);
            composantService.saveComposant(materiaux,idProject);

        }else if(choix == 2) {
            System.out.println("s'il vous entrer le taux horraire : ");
            Double taux = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("s'il vous entrer combien d'heure de travaile  : ");
            Double heure = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("s'il vous entrer la productivite : ");
            Double productivite = scanner.nextDouble();
            scanner.nextLine();

            Main_oeuvre mainOeuvre = new Main_oeuvre(nom,"main_oeuvre",0,taux,heure,productivite);
            composantService.saveComposant(mainOeuvre,idProject);

        }else System.out.println("choix invalid");
    }
    public double calculerCoutTotalMateriaux(int projectId) {
        return composantService.calculerCoutTotalMateriaux(projectId);
    }
    public Double calculeCoutTotaleMain_oeuvre(int projectId){
        return composantService.calculeCoutTotaleMain_oeuvre(projectId);
    }



}
