package Domain;

public class Main_oeuvre extends Composant{
    public Double tauxHoraire;
    public Double heuresTravail;
    public Double productiviteOuvrier;

    public Main_oeuvre(String name, String typeComposant, String typeTVA, Double tauxHoraire, Double heuresTravail, Double productiviteOuvrier) {
        super(name, typeComposant, typeTVA);
        this.tauxHoraire = tauxHoraire;
        this.heuresTravail = heuresTravail;
        this.productiviteOuvrier = productiviteOuvrier;
    }

    @Override
    public String toString() {
        return "Main_oeuvre{" +
                "tauxHoraire=" + tauxHoraire +
                ", heuresTravail=" + heuresTravail +
                ", productiviteOuvrier=" + productiviteOuvrier +
                ", name='" + name + '\'' +
                ", typeComposant='" + typeComposant + '\'' +
                ", typeTVA='" + typeTVA + '\'' +
                '}';
    }

    public Double getTauxHoraire() {
        return tauxHoraire;
    }

    public void setTauxHoraire(Double tauxHoraire) {
        this.tauxHoraire = tauxHoraire;
    }

    public Double getHeuresTravail() {
        return heuresTravail;
    }

    public void setHeuresTravail(Double heuresTravail) {
        this.heuresTravail = heuresTravail;
    }

    public Double getProductiviteOuvrier() {
        return productiviteOuvrier;
    }

    public void setProductiviteOuvrier(Double productiviteOuvrier) {
        this.productiviteOuvrier = productiviteOuvrier;
    }
}