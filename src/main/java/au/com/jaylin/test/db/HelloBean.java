package au.com.jaylin.test.db;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class HelloBean implements HelloDao {

	@PersistenceContext
	private EntityManager em;
	
	
	@Override
	public List<Hello> getAll() {
		@SuppressWarnings("unchecked")
		List<Hello> hellos = (List<Hello>) em.createNamedQuery("allHellos").getResultList();
		
		Collections.sort(hellos, new Comparator<Hello>() {
			public int compare(Hello o1, Hello o2) {
				return o2.getWhen().compareTo(o1.getWhen());  // latest first
			}
		});
		
		return hellos;
	}

	@Override
	public Hello fromUsername(String username) {
		Query query = em.createNamedQuery("helloFromUsername");
		query.setParameter("username", username);
		Hello hello = null;
		try {
			hello = (Hello) query.getSingleResult();
		}
		catch (NoResultException ignored) {
		}
		
		return hello;
	}

	@TransactionAttribute
	@Override
	public Hello save(Hello hello) {
		hello = em.merge(hello);
		return hello;
	}

}
