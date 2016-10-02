package io.gitbuh.bobjoetom;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import static io.gitbuh.bobjoetom.Util.*;

import java.util.ArrayList;

import io.gitbuh.bobjoetom.screen.DisplayScreen;
import io.gitbuh.bobjoetom.screen.Screen;
import io.gitbuh.bobjoetom.screen.SetUpScreen;

public class Main extends ApplicationAdapter {
	/*********************************************
	 * Brenden Brusberg
	 * Attempt at mandelbrot 7/24/2016
	 */


	public static OrthographicCamera cam;//TODO WORK AROUND:STATIC

	public static int WIDTH = 1080;//TODO CHANGE TO PHONE MAYBE SWITCH AT ASSIGMENT IF PBHONE
	public static int HEIGHT = 1080;//
	public final static String TITLE = "Mandelbrot";



	public static Screen currentScreen;
	public static SetUpScreen setUpScreen;
	DisplayScreen displayScreen;
	// https://www.khanacademy.org/computer-programming/spin-off-of-mandelbrot-set/6065922455633920

	boolean isAndroid = false;
	public void create() {
		//float w = Gdx.graphics.getWidth();
		//float h = Gdx.graphics.getHeight();
		cam = new OrthographicCamera(1080, 1080);//TODO fix to stay to the screen
		cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
		Gdx.gl.glEnable(GL20.GL_BLEND);

		if(Gdx.app.getType()== Application.ApplicationType.Android){
			isAndroid = true;
			cam.viewportHeight = 1920;
		}

		setUpScreen = new SetUpScreen();
		setCurrentScreen(setUpScreen);

	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1f, 1f, 1f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		cam.update();
		currentScreen.render(Gdx.graphics.getDeltaTime());
	}

	public static void setCurrentScreen(Screen screen){//TODO GET RID OF STATIC
		currentScreen = screen;
	}
}
