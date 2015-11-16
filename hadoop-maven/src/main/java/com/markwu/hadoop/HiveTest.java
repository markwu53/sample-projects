package com.markwu.hadoop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class HiveTest {

        public static void main(String[] args) throws Exception {
                String url = "jdbc:hive2://sandbox.hortonworks.com:10000";
                Connection hiveConnection = DriverManager.getConnection(url);
                System.out.println("connected");
                String sql = "select * from sample_07";
                PreparedStatement ps = hiveConnection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                ResultSetMetaData meta = rs.getMetaData();
                int columnCount = meta.getColumnCount();
                for (int col = 1; col < columnCount; col ++) {
                        System.out.print(meta.getColumnName(col));
                        System.out.print(" | ");
                }
                System.out.println();
                while (rs.next()) {
                        for (int col = 1; col < columnCount; col ++) {
                                System.out.print(rs.getString(col));
                                System.out.print(" | ");
                        }
                        System.out.println();
                }
                rs.close();
                ps.close();
                hiveConnection.close();
        }

}
