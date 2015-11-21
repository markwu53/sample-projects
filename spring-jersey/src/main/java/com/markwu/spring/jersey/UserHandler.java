package com.markwu.spring.jersey;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Path("/user")
public class UserHandler {

        private static Gson gson = new Gson();

        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public String getList() {
                System.out.println("get request in /user");
                String result = gson.toJson(Users.getList(), new TypeToken<List<User>>(){}.getType());
                return result;
        }

        @Path("/{userId}")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public String getById(@PathParam("userId") Integer id) {
                String result = gson.toJson(Users.getById(id), User.class);
                return result;
        }

        @PUT
        @Consumes(MediaType.APPLICATION_JSON)
        public void createUser(String json) {
                User user = gson.fromJson(json, User.class);
                Users.insert(user);
        }

        @Path("/update")
        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        public void updateUser(String json) {
                User user = gson.fromJson(json, User.class);
                Users.update(user);
        }

        @Path("/delete/{userId}")
        //@GET
        @DELETE
        public void deleteUser(@PathParam("userId") Integer userId) {
                Users.delete(userId);
        }

}