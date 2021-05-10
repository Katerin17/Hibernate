package main.hiber.run;

import main.hiber.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmCreateModel {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        final SessionFactory sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();
        final Session session = sf.openSession();
        try (session) {
            session.beginTransaction();

            Engine engine = Engine.of("3GR-FSE");
            session.save(engine);

            Driver driver1 = Driver.of("Mironenko Ekaterina");
            Driver driver2 = Driver.of("Grishin Viktor");
            session.save(driver1);
            session.save(driver2);

            Car car = Car.of("Toyota Crown", engine);
            car.addDriver(driver1);
            car.addDriver(driver2);
            session.save(car);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
