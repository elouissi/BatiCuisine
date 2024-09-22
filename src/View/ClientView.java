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
    ProjectView projectView = new ProjectView();

    public void SearchClient() throws SQLException {
        int choix = 0;
        System.out.println("--- Recherche de client ---\n");
        System.out.println("1. Chercher un client existant\n");
        System.out.println("2. Ajouter un nouveau client\n");
        System.out.println("3. Modifier un client");
        System.out.println("4. Supprimer un client");
        System.out.println("5. Afficher les clients");
        choix = scanner.nextInt();
        scanner.nextLine();

        switch (choix) {
            case 1:
                System.out.println("Entrez le nom du client :");
                String searchName = scanner.nextLine();
                Optional<Client> client =  clientService.getClient(searchName);
                if (client.isPresent()) {
                    System.out.println(client);
                } else {
                    System.out.println("Le nom que vous cherchez n'existe pas");
                    return;
                }
                System.out.println("Voulez-vous ajouter un projet à ce client (o/n) ?");
                String choise = scanner.nextLine();

                while (choise.equalsIgnoreCase("o")) {
                     projectView.saveProject(client.get());
                    System.out.println("Voulez-vous ajouter un autre projet à ce client (o/n) ?");
                    choise = scanner.nextLine();
                }

                System.out.println("Retour au menu principal.");
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
                    System.out.println("Voulez-vous ajouter un projet à ce client (o/n) ?");
                    String choix1 = scanner.nextLine();

                     while (choix1.equalsIgnoreCase("o")) {
                        projectView.saveProject(clientSave);
                        System.out.println("Voulez-vous ajouter un autre projet à ce client (o/n) ?");
                        choix1 = scanner.nextLine();
                    }
                     System.out.println("Retour au menu principal.");
                } else {
                    System.out.println("Erreur : Un client avec ce nom existe déjà.");
                }
                break;
            case 3:
                System.out.println("Entrez le nom du client que vous voulez modifier :");
                String UpdatesName = scanner.nextLine();
                Optional<Client> clientUp =  clientService.getClient(UpdatesName);
                if (clientUp.isPresent()){
                    System.out.println("enter le nouveau nom : ");
                    String nomUpdate = scanner.nextLine();
                    System.out.println("enter leur adresse :  ");
                    String adresseUpdate = scanner.nextLine();
                    System.out.println("enter leur Numéro de téléphone :   ");
                    String telephoneUpdate = scanner.nextLine();
                    System.out.println("est ce que ce client est professionel  :  ");
                    String ProfessionnelUpdate = scanner.nextLine();
                    boolean estProfessionnelUpdate = ProfessionnelUpdate.equals("oui");
                    clientService.updateClient(new Client(nomUpdate, adresseUpdate, telephoneUpdate,estProfessionnelUpdate) , UpdatesName);
                    Optional<Client> updatedClinet = clientService.getClient(nomUpdate);
                    if (updatedClinet.isPresent()) {
                        System.out.println("Client modifié avec succès !");
                        System.out.println("Voulez-vous ajouter un projet à ce client (o/n) ?");
                        String choise2 = scanner.nextLine();

                        while (choise2.equalsIgnoreCase("o")) {
                            projectView.saveProject(updatedClinet.get());
                            System.out.println("Voulez-vous ajouter un autre projet à ce client (o/n) ?");
                            choise2 = scanner.nextLine();
                        }

                        System.out.println("Retour au menu principal.");
                    } else {
                        System.out.println("Erreur : Un client avec ce nom existe déjà.");
                    }
                }else {
                    System.out.println("le nom que vous chercher n'existe pas");
                }

                break;
            case 4:
                System.out.println("Entrez le nom du client que vous voulez supprimer:");
                String deletedName = scanner.nextLine();
                Optional<Client> clientDl =  clientService.getClient(deletedName);
                if (clientDl.isPresent()){
                    clientService.deleteClient(deletedName);
                    System.out.println("le client est bien supprimé");
                }else {
                    System.out.println("le client non trouvé");
                }
                break;
            case 5:
                System.out.println("voici la liste des clients");
                clientService.getAll().forEach(System.out::println);
                }



    }


}
