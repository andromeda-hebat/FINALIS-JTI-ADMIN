package andromeda.hebat.finalisjtiadmin.models;

public class BerkasPengajuan {
    private int idVerifikasi;
    private String idBerkas;
    private String statusVerifikasi;
    private String nim;
    private String namaLengkap;
    private String tanggalRequest;

    public BerkasPengajuan(int idVerifikasi, String idBerkas, String statusVerifikasi, String nim, String namaLengkap, String tanggalRequest) {
        this.idVerifikasi = idVerifikasi;
        this.idBerkas = idBerkas;
        this.statusVerifikasi = statusVerifikasi;
        this.nim = nim;
        this.namaLengkap = namaLengkap;
        this.tanggalRequest = tanggalRequest;
    }

    public int getIdVerifikasi() { return idVerifikasi; }

    public void setIdVerifikasi(int idVerifikasi) { this.idVerifikasi = idVerifikasi; }

    public String getIdBerkas() { return idBerkas; }

    public void setIdBerkas(String idBerkas) { this.idBerkas = idBerkas; }

    public String getStatusVerifikasi() { return statusVerifikasi; }

    public void setStatusVerifikasi(String statusVerifikasi) { this.statusVerifikasi = statusVerifikasi; }

    public String getNim() { return nim; }

    public void setNim(String nim) { this.nim = nim; }

    public String getNamaLengkap() { return namaLengkap; }

    public void setNamaLengkap(String namaLengkap) { this.namaLengkap = namaLengkap; }

    public String getTanggalRequest() { return tanggalRequest; }

    public void setTanggalRequest(String tanggalRequest) { this.tanggalRequest = tanggalRequest; }
}
