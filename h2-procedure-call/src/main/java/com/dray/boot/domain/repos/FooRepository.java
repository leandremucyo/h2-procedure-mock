package com.dray.boot.domain.repos;

import com.dray.boot.domain.Foo;
import org.springframework.data.repository.CrudRepository;

public interface FooRepository extends CrudRepository<Foo, Long> {
}
