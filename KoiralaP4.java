/**
   @author ROSHAN KOIRALA
   @version 1.0
   @since 11/24/2014
  
   A Java Program to maintain and query the collection of books for a library
 */


//importing required packages
import java.util.*;
import java.io.*;


/**
   A  class that stores book information from a file into a collection, 
   and allows user to search, remove, loan, and display the books in the collection
 */
public class KoiralaP4
{

   private static Map<String, KoiralaBook> library = new HashMap<String, KoiralaBook>();            // A map to store book objects 
   private static Map<String, KoiralaBook> loanedBooks = new HashMap<String, KoiralaBook>();        // A map to store loaned book objects

  
  
  /**
   Stores the book information from a file into the map collection, processes a number of 
   commands from another file on book object, and runs the program
   @param args The argument to be passed into the main method
  */

   public static void main(String[] args) throws IOException
   {
     
      Scanner fileScan = new Scanner(new File("books.txt"));   // Scanner to scan the file
      KoiralaBook temp;
     
      // Storing the book information from the file into library map
      while (fileScan.hasNextLine())
      {
         int count = 0;
         String[] information = new String[5];
      
         String line = fileScan.nextLine();
         Scanner lineScan = new Scanner(line);
         lineScan.useDelimiter(",");
      
         while(lineScan.hasNext())
         {
            information[count] = lineScan.next();
            count++;
         }
         temp =  new KoiralaBook( information[0],information[1],information[2],information[3],information[4]);   // Creating a new book object
      
         // Checking if the book has valid ISBN and if it already has a duplicate in the collection.
         if( temp.hasValidIsbn() && !library.containsKey(temp.getIsbn()))
         {
            library.put(temp.getIsbn(), temp);
         }
      } // End of reading file and Storing book in map
      
      
      
      // Displaying the contents of Library map collection
      System.out.println("-----------------------BOOK COLLECTION---------------------------------\nISBN\t\tTITLE");
      Iterator iter = library.keySet().iterator();  // Iterates over the key set of the library map
      while(iter.hasNext())
      {
         KoiralaBook tempBook;
         tempBook = library.get(iter.next());
         System.out.println(tempBook.getIsbn()+"\t"+tempBook.getTitle());
      }      
      System.out.println("-----------------------------------------------------------------------");
      System.out.println();
      // End of Displaying
      
      
      // Reading a number of command from another file, and calling requried methods to perfom the task
      Scanner inputScan = new Scanner( new File("input.txt"));
      while ( inputScan.hasNextLine())
      {
         String actionKeyword  = inputScan.next();
         String argument = inputScan.next();
         
         switch(actionKeyword)  // Checking what action keyword is read form file
         {
            case "display":
               System.out.println("PROCESSING COMMAND ..."+actionKeyword+" "+argument);
               display(argument);
               System.out.println();
               break;
         
            case "search":
               System.out.println("PROCESSING COMMAND ..."+actionKeyword+" "+argument);
               search(argument);
               System.out.println();
               break;
         
            case "remove":
               System.out.println("PROCESSING COMMAND ..."+actionKeyword+" "+argument);
               remove(argument);
               System.out.println();
               break;
         
            case"checkout":
               System.out.println("PROCESSING COMMAND ..."+actionKeyword+" "+argument);
               checkout(argument);
               System.out.println();
               break;
         
            case"return":
               System.out.println("PROCESSING COMMAND ..."+actionKeyword+" "+argument);
               returnBook(argument);
               System.out.println();
               break;
         
            case "show":
               System.out.println("PROCESSING COMMAND ..."+actionKeyword+" "+argument);
               showAll();
               System.out.println();
               break;
         }
      
      
      }
   } // End of Main Method
   
   
  /**
   Displays all of the information of the book with the given isbn, if the book exists
   @param isbn The isbn of the book whose information is to be displayed
  */
   private static void display(String isbn)
   {
      if(library.get(isbn) == null)
         System.out.println("Book with ISBN "+isbn+"  does not exist.");
      else
      {
         System.out.println(library.get(isbn));
      } 
   }
   
   
  /**
   Finds and displays information of all books with titles containing the given keyword
   @param keyword The keyword which is to be searched in the titles of all book
  */
   private static void search(String keyword)
   {
      boolean none = true;
      Iterator iter = library.keySet().iterator();
      System.out.println("BOOKS WITH TITLE CONTAINING "+ keyword);
      while(iter.hasNext())
      {
         KoiralaBook tempBook = library.get(iter.next());
         if(tempBook.getTitle().toLowerCase().contains(keyword.toLowerCase()))
         {
            System.out.println(tempBook.getIsbn()+"\t"+tempBook.getTitle());
            none = false;   
         }
         
      }
      if (none)
         System.out.println("None");
   }
   
   
  /**
   Removes the book corresponding to the given isbn from the library
   @param isbn The isbn number of the book which is to be removed
  */
   private static void remove(String isbn)
   {
      System.out.println("Removing ... "+library.remove(isbn).getTitle());
   }
   
   
  /**
   Checks out the book corresponding to the given isbn as being loadned, if it available
   @param isbn The isbn number of the book which is to be checked out for loaning
  */
   private static void checkout(String isbn)
   {
      if ( library.get(isbn) != null)
      {
      
         KoiralaBook loaned = library.remove(isbn);
         loanedBooks.put(isbn,loaned);
         System.out.println("Checking out ... "+ isbn+" "+ loaned.getTitle());
      }
      else
      {
         if(loanedBooks.get(isbn)!= null)
         {
            System.out.println(isbn+" "+loanedBooks.get(isbn).getTitle()+" is not available.");
         }
         else
            System.out.println("Book with ISBN "+ isbn+" does not exist.");
      }
    }
   
   
  /**
   Returns the book with the given isbn number back to library
   @param isbn The isbn number of the book which is to be returned back
  */ 
   private static void returnBook(String isbn)
   {
      if ( loanedBooks.get(isbn)!= null)
      {
         KoiralaBook returned = loanedBooks.remove(isbn);
         library.put(isbn, returned);
         System.out.println("Returning back ... "+ isbn+" "+ returned.getTitle());
      }
      else
         System.out.println("Book with ISBN "+isbn+" does not exist.");
   }
   
   
    
  /**
   Displays the title and ISBN about all the books in the library in tabular format
  */
   private static void showAll()
   {
      System.out.println("------------------------ALL BOOKS IN THE LIBRARY--------------------------\nISBN\t\tTITLE");
      
      // Displaying from Library Map Collection
      Iterator iter1 = library.keySet().iterator();
      while(iter1.hasNext())
      {
         KoiralaBook tempBook1;
         tempBook1 = library.get(iter1.next());
         System.out.println(tempBook1.getIsbn()+"\t"+tempBook1.getTitle());
      }  
       
      // Displaying from loanedBooks Map Collection 
      Iterator iter2 = loanedBooks.keySet().iterator();
      while ( iter2.hasNext())
      {
         KoiralaBook tempBook2;
         tempBook2 = loanedBooks.get(iter2.next());
         System.out.println(tempBook2.getIsbn()+"\t"+tempBook2.getTitle());
      }
      System.out.println("--------------------------------------------------------------------------");
   }

} // End of Class