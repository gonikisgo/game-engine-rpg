package cz.cvut.fel.pjv.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Hashtable;

/*
class for reading game objects' info from .json file
 */

public class ReadJson {
    public Hashtable<String, ArrayList<ObjectInfo>> dictionary;

    public ReadJson() {
        try {
            Reader reader = new FileReader("src/main/resources/game_info/saving.json");
            Type typeOfHashDict = new TypeToken<Hashtable<String, ArrayList<ObjectInfo>>>() {
            }.getType();
            dictionary = new Gson().fromJson(reader, typeOfHashDict);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
