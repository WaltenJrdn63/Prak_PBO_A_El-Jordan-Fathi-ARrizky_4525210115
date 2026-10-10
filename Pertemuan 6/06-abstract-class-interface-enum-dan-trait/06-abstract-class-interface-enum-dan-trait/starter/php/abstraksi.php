<?php
declare(strict_types=1);

final class Mobil extends Kendaraan implements Movable, Fuelable
{
    use Loggable;                       

    private float $isiTangki = 0;

    public function __construct(string $merek, int $tahun, private readonly float $kapasitas)
    {
        parent::__construct($merek, $tahun);
    }

    public function jumlahRoda(): int { return 4; }

    public function bergerak(): void {
        echo "Mobil {$this->merek} melaju di jalanan kota Bogor", PHP_EOL;
    }
    public function kecepatanMaksimum(): float { return 180.0; }
    public function isiBahanBakar(float $jumlah): void {
        if ($jumlah <= 0) {
            throw new \InvalidArgumentException("Jumlah isi tidak boleh nol atau negatif");
        }
        if ($this->isiTangki + $jumlah > $this->kapasitas) {
            throw new \InvalidArgumentException("Isi tangki melebihi kapasitas");
        }
        $this->isiTangki += $jumlah;
        $this->log("Isi tangki bertambah $jumlah liter, total isi: {$this->isiTangki} liter");
    }

    public function kapasitasTangki(): float { return $this->kapasitas; }
    public function tipeBahanBakar(): TipeBahanBakar { return TipeBahanBakar::Bensin; }
    public function getIsiTangki(): float { return $this->isiTangki; }
}

// Perbaikan kelas Sepeda
final class Sepeda extends Kendaraan implements Movable
{
    public function jumlahRoda(): int { return 2; }

    public function bergerak(): void {
        echo "Sepeda {$this->merek} dikayuh di jalur sepeda kota Bogor", PHP_EOL;
    }

    public function kecepatanMaksimum(): float { 
        return 35.0; 
    }
}
