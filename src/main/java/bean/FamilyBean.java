package bean;

import java.util.Date;

public class FamilyBean {
    private String family_name;
    private int family_id;

    private String genus_name;
    private String species_name;



    public void setFamily_id(int family_id) {
        this.family_id = family_id;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }

    public String getFamily_name() {
        return family_name;
    }

    public int getFamily_id() {
        return family_id;
    }

    public String getGenus_name() {
        return genus_name;
    }

    public void setGenus_name(String genus_name) {
        this.genus_name = genus_name;
    }

    public String getSpecies_name() {
        return species_name;
    }

    public void setSpecies_name(String species_name) {
        this.species_name = species_name;
    }
}

