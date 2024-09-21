package Service;


import Domain.Composant;
import Domain.Main_oeuvre;
import Domain.Materiaux;
import Repositorie.Composant.ComposantRepository;
import Repositorie.Main_oeuvre.Main_oeuvreRepository;
import Repositorie.Materiaux.MateriauxRepository;

import java.sql.SQLException;
import java.util.Scanner;

public class ComposantService {

    private Scanner scanner;

    ComposantRepository composantRepository = new ComposantRepository();
    MateriauxRepository materiauxRepository = new MateriauxRepository();
    Main_oeuvreRepository mainOeuvreRepository = new Main_oeuvreRepository();


    public void saveComposant(Composant composant,int id) throws SQLException {
        if (composant instanceof Materiaux){
            Materiaux materiaux = (Materiaux) composant;
            materiauxRepository.add(materiaux,id);

        }else if(composant instanceof Main_oeuvre){
            Main_oeuvre mainOeuvre = (Main_oeuvre) composant;
            mainOeuvreRepository.add(mainOeuvre,id);
            System.out.println("error de type");

        }




    }
}
