package cz.cvut.fel.pjv.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cz.cvut.fel.pjv.handlers.WeaponHandler;

import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
class for writing game objects' info in .json file
 */

public class WriteJson {
    private final static Logger LOGGER = Logger.getLogger(WeaponHandler.class.getName());
    public WriteJson(Hashtable<String, ArrayList<ObjectInfo>> dictionary) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            Writer writer = new FileWriter("src/main/resources/game_info/saving.json");
            gson.toJson(dictionary, writer);
            writer.close();
            LOGGER.log(Level.INFO, ".json file has been written");
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ".json file wasn't written", ex);
        }
    }
}
