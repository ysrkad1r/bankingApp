package org.example.session;

import org.example.model.enums.UserType;
import org.example.interfaces.User;

public class SessionManager {
    private static SessionManager instance;
    private UserType currentUserType;
    private User currentUser;

    private SessionManager() {}

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void login(User user) {
        this.currentUser = user;
        this.currentUserType = user.getUserType();
    }

    public void logOut() {
        this.currentUser = null;
        this.currentUserType = null;
    }

    public UserType getCurrentUserType() {
        return currentUserType;
    }

    public User getLoggedInUser() {
        return currentUser;
    }

    public void setLoggedInUser(User user) {
        this.currentUser = user;
    }

    // Opsiyonel: Kullanıcıyı belirli tipe cast eden yardımcı method
    public <T extends User> T getLoggedInUserAs(Class<T> clazz) {
        return clazz.cast(currentUser);
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }
}
