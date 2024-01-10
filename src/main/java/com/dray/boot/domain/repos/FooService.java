package com.dray.boot.domain.repos;

import com.dray.boot.domain.Foo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Transactional
public class FooService {

  @Autowired private final EntityManager entityManager;
  @Autowired private final Session session;

  public List<Foo> queryWithEntityManager(Foo.FooType fooType) {
    StoredProcedureQuery query =
        entityManager.createStoredProcedureQuery("stored_proc_mock", Foo.class);
    query.registerStoredProcedureParameter(0, String.class, ParameterMode.IN);
    query.setParameter(0, fooType.name());
    return query.getResultList();
  }

  public List<Foo> queryWithSession(Foo.FooType fooType) {
    Query<Foo> query = session.createNativeQuery("call stored_proc_mock(:fooType)", Foo.class);
    query.setParameter("fooType", fooType.name());
    return query.list();
  }
}
