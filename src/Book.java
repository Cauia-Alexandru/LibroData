import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Book implements IPublishingArtifacts {
    private int ID;
    private String name;
    private String subtitle;
    private String ISBN;
    private int pageCount;
    private String keywords;
    private int languageID;
    private Calendar createdON;
    private Author[] authors;
    private int sizeAuthors = 0;

    public Book(int ID, String name, String subtitle, String ISBN, int pageCount, String keywords, int languageID, Calendar createdON) {
        this.ID = ID;
        this.name = name;
        this.subtitle = subtitle;
        this.ISBN = ISBN;
        this.pageCount = pageCount;
        this.keywords = keywords;
        this.languageID = languageID;
        this.createdON = createdON;
        this.authors = new Author[1];
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

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getLanguageID() {
        return languageID;
    }

    public void setLanguageID(int languageID) {
        this.languageID = languageID;
    }

    public Calendar getCreatedON() {
        return createdON;
    }

    public void setCreatedON(Calendar createdON) {
        this.createdON = createdON;
    }

    public Author[] getAuthors() {
        return authors;
    }

    public void setAuthors(Author[] authors) {
        this.authors = authors;
    }

    public int getSizeAuthors() {
        return sizeAuthors;
    }

    public void setSizeAuthors(int sizeAuthors) {
        this.sizeAuthors = sizeAuthors;
    }

    @Override
    public String Publish() {
        String tempAuthors = "\t<authors>";
        for (int i = 0; i < authors.length; i++) {                                                // intr-un string aparte stochez autorii
            if (authors[i] != null)
                tempAuthors += authors[i].getFirstName() + " " + authors[i].getLastName() + " ";
        }
        tempAuthors = tempAuthors.substring(0, tempAuthors.length() - 1);                         // scap de spatiu de la sfarsit
        tempAuthors += "<authors>";
        Date date = this.createdON.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String stringData = dateFormat.format(date);                                               // cast de la calendar la string
        String toReturn = "<xml>\n" + "\t<title>" + name + "</title>\n" +
                "\t<subtitle>" + subtitle + "</subtitle>\n" +
                "\t<isbn>" + ISBN + "</isbn>\n" +
                "\t<pageCount>" + pageCount + "</pageCount>\n" +
                "\t<keywords>" + keywords + "</keywords>\n" +
                "\t<languageID>" + languageID + "</languageID>\n" +
                "\t<createdOn>" + stringData + "</createdOn>\n" + tempAuthors + "\n" + "</xml>";
        return toReturn;

    }

    public static Book[] bookIn() {
        Book[] books = new Book[1];
        try (BufferedReader br = new BufferedReader(new FileReader("src/init/books.in"))) {
            br.readLine();
            String line;
            int i = 0;
            int sizeBooks = 0;
            while ((line = br.readLine()) != null) {
                if (sizeBooks >= books.length) {                                       // in caz ca array-ul este plin, maresc dimensiunea
                    books = Arrays.copyOf(books, 2 * books.length);
                }
                String[] splitBook = line.split("###");                          // adaug intr-un array continutul fisierului linie cu linie
                int ID = Integer.parseInt(splitBook[0]);                                //cuvintele sunt despartite de ###
                int pageCount = Integer.parseInt(splitBook[4]);                         // fac cast unde e nevoie
                int languageID = Integer.parseInt(splitBook[6]);
                Calendar createdON = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
                createdON.setTime(sdf.parse(splitBook[7]));
                Book book = new Book(ID, splitBook[1], splitBook[2], splitBook[3], pageCount,                          //creez cartea
                        splitBook[5], languageID, createdON);

                books[i] = book;                                                                                        // adaug cartea in array de carti
                sizeBooks++;
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return books;
    }


    public void resizeAuthors() {
        if (this.sizeAuthors >= this.authors.length) {
            this.authors = Arrays.copyOf(this.authors, this.authors.length + 1);
        }
    }

    public void addAuthor(Author author) {
        resizeAuthors();
        this.authors[sizeAuthors] = author;
        this.sizeAuthors++;
    }


}

