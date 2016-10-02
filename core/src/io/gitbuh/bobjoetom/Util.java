package io.gitbuh.bobjoetom;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

/**
 * Created by Brenden on 7/24/2016.
 * A Set off debugging static methods to draw lines, points, and wait, not the best design but appropriate fo rthe size of the project
 */
public class Util {
    public static void waitTimer(int mili){
        int tick = 0;
        long lastTime = System.currentTimeMillis();
        double delta = 0.0;
        double ms = 1;//double ns = 1000000000.0 / 60.0;
        long timer = System.currentTimeMillis();
        while (tick < mili) {
            long now = System.currentTimeMillis();
            delta += (now - lastTime) / ms;
            lastTime = now;
            if (delta >= 1.0) {
                tick++;
                delta--;
            }
        }
    }
    private static ShapeRenderer shapeRenderer = new ShapeRenderer();
    //Simple Line Creator
    //TODO MOVE METHODS TO A UTIL CLASS

    public static void drawPoint(Vector2 point){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(point.x-2,point.y-2, 4,4);
        shapeRenderer.end();
    }

    public static void drawPoint(Point point){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        //shapeRenderer.setColor(point.getColor());
        //shapeRenderer.rect(point.getPosistion().x,point.getPosistion().y,1,1);
        shapeRenderer.end();
    }

    public static void drawPointRColor(Point point, float r, float g, float b){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(
                     (r)*(1f/point.getTint()),
                     (g)*(1f/point.getTint()),
                     (b)*(1f/point.getTint()),
                1.0f);
        shapeRenderer.rect(point.getX(),point.getY(),1,1);
        shapeRenderer.end();
    }

    public static void drawPoints(ArrayList<Point> points,float r, float g, float b){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for(int i = 0; i < points.size(); i++){
            shapeRenderer.setColor(
                    (r)*(1f/ points.get(i).getTint()),
                    (g)*(1f/ points.get(i).getTint()),
                    (b)*(1f/ points.get(i).getTint()),
                    1.0f);
            shapeRenderer.point(points.get(i).getX(),points.get(i).getY(),0);
        }
        shapeRenderer.end();
    }
    public static void drawPoints(float[][] points,float r, float g, float b){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for(int i = 0; i < points.length; i++){
            shapeRenderer.setColor(
                    (r)*(1f/ points[i][2]),
                    (g)*(1f/ points[i][2]),
                    (b)*(1f/ points[i][2]),
                    1.0f);
            shapeRenderer.point(points[i][0],points[i][1],0);
        }
        shapeRenderer.end();
    }

    public static void drawPoint(Vector2 point, Color color){//Colors constructor (R,G,B,A)
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(color);
        shapeRenderer.rect(point.x,point.y, 1,1);
        shapeRenderer.end();
    }

    public static void drawLine(Vector2 start, Vector2 end, Color color)
    {
        Gdx.gl.glLineWidth(1);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(color);
        shapeRenderer.line(start, end);
        shapeRenderer.end();
    }

    public static void drawLine(Vector2 start, Vector2 end, Color color, int lineWidth)
    {

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(color);
        shapeRenderer.line(start, end);
        shapeRenderer.end();
        Gdx.gl.glLineWidth(lineWidth);
    }

    public static void drawLine(Vector2 start, Vector2 end, Matrix4 projectionMatrix)
    {
        Gdx.gl.glLineWidth(1);
        shapeRenderer.setProjectionMatrix(projectionMatrix);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.line(start, end);
        shapeRenderer.end();

    }

    public static void drawLine(Vector2 start, Vector2 end){
        Gdx.gl.glLineWidth(1);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.line(start, end);
        shapeRenderer.end();
    }

    public static ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    public static void setShapeRenderer(ShapeRenderer shapeRenderer) {
        Util.shapeRenderer = shapeRenderer;
    }
}
