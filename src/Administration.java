import java.util.Arrays;
import java.util.HashSet;

public class Administration {
    Book[] books;
    Language[] languages;
    Author[] authors;
    PublishingRetailer[] publishingRetailers;
    Countries[] countries;
    PublishingBrand[] publishingBrands;
    EditorialGroup[] editorialGroups;

    public Administration() {
        this.books = Book.bookIn();
        this.languages = Language.languageIn();
        this.authors = Author.authorsIn();
        this.publishingRetailers = PublishingRetailer.publishRetailorIn();                          // citesc datele din fisier
        this.publishingBrands = PublishingBrand.publishingBrandsIn();
        this.editorialGroups = EditorialGroup.editorialGroupsIn();
        this.countries = Countries.countriesIn();

        Connections.booksAuthors(this.books, this.authors);
        Connections.editorialGroupsBooks(this.books, this.editorialGroups);
        Connections.publishingBrandsBooks(this.books, this.publishingBrands);
        Connections.publishingRetailorsCountries(this.countries, this.publishingRetailers);
        Connections.publishingRetailorsBooks(this.books, this.publishingRetailers);                    // apelez functiile din clasa Asocieri ca sa fac legaturile
        Connections.publishingRetailorsEditorialGroups(this.editorialGroups, this.publishingRetailers);
        Connections.publishingRetailorsBrands(this.publishingBrands, this.publishingRetailers);
    }


    public Book[] getBooksPublishingRetailer(int publishingRetailerID) {
        PublishingRetailer retailer = null;
        for (PublishingRetailer searchRetailer : this.publishingRetailers) {            // caut retailerul prin array dupa Id-ul primit ca parametru
            if (searchRetailer == null) continue;
            if (searchRetailer.getID() == publishingRetailerID) {
                retailer = searchRetailer;
                break;
            }
        }
        if (retailer == null) return null;
        IPublishingArtifacts[] artifacts = retailer.getPublishingArtifacts();           // stochez obictele care implementeaza interfata
        Book[] books = new Book[1];
        int sizeBooks = 0, i = 0;
        for (IPublishingArtifacts artifact : artifacts) {
            if (sizeBooks >= books.length) {
                books = Arrays.copyOf(books, 2 * books.length);
            }
            if (artifact instanceof Book book) { // book = (Book)artifact;              // daca e de tip book, adaug in array
                books[i] = book;
                i++;
                sizeBooks++;
            } else if (artifact instanceof EditorialGroup group) {
                Book[] booksEditorial = group.getBooks();                               // stochez cartile din editorialGroup
                for (Book book : booksEditorial) {
                    if (existBook(books, book) == false) {                              // verific daca cartea este deja in array
                        books[i] = book;
                        i++;
                        sizeBooks++;
                    }
                }
            } else if (artifact instanceof PublishingBrand publishBrand) {
                Book[] booksPublish = publishBrand.getBooks();
                for (Book book : booksPublish) {
                    if (existBook(books, book) == false) {
                        books[i] = book;
                        i++;
                        sizeBooks++;
                    }
                }
            }
        }
        return books;
    }

    public static boolean existBook(Book[] books, Book book) {
        int i;
        for (i = 0; i < books.length; i++) {
            if (books[i] == book) {
                return true;
            }
        }
        return false;
    }

    public Language[] getLanguagesForPublishingRetailer(int publishingRetailerID) {
        Language[] languages = this.languages;
        PublishingRetailer retailer = null;
        for (PublishingRetailer searchRetailer : this.publishingRetailers) {
            if (searchRetailer.getID() == publishingRetailerID) {
                retailer = searchRetailer;
                break;
            }
        }
        IPublishingArtifacts[] artifacts = retailer.getPublishingArtifacts();
        Language[] languages2 = new Language[1];
        int sizeLanguage = 0, i = 0;
        for (IPublishingArtifacts artifact : artifacts) {
            if (sizeLanguage >= languages2.length) {
                languages2 = Arrays.copyOf(languages2, 2 * languages2.length);          // maresc dimensiunea array-ului
            }
            if (artifact instanceof Book book) {
                int languageFromBook = book.getLanguageID();                                     // stochez Id-ul limbii din carte
                for (Language language : languages) {
                    if (language.getID() == languageFromBook) {                                 // caut in array acea limba
                        if (existLanguage(languages2, language) == false) {                     // daca nu exista o adaug
                            languages2[i] = language;
                            i++;
                            sizeLanguage++;
                        }
                    }
                }
            } else if (artifact instanceof EditorialGroup group) {
                Book[] booksFromGroups = group.getBooks();
                for (Book book : booksFromGroups) {
                    int languageFromGroups = book.getID();
                    for (Language language : languages) {
                        if (language.getID() == languageFromGroups) {
                            if (existLanguage(languages2, language)) {
                                languages2[i] = language;
                                i++;
                                sizeLanguage++;
                            }
                        }
                    }
                }
            } else if (artifact instanceof PublishingBrand publishBrand) {
                Book[] booksPublish = publishBrand.getBooks();
                for (Book book : booksPublish) {
                    int languageFromBrand = book.getID();
                    for (Language language : languages) {
                        if (language.getID() == languageFromBrand) {
                            if (existLanguage(languages2, language)) {
                                languages2[i] = language;
                                i++;
                                sizeLanguage++;
                            }
                        }
                    }
                }
            }
        }
        return languages2;
    }

    public static boolean existLanguage(Language[] languages, Language language) {
        int i;
        for (i = 0; i < languages.length; i++) {
            if (languages[i] == language)
                return true;
        }
        return false;
    }

    public static boolean existCountry(Countries[] countries, Countries country) {
        int i;
        for (i = 0; i < countries.length; i++) {
            if (countries[i] == country)
                return true;
        }
        return false;
    }

    private PublishingRetailer getPubById(int id) {

        for (PublishingRetailer publishingRetailer : this.publishingRetailers) {

            if (publishingRetailer.getID() == id) return publishingRetailer;
        }

        return null;
    }

    public Countries[] getCountriesForBookID(int bookID) {
        Countries[] countries = new Countries[1];
        int sizeCountries = 0, i = 0;
        for (PublishingRetailer publishingRetailer : this.publishingRetailers) {

            if (publishingRetailer == null) continue;
            Book[] books = getBooksPublishingRetailer(publishingRetailer.getID());                      // stochez cartile retailerului
            if (books == null) continue;

            for (Book book : books) {
                if (book != null && book.getID() == bookID) {                                            // caut cartea dupa Id-ul primit ca parametru
                    Countries[] countries1 = this.getPubById(publishingRetailer.getID()).getCountries(); // stochez tarile retailerului
                    for (Countries country : countries1) {
                        if (!existCountry(countries, country)) {                                          // daca nu exista tara
                            if (sizeCountries >= countries.length) {
                                countries = Arrays.copyOf(countries, 2 * countries.length);     // maresc array-ul
                                countries[i] = country;                                                  // adaug tara in array
                                i++;
                                sizeCountries++;
                            } else {
                                countries[i] = country;
                                i++;
                                sizeCountries++;
                            }
                        }
                    }
                }
            }
        }
        return countries;
    }

    public Book[] getCommonBooksRetailersIDs(int retailerID1, int retailerID2) {
        Book[] books1 = getBooksPublishingRetailer(retailerID1);
        Book[] books2 = getBooksPublishingRetailer(retailerID2);
        Book[] bookToReturn = new Book[1];
        HashSet<Book> commonBooks = new HashSet<>();
        int i = 0;
        for (Book book1 : books1)     //O(n)                           //parcurg primul array de carti
            commonBooks.add(book1);                                    //le adaug in hashset
        for (Book book2 : books2) {   //O(m)                           //parcurg al 2 array
            if (commonBooks.contains(book2)) {                         //verific daca cartea se afla in hashset
                if (i >= bookToReturn.length) {
                    bookToReturn = Arrays.copyOf(bookToReturn, 2 * bookToReturn.length);
                }
                bookToReturn[i++] = book2;                              //o adaug
            }
        }


        return bookToReturn;
    }

    public Book[] getAllBooksForRetailerIDs(int retailerID1, int retailerID2) {
        Book[] books1 = getBooksPublishingRetailer(retailerID1);
        Book[] books2 = getBooksPublishingRetailer(retailerID2);
        HashSet<Book> allBooks = new HashSet<>();

        for (Book book1 : books1)
            allBooks.add(book1);
                                                  //adaug cartile de la ambii retaileri in hashset
        for (Book book2 : books2) {
            allBooks.add(book2);
        }

        Book[] books = new Book[allBooks.size()];
        int size = 0;
        for (Book book : allBooks)
            if (book != null)
                books[size++] = book;             //le adaug in array ca sa le returnez

        return books;
    }
}


