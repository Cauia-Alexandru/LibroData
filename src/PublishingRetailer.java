import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class PublishingRetailer {
    private int ID;
    private String name;
    private IPublishingArtifacts[] publishingArtifacts = new IPublishingArtifacts[1];
    private Countries[] countries = new Countries[1];
    private int sizeCountries = 0;
    private int sizePublishArtifacts = 0;

    public PublishingRetailer(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IPublishingArtifacts[] getPublishingArtifacts() {
        return publishingArtifacts;
    }

    public void setPublishingArtifacts(IPublishingArtifacts[] publishingArtifacts) {
        this.publishingArtifacts = publishingArtifacts;
    }

    public Countries[] getCountries() {
        return countries;
    }

    public void setCountries(Countries[] countries) {
        this.countries = countries;
    }

    public int getSizeCountries() {
        return sizeCountries;
    }

    public void setSizeCountries(int sizeCountries) {
        this.sizeCountries = sizeCountries;
    }

    public int getSizePublishArtifacts() {
        return sizePublishArtifacts;
    }

    public void setSizePublishArtifacts(int sizePublishArtifacts) {
        this.sizePublishArtifacts = sizePublishArtifacts;
    }

    public static PublishingRetailer[] publishRetailorIn() {
        PublishingRetailer[] publishingRetailers = new PublishingRetailer[1];
        try (BufferedReader br = new BufferedReader(new FileReader("src/init/publishing-retailers.in"))) {
            br.readLine();
            String line;
            int i = 0;
            int publishRetSize = 0;
            while((line = br.readLine()) != null) {
                if (publishRetSize >= publishingRetailers.length) {
                    publishingRetailers = Arrays.copyOf(publishingRetailers, 2 * publishingRetailers.length);
                }
                String[] splitPubRet = line.split("###");
                int ID = Integer.parseInt(splitPubRet[0]);
                PublishingRetailer retailer = new PublishingRetailer(ID, splitPubRet[1]);
                publishingRetailers[i] = retailer;
                i++;
                publishRetSize++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return publishingRetailers;
    }

    public void resizeCountries(){
        if(this.sizeCountries >= this.countries.length){
            this.countries = Arrays.copyOf(this.countries, this.countries.length + 1);
        }
    }

    public void addCountry(Countries country){
        resizeCountries();
        this.countries[sizeCountries] = country;
        this.sizeCountries++;
    }

    public void resizeArtifacts(){
        if(this.sizePublishArtifacts >= this.publishingArtifacts.length){
            this.publishingArtifacts = Arrays.copyOf(this.publishingArtifacts, this.publishingArtifacts.length + 1);
        }
    }

    public void addArtifact(IPublishingArtifacts artifacts){
        resizeArtifacts();
        this.publishingArtifacts[sizePublishArtifacts] = artifacts;
        this.sizePublishArtifacts++;
    }
}
