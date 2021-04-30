package sv.edu.udb.actividadlab_consumirapi.models;

import java.util.List;

public class Detalle {

    public List<Item> getDetails() {
        return details;
    }

    public void setDetails(List<Item> details) {
        this.details = details;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Detalle(List<Item> details) {
        this.details = details;
    }

    private List<Item> details;
    private int success;
    private String message;


    public static class Item{

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }


        public Item(String name, int age, String mobile, String email) {
            this.name = name;
            this.age = age;
            this.mobile = mobile;
            this.email = email;
        }
        private int id;
        private String name;
        private int age;
        private String mobile;
        private String email;
    }
}
