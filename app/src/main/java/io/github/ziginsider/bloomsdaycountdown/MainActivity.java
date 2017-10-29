package io.github.ziginsider.bloomsdaycountdown;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;
import java.util.Random;

import me.anwarshahriar.calligrapher.Calligrapher;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    TextView txtLongString, txtDaysRemain;

    public static final int BLOOM_MONTH = 6;
    public static final int BLOOM_DAY = 16;

    BackgroundSound backgroundSound = new BackgroundSound();

    @Override
    protected void onResume() {
        super.onResume();
        backgroundSound.execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "agildiaexp.ttf", true);

        imageView = (ImageView) findViewById(R.id.imageView);
        ((AnimationDrawable)imageView.getDrawable()).start();

        txtDaysRemain = (TextView) findViewById(R.id.txtDayRemain);
        txtLongString = (TextView) findViewById(R.id.txtLongString);

        getStartTimer();
     }

    private void getStartTimer() {
        long differance = getRemainDays();

        new CountDownTimer(differance, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int days = (int)(millisUntilFinished/(1000*60*60*24));
                int hours = (int)(millisUntilFinished/(1000*60*60)%24);
                int mins = (int)(millisUntilFinished/(1000*60)%60);
                int sec = (int)(millisUntilFinished/1000%60);

                txtDaysRemain.setText(String.format("%d", days));
                txtLongString.setText(String.format("%d DAYS %02d:%02d:%02d",
                        days,
                        hours,
                        mins,
                        sec));
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    private long getRemainDays() {
        Date currentDate = new Date();
        Date endDate;

        if (currentDate.getMonth() <= (BLOOM_MONTH - 1)) {
            endDate = new Date(currentDate.getYear(), (BLOOM_MONTH - 1), BLOOM_DAY);

        } else {
            endDate = new Date((currentDate.getYear() + 1), (BLOOM_MONTH - 1), BLOOM_DAY );
        }
        return endDate.getTime() - currentDate.getTime();
    }

    class BackgroundSound extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(Void aVoid) {
            super.onCancelled(aVoid);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... params) {
            MediaPlayer mediaPlayer;
            Random random = new Random(System.currentTimeMillis());
            int rd = random.nextInt(6 - 1) + 1;
            if (rd == 1) {
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.walla_1);
            } else if (rd == 2) {
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.walla_2);
            } else if (rd == 3) {
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.walla_3);
            } else if (rd == 4) {
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.walla_4);
            } else if (rd == 5) {
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.walla_5);
            } else {
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.walla_1);
            }

            mediaPlayer.setLooping(true);
            mediaPlayer.setVolume(1.0f, 1.0f);
            mediaPlayer.start();

            return null;

        }
    }
}
