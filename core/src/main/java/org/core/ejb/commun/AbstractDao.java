package org.core.ejb.commun;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;

import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.core.ejb.ElasticSearchInterceptor;

import com.mysema.query.SearchResults;
import com.mysema.query.hql.HQLQuery;
import com.mysema.query.hql.jpa.JPAQuery;
import com.mysema.query.types.path.PEntity;

/**
 * The Class AbstractDao.
 *
 * @param <T>
 *            the generic type
 * @param <ID>
 *            the generic type
 */

@Interceptors(ElasticSearchInterceptor.class)
public abstract class AbstractDao<T, ID> {

	/** The em. */
	@PersistenceContext
	EntityManager em;

	/** The type. */
	protected Class<T> type;

	/** The id. */
	protected Class<ID> id;

	/**
	 * Instantiates a new abstract dao.
	 */
	@SuppressWarnings("unchecked")
	public AbstractDao() {

		// ADDED TO SUPPORT PROXY TYPE
		Class<?> parametetrizdType = getClass();

		while (!ParameterizedType.class.isAssignableFrom(parametetrizdType.getGenericSuperclass().getClass())) {
			parametetrizdType = parametetrizdType.getSuperclass();
		}

		type = (Class<T>) ((ParameterizedType) parametetrizdType.getGenericSuperclass()).getActualTypeArguments()[0];
		id = (Class<ID>) ((ParameterizedType) parametetrizdType.getGenericSuperclass()).getActualTypeArguments()[1];

	}

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<T> findAll() {

		HQLQuery query = new JPAQuery(em);

		List<String> asList = Arrays.asList(type.getName().split("\\."));
		String packageName = "";
		for (String s : asList) {
			if (asList.indexOf(s) < asList.size() - 1)
				packageName += s + ".";
		}

		Class<?> qCls = null;
		try {
			qCls = Class.forName(packageName + "Q" + type.getSimpleName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			@SuppressWarnings("unchecked")
			PEntity<T> newInstance = (PEntity<T>) qCls.getConstructor(String.class).newInstance("x");
			SearchResults<T> listResults = query.from((PEntity<?>) qCls.getConstructor(String.class).newInstance("x"))
					.listResults(newInstance);
			return (List<T>) listResults.getResults();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}

		return null;
	}

	/**
	 * Find.
	 *
	 * @param id
	 *            the id
	 * @return the t
	 */
	public T find(ID id) {
		return em.find(type, id);
	}

	/**
	 * Removes the.
	 *
	 * @param o
	 *            the o
	 */
	public void remove(T o) {
		em.remove(o);
	}

	/**
	 * Merge.
	 *
	 * @param o
	 *            the o
	 * @return the t
	 */
	public T merge(T o) {
		return em.merge(o);
	}

	/**
	 * Persist.
	 *
	 * @param o
	 *            the o
	 */
	public void persist(T o) {
		em.persist(o);
	}

	public Class<T> getType() {
		return type;
	}

}
