package andromeda.hebat.finalisjtiadmin.models;

public class Mahasiswa {
    private String nim;
    private String nama;
    private String password;
    private String email;
    private String jurusan;
    private String prodi;

    public Mahasiswa() { };
    
    public Mahasiswa(String nim, String nama, String password, String email, String jurusan, String prodi) {
        this.nim = nim;
        this.nama = nama;
        this.password = password;
        this.email = email;
        this.jurusan = jurusan;
        this.prodi = prodi;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    
}