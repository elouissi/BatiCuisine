package Domain;
import Enum.EtatProjet;

public class Materiaux extends Composant {
    public Double coutUnitaire;
    public Double quantite;
    public Double coutTransport;
    public Double coefficientQualite;



    public Materiaux(String name, String typeComposant, double tauxTVA, Double coutUnitaire, Double quantite, Double coutTransport, Double coefficientQualite) {
        super(name, typeComposant, tauxTVA);
         this.coutUnitaire = coutUnitaire;
        this.quantite = quantite;
        this.coutTransport = coutTransport;
        this.coefficientQualite = coefficientQualite;
    }

    public Materiaux() {

    }

    public Materiaux(int id, String nom, String type, double tva, double quantite, double coutUnitaire, double coutTransport,double coefficientQualite) {
        super(nom, type, tva);
        this.id = id;
        this.coutUnitaire = coutUnitaire;
        this.quantite = quantite;
        this.coutTransport = coutTransport;
        this.coefficientQualite = coefficientQualite;
    }

    public Double getCoutUnitaire() {
        return coutUnitaire;
    }

    public void setCoutUnitaire(Double coutUnitaire) {
        this.coutUnitaire = coutUnitaire;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public Double getCoutTransport() {
        return coutTransport;
    }

    public void setCoutTransport(Double coutTransport) {
        this.coutTransport = coutTransport;
    }
    public Double getCoefficientQualite() {
        return coefficientQualite;
    }

    public void setCoefficientQualite(Double coefficientQualite) {
        this.coefficientQualite = coefficientQualite;
    }

    public double calculerCoutTotal() {
        return (quantite * coutUnitaire) +coutTransport;
    }

    @Override
    public String toString() {
        return "Materiaux{" +
                "coutUnitaire=" + coutUnitaire +
                ", quantite=" + quantite +
                ", coutTransport=" + coutTransport +
                ", coefficientQualite=" + coefficientQualite +
                ", name='" + name + '\'' +
                ", typeComposant='" + typeComposant + '\'' +
                ", tauxTVA=" + tauxTVA +
                ", id=" + id +
                '}';
    }
}
