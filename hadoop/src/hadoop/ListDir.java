package hadoop;

import java.io.FileInputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class ListDir {

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        conf.addResource(new FileInputStream("core-site.xml"));
        conf.addResource(new FileInputStream("hdfs-site.xml"));
        conf.addResource(new FileInputStream("yarn-site.xml"));
        //conf.addResource(new FileInputStream("/usr/hdp/current/hadoop-client/conf/core-site.xml"));
        //conf.addResource(new FileInputStream("/usr/hdp/current/hadoop-client/conf/hdfs-site.xml"));
        //conf.addResource(new FileInputStream("/usr/hdp/current/hadoop-client/conf/yarn-site.xml"));
        FileSystem fs = FileSystem.get(conf);
        for (FileStatus file: fs.listStatus(new Path("/user"))) {
            System.out.println(file.getPath().getName());
        }
    }

}
