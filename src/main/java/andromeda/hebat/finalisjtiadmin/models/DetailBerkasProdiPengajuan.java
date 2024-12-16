package andromeda.hebat.finalisjtiadmin.models;

public class DetailBerkasProdiPengajuan extends BerkasPengajuan{
    private String toeic;
    private String distribusiSkripsi;
    private String magang;
    private String bebasKompen;

    public DetailBerkasProdiPengajuan() {
    }

    public DetailBerkasProdiPengajuan(int idVerifikasi, String idBerkas, String statusVerifikasi, String nim, String namaLengkap, String tanggalRequest, String toeic, String distribusiSkripsi, String magang, String bebasKompen) {
        super(idVerifikasi, idBerkas, statusVerifikasi, nim, namaLengkap, tanggalRequest);
        this.toeic = toeic;
        this.distribusiSkripsi = distribusiSkripsi;
        this.magang = magang;
        this.bebasKompen = bebasKompen;
    }

    public String getToeic() {
        return toeic;
    }

    public void setToeic(String toeic) {
        this.toeic = toeic;
    }

    public String getDistribusiSkripsi() {
        return distribusiSkripsi;
    }

    public void setDistribusiSkripsi(String distribusiSkripsi) {
        this.distribusiSkripsi = distribusiSkripsi;
    }

    public String getMagang() {
        return magang;
    }

    public void setMagang(String magang) {
        this.magang = magang;
    }

    public String getBebasKompen() {
        return bebasKompen;
    }

    public void setBebasKompen(String bebasKompen) {
        this.bebasKompen = bebasKompen;
    }
}
