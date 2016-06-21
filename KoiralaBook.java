/**
   @author ROSHAN KOIRALA
   @version 1.0
   @since 11/24/2014
  
   A Java Program to maintain all the information of Books
 */


// importing required packages
import java.util.*;

/**
   A  class that stores all the information corresponding to each book of the library
 */
public class KoiralaBook
{
   private String title;          // Title of the book
   private String author;         // Author of the book
   private String publisher;      // Publisher of the book
   private String year;           // Year of Publication of the book
   private String isbn;           // ISBN Number of the book

   
   /**
   The constructor  initializes the all the instances, passeed as arguments, of book object
   @param title The title of the book
   @param author The author of the book
   @param publisher The publisher of the book
   @param year The Year of publication of the book
   @param isbn The ISBN number of the book
  */
   public KoiralaBook(String title, String author, String publisher, String year, String isbn)
   {
      this.title = title;
      this.author = author;
      this.publisher = publisher;
      this.year = year;
      this.isbn = isbn;
   } 

   /**
      Returns the title of the book
      @return The value in the title field
   */
   public String getTitle()
   {
      return title;
   }
   
   /**
      Returns the author of the book
      @return The value in the author field
   */
   public String getAuthor()
   {
      return author;
   }
  
  /**
      Returns the publisher of the book
      @return The value in the publisher field
   */
   public String getPublisher()
   {
      return publisher;
   }
  
  /**
      Returns the year of publication of the book
      @return The value in the year field
   */
   public String getYear()
   {
      return year;
   }
   
   /**
      Returns the ISBN number of the book
      @return The value in the isbn field
   */
   public String getIsbn()
   {
      return isbn;
   }

   /**
      Checks the validity of the ISBN number of the book object
      @return True is the book object has valid ISBN, false otherwise
   */
   public boolean hasValidIsbn()
   {
      int sum=0;
      int checkSum;
      String lastDigit;
      String plainISBN="";
      Scanner isbnScan = new Scanner(isbn);
      isbnScan.useDelimiter("-");
      
      // Removing all the Dash(-) from the ISBN String and converting the ISBN to plain ISBN.
      while(isbnScan.hasNext())
      {
         plainISBN+= isbnScan.next();
      }
   
      // Calculating the sum from the first nine digits of the ISBN  
      for ( int i = 1 ; i <= 9 ; i++)
      {
         sum+= (Integer.parseInt(plainISBN.substring(i-1, i))*i) ;
      }
      checkSum = sum%11;
      if( checkSum == 10)
         lastDigit = "X"; 
      else
         lastDigit = ""+checkSum;
     
     // Checking if the calculated last character is equal to the last character of the plain ISBN
      if(lastDigit.equalsIgnoreCase(plainISBN.substring(9,10)))
         return true;
      return false;
   
   }

   /**
      Displays  the all the contents of the book object
      @return The String containing all the information of the book object
   */
   public String toString()
   {
      String result;
      result =("Title               : "+ title+"\n"+"Author              : "+author+"\n"+"Publisher           : "+publisher+"\n"+"Year of Publication : "+year+"\n"+"ISBN                : "+isbn);
      return result;
   }
   
   
   /**
      Checks if a book object is equal to the other based on the ISBN number
      @return True if the two book objects are equal, false otherwise
   */
   public boolean equals(Object obj) 
   {
      if (obj instanceof KoiralaBook) 
      {
         KoiralaBook other = (KoiralaBook) obj;
         return isbn.equals(other.isbn);
      }
      return false;
   }
  
  /**
      Returns the Hash Code for book object based on the ISBN number of the book
      @return The integer representing the hash code of the ISBN number
   */
   public int hashCode() 
   {
      return isbn.hashCode();
   }

}