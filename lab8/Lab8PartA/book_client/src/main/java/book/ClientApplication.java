package book;

import book.client.BookClient;
import book.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientApplication implements CommandLineRunner {
	@Autowired
	BookClient bookClient;

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("========== ADD BOOKS ==========");
		bookClient.addBook(new Book("1111", "Robert Martin", "Clean Code", 34.99));
		bookClient.addBook(new Book("2222", "Joshua Bloch", "Effective Java", 39.99));

		System.out.println("\n========== GET ALL BOOKS ==========");
		bookClient.getAllBooks();

		System.out.println("\n========== GET BOOK BY ISBN ==========");
		bookClient.getBook("1111");

		System.out.println("\n========== UPDATE BOOK ==========");
		bookClient.updateBook("1111", new Book("1111", "Robert Martin", "Clean Code Revised", 44.99));
		bookClient.getBook("1111");

		System.out.println("\n========== SEARCH BOOKS BY AUTHOR ==========");
		bookClient.searchBooksByAuthor("Joshua Bloch");

		System.out.println("\n========== DELETE BOOK ==========");
		bookClient.deleteBook("2222");
		System.out.println("\n========== GET ALL BOOKS ==========");
		bookClient.getAllBooks();
	}
}
