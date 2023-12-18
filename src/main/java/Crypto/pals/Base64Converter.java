package Crypto.pals;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;

public class Base64Converter {

    /**
     * Challenge 1: Apache shiro {shiro.apache.org} to encode and decode stuff.
     */
    public static String toBase64From(String hex) {

        byte[] raw = Hex.decode(hex);
        return Base64.encodeToString(raw);
    }

    public static String toHexFrom(String base64) {
        byte[] raw = Base64.decode(base64);
        return Hex.encodeToString(raw);
    }
}
