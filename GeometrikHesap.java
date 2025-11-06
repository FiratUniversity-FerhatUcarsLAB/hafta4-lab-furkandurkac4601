package geometri;

import java.text.DecimalFormat;

public class UcgenAlani {

    public static void main(String[] args) {
        // 🔹 Üçgenin kenar uzunluklarını tanımla
        double kenarA = 5.0;
        double kenarB = 7.0;
        double kenarC = 9.0;

        // 🔹 Yarı çevreyi hesapla (Heron formülü için gerekli)
        double yariCevre = (kenarA + kenarB + kenarC) / 2;

        // 🔹 Heron formülüyle alanı hesapla
        double alan = Math.sqrt(
                yariCevre *
                (yariCevre - kenarA) *
                (yariCevre - kenarB) *
                (yariCevre - kenarC)
        );

        // 🔹 Sayısal biçimlendirme (2 ondalık basamak)
        DecimalFormat df = new DecimalFormat("#.##");

        // 🔹 Sonuçları ekrana yazdır
        System.out.println("=== ÜÇGEN ALANI HESAPLAMA ===");
        System.out.println("Kenar A: " + kenarA);
        System.out.println("Kenar B: " + kenarB);
        System.out.println("Kenar C: " + kenarC);
        System.out.println("-------------------------------");
        System.out.println("Yarı Çevre: " + df.format(yariCevre));
        System.out.println("Üçgenin Alanı: " + df.format(alan));
    }
}

