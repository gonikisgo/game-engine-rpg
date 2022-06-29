package cz.cvut.fel.pjv.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cz.cvut.fel.pjv.levels.LevelConstructor.ObjectInfo;

import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Hashtable;

public class WriteJson {
    public WriteJson(Hashtable<String, ArrayList<ObjectInfo>> dictionary) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        //System.out.println(gson.toJson(dictionary));
        try {
            Writer writer = new FileWriter("Output.json");
            gson.toJson(dictionary, writer);
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
