import books.web.Books;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import books.domain.Book;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.closeTo;

public class BooksRESTTest {

    @BeforeClass
    public static void setup() {
        RestAssured.port = Integer.valueOf(8080);
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "";
    }

    @Test
    public void testGetOneBook() {
        // add the book to be fetched
        Book book = new Book("878","Book 123", 18.95, "Joe Smith");
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/books").then()
                .statusCode(200);
        // test getting the book
        given()
                .when()
                .get("books/878")
                .then()
                .contentType(ContentType.JSON)
                .and()
                .body("isbn",equalTo("878"))
                .body("title",equalTo("Book 123"))
                .body("price",equalTo(18.95f))
                .body("author",equalTo("Joe Smith"));
        //cleanup
        given()
                .when()
                .delete("books/878");
    }

    @Test
    public void testDeleteOneBook(){
        Book book = new Book("878","Book 123", 18.95, "Joe Smith");
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/books").then()
                .statusCode(200);

        given()
                .when()
                .delete("books/878");
        given()
                .when()
                .get("books/878").then()
                .statusCode(404);
    }

    @Test
    public void testAddOneBook(){
        Book book = new Book("878","Book 123", 18.95, "Joe Smith");
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/books").then()
                .statusCode(200);
        given()
                .when()
                .get("books/878")
                .then()
                .statusCode(200)
                .and()
                .body("isbn",equalTo("878"))
                .body("title",equalTo("Book 123"))
                .body("price",equalTo(18.95f))
                .body("author",equalTo("Joe Smith"));
        //cleanup
        given()
                .when()
                .delete("books/878");

    }

    @Test
    public void testUpdateOneBook(){
        Book book = new Book("878","Book 123", 18.95, "Joe Smith");
        Book updateBook = new Book("878","Book 456", 18.95, "Joe Smith");
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/books").then()
                .statusCode(200);
        given()
                .contentType("application/json")
                .body(updateBook)
                .when().post("/books").then()
                .statusCode(200);
        given()
                .when()
                .get("books/878")
                .then()
                .statusCode(200)
                .and()
                .body("isbn",equalTo("878"))
                .body("title",equalTo("Book 456"))
                .body("price",equalTo(18.95f))
                .body("author",equalTo("Joe Smith"));
        //cleanup
        given()
                .when()
                .delete("books/878");

    }

    @Test
    public void testSearchAllBooks(){

        // Given: Add 2 books
        Book book1 = new Book("878", "Book 123", 18.95, "Joe Smith");
        Book book2 = new Book("123", "Book 456", 10.95, "Joe Smith");

        given()
                .contentType("application/json")
                .body(book1)
                .when().post("/books")
                .then().statusCode(200);

        given()
                .contentType("application/json")
                .body(book2)
                .when().post("/books")
                .then().statusCode(200);

        // When: Search by author and then
        given()
                .queryParam("author", "Joe Smith") // Make sure your controller doesn't strip quotes
                .when().get("/books")
                .then()
                .statusCode(200)
                .body("books.isbn", hasItems("878", "123"))
                .body("books.title", hasItems("Book 123", "Book 456"))
                .body("books.price", hasItems(18.95f, 10.95f))
                .body("books.author", everyItem(equalTo("Joe Smith"))); // all authors should be "Joe Smith"

        // Cleanup
        given().when().delete("/books/878");
        given().when().delete("/books/123");

    }


}
