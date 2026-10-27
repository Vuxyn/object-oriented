package minigithub.exception;

/**
 * EXCEPTION buatan sendiri untuk data/input yang tidak valid.
 *
 * extends RuntimeException = unchecked exception, jadi method yang melemparnya
 * tidak wajib menulis "throws" dan pemanggil tidak wajib try-catch.
 *
 * Dibuat terpisah dari IllegalStateException supaya jelas bedanya:
 * - ValidationException  -> datanya salah (judul kosong, severity di luar 1-3)
 * - IllegalStateException -> datanya benar, tetapi statusnya tidak mengizinkan aksi
 *   (misalnya merge PR yang belum di-approve)
 */
public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ValidationException(String message) {
        super(message);
    }
}
