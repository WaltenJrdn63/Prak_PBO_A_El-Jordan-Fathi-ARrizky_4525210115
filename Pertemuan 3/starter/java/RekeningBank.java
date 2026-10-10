public class RekeningBank {

    public static final double BUNGA_TAHUNAN = 0.025;
    public static final double BIAYA_ADMIN = 5000;
    public static final double BATAS_PENARIKAN = 5000000;

    private static int jumlahRekening = 0;

    private final String nomor;
    private final String pemilik;
    private double saldo;

    public RekeningBank(String nomor, String pemilik) {
        this(nomor, pemilik, 0);
    }

    public RekeningBank(String nomor, String pemilik, double saldoAwal) {
        if (nomor == null || nomor.trim().isEmpty() || saldoAwal < 0) {
            throw new IllegalArgumentException("Nomor tidak boleh kosong dan saldo awal tidak boleh negatif.");
        }

        this.nomor = nomor;
        this.pemilik = pemilik;
        this.saldo = saldoAwal;

        jumlahRekening++;
    }

    public void setor(double jumlah) {
        if (jumlah <= 0) {
            throw new IllegalArgumentException("Jumlah setoran harus bernilai positif.");
        }
        saldo += jumlah;
    }

    public void tarik(double jumlah) {
        if (jumlah <= 0) {
            throw new IllegalArgumentException("Jumlah penarikan harus bernilai positif.");
        }
        if (jumlah > saldo) {
            throw new RuntimeException("Saldo tidak mencukupi.");
        }
        if (jumlah > BATAS_PENARIKAN) {
            throw new RuntimeException("Penarikan melebihi batas maksimal sekali transaksi.");
        }
        saldo -= jumlah;
    }

    public void potongBiayaAdmin() {
        saldo = Math.max(0, saldo - BIAYA_ADMIN);
    }

    public static int getJumlahRekening() {
        return jumlahRekening;
    }

    public static double bungaSetahun(double pokok) {
        return pokok * BUNGA_TAHUNAN;
    }

    public double getSaldo()  { return saldo; }
    public String getNomor()  { return nomor; }

    @Override
    public String toString() {
        return String.format("Rekening[%s] %-14s Rp%,.2f", nomor, pemilik, saldo);
    }
}