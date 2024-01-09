insert into foo(id,foo_type)
values (100, 'Asian');

CREATE ALIAS IF NOT EXISTS stored_proc_mock FOR "com.dray.boot.domain.repos.ProcedureMock.execute";