package andromeda.hebat.finalisjtiadmin.models;

public class LogAktivitas {
    private Integer id;
    private String idAdmin;
    private String idBerkas;
    private String statusSebelum;
    private String statusSesudah;
    private String tanggal;

    public LogAktivitas(Integer id, String idAdmin, String idBerkas, String statusSebelum, String statusSesudah, String tanggal) {
        this.id = id;
        this.idAdmin = idAdmin;
        this.idBerkas = idBerkas;
        this.statusSebelum = statusSebelum;
        this.statusSesudah = statusSesudah;
        this.tanggal = tanggal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(String idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getIdBerkas() {
        return idBerkas;
    }

    public void setIdBerkas(String idBerkas) {
        this.idBerkas = idBerkas;
    }

    public String getStatusSebelum() {
        return statusSebelum;
    }

    public void setStatusSebelum(String statusSebelum) {
        this.statusSebelum = statusSebelum;
    }

    public String getStatusSesudah() {
        return statusSesudah;
    }

    public void setStatusSesudah(String statusSesudah) {
        this.statusSesudah = statusSesudah;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }



}
