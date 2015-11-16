package com.markwu.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;

public class SparkHbaseTest {

        @SuppressWarnings({ "serial" })
        public static void main(String[] args) {
                JavaSparkContext sc = new JavaSparkContext(new SparkConf().setMaster("local").setAppName("test"));
                sc.textFile("pom.xml")
                .map(new Function<String, String>() {
                        public String call(String v1) throws Exception {
                                System.out.println(v1);
                                return v1;
                        }
                }).foreach(new VoidFunction<String>() {
                        public void call(String t) throws Exception {
                        }
                });
                sc.close();
        }

}
