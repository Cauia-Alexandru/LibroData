import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Language {
    private int ID;
    private String code;
    private String name;

    public Language(int ID, String code, String name) {
        this.ID = ID;
        this.code = code;
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Language[] languageIn() {
        Language[] languages = new Language[1];
        try (BufferedReader br = new BufferedReader(new FileReader("src/init/languages.in"))) {
            br.readLine();
            String line;
            int i = 0;
            int languagesSize = 0;
            while((line = br.readLine()) != null) {
                if (languagesSize >= languages.length) {
                    languages = Arrays.copyOf(languages, 2 * languages.length);
                }
            String[] splitLanguages = line.split("###");
                int ID = Integer.parseInt(splitLanguages[0]);
                Language language = new Language(ID, splitLanguages[1], splitLanguages[2]);
                languages[i] = language;
                i++;
                languagesSize++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return languages;
    }
}
