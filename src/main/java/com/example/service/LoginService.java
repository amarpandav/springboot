package com.example.service;

/**
 * Created by amarpandav on 03/04/17.
 */
public interface LoginService {


    public String findLoggedInUsername();

    public void autologin(String username, String password);
}
