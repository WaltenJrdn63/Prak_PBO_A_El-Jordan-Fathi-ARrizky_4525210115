public interface Movable {

    void bergerak();

    double kecepatanMaksimum();

    default String ringkasanGerak() {
        return String.format("kecepatan maksimum %.0f km/jam", kecepatanMaksimum());
    }
}