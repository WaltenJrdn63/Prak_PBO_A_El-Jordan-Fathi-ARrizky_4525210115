public class Dosen extends Pegawai {

    private final int jumlahSks;
    private static final double HONOR_SKS = 100_000;

    public Dosen(String nip, String nama, double gajiPokok, int jumlahSks) {
        super(nip, nama, gajiPokok);
        this.jumlahSks = jumlahSks;
    }

    @Override
    public double hitungGaji() {
        return super.hitungGaji() + (jumlahSks * HONOR_SKS);
    }

    @Override
    public String jenis() {
        return "DOSEN";
    }

    public int getJumlahSks() {
        return jumlahSks;
    }
}