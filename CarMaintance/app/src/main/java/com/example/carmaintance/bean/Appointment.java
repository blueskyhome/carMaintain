package com.example.carmaintance.bean;

public class Appointment {
    private int id;
    private String username;
    private String store_name;
    private String program_name;
    private int program_price;
    private String program_url;
    private String time;
    private int complete;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getProgram_name() {
        return program_name;
    }

    public int getProgram_price() {
        return program_price;
    }

    public void setProgram_price(int program_price) {
        this.program_price = program_price;
    }

    public void setProgram_name(String program_name) {
        this.program_name = program_name;
    }

    public String getProgram_url() {
        return program_url;
    }

    public void setProgram_url(String program_url) {
        this.program_url = program_url;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int isComplete() {
        return complete;
    }

    public void setComplete(int complete) {
        this.complete = complete;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", store_name='" + store_name + '\'' +
                ", program_name='" + program_name + '\'' +
                ", program_url='" + program_url + '\'' +
                ", time='" + time + '\'' +
                ", complete=" + complete +
                '}';
    }
}
