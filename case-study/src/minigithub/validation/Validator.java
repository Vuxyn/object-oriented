package minigithub.validation;

import minigithub.exception.ValidationException;

/**
 * VALIDASI SENDIRI: kumpulan method statis untuk mengecek data.
 *
 * Semua method di sini melempar ValidationException jika data tidak valid.
 * Class dibuat final dan constructor-nya private karena hanya menjadi wadah
 * method utility, tidak perlu dibuat objeknya.
 */
public final class Validator {

    private Validator() {
    }

    /** Memastikan teks tidak null dan tidak kosong. Hasilnya sudah di-trim. */
    public static String requireText(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new ValidationException(fieldName + " tidak boleh kosong");
        }
        return value.trim();
    }

    /** Memastikan objek tidak null. */
    public static <T> T requireNotNull(T value, String fieldName) {
        if (value == null) {
            throw new ValidationException(fieldName + " tidak boleh null");
        }
        return value;
    }

    /** Memastikan angka tidak kurang dari batas minimum. */
    public static void requireMin(int value, int min, String message) {
        if (value < min) {
            throw new ValidationException(message);
        }
    }

    /** Memastikan angka berada di antara min dan max. */
    public static void requireRange(int value, int min, int max, String message) {
        if (value < min || value > max) {
            throw new ValidationException(message);
        }
    }

    /** Memastikan kondisi bernilai true, misalnya dua branch tidak boleh sama. */
    public static void requireTrue(boolean condition, String message) {
        if (!condition) {
            throw new ValidationException(message);
        }
    }
}
