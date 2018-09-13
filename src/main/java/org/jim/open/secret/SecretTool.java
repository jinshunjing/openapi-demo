package org.jim.open.secret;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Random;
import java.util.UUID;

/**
 * Tool to generate the client id and secret
 *
 * @author  author
 */
public class SecretTool {

    public static final String clientId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

    public static String clientSecret() {
        String val = "";
        int length = 32;
        Random random = new Random();
        for(int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            if("char".equalsIgnoreCase(charOrNum)) {
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (choice + random.nextInt(26));
            } else if("num".equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }

        return DigestUtils.sha1Hex(val);
    }

}
