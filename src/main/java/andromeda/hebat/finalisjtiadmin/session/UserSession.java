package andromeda.hebat.finalisjtiadmin.session;

import andromeda.hebat.finalisjtiadmin.models.Admin;

public class UserSession {
    private static UserSession instance;
    private String sessionID;
    private Admin admin;

    public UserSession() { }

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }

        return instance;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Admin getAdmin() {
        return this.admin;
    }
}
