/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.obrii.mit.dp2021.FileReader;

import java.io.Serializable;

/**
 *
 * @author Дмитрий
 */
public class FileHandler implements Serializable {
    
    private static String pathOfFile;
    private static String nameOfFile;
    
    public static String getPathOfFile() {
        return pathOfFile;
    }

    public static void setPathOfFile(String pathOfFile) {
        FileHandler.pathOfFile = pathOfFile;
    }

    public static String getNameOfFile() {
        return nameOfFile;
    }

    public static void setNameOfFile(String nameOfFile) {
        FileHandler.nameOfFile = nameOfFile;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FileHandler{pathOfFile=").append(pathOfFile);
        sb.append(", nameOfFile=").append(nameOfFile);
        sb.append('}');
        return sb.toString();
    }
}
