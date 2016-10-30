package training.data.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 */
public class Config {

    private static final String URL = "service.datasource.{}.url";
    private static final String USER = "service.datasource.{}.user";
    private static final String PASSWORD = "service.datasource.{}.password";

    public final String url;
    public final String user;
    public final String password;
    public final int id;

    public Config(String resource, int id) {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream(resource));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        url = properties.getProperty(URL.replaceAll("\\{\\}", String.valueOf(id)));
        user = properties.getProperty(USER.replaceAll("\\{\\}", String.valueOf(id)));
        password = properties.getProperty(PASSWORD.replaceAll("\\{\\}", String.valueOf(id)));
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Config config = (Config) o;

        if (id != config.id) return false;
        if (url != null ? !url.equals(config.url) : config.url != null) return false;
        if (user != null ? !user.equals(config.user) : config.user != null) return false;
        return password != null ? password.equals(config.password) : config.password == null;

    }

    @Override
    public int hashCode() {
        int result = url != null ? url.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }

    @Override
    public String toString() {
        return "Config{" +
                "url='" + url + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }
}
