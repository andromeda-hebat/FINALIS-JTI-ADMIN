package andromeda.hebat.finalisjtiadmin.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Surat {
    private IntegerProperty idSurat;
    private String namaSurat;
    private String keperluan;
    private String fileSurat;

    public Surat(int idSurat, String namaSurat, String keperluan, String fileSurat) {
        this.idSurat = new SimpleIntegerProperty(idSurat);
        this.namaSurat = namaSurat;
        this.keperluan = keperluan;
        this.fileSurat = fileSurat;
    }

    public int getIdSurat() {
        return idSurat.get();
    }

    public void setIdSurat(int idSurat) {
        this.idSurat.set(idSurat);
    }

    public IntegerProperty idSuratProperty() {
        return idSurat;
    }

    public String getNamaSurat() {
        return namaSurat;
    }

    public void setNamaSurat(String namaSurat) {
        this.namaSurat = namaSurat;
    }

    public String getKeperluan() {
        return keperluan;
    }

    public void setKeperluan(String keperluan) {
        this.keperluan = keperluan;
    }

    public String getFileSurat() {
        return fileSurat;
    }

    public void setFileSurat(String fileSurat) {
        this.fileSurat = fileSurat;
    }
}
