package cz.cvut.fel.pjv.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import cz.cvut.fel.pjv.handlers.WeaponHandler;

import javax.swing.*;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
class for reading game objects' info from .json file
 */

public class ReadJson {
    private final static Logger LOGGER = Logger.getLogger(WeaponHandler.class.getName());
    public Hashtable<String, ArrayList<ObjectInfo>> dictionary;

    public ReadJson(String filePath) {
        try {
            Reader reader = new FileReader(filePath);
            Type typeOfHashDict = new TypeToken<Hashtable<String, ArrayList<ObjectInfo>>>() {
            }.getType();
            dictionary = new Gson().fromJson(reader, typeOfHashDict);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Previous save is missing");
            LOGGER.log(Level.SEVERE, ".json file wasn't read", ex);
        }
    }

}
