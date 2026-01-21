package com.applv.sobes.wildflyapp.repository.impl;

import com.applv.sobes.wildflyapp.entity.BaseEntity;
import com.applv.sobes.wildflyapp.repository.EntityManagerOperationsExecutor;
import com.applv.sobes.wildflyapp.repository.SqlQueries;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.hibernate.Session;

@ApplicationScoped
public class EntityManagerOperationsExecutorImpl implements EntityManagerOperationsExecutor {

	private final EntityManager em;

	@Inject
	public EntityManagerOperationsExecutorImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	public <T> Optional<T> findBySql(SqlQueries sqlQuery, Map<String, Object> params, Class<T> clazz) {
		var r = findAllBySql(sqlQuery, params, clazz);
		return CollectionUtils.isEmpty(r) ? Optional.empty() : Optional.of(r.get(0));
	}

	@Override
	public <T> List<T> findAllBySql(SqlQueries sqlQuery,
			                            Map<String, Object> params,
			                            Class<T> clazz) {
		if (MapUtils.isEmpty(params)) {
			return findAllBySql(sqlQuery, clazz);
		}
		Objects.requireNonNull(sqlQuery, "The sqlQuery variable cannot be null.");
		Objects.requireNonNull(clazz, "The clazz variable cannot be null.");
		var query = em.unwrap(Session.class).createNativeQuery(sqlQuery.getSql(), clazz);
		params.forEach(query::setParameter);

		return query.getResultList();
	}

	@Override
	public <T> List<T> findAllBySql(SqlQueries sqlQuery, Class<T> clazz) {

		return em.unwrap(Session.class).createNativeQuery(sqlQuery.getSql(), clazz).getResultList();
	}

	@Override
	public <T> T merge(T entity) {
		return em.merge(entity);
	}

	@Override
	public <T extends BaseEntity> void delete(T entity) {
		em.remove(em.contains(entity) ? entity : em.merge(entity));
	}
}
