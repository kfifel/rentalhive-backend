package com.rentalhive.fileutil;

import org.springframework.stereotype.Component;

@Component
public interface Base64ToFile {
    public boolean saveFile(String base64String, String path, String name);
}
