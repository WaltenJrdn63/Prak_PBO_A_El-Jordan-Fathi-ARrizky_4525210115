public class Mobil extends Kendaraan implements Movable, Fuelable {

    private final double kapasitasTangki;
    private double isiTangki = 0;

    public Mobil(String merek, int tahun, double kapasitasTangki) {
        super(merek, tahun);
        this.kapasitasTangki = kapasitasTangki;
    }

    @Override public int jumlahRoda() { return 4; }

    @Override public void bergerak() {
        System.out.println(super.merek + " melaju di jalanan kota Bogor");
    }

    @Override public double kecepatanMaksimum() { return 180.0; }   

    @Override public void isiBahanBakar(double jumlah) {
        if (jumlah <= 0 || jumlah > this.kapasitasTangki) {
            throw new IllegalArgumentException("Jumlah bahan bakar tidak boleh <=0 dan tidak boleh melebihi kapasitas tangki");
        }
        this.isiTangki = jumlah;
    }

    @Override 
    public double kapasitasTangki() {
        return kapasitasTangki; 
    }

    @Override 
    public TipeBahanBakar tipeBahanBakar() { 
        return TipeBahanBakar.BENSIN; 
    }

    public double getIsiTangki() { return isiTangki; }
}