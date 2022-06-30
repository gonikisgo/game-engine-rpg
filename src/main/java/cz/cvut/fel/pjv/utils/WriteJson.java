package cz.cvut.fel.pjv.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Hashtable;

/*
class for writing game objects' info in .json file
 */

public class WriteJson {
    public WriteJson(Hashtable<String, ArrayList<ObjectInfo>> dictionary) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            Writer writer = new FileWriter("src/main/resources/game_info/saving.json");
            gson.toJson(dictionary, writer);
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
