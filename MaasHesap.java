package maashesaplama;

import java.text.DecimalFormat;

// ============================
// 🔹 Maaş Bilgisi Arayüzü
// ============================
interface MaasHesaplayici {
    double hesaplaNetMaas(Calisan calisan);
    double hesaplaGelirVergisi(Calisan calisan);
    double hesaplaDamgaVergisi(Calisan calisan);
    double hesaplaSGKPrimi(Calisan calisan);
}

// ============================
// 🔹 Çalışan Sınıfı (Model)
// ============================
class Calisan {
    private final String adSoyad;
    private final double brutMaas;
    private final int cocukSayisi;
    private final boolean evliMi;

    public Calisan(String adSoyad, double brutMaas, int cocukSayisi, boolean evliMi) {
        this.adSoyad = adSoyad;
        this.brutMaas = brutMaas;
        this.cocukSayisi = cocukSayisi;
        this.evliMi = evliMi;
    }

    public String getAdSoyad() { return adSoyad; }
    public double getBrutMaas() { return brutMaas; }
    public int getCocukSayisi() { return cocukSayisi; }
    public boolean isEvliMi() { return evliMi; }
}

// ============================
// 🔹 Hesaplayıcı Sınıfı (Uygulama Mantığı)
// ============================
class StandartMaasHesaplayici implements MaasHesaplayici {

    private static final double SGK_ORANI = 0.14;       // %14 SGK primi
    private static final double ISSIZLIK_ORANI = 0.01;  // %1 işsizlik sigortası
    private static final double DAMGA_VERGISI_ORANI = 0.00759; // %0.759 damga vergisi

    @Override
    public double hesaplaSGKPrimi(Calisan calisan) {
        return calisan.getBrutMaas() * (SGK_ORANI + ISSIZLIK_ORANI);
    }

    @Override
    public double hesaplaDamgaVergisi(Calisan calisan) {
        return calisan.getBrutMaas() * DAMGA_VERGISI_ORANI;
    }

    @Override
    public double hesaplaGelirVergisi(Calisan calisan) {
        double sgkKesinti = hesaplaSGKPrimi(calisan);
        double vergiMatrahi = calisan.getBrutMaas() - sgkKesinti;

        double vergiOrani;
        if (vergiMatrahi <= 32000) {
            vergiOrani = 0.15;
        } else if (vergiMatrahi <= 70000) {
            vergiOrani = 0.20;
        } else if (vergiMatrahi <= 250000) {
            vergiOrani = 0.27;
        } else {
            vergiOrani = 0.35;
        }

        double gelirVergisi = vergiMatrahi * vergiOrani;

        // Evli ve çocuklu çalışanlar için küçük bir vergi indirimi (%1-2 civarı)
        if (calisan.isEvliMi()) {
            gelirVergisi *= 0.98; // %2 indirim
        }
        gelirVergisi -= calisan.getCocukSayisi() * 100; // her çocuk için 100₺ avantaj

        return Math.max(gelirVergisi, 0); // negatif olmasın
    }

    @Override
    public double hesaplaNetMaas(Calisan calisan) {
        double brut = calisan.getBrutMaas();
        double sgk = hesaplaSGKPrimi(calisan);
        double damga = hesaplaDamgaVergisi(calisan);
        double gelirVergisi = hesaplaGelirVergisi(calisan);
        return brut - (sgk + damga + gelirVergisi);
    }
}

// ============================
// 🔹 Yardımcı Sınıf (Raporlama)
// ============================
class MaasRaporu {
    private final DecimalFormat df = new DecimalFormat("#,##0.00 ₺");

    public void yazdir(Calisan c, MaasHesaplayici hesaplayici) {
        double sgk = hesaplayici.hesaplaSGKPrimi(c);
        double damga = hesaplayici.hesaplaDamgaVergisi(c);
        double gelir = hesaplayici.hesaplaGelirVergisi(c);
        double net = hesaplayici.hesaplaNetMaas(c);

        System.out.println("=========== MAAŞ RAPORU ===========");
        System.out.println("Çalışan: " + c.getAdSoyad());
        System.out.println("Brüt Maaş: " + df.format(c.getBrutMaas()));
        System.out.println("-----------------------------------");
        System.out.println("SGK + İşsizlik Primi: " + df.format(sgk));
        System.out.println("Gelir Vergisi: " + df.format(gelir));
        System.out.println("Damga Vergisi: " + df.format(damga));
        System.out.println("-----------------------------------");
        System.out.println("Net Maaş: " + df.format(net));
        System.out.println("===================================");
    }
}

// ============================
// 🔹 Ana Program (Main)
// ============================
public class MaasHesaplamaApp {
    public static void main(String[] args) {

        // Örnek çalışan verileri (değerleri biz belirliyoruz)
        Calisan calisan = new Calisan("Furkan Durkac", 35000, 1, true);

        MaasHesaplayici hesaplayici = new StandartMaasHesaplayici();
        MaasRaporu rapor = new MaasRaporu();

        rapor.yazdir(calisan, hesaplayici);
    }
}

