# LibroData

## General technical details
```
- In this project, I created 10 classes and one interface. In each class, I created the
necessary variables, constructors, getter and setter methods, as well as functions
for reading data from files, which I named 'classname'In.

- In the classes (Book, EditorialGroup, PublishingBrand) that implement the 
'IPublishingArtifacts' interface, I also created the 'Publish' function, which 
displays the data about that class in XML format.
```

## Connections

**The next part of the project involves forming connections between classes**. For this 
purpose, I created the ***'Associations' class***, where I created separate methods for 
each association. The idea behind each method is roughly the same, but the parameters
it receives differ depending on which objects are being associated. Using a BufferedReader,
I read the IDs from the file and stored them in two integer variables. With two 'for 
each' loops, I searched for the objects I needed based on the those two variables where I 
stored the IDs. Then, using the 'addObject' function, I added the object to an array 
in the instance of the class that holds that array.

## Implementation of some methods(explication)
- **The 'getBooksPublishingRetailer'** method returns a list of books from a retailer, 
receiving the 'IdRetailer' as a parameter. Using a 'for each' loop, I search for the
retailer and store it in a variable. Then, I store the objects that implement the 
interface in an array. After that, with another 'for each' loop, I go through those 
objects, check their type, access the books, and add them to the array.


- **The 'getCountriesForBookID'** method returns the countries where a book can be 
distributed. Taking into consideration that a book can be owned by multiple retailers,
I use a 'for each' loop to go through the retailers, and with a method, I store their
books. Then, I search for the book in that array of books, and using a method that 
returns the publishing retailer based on their ID, I access the 'countries' field. 
I check that the languages are not repeated in the array, add them, and then return 
the result.


- **The 'getCommonBooksRetailersIDs'** method returns the common books between two 
retailers. I use a HashSet to achieve a time complexity of O(n+m). With a 'for' loop,
I go through the books of the first retailer, add them to the HashSet. Then, with 
another 'for' loop, I go through the second array, check if the book is also in the 
HashSet, and add it to an array, which is then returned.


- **The 'getAllBooksForRetailerIDs'** method returns all the books of two retailers, 
without repetition. With two 'for' loops, I go through the arrays, add the books to 
a HashSet, and then return them as an array. A HashSet has the property of containing
each element only once.