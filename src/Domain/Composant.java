package Domain;

public abstract class Composant {
    public String name;
    public String typeComposant;
    public String typeTVA;


    public Composant(String name, String typeComposant, String typeTVA) {
        this.name = name;
        this.typeComposant = typeComposant;
        this.typeTVA = typeTVA;
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

    public String getTypeTVA() {
        return typeTVA;
    }

    public void setTypeTVA(String typeTVA) {
        this.typeTVA = typeTVA;
    }

    @Override
    public String toString() {
        return "Composant{" +
                "name='" + name + '\'' +
                ", typeComposant='" + typeComposant + '\'' +
                ", typeTVA='" + typeTVA + '\'' +
                '}';
    }
}
