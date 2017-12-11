import org.newdawn.slick.SlickException;
import org.newdawn.slick.muffin.FileMuffin;
import org.newdawn.slick.muffin.Muffin;
import org.newdawn.slick.muffin.WebstartMuffin;
import org.newdawn.slick.util.Log;

import java.io.IOException;
import java.util.HashMap;

public class SavedState {
    private String fileName;
    private Muffin muffin;
    private HashMap numericData = new HashMap(); //Where the data will be stored
    private HashMap stringData = new HashMap(); // where all the string data will be stored

    public SavedState(String fileName) throws SlickException {
        this.fileName = fileName;

    }

    /*
    Get a number stored at any given lcation
    methodOfFiels = The number saved at a given location
    return: The number
    */
    public double getNumber(String nameOfField) {
        return getNumber(nameOfField, 0);

    }



    public double getNumber(String nameOfField, double defaultValue) {
        Double value = ((Double) numericData.get(nameOfField));

        if (value == null) {
            return defaultValue;
        }

        return value.doubleValue();
    }

    public String getString(String nameOfField) {
        return getString(nameOfField, null);
    }

    public String getString(String nameOfField, String defaultValue) {
        String value = (String) stringData.get(nameOfField);

        if (value == null) {
            return defaultValue;
        }

        return value;
    }

    public void setString(String nameOfField, String value) {
        stringData.put(nameOfField, value);
    }

    public void save() throws IOException {
        muffin.saveFile(numericData, fileName + "_Number");
        muffin.saveFile(stringData, fileName + "String");
    }

    public void load() throws IOException {
        numericData = muffin.loadFile(fileName + "_number");
        stringData = muffin.loadFile(fileName + "_String");
    }

    public void clear(){
        numericData.clear();
        stringData.clear();
    }
}
