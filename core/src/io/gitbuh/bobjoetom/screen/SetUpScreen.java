package io.gitbuh.bobjoetom.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

import io.gitbuh.bobjoetom.Main;

import static io.gitbuh.bobjoetom.Util.getShapeRenderer;

/**
 * Created by Brenden on 8/1/2016.
 */
public class SetUpScreen extends Screen {
    Skin skin;
    Stage stage;

    float epsilon;
    int maxIterations;
    float a;
    float b;

    Label input;
    int inputWIDTH = 600;
    int inputHEIGHT = 200;
    int inputX = Main.WIDTH/2;
    int inputY = (Main.HEIGHT/2);

    TextButton renderBtn;
    int renderBtnWIDTH = 300;
    int renderBtnHEIGHT = 300;
    int renderBtnX = Main.WIDTH/2;
    int renderBtnY = (Main.HEIGHT/2)-200;

    Slider epsilonSlider;
    int epsilonSliderWIDTH = 800;
    int epsilonSliderHEIGHT = 50;
    int epsilonSliderX = Main.WIDTH/2;
    int epsilonSliderY = (Main.HEIGHT/2)+200;

    Slider maxIterationsSlider;
    int maxIterationsSliderWIDTH = 800;
    int maxIterationsSliderHEIGHT = 50;
    int maxIterationsSliderX = Main.WIDTH/2;
    int maxIterationsSliderY = (Main.HEIGHT/2)+100;

    Slider aSlider;
    int aSliderWIDTH = 800;
    int aSliderHEIGHT = 50;
    int aSliderX = Main.WIDTH/2;
    int aSliderY = (Main.HEIGHT/2)+400;

    Slider bSlider;
    int bSliderWIDTH = 800;
    int bSliderHEIGHT = 50;
    int bSliderX = Main.WIDTH/2;
    int bSliderY = (Main.HEIGHT/2)+300;

    public SetUpScreen() {
        epsilon = .001f;
        maxIterations = 1;
        a = -2f;
        b= -2f;


        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        renderBtn = new TextButton("Render",skin);//TODO ADD SLDIER FOR C VALUES AND SLDIER FOR RGB
        renderBtn.setPosition((renderBtnX)-(renderBtnWIDTH/2),(renderBtnY)-(renderBtnHEIGHT/2));//TODO AND WTF IS THIS DOING
        renderBtn.setSize(renderBtnWIDTH,renderBtnHEIGHT);
        renderBtn.addListener(new DragListener(){
            public void touchUp(InputEvent e, float x, float y, int point, int button){
                System.out.println("Activated");
                Main.setCurrentScreen(new DisplayScreen(epsilon,maxIterations,a,b));
                dispose();
                getShapeRenderer().setProjectionMatrix(Main.cam.combined);//WORK AROUND
            }
        });
        stage.addActor(renderBtn);
        //TODO SWITCH TO DRAG LISTERNERS
        epsilonSlider = new Slider(.0001f,.1f,.001f,false,skin);
        epsilonSlider.setPosition((epsilonSliderX)-(epsilonSliderWIDTH/2),(epsilonSliderY)-(epsilonSliderHEIGHT/2));
        epsilonSlider.setSize(epsilonSliderWIDTH,epsilonSliderHEIGHT);
        epsilonSlider.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                epsilon = epsilonSlider.getValue();
                updateInput();
            }

        });
        stage.addActor(epsilonSlider);

        maxIterationsSlider = new Slider(1,100,1,false,skin);
        maxIterationsSlider.setPosition((maxIterationsSliderX)-(maxIterationsSliderWIDTH/2),(maxIterationsSliderY)-(maxIterationsSliderHEIGHT/2));
        maxIterationsSlider.setSize(maxIterationsSliderWIDTH,maxIterationsSliderHEIGHT);
        maxIterationsSlider.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                maxIterations = Math.round(maxIterationsSlider.getValue());
                updateInput();
            }
        });
        stage.addActor(maxIterationsSlider);

        aSlider = new Slider(-2f,2f,.05f,false,skin);
        aSlider.setPosition((aSliderX)-(aSliderWIDTH/2),(aSliderY)-(aSliderHEIGHT/2));
        aSlider.setSize(aSliderWIDTH,aSliderHEIGHT);
        aSlider.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                a = aSlider.getValue();
                updateInput();
            }
        });
        stage.addActor(aSlider);

        bSlider = new Slider(-2f,2f,.05f,false,skin);
        bSlider.setPosition((bSliderX)-(bSliderWIDTH/2),(bSliderY)-(bSliderHEIGHT/2));
        bSlider.setSize(bSliderWIDTH,bSliderHEIGHT);
        bSlider.addListener(new ChangeListener(){
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                b = bSlider.getValue();
                updateInput();
            }
        });
        stage.addActor(bSlider);

        input = new Label("Epislon = "+epsilon+": MaxIterations = "+maxIterations+":A = "+a+":B = "+b,skin);
        input.setPosition((inputX)-(inputWIDTH/2),(inputY)-(inputHEIGHT/2));
        input.setSize(inputWIDTH,inputHEIGHT);
        stage.addActor(input);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(30.0f/255f, 144.0f/255f, 255.0f/255f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public void updateInput(){
        input.setText("Epislon = "+epsilon+": MaxIterations = "+maxIterations+":A = "+a+":B = "+b);
    }
}
