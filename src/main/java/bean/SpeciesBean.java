package bean;

import java.util.Date;

public class SpeciesBean {
    private int species_id;
    private String species_name;
    private String species_othername;
    private String createdby;
    private Date createdtime;
    private Date updatetime;
    private String species_morph;
    private String species_tech;
    private String species_appl;


    private String family_name; //科
    private String genus_name; //属
    private String totalprovience;


    public int getSpecies_id() {
        return species_id;
    }

    public void setSpecies_id(int species_id) {
        this.species_id = species_id;
    }

    public String getSpecies_name() {
        return species_name;
    }

    public void setSpecies_name(String species_name) {
        this.species_name = species_name;
    }

    public String getSpecies_othername() {
        return species_othername;
    }

    public void setSpecies_othername(String species_othername) {
        this.species_othername = species_othername;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getSpecies_morph() {
        return species_morph;
    }

    public void setSpecies_morph(String species_morph) {
        this.species_morph = species_morph;
    }

    public String getSpecies_tech() {
        return species_tech;
    }

    public void setSpecies_tech(String species_tech) {
        this.species_tech = species_tech;
    }

    public String getSpecies_appl() {
        return species_appl;
    }

    public void setSpecies_appl(String species_appl) {
        this.species_appl = species_appl;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }

    public void setGenus_name(String genus_name) {
        this.genus_name = genus_name;
    }

    public String getFamily_name() {
        return family_name;
    }

    public String getGenus_name() {
        return genus_name;
    }

    public String getTotalprovience() {
        return totalprovience;
    }

    public void setTotalprovience(String totalprovience) {
        this.totalprovience = totalprovience;
    }
}
