package bookstoread;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.Year;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("A bookshelf")
@ExtendWith(BooksParameterResolver.class)
public class BookShelfSpec {

  private BookShelf shelf;
  private Book effectiveJava;
  private Book codeComplete;
  private Book mythicalManMonth;
  private Book cleanCode;

  @BeforeEach
  void init(Map<String, Book> books) throws Exception {
    shelf = new BookShelf();
    this.effectiveJava = books.get("Effective Java");
    this.codeComplete = books.get("Code Complete");
    this.mythicalManMonth = books.get("The Mythical Man-Month");
    this.cleanCode = books.get("Clean Code");
  }

  private BookShelfSpec(TestInfo testInfo) {
    System.out.println("Working on test " + testInfo.getDisplayName());
  }

  /*
  @Test
  @DisplayName("is empty when no book is added to it")
  public void shelf_empty_when_no_book_added(TestInfo testInfo) throws Exception {
    System.out.println("Working on test " + testInfo.getDisplayName());
//    BookShelf shelf = new BookShelf();
    List<String> books = shelf.books();
    assertTrue(books.isEmpty(), () -> "BookShelf should be empty.");
  }

  @Test
  void nullAssertionTest() {
    String str = null;
    assertNull(str);
    assertNull(str, "str should be null");
    assertNull(str, () -> "str should be null");
  }

  @Test
  void shouldCheckForEvenNumbers() {
    int number = new Random(10).nextInt();
    assertTrue(() -> number % 2 == 0, number + " is not an even number.");

    BiFunction<Integer, Integer, Boolean> divisible = (x, y) -> x % y == 0;
    Function<Integer, Boolean> multipleOf2 = (x) -> divisible.apply(x, 2);
    assertTrue(() -> multipleOf2.apply(number), () -> " 2 is not factor of " + number);

    List<Integer> numbers = Arrays.asList(1, 1, 1, 1, 2);
    assertTrue(() -> numbers.stream().distinct().anyMatch(BookShelfSpec::isEven), "Did not find an even number in the list");
  }

  static boolean isEven(int number) {
    return number % 2 == 0;
  }

//  @Test
//  void thisTestShouldFail() {
//    fail(() -> "This test should fail");
//  }

  @Test
  public void shelfToStringShouldPrintBookCountAndTitles() throws Exception {
//    BookShelf shelf = new BookShelf();
    List<String> books = new ArrayList<>();
    books.add("The Phoenix Project");
    books.add("Java 8 in Action");
    String shelfStr = books.toString();
    assertAll(() -> assertTrue(shelfStr.contains("The Phoenix Project"), "1st book title missing"),
            () -> assertTrue(shelfStr.contains("Java 8 in Action"), "2nd book title missing "),
            () -> assertTrue(shelfStr.contains("2 books found"), "Book count missing"));
  }

  @Test
  void bookshelfContainsTwoBooksWhenTwoBooksAdded() {
//    BookShelf shelf = new BookShelf();
    shelf.add("Effective Java", "Code Complete");
    List<String> books = shelf.books();
    assertEquals(2, books.size(), () -> "BookShelf should have two books.");
  }

  @Test
  public void emptyBookShelfWhenAddIsCalledWithoutBooks() {
//    BookShelf shelf = new BookShelf();
    shelf.add();
    List<String> books = shelf.books();
    assertTrue(books.isEmpty(), () -> "BookShelf should be empty");
  }

  @Test
  void booksReturnedFromBookShelfIsImmutableForClient() {
//    BookShelf shelf = new BookShelf();
    shelf.add("Effective Java", "Code Complete");
    List<String> books = shelf.books();
    try {
      books.add("The Mythical Man-Month");
      fail(() -> "Should not be able to add book to books");
    } catch (Exception e) {
      assertTrue(e instanceof UnsupportedOperationException, () -> "Should throw UnsupportedOperationException.");
    }
  }

  @Test
  void bookshelfArrangedByBookTitle() {
    shelf.add("Effective Java", "Code Complete", "The Mythical Man-Month");
    List<String> books = shelf.arrange();
    assertEquals(Arrays.asList("Code Complete", "Effective Java", "The Mythical Man-Month"),
            books,
            () -> "Books in a bookshelf should be arranged lexicographically by book title");
  }

  @Test
  void booksInBookShelfAreInInsertionOrderAfterCallingArrange() {
    shelf.add("Effective Java", "Code Complete", "The Mythical Man-Month");
    shelf.arrange();
    List<String> books = shelf.books();
    assertEquals(Arrays.asList("Effective Java", "Code Complete", "The Mythical Man-Month"), books, () -> "Books in bookshelf are in insertion order");
  }
  */

  @Test
  public void shelfEmptyWhenNoBookAdded() {
    List<Book> books = shelf.books();
    assertTrue(books.isEmpty(), () -> "BookShelf should be empty.");
  }

  @Test
  public void bookshelfContainsTwoBooksWhenTwoBooksAdded() {
    shelf.add(effectiveJava, codeComplete);
    List<Book> books = shelf.books();
    assertEquals(2, books.size(), () -> "BookShelf should have two books.");
  }

  @Test
  public void emptyBookShelfWhenAddIsCalledWithoutBooks() {
    shelf.add();
    List<Book> books = shelf.books();
    assertTrue(books.isEmpty(), () -> "BookShelf should be empty.");
  }

  @Test
  public void booksReturnedFromBookShelfIsImmutableForClient() {
    shelf.add(effectiveJava, codeComplete);
    List<Book> books = shelf.books();
    try {
      books.add(mythicalManMonth);
      fail(() -> "Should not be able to add book to books");
    } catch (Exception e) {
      assertTrue(e instanceof UnsupportedOperationException, () ->
              "Should throw UnsupportedOperationException.");
    }
  }

  @Test
  void bookshelfArrangedByBookTitle() {
    shelf.add(effectiveJava, codeComplete, mythicalManMonth);
    List<Book> books = shelf.arrange();
    assertEquals(asList(codeComplete, effectiveJava, mythicalManMonth), books, () -> "Books in a bookshelf should be arranged lexicographically by book title");
  }

  @Test
  void booksInBookShelfAreInInsertionOrderAfterCallingArrange() {
    shelf.add(effectiveJava, codeComplete, mythicalManMonth);
    shelf.arrange();
    List<Book> books = shelf.books();
    assertEquals(asList(effectiveJava, codeComplete, mythicalManMonth),
            books, () -> "Books in bookshelf are in insertion order");
  }

//  @Test
//  void bookshelfArrangedByUserProvidedCriteria() {
//    shelf.add(effectiveJava, codeComplete, mythicalManMonth);
//    List<Book> books = shelf.arrange(Comparator.<Book>naturalOrder().reversed());
//    assertEquals(
//            asList(mythicalManMonth, effectiveJava, codeComplete),
//            books,
//            () -> "Books in a bookshelf are arranged in descending order of book title");
//  }

  @Test
  void bookshelfArrangedByUserProvidedCriteria() {
    shelf.add(effectiveJava, codeComplete, mythicalManMonth);
    Comparator<Book> reversed = Comparator.<Book>naturalOrder().reversed();
    List<Book> books = shelf.arrange(reversed);
    assertThat(books).isSortedAccordingTo(reversed);
  }

  @Test
  @DisplayName("books inside bookshelf are grouped by publication year")
  void groupBooksInsideBookShelfByPublicationYear() {
    shelf.add(effectiveJava, codeComplete, mythicalManMonth, cleanCode);

    Map<Year, List<Book>> booksByPublicationYear = shelf.
            groupByPublicationYear();

    assertThat(booksByPublicationYear)
            .containsKey(Year.of(2008))
            .containsValues(Arrays.asList(effectiveJava, cleanCode));

    assertThat(booksByPublicationYear)
            .containsKey(Year.of(2004))
            .containsValues(singletonList(codeComplete));

    assertThat(booksByPublicationYear)
            .containsKey(Year.of(1975))
            .containsValues(singletonList(mythicalManMonth));
  }

  @Test
  @DisplayName("books inside bookshelf are grouped according to user provided criteria(group by author name)")
  void groupBooksByUserProvidedCriteria() {
    shelf.add(effectiveJava, codeComplete, mythicalManMonth, cleanCode);

    Map<String, List<Book>> booksByAuthor = shelf.groupBy(Book::getAuthor);

    assertThat(booksByAuthor)
            .containsKey("Joshua Bloch")
            .containsValues(singletonList(effectiveJava));

    assertThat(booksByAuthor)
            .containsKey("Steve McConnel")
            .containsValues(singletonList(codeComplete));

    assertThat(booksByAuthor)
            .containsKey("Frederick Phillips Brooks")
            .containsValues(singletonList(mythicalManMonth));

    assertThat(booksByAuthor)
            .containsKey("Robert C. Martin")
            .containsValues(singletonList(cleanCode));
  }

}
