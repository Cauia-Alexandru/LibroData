import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class PublishingBrand implements IPublishingArtifacts {
    private int ID;
    private String name;
    private Book[] books = new Book[1];
    private int sizeBooks = 0;

    public PublishingBrand(int ID, String name) {
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

    @Override
    public String Publish() {
        String[] booksToReturn = PublishBook(this.books);
        String toReturn = "<xml>\n" + "\t<publishingBrand>\n" + "\t\t<ID>" + ID + "</ID>\n" +
                "\t\t<Name>" + name + "</Name>\n" + "\t</ publishingBrand>\n" +
                "\t<books>\n";
        for(int i = 0; i < booksToReturn.length; i++){
            toReturn += booksToReturn[i];
        }
        toReturn += "\t</books>\n" + "</xml>";
        return toReturn;
    }
// funtie de afisare a cartilor, o apelez in "Publish"
    public static String[] PublishBook(Book[] books) {
        String[] s = new String[books.length];
        for(int i = 0; i < books.length; i++) {

                Date date = books[i].getCreatedON().getTime();
                DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                String stringData = dateFormat.format(date);
                String PublishingBooks = "\t\t<book>\n" + "\t\t\t<title>" + books[i].getName() + "</title>\n" +
                        "\t\t\t<subtitle>" + books[i].getSubtitle() + "</subtitle>\n" +
                        "\t\t\t<isbn>" + books[i].getISBN() + "</isbn>\n" +
                        "\t\t\t<pageCount>" + books[i].getPageCount() + "</pageCount>\n" +
                        "\t\t\t<keywords>" + books[i].getKeywords() + "</keywords>\n" +
                        "\t\t\t<languageID>" + books[i].getLanguageID() + "</languageID>\n" +
                        "\t\t\t<createdOn>" + stringData + "<createdOn>\n" +
                        "\t\t\t<authors>";
                Author[] aut = books[i].getAuthors();
                for (Author a:aut){
                    if(a != null) {
                        PublishingBooks = PublishingBooks + a.getLastName() + " " + a.getFirstName() + " ";
                    }
                }
                PublishingBooks = PublishingBooks + "<authors>\n" + "\t\t</book>\n";
                s[i] = PublishingBooks;
        }
        return s;
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

    public static PublishingBrand[] publishingBrandsIn() {
        PublishingBrand[] PublishingBrands = new PublishingBrand[1];
        try (BufferedReader br = new BufferedReader(new FileReader("src/init/publishing-brands.in"))) {
            br.readLine();
            String line;
            int i = 0;
            int publishingBrandSize = 0;
            while((line = br.readLine()) != null) {
                if (publishingBrandSize >= PublishingBrands.length) {
                    PublishingBrands = Arrays.copyOf(PublishingBrands, 2 * PublishingBrands.length);
                }
                String[] splitPublishingBrands = line.split("###");
                int ID = Integer.parseInt(splitPublishingBrands[0]);
                PublishingBrand publishBrand = new PublishingBrand(ID, splitPublishingBrands[1]);
                PublishingBrands[i] = publishBrand;
                i++;
                publishingBrandSize++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return PublishingBrands;
    }
}
