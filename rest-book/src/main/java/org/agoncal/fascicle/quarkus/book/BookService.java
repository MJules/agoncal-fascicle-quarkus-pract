package org.agoncal.fascicle.quarkus.book;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

// tag::adocSnippet[]
@ApplicationScoped
@Transactional(REQUIRED)
public class BookService {

  // tag::adocConfigProperty[]
  @ConfigProperty(name = "book.discount", defaultValue = "1")
  BigDecimal discount;
  // end::adocConfigProperty[]

  @Transactional(SUPPORTS)
  public List<Book> findAllBooks() {
    return Book.listAll();
  }

  @Transactional(SUPPORTS)
  public Book findBookById(Long id) {
    return Book.findById(id);
  }

  @Transactional(SUPPORTS)
  public Book findRandomBook() {
    Book randomBook = null;
    while (randomBook == null) {
      randomBook = Book.findRandom();
    }
    return randomBook;
  }

  // tag::adocPersistBook[]
  public Book persistBook(@Valid Book book) {
    // tag::adocConfigProperty[]
    book.price = book.price.multiply(discount);
    // end::adocConfigProperty[]
    Book.persist(book);
    return book;
  }
  // end::adocPersistBook[]

  public Book updateBook(@Valid Book book) {
    Book entity = Book.findById(book.id);
    entity.title = book.title;
    entity.isbn = book.isbn;
    entity.author = book.author;
    entity.yearOfPublication = book.yearOfPublication;
    entity.nbOfPages = book.nbOfPages;
    entity.rank = book.rank;
    entity.price = book.price;
    entity.smallImageUrl = book.smallImageUrl;
    entity.mediumImageUrl = book.mediumImageUrl;
    entity.description = book.description;
    return entity;
  }

  public void deleteBook(Long id) {
    Book book = Book.findById(id);
    book.delete();
  }
}
// end::adocSnippet[]
