import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class EditorialGroup implements IPublishingArtifacts {
    private int ID;
    private String name;
    private Book[] books = new Book[1];
    private int sizeBooks = 0;

    public EditorialGroup(int ID, String name) {
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

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }

    public int getSizeBooks() {
        return sizeBooks;
    }

    public void setSizeBooks(int sizeBooks) {
        this.sizeBooks = sizeBooks;
    }

    public void resizeBooks(){
        if(this.sizeBooks >= this.books.length){
            this.books = Arrays.copyOf(this.books, this.books.length + 1);
        }
    }

    public void addBook(Book book){
        resizeBooks();
        this.books[sizeBooks] = book;
        this.sizeBooks++;
    }

    @Override
    public String Publish() {
        String toReturn = "<xml>\n" + "\t<editorialGroup>\n" + "\t\t<ID>" + ID + "/<ID>\n" +
                "\t\t<Name>" + name + "</Name>\n" + "\t</editorialGroup>\n" +
                "\t<books>\n";
        String[] booksToReturn = PublishingBrand.PublishBook(this.books);
        for(int i = 0; i < booksToReturn.length; i++){
            toReturn += booksToReturn[i];
        }
        toReturn += "\t</books>\n" + "</xml>";
        return toReturn;
    }

    public static EditorialGroup[] editorialGroupsIn() {
        EditorialGroup[] editorialGroups = new EditorialGroup[1];
        try (BufferedReader br = new BufferedReader(new FileReader("src/init/editorial-groups.in"))) {
            br.readLine();
            String line;
            int i = 0;
            int editorialGroupsSize = 0;
            while((line = br.readLine()) != null) {
                if (editorialGroupsSize >= editorialGroups.length) {
                    editorialGroups = Arrays.copyOf(editorialGroups, 2 * editorialGroups.length);
                }
                String[] splitEditorialGropus = line.split("###");
                int ID = Integer.parseInt(splitEditorialGropus[0]);
                EditorialGroup editGroup = new EditorialGroup(ID, splitEditorialGropus[1]);
                editorialGroups[i] = editGroup;
                i++;
                editorialGroupsSize++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return editorialGroups;
    }
}
