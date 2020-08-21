package com.example.mp3skoto;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.Toast;

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
            allMp3 = am.list("hira");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allMp3;
    }

    public void playMp3(AssetManager am, String pathMp3) {
        MediaPlayer mp = new MediaPlayer();
        if (mp.isPlaying()) {
            mp.pause();
        }
        else{
            try {
                AssetFileDescriptor afd = am.openFd("hira/"+pathMp3);
                mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                mp.prepare();
                mp.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
