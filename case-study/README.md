# Mini GitHub — Studi Kasus Abstract Class, Interface, Inheritance & Polymorphism

**Nama:** I Kadek Mahesa Permana Putra  
**NIM:** F1D02410052  
**Kelas:** 5A  
**Mata Kuliah:** Object Oriented Programming  
**Bahasa:** Java 11+

## 1. Studi Kasus

Mini GitHub adalah simulasi sederhana **activity feed** sebuah repository.

- `Repository` menyimpan banyak `Contribution`.
- Jenis kontribusi: `Commit`, `Issue`, `BugReport`, dan `PullRequest`.
- Setiap kontribusi punya poin, tetapi rumusnya berbeda-beda.
- Hanya `Issue` dan `PullRequest` yang bisa ditutup.
- Hanya `PullRequest` yang bisa di-review dan di-merge.

Dari kebutuhan tersebut:

| Konsep | Implementasi |
|---|---|
| Abstract class | `Contribution`, `Repository` |
| Abstract method | `Contribution.getSummary()`, `Contribution.getPoints()`, `Repository.canView()` |
| Interface | `Closable`, `Reviewable` |
| Inheritance | `BugReport extends Issue`, `Issue extends Contribution`, `PrivateRepository extends Repository` |
| Polymorphism | `Contribution.describe()`, `Repository.getTotalPoints()`, `repo.canView(user)` |
| Encapsulation | field `private`, `Validator`, `List.copyOf()` |

## 2. Struktur Proyek

```
case-study/
├── README.md
├── docs/
│   └── class-diagram.mmd          # sumber class diagram (Mermaid)
└── src/minigithub/
    ├── Main.java                  # program demo
    ├── contract/                  # INTERFACE
    │   ├── Closable.java
    │   └── Reviewable.java
    ├── exception/                 # custom exception
    │   └── ValidationException.java
    ├── validation/                # validasi sendiri
    │   └── Validator.java
    ├── model/                     # hierarki Contribution
    │   ├── User.java
    │   ├── Contribution.java      # ABSTRACT CLASS
    │   ├── Commit.java
    │   ├── Issue.java             # implements Closable
    │   ├── BugReport.java         # extends Issue -> inheritance bertingkat
    │   └── PullRequest.java       # implements Closable + Reviewable
    └── repository/                # hierarki Repository
        ├── Repository.java         # ABSTRACT CLASS
        ├── PublicRepository.java
        └── PrivateRepository.java
```

## 3. Cara Menjalankan

```bash
javac -d out $(find src -name "*.java")
java -cp out minigithub.Main
```

Di Windows (PowerShell):

```powershell
javac -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
java -cp out minigithub.Main
```

## 4. Class Diagram

![Class Diagram](docs/Class%20Diagram.png)


Relasi utamanya:

```
Contribution (abstract)
├── Commit
├── Issue (implements Closable)
│   └── BugReport
└── PullRequest (implements Closable, Reviewable)

Repository (abstract)
├── PublicRepository
└── PrivateRepository
```

## 5. Penjelasan Konsep OOP

### 5.1 Abstract Class dan Abstract Method

`Contribution` adalah **abstract class**. Objeknya tidak bisa dibuat langsung:

```java
new Contribution(andi, "judul"); // ERROR: Contribution is abstract
```

Alasannya, "kontribusi" hanyalah konsep umum. Yang nyata dibuat adalah `Commit`, `Issue`,
`BugReport`, atau `PullRequest`.

`Contribution` punya dua **abstract method**:

```java
public abstract String getSummary();
public abstract int getPoints();
```

Karena abstrak, method tersebut belum punya isi. Setiap subclass **wajib** meng-override-nya
karena ringkasan dan poin tiap jenis kontribusi berbeda. Kalau subclass lupa meng-override,
kode tidak akan bisa dikompilasi.

Selain abstract method, `Contribution` juga punya **method konkret** yang langsung diwariskan:

```java
public final String describe() {
    return String.format("#%d %-12s %-8s %2d poin | %s",
            id, getType(), author, getPoints(), getSummary());
}
```

`describe()` sudah berisi logika format activity feed. Subclass tidak perlu menulis ulang.

`Repository` juga abstract class. Abstract method-nya adalah `canView(User user)` karena aturan
akses tiap repository berbeda:

```java
public abstract boolean canView(User user);
```

### 5.2 Interface

Interface adalah **kontrak kemampuan**. Class yang implements interface wajib menyediakan
method yang sudah dideklarasikan.

```java
public interface Closable {
    void close();
    boolean isClosed();
}
```

| Interface | Diimplementasikan oleh | Alasan |
|---|---|---|
| `Closable` | `Issue`, `PullRequest` | Hanya issue dan PR yang bisa ditutup. Commit tidak bisa. |
| `Reviewable` | `PullRequest` | Hanya PR yang punya proses review/approval. |

**Kenapa `close()` tidak ditaruh langsung di `Contribution`?**

Kalau `close()` ditaruh di abstract class `Contribution`, maka `Commit` ikut mewarisi `close()`.
Padahal commit tidak bisa ditutup. Interface membuat kemampuan hanya "dipasang" ke class yang
membutuhkannya.

**Kenapa PullRequest bisa punya banyak kemampuan?**

```java
public class PullRequest extends Contribution implements Closable, Reviewable {
```

Java hanya mengizinkan **satu** `extends`, tetapi boleh **banyak** `implements`. Dengan interface,
`PullRequest` bisa sekaligus "bisa ditutup" dan "bisa di-review". Hal ini tidak bisa dilakukan
dengan dua abstract class.

**Perbedaan abstract class vs interface:**

| | Abstract class | Interface |
|---|---|---|
| Hubungan | "is-a" (adalah) | "can-do" (bisa melakukan) |
| Isi | field, constructor, method konkret + abstract | kontrak method |
| Pewarisan | `extends`, maksimal 1 | `implements`, boleh banyak |
| Contoh di project | `Contribution`, `Repository` | `Closable`, `Reviewable` |

### 5.3 Inheritance

Inheritance membuat subclass mewarisi field dan method dari induknya.

**Hierarki Contribution:**

```
Contribution (abstract)
├── Commit
├── Issue (implements Closable)
│   └── BugReport
└── PullRequest (implements Closable, Reviewable)
```

`BugReport extends Issue` adalah **inheritance bertingkat** (level 2):
`Contribution` -> `Issue` -> `BugReport`.

Bagian kode yang menunjukkan inheritance:

```java
public class BugReport extends Issue {
    public BugReport(User author, String title, int severity) {
        super(author, title); // memanggil constructor Issue
        ...
    }
}
```

`BugReport` **tidak menulis ulang** `close()` dan `isClosed()`. Keduanya otomatis diwarisi dari
`Issue`:

```java
bug.close(); // method milik Issue, dipakai oleh BugReport
```

Subclass juga bisa memakai logika induk lewat `super`:

```java
return super.getSummary() + " ...";   // menambah info dari versi Issue
return super.getPoints() + severity;  // 3 poin Issue + severity
```

**Hierarki Repository:**

```
Repository (abstract)
├── PublicRepository  -> canView() = semua boleh lihat
└── PrivateRepository -> canView() = hanya owner/collaborator
```

### 5.4 Polymorphism

Polymorphism artinya **satu tipe variabel, banyak bentuk perilaku**. Method yang dijalankan
ditentukan oleh objek aslinya saat program berjalan (runtime polymorphism / method overriding).

**Contoh 1 — Repository:**

```java
List<Repository> repositories = List.of(publicRepo, privateRepo);

for (Repository repo : repositories) {
    repo.canView(budi); // method sama, hasil beda
}
```

- `publicRepo.canView(budi)` -> `true` (semua orang boleh lihat)
- `privateRepo.canView(budi)` -> `true` (budi collaborator)
- `privateRepo.canView(citra)` -> `false` (citra bukan member)

**Contoh 2 — Contribution:**

```java
for (Contribution c : repository.getContributions()) {
    System.out.println(c.describe());
}
```

Tipe variabelnya `Contribution`, tetapi `describe()` memanggil `getPoints()` dan `getSummary()`
milik objek aslinya:

| Objek | `getSummary()` yang jalan | `getPoints()` yang jalan |
|---|---|---|
| `Commit` | versi Commit | 1 atau 2 |
| `Issue` | versi Issue | 3 |
| `BugReport` | versi BugReport | 3 + severity |
| `PullRequest` | versi PullRequest | 5 atau 8 |

**Contoh 3 — total poin:**

```java
public int getTotalPoints() {
    int total = 0;
    for (Contribution contribution : contributions) {
        total += contribution.getPoints(); // rumus mengikuti subclass
    }
    return total;
}
```

**Contoh 4 — interface reference:**

```java
Reviewable reviewable = pr;  // PullRequest implements Reviewable
reviewable.review(andi);

Closable closable = bug;     // BugReport mewarisi Closable dari Issue
closable.close();
```

Tidak perlu cek tipe manual: kalau objek tidak implements interface, kode tidak akan bisa
dikompilasi. Interface sudah menjadi kontrak di level tipe. `Commit` tidak implements `Closable`,
jadi memang tidak punya method `close()`.

### 5.5 Encapsulation

Encapsulation berarti data dilindungi dan hanya bisa diakses/diubah lewat method yang aman.

- Semua field dibuat **`private`**. Field yang tidak boleh berubah dibuat **`final`**
  (`id`, `author`, `username`, `sourceBranch`, `targetBranch`).
- **Validasi sendiri**: class `Validator` mengumpulkan pengecekan `requireText()`,
  `requireNotNull()`, `requireMin()`, `requireRange()`, dan `requireTrue()`. Kalau gagal,
  `Validator` melempar `ValidationException`, yaitu custom exception yang extends `RuntimeException`.
- **Membedakan jenis error**: `ValidationException` untuk data yang salah, sedangkan
  `IllegalStateException` untuk aksi yang tidak boleh dilakukan pada status saat ini
  (misalnya merge PR yang belum di-approve).
- **Status hanya berubah lewat method yang punya aturan.** `PullRequest.status` tidak punya setter.
  Status hanya berubah lewat `close()`, `review()`, dan `merge()`.
- **Aturan bisnis dijaga di dalam class**: author tidak boleh me-review PR sendiri, merge butuh
  minimal 1 approval, dan PR yang sudah di-merge tidak bisa ditutup.
- **Defensive copy**: `getContributions()` mengembalikan `List.copyOf(contributions)`, sehingga
  list asli tidak bisa diubah dari luar.
- `Contribution` constructor bersifat `protected`, jadi hanya subclass yang bisa memanggilnya.
- `idCounter` bersifat `private static`, jadi tidak bisa diubah dari luar.

## 6. Aturan Poin

| Jenis | Rumus |
|---|---|
| `Commit` | 1 poin, +1 jika mengubah >= 5 file |
| `Issue` | 3 poin |
| `BugReport` | 3 poin + severity (1–3) |
| `PullRequest` | 5 poin, +3 jika sudah di-merge |

## 7. Contoh Output

```
=== 1. ABSTRACT CLASS + POLYMORPHISM ===
andi/mini-github [Public] - 0 kontribusi
  budi  bisa lihat? ya
  citra bisa lihat? ya
andi/catatan-rahasia [Private] - 0 kontribusi
  budi  bisa lihat? ya
  citra bisa lihat? tidak

andi/mini-github [Public] - 3 kontribusi
  #1 Commit       @andi     1 poin | commit "Tambah class Repository" (3 file)
  #2 BugReport    @citra    6 poin | [BUG sev-3] issue "Poin PR tidak bertambah" [OPEN]
  #3 PullRequest  @budi     5 poin | PR feature/review -> main [OPEN, 0 approval]
  Total poin: 12

=== 2. INTERFACE Reviewable ===
Ditolak : Author tidak boleh me-review PR sendiri
andi me-review PR #3, approval = 1
PR #3 berhasil di-merge

=== 3. INTERFACE Closable ===
BugReport ditutup? true
Gagal   : PR #3 sudah di-merge, tidak bisa ditutup
Commit tidak bisa ditutup karena bukan Closable

=== 4. VALIDASI ===
Ditolak : Judul tidak boleh kosong
Ditolak : Severity harus antara 1 sampai 3

=== 5. HASIL AKHIR ===
andi/mini-github [Public] - 3 kontribusi
  #1 Commit       @andi     1 poin | commit "Tambah class Repository" (3 file)
  #2 BugReport    @citra    6 poin | [BUG sev-3] issue "Poin PR tidak bertambah" [CLOSED]
  #3 PullRequest  @budi     8 poin | PR feature/review -> main [MERGED, 1 approval]
  Total poin: 15
```

## 8. Format Submit Forum

```
Subject: 5A_F1D02410052_I Kadek Mahesa Permana Putra

Message:
- Class diagram: (tempel gambar)
- Kode: (link commit repository publik)
```
