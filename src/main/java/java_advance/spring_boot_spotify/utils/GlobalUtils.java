package java_advance.spring_boot_spotify.utils;
import com.google.gson.Gson;
import org.hibernate.mapping.Any;

public class GlobalUtils {

    public static String JsonStringify(Object data) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(data);
        return jsonString;
    }
}
