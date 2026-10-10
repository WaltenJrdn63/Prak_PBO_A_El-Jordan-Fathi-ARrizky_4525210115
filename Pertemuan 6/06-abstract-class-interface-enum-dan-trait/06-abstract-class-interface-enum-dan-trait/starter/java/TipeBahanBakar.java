/**
 * Enum: hanya nilai yang terdaftar di sini yang mungkin ada.
 * Bandingkan dengan `public static final int BENSIN = 1;`
 * yang membiarkan angka 99 lolos begitu saja.
 */
public enum TipeBahanBakar {

   
    //         BENSIN  -> "Bensin",  12000
    //         SOLAR   -> "Solar",   10500
    //         LISTRIK -> "Listrik",  2500   (per kWh)
    BENSIN("Bensin", 12000),
    SOLAR("Solar", 10500),
    LISTRIK("Listrik", 2500);

    private final String label;
    private final double hargaPerSatuan;

    TipeBahanBakar(String label, double hargaPerSatuan) {
        this.label = label;
        this.hargaPerSatuan = hargaPerSatuan;
    }

    public String getLabel() { return label; }

    
    public double biayaPengisian(double jumlah) {
        return this.hargaPerSatuan * jumlah;   
    }

   
    public boolean ramahLingkungan() {
        if (this == LISTRIK)
            return true;
        else
            return false;
    }
}
