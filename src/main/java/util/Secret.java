package util;

import java.util.Properties;

public class Secret extends Credentials{
    static public void setPass(Properties dbProperties) {

        dbProperties.setProperty("user", "");
        dbProperties.setProperty("password", "");

    }
}
