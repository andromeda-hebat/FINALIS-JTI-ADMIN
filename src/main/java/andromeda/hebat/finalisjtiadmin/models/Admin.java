package andromeda.hebat.finalisjtiadmin.models;

public class Admin {
    private String userId;
    private String name;
    private String password;
    private String email;
    private String jabatan;
    private String fotoProfil;

    public Admin() { }

    public Admin(String userId, String name, String password, String email, String jabatan) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.jabatan = jabatan;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String role) {
        this.jabatan = role;
    }

    public String getFotoProfil() {
        return fotoProfil;
    }

    public void setFotoProfil(String fotoProfil) {
        this.fotoProfil = fotoProfil;
    }
}
