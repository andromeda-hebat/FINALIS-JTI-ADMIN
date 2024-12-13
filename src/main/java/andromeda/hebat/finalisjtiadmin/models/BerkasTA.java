package andromeda.hebat.finalisjtiadmin.models;

public class BerkasTA {
    private int no;
    private String nim;
    private String nama_lengkap;
    private String aktivitas;
    private String tanggalRequest;
    private String laporan_TA;
    private String aplikasi;
    private String bukti_aplikasi;
    private String tanggal_persetujuan;
    private String status;
    private String keterangan;

    public BerkasTA(String nim, String nama_lengkap, String keterangan, String tanggalRequest) {
        this.nim = nim;
        this.nama_lengkap = nama_lengkap;
        this.keterangan = keterangan;
        this.tanggalRequest = tanggalRequest;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNamaMahasiswa() {
        return nama_lengkap;
    }

    public void setNamaMahasiswa(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getAktivitas() {
        return aktivitas;
    }

    public void setAktivitas(String aktivitas) {
        this.aktivitas = aktivitas;
    }

    public String getTanggalRequest() {
        return tanggalRequest;
    }

    public void setTanggalRequest(String tanggal_request) {
        this.tanggalRequest = tanggal_request;
    }

    public String getLaporan_TA() {
        return laporan_TA;
    }

    public void setLaporan_TA(String laporan_TA) {
        this.laporan_TA = laporan_TA;
    }

    public String getAplikasi() {
        return aplikasi;
    }

    public void setAplikasi(String aplikasi) {
        this.aplikasi = aplikasi;
    }

    public String getBukti_aplikasi() {
        return bukti_aplikasi;
    }

    public void setBukti_aplikasi(String bukti_aplikasi) {
        this.bukti_aplikasi = bukti_aplikasi;
    }

    public String getTanggal_persetujuan() {
        return tanggal_persetujuan;
    }

    public void setTanggal_persetujuan(String tanggal_persetujuan) {
        this.tanggal_persetujuan = tanggal_persetujuan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
