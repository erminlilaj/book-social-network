package com.eri.book.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class BookMapper {
    public Book toBook(BookRequest request) {
        return Book.builder()
        .id(request.id())
        .title(request.title())
                .authorName(request.authorName())
                .synopsis(request.synopsis())
                .archived(false)//by default when u create a book
                .sharable(request.shareable())
                .build();
    }
}
