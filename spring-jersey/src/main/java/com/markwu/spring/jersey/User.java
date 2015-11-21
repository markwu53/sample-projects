package com.markwu.spring.jersey;

import java.io.Serializable;

public class User implements Serializable {

        private static final long serialVersionUID = -7350755517648394491L;

        private Integer id;
        private String name;
        private Integer age;
 
        public User(String name, Integer age) {
                this.setName(name);
                this.setAge(age);
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public Integer getAge() {
                return age;
        }

        public void setAge(Integer age) {
                this.age = age;
        }

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public void update(User user) {
                this.setName(user.getName());
                this.setAge(user.getAge());
        }

        public User copy() {
                User user = new User(this.name, this.age);
                user.setId(this.id);
                return user;
        }

}
