/**
 * Sesi 2 — enkapsulasi yang menjaga invariant.
 */
public class Mahasiswa {

    public static final double BOBOT_TUGAS = 0.30;
    public static final double BOBOT_UTS   = 0.30;
    public static final double BOBOT_UAS   = 0.40;

    private static final double NILAI_MIN = 0;
    private static final double NILAI_MAX = 100;

    private final String nim;
    private final String nama;
    private final double nilaiTugas;
    private final double nilaiUts;
    private final double nilaiUas;

    public Mahasiswa(String nim, String nama, double nilaiTugas, double nilaiUts, double nilaiUas) {
        if (nim == null || nim.trim().isEmpty()) {
            throw new IllegalArgumentException("NIM tidak boleh kosong atau null.");
        }

        pastikanNilaiSah("Tugas", nilaiTugas);
        pastikanNilaiSah("UTS", nilaiUts);
        pastikanNilaiSah("UAS", nilaiUas);

        this.nim = nim;
        this.nama = nama;
        this.nilaiTugas = nilaiTugas;
        this.nilaiUts = nilaiUts;
        this.nilaiUas = nilaiUas;
    }

    private static void pastikanNilaiSah(String namaKomponen, double nilai) {
        if (nilai < NILAI_MIN || nilai > NILAI_MAX) {
            throw new IllegalArgumentException("Nilai " + namaKomponen + " harus berada di antara " + NILAI_MIN + " dan " + NILAI_MAX);
        }
    }

    public double nilaiAkhir() {
        return (nilaiTugas * BOBOT_TUGAS) + (nilaiUts * BOBOT_UTS) + (nilaiUas * BOBOT_UAS);
    }

    public String hurufMutu() {
        double akhir = nilaiAkhir();
        if (akhir >= 80) return "A";
        if (akhir >= 70) return "B";
        if (akhir >= 60) return "C";
        if (akhir >= 50) return "D";
        return "E";
    }

    public String getNim()  { return nim; }
    public String getNama() { return nama; }

    @Override
    public String toString() {
        return String.format("%-10s %-18s akhir=%6.2f  mutu=%s",
                nim, nama, nilaiAkhir(), hurufMutu());
    }
}
```[cite: 1]

---

### 2. Main Java (`Main.java`)

```java
public class Main {
    public static void main(String[] args) {

        System.out.println("=== Rekap Nilai ===");
        Mahasiswa[] kelas = {
            new Mahasiswa("2024001", "Ani Lestari",  85, 78, 90),
            new Mahasiswa("2024002", "Budi Santoso", 60, 55, 62),
            new Mahasiswa("2024003", "Citra Wijaya", 92, 88, 95)
        };
        for (Mahasiswa m : kelas) {
            System.out.println("  " + m);
        }

        System.out.println();
        System.out.println("=== Objek menolak data yang melanggar aturan ===");

        try {
            new Mahasiswa("2024004", "Salah Nilai", 150, 80, 80);
            System.out.println("  MASALAH: nilai 150 seharusnya ditolak!");
        } catch (IllegalArgumentException e) {
            System.out.println("  Ditolak: " + e.getMessage());
        }

        try {
            new Mahasiswa("", "NIM Kosong", 80, 80, 80);
            System.out.println("  MASALAH: NIM kosong seharusnya ditolak!");
        } catch (IllegalArgumentException e) {
            System.out.println("  Ditolak: " + e.getMessage());
        }
    }
}
```[cite: 2]

---

### 3. PHP (`Mahasiswa.php`)

```php
<?php
declare(strict_types=1);

class Mahasiswa
{
    public const float BOBOT_TUGAS = 0.30;
    public const float BOBOT_UTS   = 0.30;
    public const float BOBOT_UAS   = 0.40;

    private const float NILAI_MIN = 0;
    private const float NILAI_MAX = 100;

    public function __construct(
        private readonly string $nim,
        private readonly string $nama,
        private float $nilaiTugas,
        private float $nilaiUts,
        private float $nilaiUas,
    ) {
        if (trim($this->nim) === '') {
            throw new InvalidArgumentException("NIM tidak boleh kosong.");
        }

        self::pastikanNilaiSah('Tugas', $this->nilaiTugas);
        self::pastikanNilaiSah('UTS', $this->nilaiUts);
        self::pastikanNilaiSah('UAS', $this->nilaiUas);
    }

    private static function pastikanNilaiSah(string $namaKomponen, float $nilai): void
    {
        if ($nilai < self::NILAI_MIN || $nilai > self::NILAI_MAX) {
            throw new InvalidArgumentException("Nilai {$namaKomponen} harus berada di antara " . self::NILAI_MIN . " dan " . self::NILAI_MAX);
        }
    }

    public function nilaiAkhir(): float
    {
        return ($this->nilaiTugas * self::BOBOT_TUGAS) + 
               ($this->nilaiUts * self::BOBOT_UTS) + 
               ($this->nilaiUas * self::BOBOT_UAS);
    }

    public function hurufMutu(): string
    {
        $akhir = $this->nilaiAkhir();
        return match (true) {
            $akhir >= 80 => 'A',
            $akhir >= 70 => 'B',
            $akhir >= 60 => 'C',
            $akhir >= 50 => 'D',
            default      => 'E',
        };
    }

    public function getNim(): string  { return $this->nim; }
    public function getNama(): string { return $this->nama; }

    public function __toString(): string
    {
        return sprintf('%-10s %-18s akhir=%6.2f  mutu=%s',
            $this->nim, $this->nama, $this->nilaiAkhir(), $this->hurufMutu());
    }
}
```[cite: 3]

---

### 4. Main PHP (`test.php`)

```php
<?php
declare(strict_types=1);

require_once __DIR__ . '/Mahasiswa.php';

echo '=== Rekap Nilai ===', PHP_EOL;
$kelas = [
    new Mahasiswa('2024001', 'Ani Lestari',  85, 78, 90),
    new Mahasiswa('2024002', 'Budi Santoso', 60, 55, 62),
    new Mahasiswa('2024003', 'Citra Wijaya', 92, 88, 95),
];
foreach ($kelas as $m) {
    echo '  ', $m, PHP_EOL;
}

echo PHP_EOL, '=== Objek menolak data yang melanggar aturan ===', PHP_EOL;

try {
    new Mahasiswa('2024004', 'Salah Nilai', 150, 80, 80);
    echo '  MASALAH: nilai 150 seharusnya ditolak!', PHP_EOL;
} catch (InvalidArgumentException $e) {
    echo '  Ditolak: ', $e->getMessage(), PHP_EOL;
}

try {
    new Mahasiswa('', 'NIM Kosong', 80, 80, 80);
    echo '  MASALAH: NIM kosong seharusnya ditolak!', PHP_EOL;
} catch (InvalidArgumentException $e) {
    echo '  Ditolak: ', $e->getMessage(), PHP_EOL;
}
```[cite: 4]