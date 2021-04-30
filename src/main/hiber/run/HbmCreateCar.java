package main.hiber.run;

import main.hiber.model.CarBrand;
import main.hiber.model.CarModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmCreateCar {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        final SessionFactory sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();
        final Session session = sf.openSession();
        try (session) {
            session.beginTransaction();

            CarModel carModel1 = CarModel.of("Crown");
            CarModel carModel2 = CarModel.of("Camry");
            CarModel carModel3 = CarModel.of("Mark II");
            CarModel carModel4 = CarModel.of("Chaser");
            CarModel carModel5 = CarModel.of("Land Cruiser");
            session.save(carModel1);
            session.save(carModel2);
            session.save(carModel3);
            session.save(carModel4);
            session.save(carModel5);

            CarBrand carBrand = CarBrand.of("Toyota");
            carBrand.addCarModel(session.load(CarModel.class, 1));
            carBrand.addCarModel(session.load(CarModel.class, 2));
            carBrand.addCarModel(session.load(CarModel.class, 3));
            carBrand.addCarModel(session.load(CarModel.class, 4));
            carBrand.addCarModel(session.load(CarModel.class, 5));

            session.save(carBrand);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
