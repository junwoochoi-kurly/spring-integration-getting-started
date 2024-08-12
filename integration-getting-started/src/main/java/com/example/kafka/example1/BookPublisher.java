package com.example.kafka.example1;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookPublisher {

    private long nextBookId;

    public BookPublisher() {
        this.nextBookId = 1001L;
    }

    public List<Book> getBooks() {
        List<Book> books = new ArrayList<Book>();

        books.add(createFantasyBook());
        books.add(createFantasyBook());
        books.add(createFantasyBook());
        books.add(createFantasyBook());
        books.add(createFantasyBook());
        books.add(createHorrorBook());
        books.add(createHorrorBook());
        books.add(createHorrorBook());
        books.add(createHorrorBook());
        books.add(createHorrorBook());
        books.add(createRomanceBook());
        books.add(createRomanceBook());
        books.add(createRomanceBook());
        books.add(createRomanceBook());
        books.add(createRomanceBook());
        books.add(createThrillerBook());
        books.add(createThrillerBook());
        books.add(createThrillerBook());
        books.add(createThrillerBook());
        books.add(createThrillerBook());

        return books;
    }

    Book createFantasyBook() {
        return createBook("", Genre.FANTASY);
    }

    Book createHorrorBook() {
        return createBook("", Genre.HORROR);
    }

    Book createRomanceBook() {
        return createBook("", Genre.ROMANCE);
    }

    Book createThrillerBook() {
        return createBook("", Genre.THRILLER);
    }


    Book createBook(String title, Genre genre) {
        Book book = new Book(nextBookId++);
        if (title.equals("")) {
            title = "# " + book.getBookId();
        }

        book.setTitle(title);
        book.setGenre(genre);

        return book;
    }



}
