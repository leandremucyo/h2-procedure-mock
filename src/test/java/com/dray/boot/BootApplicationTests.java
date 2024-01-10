package com.dray.boot;

import com.dray.boot.domain.Foo;
import com.dray.boot.domain.repos.FooRepository;
import com.dray.boot.domain.repos.FooService;
import jakarta.inject.Inject;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

@DataJpaTest
@Import(FooService.class)
@Sql(
    scripts = {"/create_store_procedure_mock.sql"},
    config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
class BootApplicationTests {
  @Inject FooService fooService;
  @Inject FooRepository fooRepository;

  @Test
  void contextLoads() {
    fooRepository
        .findAll()
        .forEach(
            foo -> {
              Assertions.assertTrue(
                  List.of(Foo.FooType.American, Foo.FooType.Asian).contains(foo.getFooType()));
            });

    List<Foo> fooList1 = fooService.queryWithEntityManager(Foo.FooType.Asian);
    Assertions.assertFalse(fooList1.isEmpty());
    List<Foo> fooList2 = fooService.queryWithSession(Foo.FooType.Asian);
    Assertions.assertFalse(fooList2.isEmpty());
  }
}
