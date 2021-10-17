package util;

import java.util.Properties;

public abstract class Credentials {
    static public void setPass(Properties dbProperties) {
        dbProperties.setProperty("user", "r123456");
        dbProperties.setProperty("password", "dagadegijniebepalen!");
    }

}
