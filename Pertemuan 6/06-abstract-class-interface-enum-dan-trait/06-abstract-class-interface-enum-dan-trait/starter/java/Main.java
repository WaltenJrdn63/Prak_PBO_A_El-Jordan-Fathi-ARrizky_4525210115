import java.util.List;

public class Main {
    static void isiPenuh(Fuelable kendaraan) {
        kendaraan.isiBahanBakar(kendaraan.kapasitasTangki());
        double biaya = kendaraan.tipeBahanBakar().biayaPengisian(kendaraan.kapasitasTangki());
        System.out.printf("  Diisi penuh %s — biaya Rp%,.0f%n",
                kendaraan.tipeBahanBakar().getLabel(), biaya);
    }

    public static void main(String[] args) {
        Mobil mobil = new Mobil("Toyota Avanza", 2022, 45);
        Sepeda sepeda = new Sepeda("Polygon", 2023);

        System.out.println("=== Semua Movable ===");
        for (Movable m : List.of(mobil, sepeda)) {
            m.bergerak();
            System.out.println("    " + m.ringkasanGerak());
        }

        System.out.println();
        System.out.println("=== Hanya yang Fuelable ===");
        isiPenuh(mobil);
        // isiPenuh(sepeda); // Akan error saat kompilasi karena Sepeda tidak mengimplementasikan Fuelable

        System.out.println();
        System.out.println("=== Enum punya perilaku ===");
        for (TipeBahanBakar t : TipeBahanBakar.values()) {
            System.out.printf("  %-8s ramah lingkungan? %-5s  biaya 10 satuan: Rp%,.0f%n",
                    t.getLabel(), t.ramahLingkungan(), t.biayaPengisian(10));
        }
    }
}