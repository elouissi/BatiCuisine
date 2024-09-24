package Service.interfaces;

import Domain.Devis;

import java.sql.SQLException;

public interface DevisInterface {
    Devis saveDevis(Devis devis, int id) throws SQLException;

    Devis updateAccepte(Devis updatedDevis, int id);
}
