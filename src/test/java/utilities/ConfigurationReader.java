package utilities;
import javax.imageio.IIOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class ConfigurationReader {
    private static Properties configFile;

    static {
        try {
            //path to our .properties file
            //MIGHT BE WRONG PATH HERE
            String path = "configuration.properties";
            //we create object of input stream to access file
            FileInputStream input = new FileInputStream(path);
            //initialize configFile
            configFile = new Properties();
            //load properties file
            configFile.load(input);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String getProperty(String key){
        return configFile.getProperty(key);
    }
}