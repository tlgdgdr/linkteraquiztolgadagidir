package com.linktera.linkteraquiz.service.impl;

import com.linktera.linkteraquiz.model.Author;
import com.linktera.linkteraquiz.model.Book;
import com.linktera.linkteraquiz.model.User;
import com.linktera.linkteraquiz.response.AuthorResponse;
import com.linktera.linkteraquiz.response.BookResponse;
import com.linktera.linkteraquiz.response.UserResponse;
import com.linktera.linkteraquiz.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookResponse bookResponse;
    @Autowired
    AuthorResponse authorResponse;
    @Autowired
    UserResponse userResponse;

    @Override
    public List<Book> get() {
        if(bookResponse.getBooks().isEmpty()){
            throw new IllegalStateException("There is no book data in db");
        }
        return bookResponse.getBooks();
    }

    @Override
    public Book get(UUID uuid) {
        return bookResponse.getBooks().stream().filter(t -> t.getBookId().equals(uuid)).findFirst().get();
    }

    @Override
    public void save(Book book) throws Exception {
        Optional<Book> bookOptional = bookResponse.getBooks().stream().filter(t->t.getTitle().equals(book.getTitle())&& t.getAuthor().equals(book.getAuthor())).findFirst();
        if(bookOptional.isPresent()){
            throw new Exception("There is a book with same name by author");
        }else{
            bookResponse.getBooks().add(book);
        }

    }

    @Override
    public void update(UUID uuid, Book book) throws Exception {
        Optional<Book> bookOptional = bookResponse.getBooks().stream().filter(t->t.getBookId().equals(uuid)).findFirst();
        if(!bookOptional.isPresent()){
            throw new Exception("Book is not exist");
        }
        else if(bookOptional.get().isRented()){
            throw new Exception("Book is on rent");
        }else{
            bookOptional.get().setTitle(book.getTitle());
            bookOptional.get().setAuthor(book.getAuthor());
        }
    }

    @Override
    public void delete(UUID uuid) throws Exception {
        Optional<Book> bookOptional = bookResponse.getBooks().stream().filter(t->t.getBookId().equals(uuid)).findFirst();
        if(!bookOptional.isPresent()){
            throw new Exception("Book is not exist");
        }
        else if(bookOptional.get().isRented()){
            throw new Exception("Book is on rent");
        }else{
            bookResponse.getBooks().remove(bookOptional.get());
        }
    }

    @Override
    public void rent(UUID uuid,Book book) throws Exception {
        Optional<Book> bookOptional = bookResponse.getBooks().stream().filter(t->t.getBookId().equals(book.getBookId())).findFirst();
        Optional<User> userOptional = userResponse.getUsers().stream().filter(t->t.getUserId().equals(uuid)).findFirst();
        if(!bookOptional.isPresent()){
            throw new Exception("Book is not exist");
        }
        if(!userOptional.isPresent()){
            throw new Exception("User is not exist");
        }
        else if(bookOptional.get().isRented()){
            throw new Exception("Book has already rented");
        }else{
            bookOptional.get().setRented(true);
            userOptional.get().getRentedBook().add(book);
        }
    }

    @Override
    public List<Book> getBooksByAuthor(UUID uuid) throws Exception {
        Optional<Author> authorOptional = authorResponse.getAuthors().stream().filter(a->a.getAuthorId().equals(uuid)).findFirst();
        if(!authorOptional.isPresent()){
            throw new Exception("Author is not exist");
        }
        else{
            return authorOptional.get().getBookList();
        }
    }

    @Override
    public List<Book> viewRentedBooks(UUID uuid) throws Exception {
        Optional<User> userOptional = userResponse.getUsers().stream().filter(u->u.getUserId().equals(uuid)).findFirst();
        if(!userOptional.isPresent()){
            throw new Exception("User is not exist");
        }
        else if (userOptional.get().getRentedBook().isEmpty()){
            throw new Exception("User has no rented books before");
        }
        else{
            return userOptional.get().getRentedBook();
        }
    }

    @Override
    public List<User> viewUsersWhoRentedBookBefore() throws Exception {
        List<User> userList = userResponse.getUsers().stream().filter(u->u.getRentedBook().isEmpty()==false).collect(Collectors.toList());
        if(userList.isEmpty()){
            throw new Exception("There are no users who has rented book before");
        }else{
            return userList;
        }
    }

}
