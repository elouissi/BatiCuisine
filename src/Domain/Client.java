package Domain;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public String name;
    public String adresse;
    public String telephone;
    public List<Project> ListProject = new ArrayList<>();

    public List<Project> getListProject() {
        return ListProject;
    }

    public void setListProject(List<Project> listProject) {
        ListProject = listProject;
    }

    public Client(String name, String adresse, String telephone, List<Project> listProject, boolean estProfessionnel) {
        this.name = name;
        this.adresse = adresse;
        this.telephone = telephone;
        ListProject = listProject;
        this.estProfessionnel = estProfessionnel;
    }

    public Client() {
    }

    public boolean estProfessionnel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                "name='" + name + '\'' +
                ", adresse='" + adresse + '\'' +
                ", telephone='" + telephone + '\'' +
                ", ListProject=" + ListProject +
                ", estProfessionnel=" + estProfessionnel +
                '}';
    }
}
