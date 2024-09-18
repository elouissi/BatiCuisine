import Config.Connection_DB;
import View.ClientView;
import View.ProjectView;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ///IMPORT CONNEXION
        Connection_DB connectionDb =new Connection_DB();
        connectionDb.Connect_to_DB("BatiCuisine","GreenPulse","");

        ///IMPORT our VIEWS
        ClientView clientView = new ClientView();
        ProjectView projectView = new ProjectView();


        int choix;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("-----------------------//=== Bienvenue dans l'application de gestion des projets de rénovation de cuisines ===//--------------");
            System.out.println("--------------------------------------------------=== Menu Principal ===-----------------------------------------------------\n");
            System.out.println("// 1 - Créer un nouveau projet\n");
            System.out.println("// 2 - Afficher les projets existants");
            System.out.println("// 3 - Calculer le coût d'un projet");
            System.out.println("// 4 - Afficher tous les utilisateurs");
            System.out.println("// 5 - entrer la consomation d'un utilisateur ");
            System.out.println("// 6 - afficher la consomation d'un utilisateur");
            System.out.println("// 7- générer le rapport d'un utilisateur");
            System.out.println("// 8 - afficher par id");
            System.out.println("// 9 - filtrage de consomation avec l'impact");
            System.out.println("// 10 - afficher average d'un utilisateur");
            System.out.println("// 11 - filtrage des utilisateurs par inactivite");
            System.out.println("// 12 - triage des utilisateurs en fonction de consomation total");
            System.out.println("// 13 - Exit");
            System.out.println("-----------------------------------------------------------------------------");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {

                case 1:



                    break;
            }}while (choix != 13);









            }
}