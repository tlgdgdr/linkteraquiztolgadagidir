package com.linktera.linkteraquiz.service.base;

import com.linktera.linkteraquiz.model.Book;
import com.linktera.linkteraquiz.model.User;

import java.util.List;
import java.util.UUID;

public interface BaseService<T,U> {

    List<T> get();

    T get(UUID uuid);

    void save(T dto) throws Exception;

    void update(UUID uuid, T dto) throws Exception;

    void delete(UUID uuid) throws Exception;

    //Book rent function for users
    void rent(UUID uuid,Book book) throws Exception;

    //The list of books should be able to be filtered by authors
    List<T> getBooksByAuthor(UUID uuid) throws Exception;

    //Users should be able to view the books they have rented
    List<T> viewRentedBooks(UUID uuid) throws Exception;

    List<U> viewUsersWhoRentedBookBefore() throws Exception;


}
