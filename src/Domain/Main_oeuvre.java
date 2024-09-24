package Domain;

public class Main_oeuvre extends Composant{
    public Double tauxHoraire;
    public Double heuresTravail;
    public Double productiviteOuvrier;
    public String type_main_œuvre;


    public Main_oeuvre(String name, String typeComposant, double tauxTVA, Double tauxHoraire, Double heuresTravail, Double productiviteOuvrier) {
        super(name, typeComposant, tauxTVA);
        this.tauxHoraire = tauxHoraire;
        this.heuresTravail = heuresTravail;
        this.productiviteOuvrier = productiviteOuvrier;
    }
    public Main_oeuvre(String name, String typeComposant, double tauxTVA, Double tauxHoraire, Double heuresTravail, Double productiviteOuvrier,String type_main_œuvre) {
        super(name, typeComposant, tauxTVA);
        this.tauxHoraire = tauxHoraire;
        this.heuresTravail = heuresTravail;
        this.productiviteOuvrier = productiviteOuvrier;
        this.type_main_œuvre  = type_main_œuvre;
    }
    public Main_oeuvre(int id,String name, String typeComposant, double tauxTVA, Double tauxHoraire, Double heuresTravail, Double productiviteOuvrier,String type_main_œuvre) {
        super(name, typeComposant, tauxTVA);
        this.id = id;
        this.tauxHoraire = tauxHoraire;
        this.heuresTravail = heuresTravail;
        this.productiviteOuvrier = productiviteOuvrier;
        this.type_main_œuvre = type_main_œuvre;
    }


    public Main_oeuvre() {

    }

    @Override
    public String toString() {
        return "Main_oeuvre{" +
                "tauxHoraire=" + tauxHoraire +
                ", heuresTravail=" + heuresTravail +
                ", productiviteOuvrier=" + productiviteOuvrier +
                ", name='" + name + '\'' +
                ", typeComposant='" + typeComposant + '\'' +
                ", tauxTVA='" + tauxTVA + '\'' +
                "type_main_œuvre = '"+ type_main_œuvre +'/'+
                '}';
    }

    public String getType_main_œuvre() {
        return type_main_œuvre;
    }

    public void setType_main_œuvre(String type_main_œuvre) {
        this.type_main_œuvre = type_main_œuvre;
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

    public double calculerCoutTotal() {
        return tauxHoraire * heuresTravail * productiviteOuvrier;
    }
}
