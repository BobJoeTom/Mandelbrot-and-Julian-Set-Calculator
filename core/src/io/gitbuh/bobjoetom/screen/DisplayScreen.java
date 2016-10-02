package io.gitbuh.bobjoetom.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;

import java.awt.Color;
import java.util.ArrayList;

import io.gitbuh.bobjoetom.Main;
import io.gitbuh.bobjoetom.Point;

import static io.gitbuh.bobjoetom.Util.*;

/**
 * Created by Brenden on 8/1/2016.
 */
public class DisplayScreen extends Screen {
    private float epsilon;
    private int maxIterations;



    ArrayList<Point> points = new ArrayList<Point>();

    float a;//A and B are complex number C(a+bi)
    float b;
    float x;//X and Y are complex number Z(x+yi)
    float y;
    float xPos=-2;
    float yPos=-2;
    float absComplex;

    public DisplayScreen(float epsilon, int maxIterations, float a, float b) {
        this.epsilon = epsilon;
        this.maxIterations = maxIterations;
        this.a = a;
        this.b = b;
    }

    public void render(float delta) {
        if(yPos<2) {
            for(int i = 0; i < 100000; i++){
                xPos+=epsilon;
                if(xPos>=2){
                    xPos=-2;
                    yPos+=epsilon;
                }
                int iterations = 0;
                x = xPos;
                y = yPos;
                if(a==0&&b==0){//TODO THIS IS FUCKING UP
                    a = xPos;
                    b = yPos;
                }
                while (iterations < maxIterations && (x * x) + (b * b) < 4) {
                    float tempX;
                    tempX = x * x - y * y + a;
                    y = 2 * x * y + b;
                    x = tempX;
                    iterations++;
                }
                if(iterations==maxIterations){
                    absComplex = (x * x) + (b * b);
                    points.add(new Point(((xPos + 2) * Main.WIDTH / 4f), ((yPos + 2) * Main.HEIGHT / 4), absComplex));
                }else{
                    points.add(new Point(((xPos + 2) * Main.WIDTH / 4f), ((yPos + 2) * Main.HEIGHT / 4), iterations));
                }
            }
        }
        float rLast = 80f / 255f;
        float gLast = 196f / 255f;
        float bLast = 258f / 255f;
        drawPoints(points,rLast,gLast,bLast);
    }
}
