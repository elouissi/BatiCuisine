package View;

import Domain.Devis;
import Service.ComposantService;
import Service.DevisService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class DevisView {

    Scanner scanner = new Scanner(System.in);
    DevisService devisService = new DevisService();

    public Devis saveDevis(Double coutFinal, int idProject) throws SQLException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateEmission = null;
        LocalDate dateValidate = null;
        LocalDate today = LocalDate.now();

        while (true) {
            System.out.println("--- Enregistrement du Devis ---\n");

             System.out.println("Entrez la date d'émission du devis (dd/MM/yyyy) : ");
            String dateEmissionE = scanner.nextLine();
            try {
                dateEmission = LocalDate.parse(dateEmissionE, format);
            } catch (Exception e) {
                System.out.println("Date d'émission invalide, veuillez entrer une date au format dd/MM/yyyy.");
                continue;
            }

             System.out.println("Entrez la date de validité du devis (dd/MM/yyyy) : ");
            String dateValidateE = scanner.nextLine();
            try {
                dateValidate = LocalDate.parse(dateValidateE, format);
            } catch (Exception e) {
                System.out.println("Date de validité invalide, veuillez entrer une date au format dd/MM/yyyy.");
                continue;
            }

             if (dateEmission.isAfter(today) && dateValidate.isAfter(today)) {
                if (dateEmission.isBefore(dateValidate)) {
                    Devis devis = new Devis(coutFinal, dateEmission, dateValidate, false);
                    return devisService.saveDevis(devis, idProject);
                } else {
                    System.out.println("La date d'émission doit être avant la date de validité. Veuillez réessayer.");
                }
            } else {
                System.out.println("Les dates doivent être après la date d'aujourd'hui. Veuillez réessayer.");
            }
        }
    }
    public Devis updateAccepte(Devis devis){
        System.out.println("vous devez soit accepte ou refuse ce devis de montant  "+devis.getMontantEstime()+"$ avant cette date"+devis.getDateValidite());
        System.out.println("o/n");
        String choix = scanner.nextLine();
        if (choix.equalsIgnoreCase("o")){
            Devis UpdatedDevis = new Devis(devis.getMontantEstime(),devis.getDateEmission(),devis.getDateValidite(),true);
            return devisService.updateAccepte(UpdatedDevis,devis.getId());
        } else if (choix.equalsIgnoreCase("n")) {
            System.out.println("non");
            Devis UpdatedDevis = new Devis(devis.getMontantEstime(),devis.getDateEmission(),devis.getDateValidite(),false);
            return devisService.updateAccepte(UpdatedDevis,devis.getId());

        }else return null;

    }
}
