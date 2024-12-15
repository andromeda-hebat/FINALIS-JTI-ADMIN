package andromeda.hebat.finalisjtiadmin.models;

public class DetailBerkasTAPengajuan extends BerkasPengajuan {
    private String laporanTA;
    private String aplikasi;
    private String buktiPublikasi;

    public DetailBerkasTAPengajuan(int idVerifikasi, String idBerkas, String statusVerifikasi, String nim, String namaLengkap, String tanggalRequest, String laporanTA, String aplikasi, String buktiPublikasi) {
        super(idVerifikasi, idBerkas, statusVerifikasi, nim, namaLengkap, tanggalRequest);
        this.laporanTA = laporanTA;
        this.aplikasi = aplikasi;
        this.buktiPublikasi = buktiPublikasi;
    }

    public String getLaporanTA() {
        return laporanTA;
    }

    public void setLaporanTA(String laporanTA) {
        this.laporanTA = laporanTA;
    }

    public String getAplikasi() {
        return aplikasi;
    }

    public void setAplikasi(String aplikasi) {
        this.aplikasi = aplikasi;
    }

    public String getBuktiPublikasi() {
        return buktiPublikasi;
    }

    public void setBuktiPublikasi(String buktiPublikasi) {
        this.buktiPublikasi = buktiPublikasi;
    }
}
