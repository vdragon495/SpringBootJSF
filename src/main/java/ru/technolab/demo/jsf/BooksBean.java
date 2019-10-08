package ru.technolab.demo.jsf;

import java.util.List;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.annotation.RequestAction;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.ocpsoft.rewrite.faces.annotation.Deferred;
import org.ocpsoft.rewrite.faces.annotation.IgnorePostback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.technolab.demo.dao.Book;
import ru.technolab.demo.dao.BookRepository;

@Scope(value = "session")
@Component(value = "books")
@ELBeanName(value = "books")
@Join(path = "/books", to = "/books.xhtml")
public class BooksBean {
	
    @Autowired
    private BookRepository bookRepository;
    
//    private List<Book> books;
//    
//    @Deferred
//    @RequestAction
//    @IgnorePostback
//    public void loadData() {
//        books = bookRepository.findAll();
//    }
    
//    public String save() {
//        productRepository.save(product);
//        product = new Product();
//        return "/product-list.xhtml?faces-redirect=true";
//    }
    
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }
}