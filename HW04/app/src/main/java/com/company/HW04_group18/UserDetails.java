
package com.company.HW04_group18;



public class UserDetails {
    String token, user_id, user_fullname, status, message;

    UserDetails(String toParse) {
        String[] parsed = toParse.split("''''");
        this.user_fullname = parsed[0];
        this.user_id = parsed[1];
        this.token = parsed[2];
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_fullname() {
        return user_fullname;
    }

    public void setUser_fullname(String user_fullname) {
        this.user_fullname = user_fullname;
    }

    @Override
    public String toString() {
        return this.user_fullname+"''''"+this.user_id+"''''"+this.token;
    }
}
