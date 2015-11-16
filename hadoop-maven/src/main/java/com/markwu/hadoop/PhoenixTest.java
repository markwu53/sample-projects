package com.markwu.hadoop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class PhoenixTest {

        public static final String url = "jdbc:phoenix:sandbox.hortonworks.com:2181:/hbase";
        public static Connection phoenixConnection;

        public static void main(String[] args) throws Exception {
                phoenixConnection = DriverManager.getConnection(url);
                System.out.println("connected");
                //exSelect();
                exDrop();
                exCreate();
                exInsert();
                phoenixConnection.close();
                System.out.println("done");
        }
        
        public static void exSelect() throws Exception {
                String sql = "select rowkey, user.name, user.age from pho_table_2";
                PreparedStatement ps = phoenixConnection.prepareStatement(sql);
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
        }

        public static void exCreate() throws Exception {
                //String sql = "create table pho_table_2 (rowkey varchar(200) primary key, name varchar(200), age varchar(10))";
                String sql = "create table pho_table_2 (rowkey varchar(200) primary key, user.name varchar(200), user.age varchar(10))";
                PreparedStatement ps = phoenixConnection.prepareStatement(sql);
                ps.execute();
                ps.close();
        }

        public static void exDrop() throws Exception {
                String sql = "drop table if exists pho_table_2";
                PreparedStatement ps = phoenixConnection.prepareStatement(sql);
                ps.execute();
                ps.close();
        }

        public static void exInsert() throws Exception {
                String sql = "upsert into pho_table_2 (rowkey, user.name, user.age) values ('1001', 'Mark Wu', '45')";
                //String sql = "insert into pho_table_2 (rowkey, user.name, user.age) values ('1001', 'Mark Wu', '45')";
                PreparedStatement ps = phoenixConnection.prepareStatement(sql);
                ps.executeUpdate();
                ps.close();
                phoenixConnection.commit();
        }

}
