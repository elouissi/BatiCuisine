package Domain;

public class Main_oeuvre extends Composant{
    public Double tauxHoraire;
    public Double heuresTravail;
    public Double productiviteOuvrier;
    public String type_main_oeuvre;



    public Main_oeuvre(String name, String typeComposant, double tauxTVA, Double tauxHoraire, Double heuresTravail, Double productiviteOuvrier,String type_main_oeuvre) {
        super(name, typeComposant, tauxTVA);
        this.tauxHoraire = tauxHoraire;
        this.heuresTravail = heuresTravail;
        this.productiviteOuvrier = productiviteOuvrier;
        this.type_main_oeuvre  = type_main_oeuvre;
    }
    public Main_oeuvre(int id,String name, String typeComposant, double tauxTVA, Double tauxHoraire, Double heuresTravail, Double productiviteOuvrier,String type_main_oeuvre) {
        super(name, typeComposant, tauxTVA);
        this.id = id;
        this.tauxHoraire = tauxHoraire;
        this.heuresTravail = heuresTravail;
        this.productiviteOuvrier = productiviteOuvrier;
        this.type_main_oeuvre = type_main_oeuvre;
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
                "type_main_oeuvre = '"+ type_main_oeuvre +'/'+
                '}';
    }

    public String getType_main_oeuvre() {
        return type_main_oeuvre;
    }

    public void setType_main_oeuvre(String type_main_oeuvre) {
        this.type_main_oeuvre = type_main_oeuvre;
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
