package org.example.session;

import org.example.interfaces.User;

public class SessionManager<T extends User> {
    private static SessionManager<?> instance;
    private T loggedInUser;

    public static <T extends User>SessionManager<T> getInstance() {
        if (instance == null) {
            instance = new SessionManager<>();
        }
        return (SessionManager<T>) instance;
    }

    public void login(T user) {
        setLoggedInUser(user);
    }

    public void logOut(){
        setLoggedInUser(null);
    }

    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    public T getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(T loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
