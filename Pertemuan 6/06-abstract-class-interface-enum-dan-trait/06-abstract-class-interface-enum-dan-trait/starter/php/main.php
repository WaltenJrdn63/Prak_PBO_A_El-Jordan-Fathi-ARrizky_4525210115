<?php
declare(strict_types=1);

require_once __DIR__ . '/abstraksi.php';

function isiPenuh(Fuelable $kendaraan): void
{
    $kendaraan->isiBahanBakar($kendaraan->kapasitasTangki());
    $biaya = $kendaraan->tipeBahanBakar()->biayaPengisian($kendaraan->kapasitasTangki());
    printf('  Diisi penuh %s — biaya Rp%s%s',
        $kendaraan->tipeBahanBakar()->label(),
        number_format($biaya, 0, ',', '.'), PHP_EOL);
}

$mobil = new Mobil('Toyota Avanza', 2022, 45);
$sepeda = new Sepeda('Polygon', 2023);

echo '=== Semua Movable ===', PHP_EOL;
foreach ([$mobil, $sepeda] as $m) {
    $m->bergerak();
    printf('    kecepatan maksimum %.0f km/jam%s', $m->kecepatanMaksimum(), PHP_EOL);
}

echo PHP_EOL, '=== Hanya yang Fuelable ===', PHP_EOL;
isiPenuh($mobil);
// isiPenuh($sepeda); // Akan memicu TypeError jika dibuka karena Sepeda bukan Fuelable

echo PHP_EOL, '=== Enum punya perilaku ===', PHP_EOL;
foreach (TipeBahanBakar::cases() as $t) {
    printf('  %-8s ramah lingkungan? %-5s  biaya 10 satuan: Rp%s%s',
        $t->label(),
        $t->ramahLingkungan() ? 'ya' : 'tidak',
        number_format($t->biayaPengisian(10), 0, ',', '.'), PHP_EOL);
}

echo PHP_EOL, '=== Trait dipakai kelas yang tidak sekerabat ===', PHP_EOL;
$mobil->log('servis berkala selesai');

// Perbaikan kelas Pesanan
class Pesanan {
    use Loggable;
    
    public function __construct(private string $idPesanan) {}

    public function tandaiSelesai(): void {
        $this->log("Pesanan {$this->idPesanan} selesai diproses");
    }
}

(new Pesanan('#1042'))->tandaiSelesai();