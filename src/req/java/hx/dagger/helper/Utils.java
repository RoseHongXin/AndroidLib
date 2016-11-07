package hx.dagger.helper;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by rose on 16-7-27.
 */
public class Utils {

    public static String inputStream2String(InputStream in) throws IOException {
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        int n;
        while ((n = in.read(b)) != -1) {
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }

}
