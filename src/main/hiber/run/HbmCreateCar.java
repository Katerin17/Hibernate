package main.hiber.run;

import main.hiber.model.CarBrand;
import main.hiber.model.CarModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class HbmCreateCar {

    public static void main(String[] args) {
        List<CarBrand> listOfBrands = new ArrayList<>();
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        final SessionFactory sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();
        final Session session = sf.openSession();
        try (session) {
            session.beginTransaction();

            listOfBrands = session.createQuery(
                    "select distinct b from CarBrand b join fetch b.carModels", CarBrand.class
            ).list();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        for (CarModel carModel: listOfBrands.get(0).getCarModels()) {
            System.out.println(carModel);
        }
    }
}
