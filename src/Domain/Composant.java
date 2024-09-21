package Domain;

public abstract class Composant {
    public String name;
    public String typeComposant;
    public double tauxTVA;
    public int id;


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

    @Override
    public String toString() {
        return "Composant{" +
                "name='" + name + '\'' +
                ", typeComposant='" + typeComposant + '\'' +
                ", tauxTVA='" + tauxTVA + '\'' +
                '}';
    }
}
