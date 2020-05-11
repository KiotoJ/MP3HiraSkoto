package com.example.mp3skoto;

import android.content.res.AssetManager;

import java.io.File;
import java.io.IOException;

public class Mp3Activity {
    private String nameFile;

    public String getNameFile() {
        return nameFile;
    }

    public String[] getAllMp3(AssetManager am) {
        String[] allMp3 = {};
        try {
            allMp3 = am.list("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allMp3;
    }

    public void readMp3(File oneMp3) {

    }
}
