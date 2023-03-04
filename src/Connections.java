import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Connections {
    public static void booksAuthors(Book[] books, Author[] authors) {

        try (BufferedReader br = new BufferedReader(new FileReader("src/init/books-authors.in"))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {

                String[] splitID = line.split("###");
                int IDBook = Integer.parseInt(splitID[0]);                  // Id-ul cartii
                int IDAuthor = Integer.parseInt(splitID[1]);                // Id-ul autorului
                Book book = null;
                for (Book foundBook : books) {
                    if (foundBook != null && foundBook.getID() == IDBook)
                        book = foundBook;                                    // am gasit cartea corespunzatoare Id-ului
                }
                Author author = null;
                for (Author foundAuthor : authors) {
                    if (foundAuthor != null && foundAuthor.getID() == IDAuthor)
                        author = foundAuthor;
                }                                                               // am gasit autorul
                assert book != null;
                book.addAuthor(author);                                         // adaug autorul in array
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void editorialGroupsBooks(Book[] books, EditorialGroup[] editorialGroups) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/init/editorial-groups-books.in"))) {
            br.readLine();
            String line;
            while((line = br.readLine()) != null){
                String[] splitID = line.split("###");
                int IDEditBook = Integer.parseInt(splitID[0]);
                int IDBook = Integer.parseInt(splitID[1]);
                EditorialGroup group = null;
                for(EditorialGroup foundGroup: editorialGroups){
                    if(foundGroup != null && foundGroup.getID() == IDEditBook)
                        group = foundGroup;
                }
                Book book = null;
                for (Book foundBook : books) {
                    if (foundBook != null && foundBook.getID() == IDBook)
                        book = foundBook;
                }
                group.addBook(book);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void publishingBrandsBooks(Book[] books, PublishingBrand[] publishingBrands) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/init/publishing-brands-books.in"))) {
            br.readLine();
            String line;
            while((line = br.readLine()) != null){
                String[] splitID = line.split("###");
                int publisherID = Integer.parseInt(splitID[0]);
                int IDBook = Integer.parseInt(splitID[1]);
                PublishingBrand publish = null;
                for(PublishingBrand foundPublish: publishingBrands){
                    if(foundPublish != null && foundPublish.getID() == publisherID)
                        publish = foundPublish;
                }
                Book book = null;
                for (Book foundBook : books) {
                    if (foundBook != null && foundBook.getID() == IDBook)
                        book = foundBook;
                }
                publish.addBook(book);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void publishingRetailorsCountries(Countries[] countries, PublishingRetailer[] publishingRetailers) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/init/publishing-retailers-countries.in"))) {
            br.readLine();
            String line;
            while((line = br.readLine()) != null){
                String[] splitID = line.split("###");
                int retailerID = Integer.parseInt(splitID[0]);
                int IDCountry = Integer.parseInt(splitID[1]);
                PublishingRetailer retailer = null;
                for(PublishingRetailer foundRetailer: publishingRetailers){
                    if(foundRetailer != null && foundRetailer.getID() == retailerID)
                        retailer = foundRetailer;
                }
                Countries country = null;
                for (Countries foundCountry : countries) {
                    if (foundCountry != null && foundCountry.getID() == IDCountry)
                        country = foundCountry;
                }
                if (retailer != null) {
                    retailer.addCountry(country);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void publishingRetailorsBooks(Book[]books, PublishingRetailer[] publishingRetailers) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/init/publishing-retailers-books.in"))) {
            br.readLine();
            String line;
            while((line = br.readLine()) != null){
                String[] splitID = line.split("###");
                int retailerID = Integer.parseInt(splitID[0]);
                int IDBook = Integer.parseInt(splitID[1]);
                PublishingRetailer retailer = null;
                for(PublishingRetailer foundRetailer: publishingRetailers){
                    if(foundRetailer != null && foundRetailer.getID() == retailerID)
                        retailer = foundRetailer;
                }
                Book book = null;
                for (Book foundBook : books) {
                    if (foundBook != null && foundBook.getID() == IDBook)
                        book = foundBook;
                }
                if (retailer != null) {
                    retailer.addArtifact(book);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void publishingRetailorsEditorialGroups(EditorialGroup[]groups, PublishingRetailer[] publishingRetailers) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/init/publishing-retailers-editorial-groups.in"))) {
            br.readLine();
            String line;
            while((line = br.readLine()) != null){
                String[] splitID = line.split("###");
                int retailerID = Integer.parseInt(splitID[0]);
                int IDGroup = Integer.parseInt(splitID[1]);
                PublishingRetailer retailer = null;
                for(PublishingRetailer foundRetailer: publishingRetailers){
                    if(foundRetailer != null && foundRetailer.getID() == retailerID)
                        retailer = foundRetailer;
                }
                EditorialGroup group = null;
                for(EditorialGroup foundGroup: groups){
                    if(foundGroup != null && foundGroup.getID() == IDGroup)
                        group = foundGroup;
                }
                if (retailer != null) {
                    retailer.addArtifact(group);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void publishingRetailorsBrands(PublishingBrand[]brands, PublishingRetailer[] publishingRetailers) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/init/publishing-retailers-publishing-brands.in"))) {
            br.readLine();
            String line;
            while((line = br.readLine()) != null){
                String[] splitID = line.split("###");
                int retailerID = Integer.parseInt(splitID[0]);
                int IDBrand = Integer.parseInt(splitID[1]);
                PublishingRetailer retailer = null;
                for(PublishingRetailer foundRetailer: publishingRetailers){
                    if(foundRetailer != null && foundRetailer.getID() == retailerID)
                        retailer = foundRetailer;
                }
                PublishingBrand publish = null;
                for(PublishingBrand foundPublish: brands){
                    if(foundPublish != null && foundPublish.getID() == IDBrand)
                        publish = foundPublish;
                }
                if (retailer != null) {
                    retailer.addArtifact(publish);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
