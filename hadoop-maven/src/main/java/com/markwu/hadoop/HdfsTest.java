package com.markwu.hadoop;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HdfsTest {

        public static void main(String[] args) throws Exception {
                Configuration conf = new Configuration();
                //for (Entry<String, String> entry: conf) System.out.println(String.format("%s=%s", entry.getKey(), entry.getValue()));
                FileSystem hdfs = FileSystem.get(conf);
                String filePath = "hdfs://sandbox.hortonworks.com:8020/data/test/pom.xml";
                BufferedReader br = new BufferedReader(new InputStreamReader(hdfs.open(new Path(filePath))));
                String line;
                while ((line = br.readLine()) != null) {
                        System.out.println(line);
                }
                br.close();
                hdfs.close();
        }

}
