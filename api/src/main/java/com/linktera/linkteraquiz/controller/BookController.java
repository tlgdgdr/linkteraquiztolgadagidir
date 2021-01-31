package com.linktera.linkteraquiz.controller;

import com.linktera.linkteraquiz.model.Author;
import com.linktera.linkteraquiz.model.Book;
import com.linktera.linkteraquiz.model.User;
import com.linktera.linkteraquiz.request.BookRequest;
import com.linktera.linkteraquiz.response.BookResponse;
import com.linktera.linkteraquiz.service.BookService;
import com.linktera.linkteraquiz.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookServiceImpl bookService;

    @GetMapping()
    public BookResponse getBooks() {
        return new BookResponse(bookService.get());
    }

    @GetMapping("/{uuid}")
    public BookResponse getBook(@PathVariable("uuid") UUID uuid) {
        if (uuid == null) {
            throw new NullPointerException("Uuid not be empty");
        }

        return new BookResponse(bookService.get(uuid));
    }

    @PostMapping()
    public void saveBook(@RequestBody BookRequest request) throws Exception {
        if (request == null || request.getBook() == null) {
            throw new NullPointerException("Book not be empty");
        }

        bookService.save(request.getBook());
    }

    @PutMapping("/{uuid}")
    public void updateBook(@RequestBody BookRequest request, @PathVariable("uuid") UUID uuid) throws Exception {
        if (request == null || request.getBook() == null || uuid == null) {
            throw new NullPointerException("Book or uuid not be empty");
        }

        bookService.update(uuid, request.getBook());
    }

    @DeleteMapping("/{uuid}")
    public void deleteBook(@PathVariable("uuid") UUID uuid) throws Exception {
        if (uuid == null) {
            throw new NullPointerException("Uuid not be empty");
        }

        bookService.delete(uuid);
    }
    @PutMapping("/rent/{uuid}")
    public void rent(@PathVariable("uuid") UUID uuid,@RequestBody BookRequest request) throws Exception {
        if (request == null || request.getBook() == null || uuid == null) {
            throw new NullPointerException("Book or uuid not be empty");
        }
        bookService.rent(uuid,request.getBook());
    }
    @GetMapping("/getbooksbyauthor/{uuid}")
    public List<Book> getBooksByAuthor(@PathVariable("uuid") UUID uuid) throws Exception {
        if (uuid == null) {
            throw new NullPointerException("Author id not be empty");
        }
        return bookService.getBooksByAuthor(uuid);
    }
    @GetMapping("/viewrentedbooks/{uuid}")
    public List<Book> viewRentedBooks(@PathVariable("uuid") UUID uuid) throws Exception {
        if (uuid == null) {
            throw new NullPointerException("User id not be empty");
        }
        return bookService.viewRentedBooks(uuid);
    }
    @GetMapping("/viewuserswhorentedbookbefore")
    public List<User> viewUsersWhoRentedBookBefore() throws Exception {
        return bookService.viewUsersWhoRentedBookBefore();
    }

}
