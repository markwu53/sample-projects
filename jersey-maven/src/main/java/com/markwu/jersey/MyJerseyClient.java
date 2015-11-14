package com.markwu.jersey;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.JerseyClientBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class MyJerseyClient {

        public static Gson gson = new GsonBuilder().setPrettyPrinting().create();

        public static void main(String[] args) {
                new MyJerseyClient().go();
        }
 
        public void go() {
                //exPut();
                //exDelete();
                exPost();
                exGet();
        }
  
        public void exGet() {
                String result = JerseyClientBuilder.createClient()
                                .target("http://localhost:8080/jersey-maven/rest/user")
                                .request().accept(MediaType.APPLICATION_JSON)
                                .get(String.class);
                List<User> list = gson.fromJson(result, new TypeToken<List<User>>(){}.getType());
                String json = gson.toJson(list, new TypeToken<List<User>>(){}.getType());
                System.out.println(json);
        }

        public void exPut() {
                User user = new User("Richard Wu", 5);
                JerseyClientBuilder.createClient()
                                .target("http://localhost:8080/jersey-maven/rest/user")
                                .request()
                                .put(Entity.json(gson.toJson(user, User.class)));
        }

        public void exPost() {
                User user = new User("Mark Wu", 46);
                user.setId(1);
                JerseyClientBuilder.createClient()
                                .target("http://localhost:8080/jersey-maven/rest/user/update")
                                .request()
                                .post(Entity.json(gson.toJson(user, User.class)));
        }

        public void exDelete() {
                JerseyClientBuilder.createClient()
                                .target("http://localhost:8080/jersey-maven/rest/user/delete/1")
                                .request()
                                //.get();
                                .delete();
        }

}
