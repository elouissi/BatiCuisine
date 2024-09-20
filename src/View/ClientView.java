package View;

import Domain.Client;
import Service.ClientService;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Optional;
import java.util.Scanner;

public class ClientView {

    Scanner scanner = new Scanner(System.in);
    ClientService clientService = new ClientService();
    public void SearchClient() throws SQLException {
        int choix = 0;
        System.out.println("--- Recherche de client ---\n");
        System.out.println("1. Chercher un client existant\n");
        System.out.println("2. Ajouter un nouveau client\n");
        choix = scanner.nextInt();
        scanner.nextLine();

        switch (choix) {
            case 1:
                System.out.println("Entrez le nom du client :");
                String searchName = scanner.nextLine();
                Optional<Client> client =  clientService.getClient(searchName);
                if (client.isPresent()){   System.out.println(client);}else System.out.println("le nom que vous chercher n'existe pas");



                break;
            case 2:

                System.out.println("enter le nom : ");
                String nom = scanner.nextLine();
                System.out.println("enter leur adresse :  ");
                String adresse = scanner.nextLine();
                System.out.println("enter leur Numéro de téléphone :   ");
                String telephone = scanner.nextLine();
                System.out.println("est ce que ce client est professionel  :  ");
                String Professionnel = scanner.nextLine();
                 boolean estProfessionnel;
                if (Professionnel.equals("oui")) {
                    estProfessionnel = true;
                } else {
                    estProfessionnel = false;
                }
                Client clientSave = clientService.saveClient(new Client(nom, adresse, telephone,estProfessionnel));
                if (clientSave != null) {
                    System.out.println("Client ajouté avec succès !");
                } else {
                    System.out.println("Erreur : Un client avec ce nom existe déjà.");
                }

        }

    }


}
