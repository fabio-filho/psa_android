package com.ufrj.nce.psa.Objects;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;

import com.ufrj.nce.psa.Utilities.Functions;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by filhoefilha on 10/04/15.
 */
public class SoundStream
{

    public static int TIME_FADE_DEFAULT = 600;

    private MediaPlayer mediaPlayer = null;
    private Context context;
    private int iVolume;


    private final static int INT_VOLUME_MAX = 100;
    private final static int INT_VOLUME_MIN = 0;
    private final static float FLOAT_VOLUME_MAX = 1;
    private final static float FLOAT_VOLUME_MIN = 0;
    private Boolean isStopping = false, isPausing = false;

    public SoundStream(Context context)
    {
        this.context = context;
    }

    public SoundStream(Context context, int resource)
    {
        mediaPlayer = MediaPlayer.create(context, resource);
        this.context = context;
    }

    public SoundStream(Context context, AssetFileDescriptor descriptor)
    {
        this.context = context;

        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();

            mediaPlayer.prepare();
            mediaPlayer.setVolume(1f, 1f);

        }catch(Exception o){
            Functions.Log("SoundStream", o.toString());
        }
    }

    public SoundStream(Context context, String path)
    {
        this.context = context;
        //mediaPlayer = MediaPlayer.create(context, Uri.fromFile(new File(path)));
        //mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        mediaPlayer = new MediaPlayer();
        mediaPlayer.reset();
        mediaPlayer.setLooping(false);
        try {
            mediaPlayer.setDataSource(context,  Uri.fromFile(new File(path)));
            mediaPlayer.prepare();
        }catch (Exception o){
            Functions.Log("SoundStream", o.toString());
        }
    }

    public void load(String path, boolean looping)
    {
        mediaPlayer = MediaPlayer.create(context, Uri.fromFile(new File(path)));
        mediaPlayer.setLooping(looping);
    }

    public void load(int address, boolean looping)
    {
        mediaPlayer = MediaPlayer.create(context, address);
        mediaPlayer.setLooping(looping);
    }

    public MediaPlayer getMediaPlayer (){
        return mediaPlayer;
    }

    public void play(int fadeDuration){

        if (mediaPlayer == null) return;

        Functions.Log("play", "isPlaying: "+isPlaying());

        if (isPlaying()) return;

        //Set current volume, depending on fade or not
        if (fadeDuration > 0)
            iVolume = INT_VOLUME_MIN;
        else
            iVolume = INT_VOLUME_MAX;

        updateVolume(0);

        //Play music
        if(!mediaPlayer.isPlaying()) mediaPlayer.start();

        //Start increasing volume in increments
        if(fadeDuration > 0)
        {
            final Timer timer = new Timer(true);
            TimerTask timerTask = new TimerTask()
            {
                @Override
                public void run()
                {
                    updateVolume(1);
                    if (iVolume == INT_VOLUME_MAX)
                    {
                        timer.cancel();
                        timer.purge();
                    }
                }
            };

            // calculate delay, cannot be zero, set to 1 if zero
            int delay = fadeDuration/INT_VOLUME_MAX;
            if (delay == 0) delay = 1;

            timer.schedule(timerTask, delay, delay);
        }
    }

    public void pause(int fadeDuration)
    {
        Functions.Log("pause","Paused");

        if (mediaPlayer == null) return;

        try {
            if (isPausing) return;

            isPausing = true;

            //Set current volume, depending on fade or not
            if (fadeDuration > 0)
                iVolume = INT_VOLUME_MAX;
            else
                iVolume = INT_VOLUME_MIN;

            updateVolume(0);

            //Start increasing volume in increments
            if (fadeDuration > 0) {
                final Timer timer = new Timer(true);
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        updateVolume(-1);
                        if (iVolume == INT_VOLUME_MIN) {
                            //Pause music
                            mediaPlayer.pause();
                            timer.cancel();
                            timer.purge();
                        }
                    }
                };

                // calculate delay, cannot be zero, set to 1 if zero
                int delay = fadeDuration / INT_VOLUME_MAX;
                if (delay == 0) delay = 1;

                timer.schedule(timerTask, delay, delay);
            }

        }catch(Exception o){
            Functions.Log("pause", o.toString());
        }

        isPausing = false;

    }

    private Boolean updateVolume(int change)
    {
        try {
            if (mediaPlayer == null) return false;

            //increment or decrement depending on type of fade
            iVolume = iVolume + change;

            //ensure iVolume within boundaries
            if (iVolume < INT_VOLUME_MIN)
                iVolume = INT_VOLUME_MIN;
            else if (iVolume > INT_VOLUME_MAX)
                iVolume = INT_VOLUME_MAX;

            //convert to float value
            float fVolume = 1 - ((float) Math.log(INT_VOLUME_MAX - iVolume) / (float) Math.log(INT_VOLUME_MAX));

            //ensure fVolume within boundaries
            if (fVolume < FLOAT_VOLUME_MIN)
                fVolume = FLOAT_VOLUME_MIN;
            else if (fVolume > FLOAT_VOLUME_MAX)
                fVolume = FLOAT_VOLUME_MAX;

            mediaPlayer.setVolume(fVolume, fVolume);
        }catch(Exception o){
            Functions.Log("updateVolume", o.toString());
            return false;
        }

        return true;
    }


    public Boolean isPlaying(){

        try {
            if (mediaPlayer != null)
                return mediaPlayer.isPlaying();
            else
                return false;


        }catch(IllegalStateException o){
            Functions.Log("isPlaying",o.toString());
        }

        return false;
    }

    public void stop(int fadeDuration)
    {
        Functions.Log("stop","stopped");

        if (mediaPlayer == null) return;

        if (isStopping) return;

        isStopping = true;

        try {
            // Set current volume, depending on fade or not
            if (fadeDuration > 0)
                iVolume = INT_VOLUME_MAX;
            else
                iVolume = INT_VOLUME_MIN;

            updateVolume(0);

            // Start increasing volume in increments
            if (fadeDuration > 0)
            {
                final Timer timer = new Timer(true);
                TimerTask timerTask = new TimerTask()
                {
                    @Override
                    public void run()
                    {
                        updateVolume(-1);
                        if (iVolume == INT_VOLUME_MIN)
                        {
                            // Stop music
                            mediaPlayer.stop();
                            timer.cancel();
                            timer.purge();
                            //Functions.Log("stop","Stopped Sound End");
                        }
                    }
                };

                // calculate delay, cannot be zero, set to 1 if zero
                int delay = fadeDuration / INT_VOLUME_MAX;
                if (delay == 0)
                    delay = 1;

                timer.schedule(timerTask, delay, delay);
            }
        } catch (Exception o) {
            Functions.Log("stop",o.toString());
        }

        isStopping = false;

    }


    public void stopAndRelease(){

        try{
            if (mediaPlayer!=null) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }

        }catch(Exception o){
            Functions.Log("stop",o.toString());
        }

    }

    public void stopAndRelease(final int fadeDuration) {

        Functions.Log("stopAndRelease","stopped");

        if (mediaPlayer == null) return;

        if (isStopping) return;

        isStopping = true;

        try {
            // Set current volume, depending on fade or not
            if (fadeDuration > 0)
                iVolume = INT_VOLUME_MAX;
            else
                iVolume = INT_VOLUME_MIN;

            updateVolume(0);

            // Start increasing volume in increments
            if (fadeDuration > 0)
            {
                final Timer timer = new Timer(true);
                TimerTask timerTask = new TimerTask()
                {
                    @Override
                    public void run()
                    {
                        updateVolume(-1);
                        if (iVolume == INT_VOLUME_MIN)
                        {
                            // Stop music
                            try{mediaPlayer.stop();}catch (Exception o){}
                            mediaPlayer.release();
                            timer.cancel();
                            timer.purge();
                            //Functions.Log("stopAndRelease","Stopped Sound End");
                        }
                    }
                };

                // calculate delay, cannot be zero, set to 1 if zero
                int delay = fadeDuration / INT_VOLUME_MAX;
                if (delay == 0)
                    delay = 1;

                timer.schedule(timerTask, delay, delay);
            }
        } catch (Exception o) {
            Functions.Log("stopAndRelease", o.toString());
        }

        isStopping = false;

    }

}