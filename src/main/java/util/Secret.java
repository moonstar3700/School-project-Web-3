package util;

import java.util.Properties;

public class Secret extends Credentials{
    static public void setPass(Properties dbProperties) {
        dbProperties.setProperty("user", "postgres");
        dbProperties.setProperty("password", "Paszcza3300");
        //dbProperties.setProperty("user", "local_r0744228");
        //dbProperties.setProperty("password", "O7=*RsHE&B!am-]i");
        /*
        dbProperties.setProperty("user", "");
        dbProperties.setProperty("password", "");
        */
    }
}
