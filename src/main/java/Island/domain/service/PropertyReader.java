package Island.domain.service;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class PropertyReader {

    public Map<String, Double> readProperty(String fileName) {

        ResourceBundle bundle = ResourceBundle.getBundle(fileName);

        Enumeration<String> keys = bundle.getKeys();

        Map<String, Double> propertyNameToValue = new HashMap<>();

        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            Double value = Double.valueOf(bundle.getString(key));

            propertyNameToValue.put(key, value);
        }

        return propertyNameToValue;
    }



}
