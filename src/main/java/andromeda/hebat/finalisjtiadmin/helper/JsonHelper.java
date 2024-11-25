package andromeda.hebat.finalisjtiadmin.helper;

public class JsonHelper {
    public static String getDataFromJson(String json, String key) {
        int dataStartIndex = json.indexOf("\"data\":");
        if (dataStartIndex == -1) return null;

        String dataJson = json.substring(dataStartIndex + 7);
        int dataEndIndex = dataJson.indexOf("}") + 1;
        dataJson = dataJson.substring(0, dataEndIndex);

        int keyStartIndex = dataJson.indexOf("\"" + key + "\":");
        if (keyStartIndex == -1) return null;

        keyStartIndex += key.length() + 3;

        char firstChar = dataJson.charAt(keyStartIndex);
        int keyEndIndex;

        if (firstChar == '"') {
            keyEndIndex = dataJson.indexOf("\"", keyStartIndex + 1);
            if (keyEndIndex == -1) return null;
            return dataJson.substring(keyStartIndex + 1, keyEndIndex);
        } else {
            keyEndIndex = dataJson.indexOf(",", keyStartIndex);
            if (keyEndIndex == -1) keyEndIndex = dataJson.indexOf("}", keyStartIndex);
            if (keyEndIndex == -1) return null;
            return dataJson.substring(keyStartIndex, keyEndIndex).trim();
        }
    }
}
