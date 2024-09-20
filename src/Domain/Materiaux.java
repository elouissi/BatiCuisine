package Domain;
import Enum.EtatProjet;

public class Materiaux extends Composant {
    public Double coutUnitaire;
    public Double quantite;
    public Double coutTransport;

    public Materiaux(String name, String typeComposant, String typeTVA, Double coutUnitaire, Double quantite, Double coutTransport) {
        super(name, typeComposant, typeTVA);
        this.coutUnitaire = coutUnitaire;
        this.quantite = quantite;
        this.coutTransport = coutTransport;
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

    @Override
    public String toString() {
        return "Materiaux{" +
                "coutUnitaire=" + coutUnitaire +
                ", quantite=" + quantite +
                ", coutTransport=" + coutTransport +
                ", name='" + name + '\'' +
                ", typeComposant='" + typeComposant + '\'' +
                ", typeTVA='" + typeTVA + '\'' +
                '}';
    }
}
