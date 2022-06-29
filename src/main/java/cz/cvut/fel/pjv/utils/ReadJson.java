package cz.cvut.fel.pjv.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import cz.cvut.fel.pjv.levels.LevelConstructor;

import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Hashtable;

public class ReadJson {
    public Hashtable<String, ArrayList<LevelConstructor.ObjectInfo>> dictionary;
    public ReadJson() {
        try {
            Reader reader = new FileReader("Output.json");
            Type typeOfHashDict = new TypeToken<Hashtable<String, ArrayList<LevelConstructor.ObjectInfo>>>() { }.getType();
            dictionary = new Gson().fromJson(reader, typeOfHashDict);
            // System.out.println(dictionary.get("Player").get(0).getX());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
