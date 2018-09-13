package org.jim.open.secret;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class SecretToolTest {

    @Test
    public void testKey() {
        String clientId = SecretTool.clientId();
        String clientSecret = SecretTool.clientSecret();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encrypt = encoder.encode(clientSecret);

        System.out.println(clientId);
        System.out.println(clientSecret);
        System.out.println(encrypt);
        // 645968e2bad447d78286724a135fa868
        // 61ccdbce2e15ee92c55019d4406eca38f2fb6b92
        // $2a$10$peNQypvwaTk9WAZ93j3RuOkbpSv0Wy23uKenR9iJIVxG/NYiP7USa
    }

}
