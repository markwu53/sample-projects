package com.markwu.spring.jersey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Users {

        private static final Set<User> users = new HashSet<User>();
        private static Integer sequence = 1;
        private static final Map<Integer, User> indexById = new HashMap<Integer, User>();

        static {
                insert(new User("Mark Wu", 45));
                insert(new User("Steven Wu", 2));
        }

        public static void insert(User user) {
                user.setId(sequence);
                sequence ++;
                users.add(user);
                indexById.put(user.getId(), user);
        }

        public static boolean update(User user) {
                if (indexById.containsKey(user.getId())) {
                        User inside = indexById.get(user.getId());
                        if (users.contains(inside)) {
                                inside.update(user);
                                return true;
                        }
                }
                return false;
        }

        public static boolean delete(Integer id) {
                if (indexById.containsKey(id)) {
                        User user = indexById.get(id);
                        indexById.remove(id);
                        users.remove(user);
                        return true;
                }
                return false;
        }

        public static boolean delete(User user) {
                return delete(user.getId());
        }

        public static User getById(Integer id) {
                if (indexById.containsKey(id)) {
                        User user = indexById.get(id);
                        if (users.contains(user)) {
                                return user.copy();
                        }
                }
                return null;
        }

        public static List<User> getList() {
                List<User> result = new ArrayList<User>();
                for (User user: users) {
                        result.add(user.copy());
                }
                return result;
        }

        public static List<User> getByName(String name) {
                List<User> result = new ArrayList<User>();
                for (User user: users) {
                        if (user.getName().equals(name)) {
                                result.add(user.copy());
                        }
                }
                return result;
        }

}
