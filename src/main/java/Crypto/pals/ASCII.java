package Crypto.pals;

public class ASCII {

    public static boolean isCookieCharacter(byte ch) {

        return isEnglishCharacter(ch) || ch == '=';
    }

    public static boolean isUrlCharacter(byte ch) {

        return isEnglishCharacter(ch) || ch == '='|| ch == ';'|| ch == '%'|| ch == '#';
    }

    public static boolean isEnglishCharacter(byte ch) {

        return (ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') ||
                ch == ' ' || ch == '-' || ch == '\'' || ch == '\n' || ch == '/' || ch == ',' || ch == '.' || ch == '?';
    }

    public static int countCharacters(byte[] content) {

        int total = 0;
        for (int i = 0; i < content.length; i++) {
            byte ch = content[i];
            if (isEnglishCharacter(ch)) {
                total++;
            }

        }
        return total;
    }

    public static byte selectFirstAscii(byte... chars) {

        for (byte b : chars) {
            if (isEnglishCharacter(b))
                return b;
        }

        throw new IllegalStateException("No ascii in input: " + (char)chars[0] + " " + (char)chars[1] + " ... ");
    }
}
