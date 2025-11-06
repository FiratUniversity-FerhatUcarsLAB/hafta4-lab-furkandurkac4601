/*
 * Ad Soyad: Furkan Durkaç
 * Ogrenci No: 250541045
 * Tarih: 06.11.2025
 * Aciklama: Görev 1 - Öğrenci Bilgi Sistemi
 * 
 * Bu program kullanıcıdan öğrenci bilgilerini alır ve
 * düzenli bir formatta ekrana yazdırır.
 * Diğer java dosyalarının başında da bu örnek formattaki gibi kısa bilgi giriniz.
 */

import java.util.Scanner;

public class OgrenciBilgi {
    public static void main(String[] args) {
        
        // 🔹 Scanner objesi oluşturma (kullanıcıdan veri almak için)
        Scanner input = new Scanner(System.in);
        
        // 🔹 Değişken tanımlamaları
        String ad = "Furkan";
        String soyad = "Durkaç";
        int ogrenciNo = 250541045;
        int yas = 20;
        double gpa = 3.20;
        
        // 🔹 Bilgileri ekrana yazdırma
        System.out.println("=== ÖĞRENCİ BİLGİ SİSTEMİ ===");
        System.out.println();
        System.out.println("Ad Soyad: " + ad + " " + soyad);
        System.out.println("Öğrenci No: " + ogrenciNo);
        System.out.println("Yaş: " + yas);
        System.out.println("GPA: " + gpa);
        System.out.println("==============================");
        
        // 🔹 Scanner'i kapatma
        input.close();
    }
}


