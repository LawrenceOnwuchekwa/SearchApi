package com.api.searchtool.StaticHelperMethods;

import java.io.IOException;

public class StaticHelperMethods {

    public static String getFileExtension(String filename){
// It ensures that the dot character (representing the file extension separator) is found within the filename
// and is not the first character of the filename.
// It also ensures that there are characters after the dot,
// indicating that there is an actual file extension.
        int lastDotIndex = filename.lastIndexOf('.');
        if(lastDotIndex>0 && lastDotIndex < filename.length() - 1) {
            return filename.substring(lastDotIndex + 1);
        }
        return null;
    }

}
