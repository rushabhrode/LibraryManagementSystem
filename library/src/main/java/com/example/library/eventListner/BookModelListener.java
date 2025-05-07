package com.example.library.eventListner;

import org.springframework.context.ApplicationListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import com.example.library.model.Book;

@Component
public class BookModelListener implements ApplicationListener<BeforeConvertEvent<Book>> {

    @Override
    public void onApplicationEvent(BeforeConvertEvent<Book> event) {
        Book book = event.getSource();
        book.normalizeFields();
    }
}