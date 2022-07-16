package com.example.gp;

import android.media.MediaExtractor;
import android.media.MediaFormat;

import com.google.gson.annotations.SerializedName;
import com.jlibrosa.audio.JLibrosa;
import com.jlibrosa.audio.exception.FileFormatNotSupportedException;
import com.jlibrosa.audio.wavFile.WavFileException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import static java.nio.ByteOrder.BIG_ENDIAN;
import static java.nio.ByteOrder.LITTLE_ENDIAN;

import java.io.IOException;

public class VoiceClass {
    @SerializedName("data")
    private  float [] data;

    public VoiceClass(String filePath) {

        File file = new File(filePath);
        // Creating an object of FileInputStream to
        // read from a file
        try (FileInputStream fl = new FileInputStream(file)) {
            byte[] arr = new byte[(int)file.length()];
            int framesCount = (int)file.length();

            // assuming stereo format, so two entries per frame
            float[] temp = new float[framesCount];
            long tempCountdown = temp.length;
            int bytesRead = 0;
            int bufferIdx;
            int clipIdx = 0;
            byte[] buffer = new byte[(int)file.length()];
            while((bytesRead = fl.read(buffer, 0, (int)file.length())) != -1)
            {
                bufferIdx = 0;
                for (int i = 0, n = (bytesRead >> 1); i < n; i ++)
                {
                    if ( tempCountdown-- >= 0)
                    {
                        temp[clipIdx++] =
                                ( buffer[bufferIdx++] & 0xff )
                                        | ( buffer[bufferIdx++] << 8 ) ;
                    }
                }
            }for (int i=0;i<temp.length;i++){
                temp[i]=temp[i]/10000000;
            }
            int targetIndex = 0;
            for( int sourceIndex = 0;  sourceIndex < temp.length;  sourceIndex++ )
            {
                if( temp[sourceIndex] != 0 )
                    temp[targetIndex++] = temp[sourceIndex];
            }
            float[] temp_no_z = new float[targetIndex];
            System.arraycopy( temp, 0, temp_no_z, 0, targetIndex );

            this.data=temp_no_z;

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public float[] getData() {
        return data;
    }

    public void setData(float[] data) {
        this.data = data;
    }

}
