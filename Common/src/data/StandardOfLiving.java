package data;

import java.io.Serializable;

/**
 * Enumeration with city Standard Of Living constants.
 */
public enum StandardOfLiving implements Serializable {
    ULTRA_HIGH,
    VERY_HIGH,
    HIGH,
    LOW,
    ULTRA_LOW;
    /**
     * Generates a list of enum string values.
     * @return String with all enum values.
     */
    public static String sortStandard(){
        String AllStandard = "";
        for(StandardOfLiving standardOfLiving: values()){
            AllStandard += standardOfLiving.name() + ", ";
        }
        return AllStandard.substring(0, AllStandard.length() - 2);
    }
}