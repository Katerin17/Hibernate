package main.hiber.run;

import main.hiber.model.Author;
import main.hiber.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmCreateBook {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        final SessionFactory sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();
        final Session session = sf.openSession();
        try (session) {
            session.beginTransaction();

            Book book1 = Book.of("Design patterns");
            Book book2 = Book.of("Grokking algorithms");
            Book book3 = Book.of("Your brain");
            Book book4 = Book.of("Java Script");
            session.save(book1);
            session.save(book2);
            session.save(book3);
            session.save(book4);

            Author author1 = Author.of("E Frimen");
            Author author2 = Author.of("E Robson");
            Author author3 = Author.of("A Bhargava");
            Author author4 = Author.of("M Dingman");

            author1.getBooks().add(book1);
            author1.getBooks().add(book4);
            author2.getBooks().add(book1);
            author3.getBooks().add(book2);
            author4.getBooks().add(book3);

            session.save(author1);
            session.save(author2);
            session.save(author3);
            session.save(author4);

            Author author = session.get(Author.class, 1);
            session.remove(author);

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
