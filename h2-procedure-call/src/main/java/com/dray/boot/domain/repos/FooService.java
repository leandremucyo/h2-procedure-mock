package com.dray.boot.domain.repos;

import com.dray.boot.domain.FoedAmerican;
import com.dray.boot.domain.Foo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@Setter
@AllArgsConstructor
public class FooService {

    @Autowired
    private final EntityManager entityManager;

    public void query(Foo.FooType fooType){
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("stored_proc_mock");
        query.registerStoredProcedureParameter(0, String.class, ParameterMode.IN);
        query.setParameter(0, fooType.name());
        query.execute();
    }
}
