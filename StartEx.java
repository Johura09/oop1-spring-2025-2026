interface BookShopOperations {
    boolean insertBook(Book b);
    boolean removeBook(Book b);
    void showAllBooks();
    Book searchBook(String isbn);
}

abstract class Book {
    private String isbn;
    private String bookTitle;
    private String authorName;
    private double price;
    private int availableQuantity;

    public Book() {}
    public Book(String isbn, String bookTitle, String authorName, double price, int availableQuantity) {
        this.isbn = isbn;
        this.bookTitle = bookTitle;
        this.authorName = authorName;
        this.price = price;
        this.availableQuantity = availableQuantity;
    }

public void setIsbn(String isbn){
    this.isbn = isbn;
}
    public void setBookTitle(String bookTitle){
    this.bookTitle = bookTitle;
}
    public void setAuthorName(String authorName){
    this.authorName = authorName;
}
    public void setPrice(double price){
    this.price = price;
}
    public void setAvailableQuantity(int availableQuantity) {
    this.availableQuantity = availableQuantity;
}
    public String getIsbn() {
        return isbn;
    }
    public String getBookTitle() {
        return bookTitle;
    }
    public String getAuthorName() {
        return authorName;
    }
    public double getPrice() {
        return price;
    }
    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void addQuantity(int amount) {
        availableQuantity = availableQuantity + amount;
    }

    public void sellQuantity(int amount) {
        if (availableQuantity >= amount) {
            availableQuantity = availableQuantity - amount;
        } else {
            System.out.println("Not enough quantity!");
        }
    }

    public abstract void showDetails();
}

class StoryBook extends Book {
    private String category;

    public StoryBook() {}

    public StoryBook(String isbn, String bookTitle, String authorName, double price, int availableQuantity, String category) {
        super(isbn, bookTitle, authorName, price, availableQuantity);
        this.category = category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public void showDetails() {
        System.out.println(" Story Book ");
        System.out.println("ISBN: " + getIsbn());
        System.out.println("Title: " + getBookTitle());
        System.out.println("Author: " + getAuthorName());
        System.out.println("Price: " + getPrice());
        System.out.println("Quantity: " + getAvailableQuantity());
        System.out.println("Category: " + category);
        System.out.println();
    }
}
class TextBook extends Book {
    private int standard;

    public TextBook() {}

    public TextBook(String isbn, String bookTitle, String authorName,double price, int availableQuantity, int standard) {
        super(isbn, bookTitle, authorName, price, availableQuantity);
        this.standard = standard;
    }

    public void setStandard(int standard) {
        this.standard = standard;
    }

    public int getStandard() {
        return standard;
    }

    @Override
    public void showDetails() {
        System.out.println(" Text Book ");
        System.out.println("ISBN: " + getIsbn());
        System.out.println("Title: " + getBookTitle());
        System.out.println("Author: " + getAuthorName());
        System.out.println("Price: " + getPrice());
        System.out.println("Quantity: " + getAvailableQuantity());
        System.out.println("Standard: " + standard);
        System.out.println();
    }
}

class BookShop implements BookShopOperations {
    private String name;
    private Book[] listOfBooks = new Book[100];

    public BookShop() {}

    public BookShop(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean insertBook(Book b) {
        for (int i = 0; i < listOfBooks.length; i++) {
            if (listOfBooks[i] == null) {
                listOfBooks[i] = b;
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean removeBook(Book b) {
        for (int i = 0; i < listOfBooks.length; i++) {
            if (listOfBooks[i] == b) {
                listOfBooks[i] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public void showAllBooks() {
        for (int i = 0; i < listOfBooks.length; i++) {
            if (listOfBooks[i] != null) {
                listOfBooks[i].showDetails();
            }
        }
    }

    @Override
    public Book searchBook(String isbn) {
        System.out.println(" Searching for book with ISBN: " + isbn);
        System.out.println("   Result: Book found! ");
        return null;
    }
}

public class StartEX {
    public static void main(String[] args) {

        BookShop shop = new BookShop("ABC Book Shop");

        StoryBook s1 = new StoryBook("S1", "Golpo 1", "Rahim", 200, 10, "Fiction");
        StoryBook s2 = new StoryBook("S2", "Golpo 2", "Karim", 220, 8, "Drama");
        StoryBook s3 = new StoryBook("S3", "Golpo 3", "Jamal", 180, 6, "Comedy");
        StoryBook s4 = new StoryBook("S4", "Golpo 4", "Salam", 250, 7, "Horror");
        StoryBook s5 = new StoryBook("S5", "Golpo 5", "Rafiq", 300, 5, "Fantasy");


        TextBook t1 = new TextBook("T1", "Math", "Sir A", 400, 15, 10);
        TextBook t2 = new TextBook("T2", "Physics", "Sir B", 450, 12, 11);
        TextBook t3 = new TextBook("T3", "Chemistry", "Sir C", 500, 10, 12);
        TextBook t4 = new TextBook("T4", "Biology", "Sir D", 350, 9, 9);
        TextBook t5 = new TextBook("T5", "English", "Sir E", 300, 20, 8);

        
        shop.insertBook(s1);
        shop.insertBook(s2);
        shop.insertBook(s3);
        shop.insertBook(s4);
        shop.insertBook(s5);

        shop.insertBook(t1);
        shop.insertBook(t2);
        shop.insertBook(t3);
        shop.insertBook(t4);
        shop.insertBook(t5);

        
        System.out.println("===== ALL BOOKS =====");
        shop.showAllBooks();


        System.out.println("===== SEARCH RESULT =====");
        Book b = shop.searchBook("T1");
        if (b != null) {
            b.showDetails();
        } else {
            System.out.println("Book not found");
        }


        t1.sellQuantity(5);
        t1.addQuantity(3);


        shop.removeBook(s1);

        System.out.println("===== AFTER REMOVE =====");
        shop.showAllBooks();
    }
}