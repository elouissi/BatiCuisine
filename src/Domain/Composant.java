package Domain;

public abstract class Composant {
    public String name;
    public String typeComposant;
    public double tauxTVA;
    public int id;
    public double quantite; // pour les matériaux
    public double coutUnitaire; // pour les matériaux
    public double coutTransport; // pour les matériaux
    public double tauxHoraire; // pour la main-d'œuvre
    public double heuresTravaillees; // pour la main-d'œuvre



    public Composant(String name, String typeComposant, double tauxTVA) {
        this.name = name;
        this.typeComposant = typeComposant;
        this.tauxTVA = tauxTVA;
    }

    public Composant() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeComposant() {
        return typeComposant;
    }

    public void setTypeComposant(String typeComposant) {
        this.typeComposant = typeComposant;
    }

    public double getTauxTVA() {
        return tauxTVA;
    }

    public void setTauxTVA(double tauxTVA) {
        this.tauxTVA = tauxTVA;
    }

     public double calculerCoutTotal() {
        if (typeComposant.equals("Matériel")) {
            return (coutUnitaire * quantite) + coutTransport;
        } else if (typeComposant.equals("Main-d'œuvre")) {
            return tauxHoraire * heuresTravaillees;
        }
        return 0;
    }

     public double calculerCoutAvecTVA() {
        return calculerCoutTotal() * (1 + tauxTVA / 100);
    }

    @Override
    public String toString() {
        return "Composant{" +
                "name='" + name + '\'' +
                ", typeComposant='" + typeComposant + '\'' +
                ", tauxTVA='" + tauxTVA + '\'' +
                ", quantite=" + quantite +
                ", coutUnitaire=" + coutUnitaire +
                ", coutTransport=" + coutTransport +
                ", tauxHoraire=" + tauxHoraire +
                ", heuresTravaillees=" + heuresTravaillees +
                '}';
    }
}
