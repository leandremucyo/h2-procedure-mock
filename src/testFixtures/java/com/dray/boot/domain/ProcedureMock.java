package com.dray.boot.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import org.h2.tools.SimpleResultSet;

public class ProcedureMock {
  public static ResultSet execute(Connection connection, String fooType) throws SQLException {
    String url = connection.getMetaData().getURL();
    if (!url.equals("jdbc:default:connection")) {
      SimpleResultSet rs = new SimpleResultSet();
      rs.addColumn("id", Types.NUMERIC, 10, 0);
      rs.addColumn("foo_type", Types.VARCHAR, 32, 0);
      return rs;
    }
    PreparedStatement ps =
        connection.prepareStatement(
            "select * from foo where foo_type = '" + Foo.FooType.valueOf(fooType).name() + "'");
    ResultSet results = ps.executeQuery();
    return results;
  }
}
