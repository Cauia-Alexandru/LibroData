import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Countries {
    private int ID;
    private String countryCode;

    public Countries(int ID, String countryCode) {
        this.ID = ID;
        this.countryCode = countryCode;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public static Countries[] countriesIn() {
        Countries[] countries = new Countries[1];
        try (BufferedReader br = new BufferedReader(new FileReader("src/init/countries.in"))) {
            br.readLine();
            String line;
            int i = 0;
            int countriesSize = 0;
            while((line = br.readLine()) != null) {
                if (countriesSize >= countries.length) {
                    countries = Arrays.copyOf(countries, 2 * countries.length);
                }
                String[] splitCountries = line.split("###");
                int ID = Integer.parseInt(splitCountries[0]);
                Countries country = new Countries(ID, splitCountries[1]);
                countries[i] = country;
                i++;
                countriesSize++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return countries;
    }
}
