import java.io.*;

public class Teste {

    public void getBooksPublishRetailerTest(int IDRetailer, String file) {
        Administration a = new Administration();
        Book[] books = a.getBooksPublishingRetailer(IDRetailer);
        try (BufferedWriter br = new BufferedWriter(new FileWriter("src/outputs/"+file))) {
            for (int i = 0; i < books.length; i++) {
                if (books[i] != null) {
                    br.write(books[i].Publish() + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getLanguagesForPublishingRetailerTest(int IDRetailer, String file) {
        Administration a = new Administration();
        Language[] languages = a.getLanguagesForPublishingRetailer(IDRetailer);
        try (BufferedWriter br = new BufferedWriter(new FileWriter("src/outputs/"+file))) {
            for (int i = 0; i < languages.length; i++) {
                if (languages[i] != null) {
                    br.write(languages[i].getID() + " " + languages[i].getName() + " " + languages[i].getCode() + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getCountriesForBookIDTest(int IDBook, String file) {
        Administration a = new Administration();
        Countries[] tari = a.getCountriesForBookID(IDBook);
        try (BufferedWriter br = new BufferedWriter(new FileWriter("src/outputs/"+file))) {

            for (int j = 0; j < tari.length; j++) {
                if (tari[j] != null) {
                    br.write(tari[j].getCountryCode() + " " + tari[j].getID() + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getAllBooksForRetailerIDsTest(int IDRetailer1, int IDRetailer2, String file) {
        Administration a = new Administration();
        Book[] books = a.getAllBooksForRetailerIDs(IDRetailer1, IDRetailer2);
        try (BufferedWriter br = new BufferedWriter(new FileWriter("src/outputs/"+file))) {
            br.write(books.length + "\n");
            for (int i = 0; i < books.length; i++) {
                if (books[i] != null) {
                    br.write(books[i].Publish() + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getCommonBooksRetailersIDsTest(int IDRetailer1, int IDRetailer2, String file){
        Administration a = new Administration();
        Book[] books = a.getCommonBooksRetailersIDs(IDRetailer1, IDRetailer2);
        try (BufferedWriter br = new BufferedWriter(new FileWriter("src/outputs/"+file))) {
            for (int i = 0; i < books.length; i++) {
                if (books[i] != null) {
                    br.write(books[i].Publish() + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Teste teste = new Teste();
        teste.getBooksPublishRetailerTest(10, "getBooksPublishRetailerTest1");
        teste.getBooksPublishRetailerTest(2, "getBooksPublishRetailerTest2");
        teste.getBooksPublishRetailerTest(3, "getBooksPublishRetailerTest3");
        teste.getBooksPublishRetailerTest(4, "getBooksPublishRetailerTest4");
        teste.getBooksPublishRetailerTest(5, "getBooksPublishRetailerTest5");


        teste.getLanguagesForPublishingRetailerTest(6,"getLanguagesForPublishingRetailerTests1");
        teste.getLanguagesForPublishingRetailerTest(7, "getLanguagesForPublishingRetailerTests2");
        teste.getLanguagesForPublishingRetailerTest(8, "getLanguagesForPublishingRetailerTests3");
        teste.getLanguagesForPublishingRetailerTest(9, "getLanguagesForPublishingRetailerTests4");
        teste.getLanguagesForPublishingRetailerTest(10, "getLanguagesForPublishingRetailerTests5");

        teste.getCountriesForBookIDTest(204, "getCountriesForBookIDTest1");
        teste.getCountriesForBookIDTest(224, "getCountriesForBookIDTest2");
        teste.getCountriesForBookIDTest(513, "getCountriesForBookIDTest3");
        teste.getCountriesForBookIDTest(275, "getCountriesForBookIDTest4");
        teste.getCountriesForBookIDTest(291, "getCountriesForBookIDTest5");

        teste.getCommonBooksRetailersIDsTest(1,2, "getCommonBooksRetailersIDsTest1");
        teste.getCommonBooksRetailersIDsTest(33,9, "getCommonBooksRetailersIDsTest2");
        teste.getCommonBooksRetailersIDsTest(3,4, "getCommonBooksRetailersIDsTest3");
        teste.getCommonBooksRetailersIDsTest(5,6, "getCommonBooksRetailersIDsTest4");
        teste.getCommonBooksRetailersIDsTest(10,4, "getCommonBooksRetailersIDsTest5");

        teste.getAllBooksForRetailerIDsTest(10,4, "getAllBooksForRetailerIDsTest1");
        teste.getAllBooksForRetailerIDsTest(11,12, "getAllBooksForRetailerIDsTest2");
        teste.getAllBooksForRetailerIDsTest(13,25, "getAllBooksForRetailerIDsTest3");
        teste.getAllBooksForRetailerIDsTest(20,16, "getAllBooksForRetailerIDsTest4");
        teste.getAllBooksForRetailerIDsTest(22,17, "getAllBooksForRetailerIDsTest5");


    }
}
