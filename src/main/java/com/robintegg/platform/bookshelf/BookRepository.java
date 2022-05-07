package com.robintegg.platform.bookshelf;

import org.springframework.data.jpa.repository.JpaRepository;

interface BookRepository extends JpaRepository<Book,Long> {

    Book findByUri(String requestURI);

}
