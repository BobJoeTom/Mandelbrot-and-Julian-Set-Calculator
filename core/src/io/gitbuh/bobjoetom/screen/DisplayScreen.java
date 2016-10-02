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
    private int amtPixels;
    private int pixelsCalced=0;

    float rLast = 80f / 255f;
    float gLast = 196f / 255f;
    float bLast = 258f / 255f;

    //ArrayList<Point> points = new ArrayList<Point>();
    float[][] points;

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
        this.amtPixels=(int)(16/(epsilon*epsilon));
        System.out.println(amtPixels);
        points = new float[amtPixels][3];
    }

    public void render(float delta) {
        if(yPos<2) {
            if(pixelsCalced<amtPixels){//TODO get rid of this little fucker
            for(int i = 0; i < (int)(amtPixels/5); i++) {
                xPos += epsilon;
                if (xPos >= 2) {
                    xPos = -2;
                    yPos += epsilon;
                }
                int iterations = 0;
                x = xPos;
                y = yPos;
                //if (a == 0 && b == 0) {//TODO THIS IS FUCKING UP
                    a = xPos;
                    b = yPos;
               // }
                while (iterations < maxIterations && (x * x) + (b * b) < 4) {
                    float tempX;
                    tempX = x * x - y * y + a;
                    y = 2 * x * y + b;
                    x = tempX;
                    iterations++;
                }
                if (pixelsCalced < amtPixels) {//TODO get rid of this little fucker too
                    if (iterations == maxIterations) {
                        absComplex = (x * x) + (b * b);
                        points[pixelsCalced][0] = ((xPos + 2) * Main.WIDTH / 4f);
                        points[pixelsCalced][1] = ((yPos + 2) * Main.HEIGHT / 4);
                        points[pixelsCalced][2] = absComplex;
                    } else {
                        points[pixelsCalced][0] = ((xPos + 2) * Main.WIDTH / 4f);
                        points[pixelsCalced][1] = ((yPos + 2) * Main.HEIGHT / 4);
                        points[pixelsCalced][2] = iterations;
                    }
                    // System.out.println(pixelsCalced + "/"+amtPixels + "=" + ((float)pixelsCalced/(float)amtPixels)*100 + "% Done");
                }
                pixelsCalced++;
                System.out.println( "Percent Overflow: " + (100-((float)pixelsCalced/(float)amtPixels)*100) + "%");
            }
            }
        }
        renderPoints();
    }

    public void renderPoints(){
        drawPoints(points,rLast,gLast,bLast);
        System.out.println("Rendered");

    }
}
