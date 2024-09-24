package Service;

import Domain.Client;
import Domain.Devis;
import Repositorie.Client.ClientRepository;
import Repositorie.Devis.DevisRepository;

import java.sql.SQLException;
import java.util.Scanner;

public class DevisService implements Service.interfaces.DevisInterface {
    private Scanner scanner;

    DevisRepository devisRepository = new DevisRepository();


    @Override
    public Devis saveDevis(Devis devis, int id) throws SQLException {
        return devisRepository.add(devis,id);
    }
    @Override
    public Devis updateAccepte(Devis updatedDevis, int id){
        return devisRepository.update(updatedDevis,id);
    }
}
