# Periyodik Tablo (Android APK)

118 elementli, Türkçe, karanlık temalı periyodik tablo uygulaması. Bağımlılık yok — saf Java + Android SDK.

## Özellikler
- Gerçek periyodik tablo düzeni (18 grup × 7 periyot + lantanit/aktinit dizileri)
- Kategoriye göre renk kodlaması ve açıklama şeridi
- Elemente dokunun: tür, atom kütlesi, periyot, grup ve elektron dizilimi
- Android 7.0+ (minSdk 24) desteği, imzalı `PeriyodikTablo.apk`

## Proje yapısı
```
AndroidManifest.xml            Uygulama bildirimi
res/drawable/ic_launcher.xml   Vektör uygulama ikonu
src/com/vibe/periodictable/
  MainActivity.java            Arayüz (programatik, layout XML'siz)
  ElementTable.java            118 elementin verisi + elektron dizilimi hesabı
build.sh                       APK derleme betiği
```

## Derleme
`build.sh` şu araçları $HOME/tools altında arar:
- Android build-tools 34 (aapt2, d8, apksigner, zipalign)
- Platform android-34 (`android.jar`)
- Eclipse ECJ derleyici (JRE üzerinde çalışır) veya herhangi bir JDK

```bash
./build.sh
# çıktı: PeriyodikTablo.apk
```

İmzalama anahtarı `debug.keystore` olarak yerelde tutulur (repo'ya dahil değildir).
Şifreler: `periodic` / `periodic`, alias: `periodic`.

## Kurulum
APK'yi telefona kopyalayıp açın; "Bilinmeyen kaynaklardan kurulum" iznini verin.

## Doğrulama notları
- Elektron dizilimleri Aufbau ilkesi + bilinen istisnalarla (Cr, Cu, Pd, Au, U vb.) hesaplanır.
- Kütle değerleri IUPAC standart kütleler; yörünge kararlısı olmayanlar köşeli parantezle gösterilir.
