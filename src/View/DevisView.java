package View;

import Domain.Devis;
import Service.ComposantService;
import Service.DevisService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DevisView {

    Scanner scanner = new Scanner(System.in);
    DevisService devisService = new DevisService();

    public Devis saveDevis(Double coutFinal,int idProject) throws SQLException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("--- Enregistrement du Devis ---\n");
        System.out.println("Entrez la date d'émission du devis  (dd/MM/yyyy)");
        String dateEmissionE = scanner.nextLine();
        System.out.println("Entrez la date de validité du devis  (dd/MM/yyyy) ");
        String dateValidateE = scanner.nextLine();
        LocalDate dateEmission = LocalDate.parse(dateEmissionE, format);
        LocalDate dateValidate = LocalDate.parse(dateValidateE, format);
        if (dateEmission.isBefore(dateValidate)){
            Devis devis = new Devis(coutFinal,dateEmission,dateValidate,false);
          return  devisService.saveDevis(devis,idProject);
        }else {
            System.out.println("veuillez entrez une date valide");
            return null;
        }
    }
    public Devis updateAccepte(Devis devis){
        System.out.println("en tant que tu es un client vous devez soit accepte ou refuse ce devis "+devis+"avant cette date"+devis.getDateValidite());
        System.out.println("o/n");
        String choix = scanner.nextLine();
        if (choix.equalsIgnoreCase("y")){
            Devis UpdatedDevis = new Devis(devis.getMontantEstime(),devis.getDateEmission(),devis.getDateValidite(),true);
            return devisService.updateAccepte(UpdatedDevis,devis.getId());
        }
        return null;

    }
}
