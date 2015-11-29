package com.my.leaflet.prepare;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

@Configuration
public class PhoenixTable {

        public static ApplicationContext appContext;
        public static JdbcTemplate jdbc;
        public static List<String> files;
        public static Map<String, Map<String, Map<String, Trip>>> group = new HashMap<String, Map<String, Map<String, Trip>>>();

        public static void main(String[] args) {
                SpringApplication app = new SpringApplication(PhoenixTable.class);
                app.setWebEnvironment(false);
                appContext = app.run(args);
                init(args);
                //createTable();
                try {
                        //sampleFiles();
                        //insertTable();
                        sortData();
                        insertAggregate();
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        public static void init(String[] args) {
                String driverClassName = appContext.getEnvironment().getProperty("phoenix.jdbc.driver");
                String url = appContext.getEnvironment().getProperty("phoenix.jdbc.url");
                BasicDataSource dataSource = new BasicDataSource();
                dataSource.setDriverClassName(driverClassName);
                dataSource.setUrl(url);
                jdbc = new JdbcTemplate(dataSource);
        }

        public static void createTable() {
                jdbc.execute(appContext.getEnvironment().getProperty("sql.drop_trip_table"));
                jdbc.execute(appContext.getEnvironment().getProperty("sql.create_trip_table"));
        }

        public static void insertTable() throws Exception {
                String sql = appContext.getEnvironment().getProperty("sql.insert_trip_table");
                int[] argTypes = new int[16];
                for (int index = 0; index < 16; index ++) {
                        argTypes[index] = Types.VARCHAR;
                }
                String filename = appContext.getEnvironment().getProperty("car.csv.relative");
                BufferedReader br = new BufferedReader(new FileReader(filename));
                String line;
                int count = 0;
                while ((line = br.readLine()) != null) {
                        if (line.trim().isEmpty()) continue;
                        String[] splits = line.split(",");
                        if (splits.length != 14) continue;
                        String[] args = new String[16];
                        args[0] = splits[0] + ":" + splits[5];
                        for (int index = 0; index < 14; index ++) {
                                args[index+1] = splits[index];
                        }
                        int random = Double.valueOf(Math.random() * files.size()).intValue();
                        args[15] = files.get(random);
                        jdbc.update(sql, args, argTypes);
                        System.out.println(++count);
                }
                br.close();
        }

        public static void sampleFiles() throws Exception {
                // put 10 route files in a list for permutation
                files = new ArrayList<String>();
                final String PATH = "data/WorkingTracks/kml/";
                String filenames = "track2.kml track4.kml track5.kml 2015-09-10_15_16_32.kml 2015-09-10_17_59_14.kml 2015-09-11_09_18_33.kml 2015-09-12_13_53_50.kml 2015-09-12_19_57_45.kml 2015-09-14_09_48_18.kml 2015-09-09_15_46_52.kml";
                String[] splits = filenames.split(" ");
                for (String filename: splits) {
                        BufferedReader br = new BufferedReader(new FileReader(PATH + filename));
                        StringBuilder fileString = new StringBuilder();
                        String line;
                        while ((line = br.readLine()) != null) {
                                fileString.append(line);
                        }
                        br.close();
                        files.add(fileString.toString());
                }
        }

        public static void sortData() throws Exception {
                String filename = appContext.getEnvironment().getProperty("car.csv.relative");
                BufferedReader br = new BufferedReader(new FileReader(filename));
                String line;
                while ((line = br.readLine()) != null) {
                        if (line.trim().isEmpty()) continue;
                        Trip trip = new Trip(line);
                        if (!group.containsKey(trip.getMedallion())) {
                                group.put(trip.getMedallion(), new TreeMap<String, Map<String, Trip>>());
                        }
                        if (!group.get(trip.getMedallion()).containsKey(trip.getTripDate())) {
                                group.get(trip.getMedallion()).put(trip.getTripDate(), new TreeMap<String, Trip>());
                        }
                        group.get(trip.getMedallion()).get(trip.getTripDate()).put(trip.getTripDatetime(), trip);
                }
                br.close();
        }

        public static JsonObject wrapJson(Trip trip) {
                JsonObject tripJson = new JsonObject();
                tripJson.add("trip:pickup_datetime", new JsonPrimitive(trip.get("trip:pickup_datetime")));
                tripJson.add("trip:dropoff_datetime", new JsonPrimitive(trip.get("trip:dropoff_datetime")));
                tripJson.add("trip:passenger_count", new JsonPrimitive(Integer.valueOf(trip.get("trip:passenger_count"))));
                tripJson.add("trip:trip_time_in_secs", new JsonPrimitive(Integer.valueOf(trip.get("trip:trip_time_in_secs"))));
                tripJson.add("trip:trip_distance", new JsonPrimitive(Double.valueOf(trip.get("trip:trip_distance"))));
                tripJson.add("trip:pickup_location", new JsonArray());
                tripJson.get("trip:pickup_location").getAsJsonArray().add(new JsonPrimitive(Double.valueOf(trip.get("trip:pickup_latitude"))));
                tripJson.get("trip:pickup_location").getAsJsonArray().add(new JsonPrimitive(Double.valueOf(trip.get("trip:pickup_longitude"))));
                tripJson.add("trip:dropoff_location", new JsonArray());
                tripJson.get("trip:dropoff_location").getAsJsonArray().add(new JsonPrimitive(Double.valueOf(trip.get("trip:dropoff_latitude"))));
                tripJson.get("trip:dropoff_location").getAsJsonArray().add(new JsonPrimitive(Double.valueOf(trip.get("trip:dropoff_longitude"))));
                return tripJson;
        }

        public static void insertAggregate() throws Exception {
                String sql = appContext.getEnvironment().getProperty("sql.insert_trip_aggregate");
                int[] argTypes = new int[] { Types.VARCHAR, Types.VARCHAR };
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                for (String medallion: group.keySet()) {
                        JsonObject days = new JsonObject();
                        for (String date: group.get(medallion).keySet()) {
                                days.add(date, new JsonArray());
                                for (String datetime: group.get(medallion).get(date).keySet()) {
                                        Trip trip = group.get(medallion).get(date).get(datetime);
                                        days.get(date).getAsJsonArray().add(wrapJson(trip));
                                }
                        }
                        System.out.println("doing aggregation for " + medallion);
                        //System.out.println(gson.toJson(days));
                        //System.out.println("----------------------------------");

                        String rowkey = medallion + ":";
                        String total = gson.toJson(days);
                        jdbc.update(sql, new Object[]{ rowkey, total }, argTypes);
                }
        }

}
