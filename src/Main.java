import Config.Connection_DB;
import View.ClientView;
import View.ProjectView;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        ///IMPORT CONNEXION


        ///IMPORT our VIEWS
        ClientView clientView = new ClientView();
        ProjectView projectView = new ProjectView();


        int choix;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("-----------------------//=== Bienvenue dans l'application de gestion des projets de rénovation de cuisines ===//--------------");
            System.out.println("--------------------------------------------------=== Menu Principal ===-----------------------------------------------------\n");
            System.out.println("// 1 - Créer un nouveau projet");
            System.out.println("// 2 - Afficher les projets existants");
            System.out.println("// 3 - Calculer le coût d'un projet");
            System.out.println("// 5 - Exit");
            System.out.println("-----------------------------------------------------------------------------");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {

                case 1:

                clientView.SearchClient();

                break;
            }
        }while (choix != 5);









            }
}