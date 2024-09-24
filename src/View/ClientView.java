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
        System.out.println("╔═════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                         ║");
        System.out.println("║                   🔍✨ Recherche et Gestion des Clients ✨🔍               ║");
        System.out.println("║                                                                         ║");
        System.out.println("╠═════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║                                                                         ║");
        System.out.println("║   1️⃣  ➜ Chercher un client existant                                      ║");
        System.out.println("║   2️⃣  ➜ Ajouter un nouveau client                                       ║");
        System.out.println("║   3️⃣  ➜ Modifier un client                                               ║");
        System.out.println("║   4️⃣  ➜ Supprimer un client                                              ║");
        System.out.println("║   5️⃣  ➜ Afficher tous les clients                                        ║");
        System.out.println("║                                                                         ║");
        System.out.println("╠═════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║               🛠️  Veuillez entrer un numéro pour faire un choix :         ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════════════╝");
        choix = scanner.nextInt();
        scanner.nextLine();

        switch (choix) {
            case 1:
                System.out.println("╔═══════════════════════════════════════════════════════════════════════════╗");
                System.out.println("║                                                                           ║");
                System.out.println("║                  ✨🔍 Recherche de Client et Gestion de Projets 🔍✨          ║");
                System.out.println("║                                                                           ║");
                System.out.println("╚═══════════════════════════════════════════════════════════════════════════╝");

                System.out.println("Entrez le nom du client :");
                String searchName = scanner.nextLine();
                Optional<Client> client =  clientService.getClient(searchName);
                if (client.isPresent()) {
                    System.out.println("✔️ Client trouvé : " + client);
                } else {
                    System.out.println("❌ Le nom que vous cherchez n'existe pas.");
                    return;
                }
                System.out.println("🔸 Voulez-vous ajouter un projet à ce client (o/n) ?");
                String choise = scanner.nextLine();

                while (choise.equalsIgnoreCase("o")) {
                     projectView.saveProject(client.get());
                    System.out.println("✔️ Projet ajouté avec succès !");
                    System.out.println("🔸 Voulez-vous ajouter un autre projet à ce client (o/n) ?");
                    choise = scanner.nextLine();
                }

                System.out.println("🔙 Retour au menu principal.");
                break;

            case 2:
                System.out.println("╔═══════════════════════════════════════════════════════════════════════════╗");
                System.out.println("║                                                                           ║");
                System.out.println("║                ✨ Création d'un Nouveau Client ✨                           ║");
                System.out.println("║                                                                           ║");
                System.out.println("╚═══════════════════════════════════════════════════════════════════════════╝");

                System.out.println("🔸 Entrez le nom du client : ");
                 String nom = scanner.nextLine();
                System.out.println("🔸 Entrez leur adresse : ");
                String adresse = scanner.nextLine();
                System.out.println("🔸 Entrez leur numéro de téléphone : ");
                String telephone = scanner.nextLine();
                System.out.println("🔸 Est-ce que ce client est professionnel ? (oui/non) : ");
                String Professionnel = scanner.nextLine();
                 boolean estProfessionnel;
                if (Professionnel.equals("oui")) {
                    estProfessionnel = true;
                } else {
                    estProfessionnel = false;
                }
                Client clientSave = clientService.saveClient(new Client(nom, adresse, telephone,estProfessionnel));
                if (clientSave != null) {
                    System.out.println("✔️ Client ajouté avec succès !");
                    System.out.println("🔸 Voulez-vous ajouter un projet à ce client ? (o/n) : ");
                    String choix1 = scanner.nextLine();

                     while (choix1.equalsIgnoreCase("o")) {
                        projectView.saveProject(clientSave);
                         System.out.println("✔️ Projet ajouté avec succès !");
                         System.out.println("🔸 Voulez-vous ajouter un autre projet à ce client ? (o/n) : ");                        choix1 = scanner.nextLine();
                    }
                    System.out.println("🔙 Retour au menu principal.");
                } else {
                    System.out.println("❌ Erreur : Un client avec ce nom existe déjà.");
                }
                break;
            case 3:
                System.out.println("╔═════════════════════════════════════════════════════════════════════════════════╗");
                System.out.println("║                  ✨ Modification des Informations d'un Client ✨                  ║");
                System.out.println("╚═════════════════════════════════════════════════════════════════════════════════╝");

                System.out.println("🔍 Entrez le nom du client que vous voulez modifier :");                String UpdatesName = scanner.nextLine();
                Optional<Client> clientUp =  clientService.getClient(UpdatesName);
                if (clientUp.isPresent()){
                    System.out.println("✏️ Entrez le nouveau nom : ");
                    String nomUpdate = scanner.nextLine();
                    System.out.println("🏠 Entrez la nouvelle adresse : ");
                    String adresseUpdate = scanner.nextLine();
                    System.out.println("📞 Entrez le nouveau numéro de téléphone : ");
                    String telephoneUpdate = scanner.nextLine();
                    System.out.println("💼 Ce client est-il professionnel ? (oui/non) : ");
                    String ProfessionnelUpdate = scanner.nextLine();
                    boolean estProfessionnelUpdate = ProfessionnelUpdate.equals("oui");
                    clientService.updateClient(new Client(nomUpdate, adresseUpdate, telephoneUpdate,estProfessionnelUpdate) , UpdatesName);
                    Optional<Client> updatedClinet = clientService.getClient(nomUpdate);
                    if (updatedClinet.isPresent()) {
                        System.out.println("✔️ Client modifié avec succès !");
                        System.out.println("🔸 Voulez-vous ajouter un projet à ce client ? (o/n) : ");
                        String choise2 = scanner.nextLine();

                        while (choise2.equalsIgnoreCase("o")) {
                            projectView.saveProject(updatedClinet.get());
                            System.out.println("🔸 Voulez-vous ajouter un autre projet à ce client ? (o/n) : ");
                            choise2 = scanner.nextLine();
                        }

                        System.out.println("🔙 Retour au menu principal.");
                    } else {
                        System.out.println("❌ Erreur : Un client avec ce nom existe déjà.");
                    }
                }else {
                    System.out.println("❌ Le nom que vous cherchez n'existe pas.");
                }

                break;
            case 4:
                System.out.println("╔═════════════════════════════════════════════════════════════════════════════════╗");
                System.out.println("║                    🗑️ Suppression d'un Client du Système 🗑️                     ║");
                System.out.println("╚═════════════════════════════════════════════════════════════════════════════════╝");

                System.out.println("🔍 Entrez le nom du client que vous voulez supprimer :");                String deletedName = scanner.nextLine();
                Optional<Client> clientDl =  clientService.getClient(deletedName);
                if (clientDl.isPresent()){
                    clientService.deleteClient(deletedName);
                    System.out.println("✔️ Le client '" + deletedName + "' a bien été supprimé du système.");
                }else {
                    System.out.println("❌ Le client '" + deletedName + "' n'a pas été trouvé.");
                }
                break;
            case 5:
                System.out.println("╔═════════════════════════════════════════════════════════════════════════════════╗");
                System.out.println("║                        📋 Liste de tous les Clients 📋                          ║");
                System.out.println("╚═════════════════════════════════════════════════════════════════════════════════╝");

                clientService.getAll().forEach(client2 -> {
                    System.out.println("Client : " + client2.getNom() + ", Adresse : " + client2.getAdresse() +
                            ", Téléphone : " + client2.getTelephone() +
                            ", Professionnel : " + (client2.estProfessionnel ? "Oui" : "Non"));
                });

        }



    }


}
