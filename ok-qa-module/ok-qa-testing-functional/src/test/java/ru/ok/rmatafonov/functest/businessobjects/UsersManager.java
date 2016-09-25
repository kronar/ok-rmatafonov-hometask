package ru.ok.rmatafonov.functest.businessobjects;

import java.util.HashMap;
import java.util.Map;

public class UsersManager {

    private Map<String, String> users = new HashMap<>();

    public void addUser(String name, String password) {
        users.put(name, password);
    }

    public String getUserPassword(String name) {
        return users.get(name);
    }
}
