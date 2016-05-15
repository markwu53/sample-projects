package hadoop.yarn;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.yarn.api.ApplicationConstants.Environment;
import org.apache.hadoop.yarn.api.records.ApplicationId;
import org.apache.hadoop.yarn.api.records.ApplicationReport;
import org.apache.hadoop.yarn.api.records.ApplicationSubmissionContext;
import org.apache.hadoop.yarn.api.records.ContainerLaunchContext;
import org.apache.hadoop.yarn.api.records.LocalResource;
import org.apache.hadoop.yarn.api.records.LocalResourceType;
import org.apache.hadoop.yarn.api.records.LocalResourceVisibility;
import org.apache.hadoop.yarn.api.records.Resource;
import org.apache.hadoop.yarn.api.records.YarnApplicationState;
import org.apache.hadoop.yarn.client.api.YarnClient;
import org.apache.hadoop.yarn.client.api.YarnClientApplication;
import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.apache.hadoop.yarn.util.Apps;
import org.apache.hadoop.yarn.util.ConverterUtils;
import org.apache.hadoop.yarn.util.Records;

public class YarnApp {

    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws Exception {
        Configuration conf = new YarnConfiguration();
        conf.addResource(new FileInputStream("core-site.xml"));
        conf.addResource(new FileInputStream("hdfs-site.xml"));
        conf.addResource(new FileInputStream("yarn-site.xml"));
        //conf.addResource(new FileInputStream("/usr/hdp/current/hadoop-client/conf/core-site.xml"));
        //conf.addResource(new FileInputStream("/usr/hdp/current/hadoop-client/conf/hdfs-site.xml"));
        //conf.addResource(new FileInputStream("/usr/hdp/current/hadoop-client/conf/yarn-site.xml"));

        YarnClient client = YarnClient.createYarnClient();
        client.init(conf);
        client.start();
        System.out.println("client created");

        YarnClientApplication app = client.createApplication();
        System.out.println("app created");

        ContainerLaunchContext amContainer = Records.newRecord(ContainerLaunchContext.class);

        amContainer.setCommands(Arrays.asList(String.format("$JAVA_HOME/bin/java -Xmx256M hadoop.yarn.AppMaster")));

        LocalResource appMasterJar = Records.newRecord(LocalResource.class);
        Path jarPath = new Path("/user/T162880/yarn/my-yarn-app.jar");
        FileStatus jarStat = FileSystem.get(conf).getFileStatus(jarPath);
        appMasterJar.setResource(ConverterUtils.getYarnUrlFromPath(jarPath));
        appMasterJar.setSize(jarStat.getLen());
        appMasterJar.setTimestamp(jarStat.getModificationTime());
        appMasterJar.setType(LocalResourceType.FILE);
        appMasterJar.setVisibility(LocalResourceVisibility.PUBLIC);
        amContainer.setLocalResources(Collections.singletonMap("my-yarn-app.jar", appMasterJar));

        Map<String, String> appMasterEnv = new HashMap<String, String>();
        for (String c : conf.getStrings(YarnConfiguration.YARN_APPLICATION_CLASSPATH, YarnConfiguration.DEFAULT_YARN_APPLICATION_CLASSPATH)) {
              Apps.addToEnvironment(appMasterEnv, Environment.CLASSPATH.name(), c.trim());
        }
        Apps.addToEnvironment(appMasterEnv, Environment.CLASSPATH.name(), Environment.PWD.$() + File.separator + "*");
        amContainer.setEnvironment(appMasterEnv);

        Resource capability = Records.newRecord(Resource.class);
        capability.setMemory(256);
        capability.setVirtualCores(1);

        ApplicationSubmissionContext appContext = app.getApplicationSubmissionContext();
        appContext.setApplicationName("my-yarn-app");
        appContext.setAMContainerSpec(amContainer);
        appContext.setResource(capability);
        appContext.setQueue("default");

        ApplicationId appId = appContext.getApplicationId();
        System.out.println("Submitting application " + appId);
        client.submitApplication(appContext);

        ApplicationReport appReport = client.getApplicationReport(appId);
        YarnApplicationState appState = appReport.getYarnApplicationState();
        while (appState != YarnApplicationState.FINISHED && appState != YarnApplicationState.KILLED && appState != YarnApplicationState.FAILED) {
            Thread.sleep(100);
            appReport = client.getApplicationReport(appId);
            appState = appReport.getYarnApplicationState();
        }
        
        System.out.println( "Application " + appId + " finished with" + " state " + appState + " at " + appReport.getFinishTime());

    }

}
