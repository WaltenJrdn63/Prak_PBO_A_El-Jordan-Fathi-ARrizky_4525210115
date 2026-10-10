public class PegawaiHarian extends Pegawai {

    private final int jumlahHadir;
    private final double honorHarian;

    public PegawaiHarian(String nip, String nama, int jumlahHadir, double honorHarian) {
        super(nip, nama, 0); // Gaji pokok bisa 0 karena dihitung dari kehadiran
        this.jumlahHadir = jumlahHadir;
        this.honorHarian = honorHarian;
    }

    @Override
    public double hitungGaji() {
        return jumlahHadir * honorHarian;
    }

    @Override
    public String jenis() {
        return "HARIAN";
    }

    public int getJumlahHadir() {
        return jumlahHadir;
    }
}