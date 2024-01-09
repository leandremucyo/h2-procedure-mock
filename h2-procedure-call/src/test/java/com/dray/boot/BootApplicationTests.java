package com.dray.boot;

import com.dray.boot.domain.FoedAmerican;
import com.dray.boot.domain.Foo;
import com.dray.boot.domain.repos.FoedAmericanRepository;
import com.dray.boot.domain.repos.FooRepository;
import com.dray.boot.domain.repos.FooService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.util.List;


@DataJpaTest
@Import(FooService.class)
@Sql(scripts = {"/create_store_procedure_mock.sql"},
		config = @SqlConfig(encoding = "utf-8", transactionMode = SqlConfig.TransactionMode.ISOLATED))
class BootApplicationTests {

	@Inject
	FooService fooService;
	@Inject
	FooRepository fooRepository;
	@Test
	void contextLoads() {
		fooRepository.findAll().forEach(foo -> {
			Assertions.assertTrue(List.of(Foo.FooType.American, Foo.FooType.Asian).contains(foo.getFooType()));
		});

		fooService.query(Foo.FooType.Asian);

	}

}
