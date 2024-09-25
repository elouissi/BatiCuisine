package View;

import Domain.Client;
import Domain.Project;
import Service.ClientService;
import Utils.CheckInput;

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
        System.out.println("//==============================================================================//");
        System.out.println("//                                                                              //");
        System.out.println("//                      Recherche et Gestion des Clients                        //");
        System.out.println("//                                                                               //");
        System.out.println("//==============================================================================//");
        System.out.println("//                                                                               //");
        System.out.println("//   1  -> Chercher un client existant                                            //");
        System.out.println("//   2  -> Ajouter un nouveau client                                             //");
        System.out.println("//   3  -> Modifier un client                                                     //");
        System.out.println("//   4  -> Supprimer un client                                                    //");
        System.out.println("//   5  -> Afficher tous les clients                                             //");
        System.out.println("//                                                                              //");
        System.out.println("//==============================================================================//");
        System.out.println("//                 Veuillez entrer un numero pour faire un choix :              //");
        System.out.println("//==============================================================================//");
        choix = CheckInput.readInt("");

        switch (choix) {
            case 1:
                System.out.println("//==============================================================================//");
                System.out.println("//                                                                              //");
                System.out.println("//                     Recherche de Client et Gestion de Projets                //");
                System.out.println("//                                                                              //");
                System.out.println("//==============================================================================//");


                String searchName = CheckInput.readString("Entrez le nom du client :");

                Optional<Client> client =  clientService.getClient(searchName);
                if (client.isPresent()) {
                    System.out.println("Client trouver : " + client);
                } else {
                    System.out.println("le nom que vous cherchez n'existe pas.");
                    return;
                }

                String choise = CheckInput.readString(" Voulez-vous ajouter un projet à ce client (o/n) ?");

                while (choise.equalsIgnoreCase("o")) {
                    projectView.saveProject(client.get());

                    System.out.println(" Projet ajoute avec succes !");
                     choise = CheckInput.readString(" Voulez-vous ajouter un autre projet à ce client (o/n) ?");
                }

                System.out.println(" Retour au menu principal.");
                break;

            case 2:
                System.out.println("//===========================================================================//");
                System.out.println("//                                                                           //");
                System.out.println("//                     Creation d'un Nouveau Client                          //");
                System.out.println("//                                                                           //");
                System.out.println("//===========================================================================//");

                String nom = CheckInput.readString(" Entrez le nom du client : ");

                String adresse = CheckInput.readString(" Entrez leur adresse : ");

                String telephone = CheckInput.readString(" Entrez leur numero de telephone : ");

                String Professionnel = CheckInput.readString(" Est-ce que ce client est professionnel ? (oui/non) : ");

                 boolean estProfessionnel;
                if (Professionnel.equals("oui")) {
                    estProfessionnel = true;
                } else {
                    estProfessionnel = false;
                }
                Client clientSave = clientService.saveClient(new Client(nom, adresse, telephone,estProfessionnel));
                if (clientSave != null) {
                    System.out.println("Client ajoute avec succes !");

                    String choix1 = CheckInput.readString(" Voulez-vous ajouter un projet à ce client ? (o/n) : ");

                     while (choix1.equalsIgnoreCase("o")) {
                        projectView.saveProject(clientSave);
                         System.out.println("Projet ajoute avec succès !");
                          choix1 = CheckInput.readString(" Voulez-vous un autre ajouter un projet à ce client ? (o/n) : ");
                    }
                    System.out.println(" Retour au menu principal.");
                } else {
                    System.out.println(" Erreur : Un client avec ce nom existe dejà.");
                }
                break;
            case 3:
                System.out.println("//==============================================================================//");
                System.out.println("//                   Modification des Informations d'un Client                   //");
                System.out.println("//===============================================================================//");

                System.out.println(" Entrez le nom du client que vous voulez modifier :");
                String UpdatesName = scanner.nextLine();
                Optional<Client> clientUp =  clientService.getClient(UpdatesName);
                if (clientUp.isPresent()){

                    String nomUpdate = CheckInput.readString("Entrez le nouveau nom : ");

                    String adresseUpdate = CheckInput.readString(" Entrez la nouvelle adresse : ");

                    String telephoneUpdate = CheckInput.readString(" Entrez le nouveau numero de telephone : ");

                    String ProfessionnelUpdate = CheckInput.readString(" Ce client est-il professionnel ? (oui/non) : ");

                    boolean estProfessionnelUpdate = ProfessionnelUpdate.equals("oui");
                    clientService.updateClient(new Client(nomUpdate, adresseUpdate, telephoneUpdate,estProfessionnelUpdate) , UpdatesName);
                    Optional<Client> updatedClinet = clientService.getClient(nomUpdate);
                    if (updatedClinet.isPresent()) {
                        System.out.println(" Client modifie avec succes !");

                        String choise2 = CheckInput.readString(" Voulez-vous ajouter un projet à ce client ? (o/n) : ");

                        while (choise2.equalsIgnoreCase("o")) {
                            projectView.saveProject(updatedClinet.get());
                            System.out.println();
                            choise2 = CheckInput.readString(" Voulez-vous ajouter un autre projet à ce client ? (o/n) : ");
                        }

                        System.out.println(" Retour au menu principal.");
                    } else {
                        System.out.println(" Erreur : Un client avec ce nom existe dejà.");
                    }
                }else {
                    System.out.println(" Le nom que vous cherchez n'existe pas.");
                }

                break;
            case 4:
                System.out.println("//================================================================================//");
                System.out.println("//                     Suppression d'un Client du Système                         //");
                System.out.println("///==============================================================================//");

                System.out.println();
                String deletedName = CheckInput.readString(" Entrez le nom du client que vous voulez supprimer :");
                Optional<Client> clientDl =  clientService.getClient(deletedName);
                if (clientDl.isPresent()){
                    clientService.deleteClient(deletedName);
                    System.out.println(" Le client '" + deletedName + "' a bien ete supprime du système.");
                }else {
                    System.out.println(" Le client '" + deletedName + "' n'a pas ete trouve.");
                }
                break;
            case 5:
                System.out.println("//==============================================================================//");
                System.out.println("//                         Liste de tous les Clients                            //");
                System.out.println("//==============================================================================//");

                clientService.getAll().forEach(client2 -> {
                    System.out.println("Client : " + client2.getNom() + ", Adresse : " + client2.getAdresse() +
                            ", Telephone : " + client2.getTelephone() +
                            ", Professionnel : " + (client2.estProfessionnel ? "Oui" : "Non")+ " /"+"projects : "+client2.getListProject() );
                });

        }



    }


}
