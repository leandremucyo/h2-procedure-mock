package com.dray.boot.domain.repos;

import com.dray.boot.domain.Foo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProcedureMock {
  public static ResultSet execute(Connection connection, String fooType) throws SQLException {
    PreparedStatement ps =
        connection.prepareStatement(
            "select * from foo where foo_type = '" + Foo.FooType.valueOf(fooType).name() + "'");
    ResultSet results = ps.executeQuery();
    return results;
  }
}
