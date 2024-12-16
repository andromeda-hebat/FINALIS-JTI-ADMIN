package andromeda.hebat.finalisjtiadmin.models;

public enum JenisAdmin {
    ADMIN_JURUSAN("Admin Jurusan"),
    ADMIN_PRODI("Admin Prodi"),
    ADMIN_TA("Admin TA");

    private String jenisAdminStr;

    JenisAdmin(String jenisAdminStr) {
        this.jenisAdminStr = jenisAdminStr;
    }

    public String getJenisAdminStr() {
        return this.jenisAdminStr;
    }
}
