package com.semantyca.yatt.model;


import com.semantyca.yatt.model.system.User;

public class Assignee extends AppEntity<Integer> {
    private String name;
    private User user;
    private int rank;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public Integer getUserId() {
        return user.id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
