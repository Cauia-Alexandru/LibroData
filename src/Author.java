import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Author {
    private int ID;
    private String firstName;
    private String lastName;

    public Author(int ID, String firstName, String lastName) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static Author[] authorsIn() {
        Author[] authors = new Author[1];
        try (BufferedReader br = new BufferedReader(new FileReader("src/init/authors.in"))) {
            br.readLine();
            String line;
            int i = 0;
            int authorsSize = 0;
            while ((line = br.readLine()) != null) {
                if (authorsSize >= authors.length) {
                    authors = Arrays.copyOf(authors, 2 * authors.length);
                }
                String[] splitAuthors = line.split("###");
                int ID = Integer.parseInt(splitAuthors[0]);
                Author author = new Author(ID, splitAuthors[1], splitAuthors[2]);
                authors[i] = author;
                i++;
                authorsSize++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return authors;
    }
}
