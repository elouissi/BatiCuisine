package Service;


import Domain.Composant;
import Domain.Main_oeuvre;
import Domain.Materiaux;
import Repositorie.Composant.ComposantRepository;
import Repositorie.Main_oeuvre.Main_oeuvreRepository;
import Repositorie.Materiaux.MateriauxRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
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

        }else {
            System.out.println("error de type");

        }
    }
    public double calculerCoutTotalMateriaux(int projectId) {
        List<Materiaux> materiauxList = materiauxRepository.getAllByProjectId(projectId);
        double coutTotal = 0.0;
        for (Materiaux materiaux : materiauxList) {
            coutTotal += materiaux.calculerCoutTotal();
        }
        return coutTotal;
    }
    public Double calculeCoutTotaleMain_oeuvre(int projectId){
        List<Main_oeuvre>  main_oeuvreList = mainOeuvreRepository.getAllByProjectId(projectId);
        double coutTotale = 0.0 ;
        for (Main_oeuvre mainOeuvre : main_oeuvreList){
            coutTotale += mainOeuvre.calculerCoutTotal();
        }
        return coutTotale;

    }
    public void updateTvaForComposant(int idComposant, double tauxTVA) throws SQLException {
         Optional<Composant> composantOptional = composantRepository.getById(idComposant);

        if (composantOptional.isPresent()) {
            Composant composant = composantOptional.get();
            composant.setTauxTVA(tauxTVA);

            composantRepository.update(composant,composant.getName());
            System.out.println("✔️ TVA mise à jour pour le composant " + composant.getName());
        } else {
            System.out.println("❌ Composant non trouvé.");
        }
    }
    public List<Composant> findComposantsByProjectId(int id) throws SQLException {
        return composantRepository.findComposantsByProjectId(id);
    }

}
