package Domain;
import Enum.EtatProjet;

public class Project {
    public String nomProjet;
    public double margeBeneficiaire;
    public double coutTotal;
    public EtatProjet EtatProjet;

    public Project(String nomProjet, double margeBeneficiaire, double coutTotal, EtatProjet etatProjet) {
        this.nomProjet = nomProjet;
        this.margeBeneficiaire = margeBeneficiaire;
        this.coutTotal = coutTotal;
        EtatProjet = etatProjet;
    }

    public String getNomProjet() {
        return nomProjet;
    }

    public void setNomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }

    public double getMargeBeneficiaire() {
        return margeBeneficiaire;
    }

    public void setMargeBeneficiaire(double margeBeneficiaire) {
        this.margeBeneficiaire = margeBeneficiaire;
    }

    public double getCoutTotal() {
        return coutTotal;
    }

    public void setCoutTotal(double coutTotal) {
        this.coutTotal = coutTotal;
    }

    public EtatProjet getEtatProjet() {
        return EtatProjet;
    }

    public void setEtatProjet(EtatProjet etatProjet) {
        EtatProjet = etatProjet;
    }

    @Override
    public String toString() {
        return "Project{" +
                "nomProjet='" + nomProjet + '\'' +
                ", margeBeneficiaire=" + margeBeneficiaire +
                ", coutTotal=" + coutTotal +
                ", EtatProjet=" + EtatProjet +
                '}';
    }
}
