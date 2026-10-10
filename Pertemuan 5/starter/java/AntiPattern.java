public class AntiPattern {

    public static void main(String[] args) {
        BangunDatar[] daftar = {
            new Lingkaran(7),
            new Persegi(5),
            // Menggunakan pendekatan segitiga berbasis alas & tinggi atau menyesuaikan kelas Segitiga
            // Jika ingin menggunakan konstruktor alas & tinggi, buat penyesuaian pada kelas Segitiga.
        };

        double total = 0;
        for (BangunDatar b : daftar) {
            total += b.luas();
        }
        System.out.printf("Total luas (cara polimorfik): %.2f%n", total);
    }
}