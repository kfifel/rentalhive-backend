package com.rentalhive.fileutil.Impl;

import com.rentalhive.fileutil.Base64ToFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class Base64ToFileImpl implements Base64ToFile {
    @Override
    public boolean saveFile(String base64String, String path, String name) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(base64String);
            String userHome = System.getProperty("user.home");
            Files.write(Paths.get(userHome, path, name), decodedBytes);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
