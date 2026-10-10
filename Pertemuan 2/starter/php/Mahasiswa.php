<?php
declare(strict_types=1);

/**
 * Sesi 2 — enkapsulasi yang menjaga invariant (PHP).
 */
class Mahasiswa
{
    public const float BOBOT_TUGAS = 0.30;
    public const float BOBOT_UTS   = 0.30;
    public const float BOBOT_UAS   = 0.40;

    private const float NILAI_MIN = 0;
    private const float NILAI_MAX = 100;

    /**
     * Constructor property promotion (PHP 8):
     * readonly adalah padanan `final` pada atribut Java.
     */
    public function __construct(
        private readonly string \$nim,
        private readonly string \$nama,
        private float \$nilaiTugas,
        private float \$nilaiUts,
        private float \$nilaiUas,
    ) {
        // Tolak NIM yang kosong atau hanya berisi spasi setelah di-trim
        if (trim(\$this->nim) === '') {
            throw new InvalidArgumentException("NIM tidak boleh kosong.");
        }

        // Validasi setiap komponen nilai agar berada di rentang 0-100
        self::pastikanNilaiSah('Tugas', \$this->nilaiTugas);
        self::pastikanNilaiSah('UTS', \$this->nilaiUts);
        self::pastikanNilaiSah('UAS', \$this->nilaiUas);
    }

    /**
     * Method pembantu privat untuk memvalidasi satu komponen nilai.
     */
    private static function pastikanNilaiSah(string $namaKomponen, float$nilai): void
    {
        if ($nilai < self::NILAI_MIN \vert{}\vert{}$nilai > self::NILAI_MAX) {
            throw new InvalidArgumentException("Nilai {\$namaKomponen} harus berada di antara " . self::NILAI_MIN . " dan " . self::NILAI_MAX);
        }
    }

    /** Hitung nilai akhir memakai konstanta bobot. */
    public function nilaiAkhir(): float
    {
        return (\$this->nilaiTugas * self::BOBOT_TUGAS) + 
               (\$this->nilaiUts * self::BOBOT_UTS) + 
               (\$this->nilaiUas * self::BOBOT_UAS);
    }

    /** Kembalikan huruf mutu berdasarkan nilai akhir. */
    public function hurufMutu(): string
    {
        $akhir =$this->nilaiAkhir();
        return match (true) {
            $akhir >= 80 => 'A',$akhir >= 70 => 'B',
            $akhir >= 60 => 'C',$akhir >= 50 => 'D',
            default      => 'E',
        };
    }

    // Getter untuk nim dan nama (tidak ada setNim karena NIM bersifat final/readonly)
    public function getNim(): string  { return \$this->nim; }
    public function getNama(): string { return \$this->nama; }

    public function __toString(): string
    {
        return sprintf('%-10s %-18s akhir=%6.2f  mutu=%s',
            $this->nim,$this->nama, $this->nilaiAkhir(),$this->hurufMutu());
    }
}
```[cite: 3]

---

### 2. `main.php`
Simpan kode ini di direktori yang sama dengan nama `main.php`:

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