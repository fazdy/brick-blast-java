# 🎮 Brick Blast Java

Game klasik Brick Blast (Breakout) yang dibuat menggunakan bahasa pemrograman Java.

## 📖 Deskripsi

Brick Blast adalah permainan arcade klasik di mana pemain mengendalikan sebuah paddle untuk memantulkan bola dan menghancurkan semua brick yang tersusun di layar. Game ini merupakan implementasi dari konsep game Breakout yang populer sejak era 1970-an. 

### Cara Bermain
1. Gunakan tombol **panah kiri** dan **panah kanan** untuk menggerakkan paddle
2. Pantulkan bola menggunakan paddle untuk menghancurkan brick
3. Hancurkan semua brick untuk memenangkan permainan
4. Jangan biarkan bola jatuh melewati paddle!

## 🎯 Tujuan Pembuatan

Game Brick Blast Java ini dibuat dengan beberapa tujuan utama:

1. **Pembelajaran Pemrograman Java** - Mengimplementasikan konsep Object-Oriented Programming (OOP) seperti class, inheritance, dan encapsulation dalam konteks pengembangan game.

2. **Pemahaman Game Development** - Mempelajari dasar-dasar pengembangan game termasuk:
   - Game loop
   - Collision detection (deteksi tabrakan)
   - Rendering grafis menggunakan Java AWT/Swing
   - Event handling untuk input keyboard

3. **Penerapan Konsep Pemrograman** - Mengaplikasikan berbagai konsep pemrograman seperti:
   - Penggunaan array 2D untuk menyimpan data brick
   - Logika pergerakan objek (bola dan paddle)
   - Sistem koordinat dan kalkulasi posisi

4. **Proyek Portfolio** - Sebagai proyek untuk mendemonstrasikan kemampuan pemrograman Java.

## 🏗️ Struktur Proyek

```
brick-blast-java/
├── src/
│   ├── Main.java          # Entry point aplikasi
│   ├── GamePanel.java     # Panel utama game & game logic
│   ├── Ball.java          # Kelas objek bola
│   ├── Paddle.java        # Kelas objek paddle
│   └── MapGenerator.java  # Generator susunan brick
└── README.md
```

## 🚀 Cara Menjalankan

1.  Pastikan Java Development Kit (JDK) sudah terinstall
2. Clone repository ini:
   ```bash
   git clone https://github.com/KemalRajasa/brick-blast-java.git
   ```
3. Masuk ke direktori src:
   ```bash
   cd brick-blast-java/src
   ```
4. Compile semua file Java:
   ```bash
   javac *.java
   ```
5. Jalankan game:
   ```bash
   java Main
   ```

## 🛠️ Teknologi yang Digunakan

- **Java** - Bahasa pemrograman utama
- **Java AWT/Swing** - Library untuk GUI dan grafis

## 📝 Lisensi

Proyek ini dibuat untuk tujuan pembelajaran.

---

⭐ Jangan lupa untuk memberikan star jika proyek ini bermanfaat! 
