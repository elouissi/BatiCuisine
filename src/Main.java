import Config.Connection_DB;
import Utils.CheckInput;
import View.ClientView;
import View.ProjectView;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        ///IMPORT our VIEWS
        ClientView clientView = new ClientView();
        ProjectView projectView = new ProjectView();


        int choix;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("//==============================================================================//");
            System.out.println("//                                                                              //");
            System.out.println("//      Bienvenue dans l'application de gestion des projets de rénovation       //");
            System.out.println("//                            de cuisines                                       //");
            System.out.println("//                                                                              //");
            System.out.println("//==============================================================================//");
            System.out.println("//                                                                               //");
            System.out.println("//                             Menu Principal                                    //");
            System.out.println("//                                                                               //");
            System.out.println("//==============================================================================//");
            System.out.println("//                                                                              //");
            System.out.println("//   1  -> Créer un nouveau projet                                              //");
            System.out.println("//   2  -> Afficher les projets existants                                       //");
            System.out.println("//   3  -> Calculer le coût d'un projet                                         //");
            System.out.println("//   4  -> Quitter l'application                                                //");
            System.out.println("//                                                                              //");
            System.out.println("//==============================================================================//");
            System.out.println("//                 Veuillez entrer un numéro pour faire un choix :              //");
            System.out.println("//==============================================================================//");


            choix = CheckInput.readInt("");

            switch (choix) {

                case 1:

                clientView.SearchClient();

                break;

                case 2:

                    projectView.getAll();

                    break;
                case 3:

                    projectView.searchProject();
                    break;
            }
        }while (choix != 4);









            }
}