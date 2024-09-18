package Domain;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public String nom;
    public String adresse;
    public String telephone;
    public List<Project> ListProject = new ArrayList<>();



    public List<Project> getListProject() {
        return ListProject;
    }

    public void setListProject(List<Project> listProject) {
        ListProject = listProject;
    }

    public Client(String nom, String adresse, String telephone,boolean estProfessionnel) {
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
         this.estProfessionnel = estProfessionnel;
    }

    public Client() {
    }

    public boolean estProfessionnel;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public boolean isEstProfessionnel() {
        return estProfessionnel;
    }

    public void setEstProfessionnel(boolean estProfessionnel) {
        this.estProfessionnel = estProfessionnel;
    }

    @Override
    public String toString() {
        return "Client{" +
                "nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", telephone='" + telephone + '\'' +
                ", ListProject=" + ListProject +
                ", estProfessionnel=" + estProfessionnel +
                '}';
    }
}
