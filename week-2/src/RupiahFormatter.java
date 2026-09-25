public class RupiahFormatter {

    // Format custom manual tanpa pakai String.format / printf / DecimalFormat
    // Contoh: 300000000 -> "300.000.000"
    public static String format(double amount) {
        long value = (long) amount;
        if (value == 0) {
            return "0";
        }

        boolean negative = false;
        if (value < 0) {
            negative = true;
            value = -value;
        }

        String s = Long.toString(value);
        String result = "";
        int count = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            result = s.charAt(i) + result;
            count++;
            if (count % 3 == 0 && i != 0) {
                result = "." + result;
            }
        }

        if (negative) {
            result = "-" + result;
        }

        return result;
    }
}
