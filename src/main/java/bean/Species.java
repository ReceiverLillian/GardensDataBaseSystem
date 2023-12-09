package bean;

public class Species {
    private int speciesId;
    private String speciesName;
    private String speciesOtherName;
    private String familyName;
    private String genusName;
    private String speciesMorph;
    private String speciesTech;
    private String speciesAppl;
    private String pictureDescri;
    private String picturePlace;
    private String photoedBy;

    public Species(int speciesId, String speciesName, String speciesOtherName, String familyName, String genusName, String speciesMorph, String speciesTech, String speciesAppl, String pictureDescri, String picturedPlace, String photoedBy) {
        this.speciesId = speciesId;
        this.speciesName = speciesName;
        this.speciesOtherName = speciesOtherName;
        this.familyName = familyName;
        this.genusName = genusName;
        this.speciesMorph = speciesMorph;
        this.speciesTech = speciesTech;
        this.speciesAppl = speciesAppl;
        this.pictureDescri = pictureDescri;
        this.picturePlace = picturedPlace;
        this.photoedBy = photoedBy;
    }

    public int getSpeciesId() {
        //return speciesId;
        return 12;
    }

    public String getSpeciesName() {
        System.out.println("当前通过getSpeciesName()获得的植物名为"+speciesName);
        return "植物名测试用";
        //return speciesName;
    }

    public String getSpeciesOtherName() {
        return speciesOtherName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getGenusName() {
        return genusName;
    }

    public String getSpeciesMorph() {
        return speciesMorph;
    }

    public String getSpeciesTech() {
        return speciesTech;
    }

    public String getSpeciesAppl() {
        return speciesAppl;
    }

    public String getPictureDescri() {
        return pictureDescri;
    }

    public String getPicturePlace() {
        return picturePlace;
    }

    public String getPhotoedBy() {
        return photoedBy;
    }

    @Override
    public String toString() {
        return "test用。";
        /*return "Species{" +
                "speciesId=" + speciesId +
                ", speciesName='" + speciesName + '\'' +
                ", speciesOtherName='" + speciesOtherName + '\'' +
                ", familyName='" + familyName + '\'' +
                ", genusName='" + genusName + '\'' +
                ", speciesMorph='" + speciesMorph + '\'' +
                ", speciesTech='" + speciesTech + '\'' +
                ", speciesAppl='" + speciesAppl + '\'' +
                ", pictureDescri='" + pictureDescri + '\'' +
                ", picturePlace='" + picturePlace + '\'' +
                ", photoedBy='" + photoedBy + '\'' +
                '}';*/
    }
}
