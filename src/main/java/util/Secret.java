package util;

import java.util.Properties;

public class Secret extends Credentials{
    static public void setPass(Properties dbProperties) {
        dbProperties.setProperty("user", "local_r0671376");
        dbProperties.setProperty("password", "oH-CXcmqc$kItTFf");
        /*
        dbProperties.setProperty("user", "");
        dbProperties.setProperty("password", "");
        */
    }
}
