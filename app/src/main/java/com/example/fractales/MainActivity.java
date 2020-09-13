package com.example.fractales;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Layout;
import android.transition.Slide;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    double angle = 0;
    SeekBar seekBar;
    ConstraintLayout layout1;
    Vista vista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // SeekBar
        seekBar = (SeekBar)findViewById(R.id.barrita);
        layout1 = (ConstraintLayout) findViewById(R.id.layout1);
        vista = new Vista(this);
        seekBar.setProgress(0);
        seekBar.setMax(4000);
        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar,
                                                  int progress, boolean fromUser) {
                        layout1.removeAllViews();
                        angle = progress/10;
                        layout1.addView(vista,0);
                    }
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                });
        layout1.addView(vista,0);
    }
    class Vista extends View{

        public Vista(Context context){
            super(context);
        }
        public void onDraw(Canvas canvas){
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(3);
            paint.setColor(Color.RED);

            canvas.translate(canvas.getWidth()/2,canvas.getHeight());
            Branch(300,canvas,paint);
        }
        public void Branch(float len,Canvas canvas,Paint paint){
            canvas.drawLine(0,0,0,-len,paint);
            canvas.translate(0,-len);
            if(len>4) {
                canvas.save();
                canvas.rotate((float)angle);
                Branch((float) (len * 0.67), canvas, paint);
                canvas.restore();
                canvas.save();
                canvas.rotate((float)-angle);
                Branch((float) (len * 0.67), canvas, paint);
                canvas.restore();
            }
        }
    }
}
