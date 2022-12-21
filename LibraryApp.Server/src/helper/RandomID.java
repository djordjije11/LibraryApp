package helper;

import java.util.Random;

/**
 *
 * @author Djordjije
 */
public class RandomID {
    
    public static String getRandomID(){
        Random random = new Random();
        String id = "";
        for (int i = 0; i < 8; i++) {
            id += random.nextInt(10);
        }
        return id; 
    }
}
