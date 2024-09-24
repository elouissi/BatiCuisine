package Service.interfaces;

import Domain.Composant;

import java.sql.SQLException;
import java.util.List;

public interface ComposantInterface {
    void saveComposant(Composant composant, int id) throws SQLException;

    double calculerCoutTotalMateriaux(int projectId);

    Double calculeCoutTotaleMain_oeuvre(int projectId);

    void updateTvaForComposant(int idComposant, double tauxTVA) throws SQLException;

    List<Composant> findComposantsByProjectId(int id) throws SQLException;
}
