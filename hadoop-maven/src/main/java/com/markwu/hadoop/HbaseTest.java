package com.markwu.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

public class HbaseTest {

        public static void main(String[] args) throws Exception {
                //Configuration conf = new Configuration();
                Configuration conf = HBaseConfiguration.create();
                //Map<String, String> ordered = new TreeMap<String, String>();
                //for (Entry<String, String> entry: conf) ordered.put(entry.getKey(), entry.getValue());
                //for (Entry<String, String> entry: ordered.entrySet()) System.out.println(String.format("%s=%s", entry.getKey(), entry.getValue()));
                Connection hbaseConnection = ConnectionFactory.createConnection(conf);
                System.out.println("connected");
                Table table = hbaseConnection.getTable(TableName.valueOf("iemployee"));
                Scan s = new Scan();
                s.addColumn(Bytes.toBytes("insurance"), Bytes.toBytes("dental"));
                ResultScanner resultScanner = table.getScanner(s);
                for (Result result: resultScanner) {
                        System.out.println(result.toString());
                        for (Cell cell: result.listCells()) {
                                String rowkey = Bytes.toString(cell.getRow());
                                String rowkey2 = Bytes.toString(cell.getRowArray());
                                String value = Bytes.toString(cell.getValue());
                                String value2 = Bytes.toString(cell.getValueArray());
                                System.out.println(String.format("rowkey=%s|value=%s", rowkey, value));
                                System.out.println(String.format("rowkey=%s|value=%s", rowkey2, value2));
                        }
                        System.out.println("row done");
                }
                resultScanner.close();
                table.close();
                hbaseConnection.close();
        }

}
