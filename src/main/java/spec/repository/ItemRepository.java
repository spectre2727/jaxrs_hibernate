package spec.repository;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import spec.entity.Item;

public class ItemRepository {
	
	private StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
			.configure()
			.build();
	private SessionFactory sessionFactory = new MetadataSources(registry)
			.buildMetadata()
			.buildSessionFactory();
	
	public List<Item> selectAllItems() {
		String query = "select i from Item i order by i.value";
		try (Session session = sessionFactory.openSession()) {
			return session.createQuery(query, Item.class).list();
		}
	}
	
	public int insertItem(Item item) {
		item.setId(UUID.randomUUID().toString().replace("-", "").substring(0, 16));
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.save(item);
			session.getTransaction().commit();
		}
		return 1;
	}
	
	public int updateItem(String id, Item item) {
		item.setId(id);
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.update(item);
			session.getTransaction().commit();
		}
		return 1;
	}
	
	public int deleteItem(String id) {
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.delete(session.find(Item.class, id));
			session.getTransaction().commit();
		}
		return 1;
	}

}
