public class Segitiga extends BangunDatar {
    private final double sisiA;
    private final double sisiB;
    private final double sisiC;

    public Segitiga(double sisiA, double sisiB, double sisiC) {
        super("Segitiga");
        if (sisiA <= 0 || sisiB <= 0 || sisiC <= 0) {
            throw new IllegalArgumentException("Sisi segitiga harus lebih besar dari 0");
        }
        this.sisiA = sisiA;
        this.sisiB = sisiB;
        this.sisiC = sisiC;
    }

    @Override
    public double luas() {
        // Menggunakan rumus Heron untuk segitiga umum (contoh: 3, 4, 5 adalah segitiga siku-siku)
        double s = keliling() / 2.0;
        return Math.sqrt(s * (s - sisiA) * (s - sisiB) * (s - sisiC));
    }

    @Override
    public double keliling() {
        return sisiA + sisiB + sisiC;
    }
}