package bookstoread;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("A bookshelf")
public class BookShelfSpec {

  private BookShelfSpec(TestInfo testInfo) {
    System.out.println("Working on test " + testInfo.getDisplayName());
  }

  @Test
  @DisplayName("is empty when no book is added to it")
  public void shelf_empty_when_no_book_added(TestInfo testInfo) throws Exception {
    System.out.println("Working on test " + testInfo.getDisplayName());
    BookShelf shelf = new BookShelf();
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
    BookShelf shelf = new BookShelf();
    List<String> books = new ArrayList<>();
    books.add("The Phoenix Project");
    books.add("Java 8 in Action");
    String shelfStr = books.toString();
    assertAll(() -> assertTrue(shelfStr.contains("The Phoenix Project"), "1st book title missing"),
            () -> assertTrue(shelfStr.contains("Java 8 in Action"), "2nd book title missing "),
            () -> assertTrue(shelfStr.contains("2 books found"), "Book count missing"));
  }

}
