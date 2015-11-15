package com.markwu.hadoop;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class ConfigTest {

        public static void main(String[] args) throws Exception {
                Configuration conf = new Configuration();
                //for (Entry<String, String> entry: conf) System.out.println(String.format("%s=%s", entry.getKey(), entry.getValue()));
                Map<String, String> origConfig = new HashMap<String, String>();
                for (Entry<String, String> entry: conf) {
                        origConfig.put(entry.getKey().toLowerCase(), entry.getValue());
                }
                conf.clear();
                //conf.addResource("core-site2.xml");
                conf.set("fs.defaultFS", "hdfs://sandbox.hortonworks.com:8020");
                for (Entry<String, String> entry: conf) System.out.println(String.format("%s=%s", entry.getKey(), entry.getValue()));
                FileSystem hdfs = FileSystem.get(conf);
                String filePath = "hdfs://sandbox.hortonworks.com:8020/data/test/pom.xml";
                //String filePath = "src/main/resources/core-site2.xml";
                //String filePath = "file:///Users/apple/git/sample-projects/hadoop-maven/src/main/resources/core-site2.xml";
                BufferedReader br = new BufferedReader(new InputStreamReader(hdfs.open(new Path(filePath))));
                String line;
                while ((line = br.readLine()) != null) {
                        System.out.println(line);
                }
                br.close();
                hdfs.close();
        }

}
