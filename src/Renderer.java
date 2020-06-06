import com.jogamp.opengl.*;
import com.jogamp.opengl.glu.GLU;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import models.*;


import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class Renderer implements GLEventListener, MouseListener,
        MouseMotionListener, KeyListener {



    private GLU glu;
    private int width, height;
    private TransformHandler transformHandler;

    private float strength = 1f;

    private boolean isMovingForward = false;
    private boolean isMovingBackwards = false;
    private boolean isMoving;
    private float movementSpeed = 0;
    private float wheelRotation = 0;

    private boolean threw = false;


    private int state = 0;

    private float alpha = 0;

    private float resistance = 0f;
    private float gravity = 0;
    private float yMove,zMove,xMove;

    private boolean gotHit = false;
    private double explosionSize = 7;
    private float explosionVisibility = .7f;

    private float rockRotationAngle = 0;

    private float[] pieceX,pieceY,pieceZ;
    private float pieceG = 0,pieceR = 0;
    private int numOfPieces = 100;

    private float orientation = 20;

    private float[] direction = {0f,0f,0f};


    private float movementSpeedX = 0;
    private float movementSpeedY = 0f;

    private float rockBaseYSpeed = 0;
    private float rockBaseXSpeed = 0;

    private float tempMoveS[] = new float[2];

    private Texture groundTexture,baseTexture,rockTexture,towerTexture,roofTexture,steelTexture,skyboxTexture,woodWheels;

    private Random random = new Random();

    @Override
    public void init(GLAutoDrawable glDrawable) {
        GL2 gl = glDrawable.getGL().getGL2();
        glu = new GLU();
        transformHandler = new TransformHandler(gl);


        // vytvoření textur
        String [] textures = {
                "grass.jpg",
                "rock.jpg",
                "roof.jpg",
                "skybox2.jpg",
                "steel.jpg",
                "wall.jpg",
                "wood2.jpg",
                "woodWheels.jpg"
        };
        InputStream is;
        for (String s: textures) {
            is = getClass().getResourceAsStream(s);

            if(is != null) {
                try {
                    switch (s) {
                        case("grass.jpg"):
                            groundTexture = TextureIO.newTexture(is, true, "jpg");
                            break;
                        case("rock.jpg"):
                            rockTexture = TextureIO.newTexture(is, true, "jpg");
                            break;
                        case("roof.jpg"):
                            roofTexture = TextureIO.newTexture(is, true, "jpg");
                            break;
                        case("skybox2.jpg"):
                            skyboxTexture = TextureIO.newTexture(is, true, "jpg");
                            break;
                        case("steel.jpg"):
                            steelTexture = TextureIO.newTexture(is, true, "jpg");
                            break;
                        case("wall.jpg"):
                            towerTexture = TextureIO.newTexture(is, true, "jpg");
                            break;
                        case("wood2.jpg"):
                            baseTexture = TextureIO.newTexture(is, true, "jpg");
                            break;
                        case("woodWheels.jpg"):
                            woodWheels = TextureIO.newTexture(is, true, "jpg");
                            break;
                    }
                } catch (IOException e) {
                    System.err.println("Chyba cteni souboru s texturou");
                }
            } else {
                System.out.println("fail to load");
            }
        }



        //inicializace exploze

        pieceX = new float[numOfPieces];
        pieceY = new float[numOfPieces];
        pieceZ = new float[numOfPieces];

        boolean isPositive;

        for (int i = 0; i < numOfPieces; i++) {

            isPositive = random.nextBoolean();
            if(isPositive){
                pieceX[i] = random.nextFloat()*10;
            }else {
                pieceX[i] = -random.nextFloat()*10;
            }

            isPositive = random.nextBoolean();
            if(isPositive){
                pieceY[i] = random.nextFloat()*10;
            }else {
                pieceY[i] = -random.nextFloat()*10;
            }

            isPositive = random.nextBoolean();
            if(isPositive){
                pieceZ[i] = random.nextFloat()*10;
            }else {
                pieceZ[i] = -random.nextFloat()*10;
            }
        }

        calculateDirection(orientation);


    }

    @Override
    public void display(GLAutoDrawable glDrawable) {

        GL2 gl = glDrawable.getGL().getGL2();


        gl.glClearColor(0f, 0f, 0f, 1f);
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();


        gl.glEnable(GL2.GL_DEPTH_TEST);

        gl.glEnable(GL2.GL_TEXTURE_2D);

        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_T, GL2.GL_REPEAT);
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_S, GL2.GL_REPEAT);
        gl.glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_REPLACE);

        //nastavení perspektivy
        glu.gluPerspective(45, width / (float) height, 0.1f, 400.0f);

        //nastavení kamery
        glu.gluLookAt(0 + movementSpeedX,-25 + movementSpeedY,15, 2f + movementSpeedX,5.5+movementSpeedY,5, 0, 0, 1);


        //stále objekty
        new Ground(glu,gl,groundTexture);
        new SkyBox(glu,gl,skyboxTexture);



        // ovládání katapultu
        if(isMovingForward){
            movementSpeedX = movementSpeedX + direction[0] / 10;
            movementSpeedY = movementSpeedY + direction[1] / 10;
        }
        if(isMovingBackwards){
            movementSpeedX = movementSpeedX - direction[0] / 10;
            movementSpeedY = movementSpeedY - direction[1] / 10;
        }

        isMoving = false;
        if(isMovingForward) {
            movementSpeed = movementSpeed + .1f;
            wheelRotation = (wheelRotation + 2) % 360;
            isMoving = true;
        }
        if(isMovingBackwards) {
            movementSpeed = movementSpeed - .1f;
            wheelRotation = (wheelRotation - 2) % 360;
            isMoving = true;
        }



        // výchozí stav
        if(state == 0) {

            gl.glMatrixMode(GL2.GL_MODELVIEW);
            gl.glPushMatrix();
            generateBase(gl);
            new Rock(glu,gl,rockTexture);
            gl.glPopMatrix();



        // stav animace hodu
        } else if (state == 1) {


            if(alpha < 83){
                alpha = alpha + 7.5f;
            } else {
                state = 2;
                tempMoveS[0] = movementSpeedX;
                tempMoveS[1] = movementSpeedY;
            }

            gl.glMatrixMode(GL2.GL_MODELVIEW);
            gl.glPushMatrix();
            generateBase(gl);
            new Rock(glu,gl,rockTexture);
            gl.glPopMatrix();

        //stav: kámen letí, ovladatelnost katapultu
        } else if (state == 2) {

            if(zMove >= -7){


                // kontrola trefy
                if(xMove + tempMoveS[0] > -5 && xMove + tempMoveS[0] < 5){
                    if(yMove + tempMoveS[1] < 65 && yMove + tempMoveS[1] > 55)  {
                        gotHit = true;
                        rockBaseXSpeed = -rockBaseXSpeed / 3;
                        rockBaseYSpeed = -rockBaseYSpeed / 3;
                    }
                }

                yMove = yMove + rockBaseYSpeed + resistance;
                xMove = xMove + rockBaseXSpeed;
                zMove = zMove + .2f + gravity;

                resistance = resistance - .0025f;
                gravity = gravity - .008f;

                rockRotationAngle += 7;
            } else {
                state = 3;
            }


            gl.glMatrixMode(GL2.GL_MODELVIEW);
            gl.glPushMatrix();

            transformHandler.move(tempMoveS[0],tempMoveS[1]);
            transformHandler.throwRock(xMove,yMove,zMove);
            transformHandler.rotateLauncher(alpha);
            transformHandler.rotateFlyingRock(rockRotationAngle);
            new Rock(glu,gl,rockTexture);

            gl.glPopMatrix();

            gl.glMatrixMode(GL2.GL_MODELVIEW);
            gl.glPushMatrix();
            generateBase(gl);
            gl.glPopMatrix();

        //stav po dopadnutí kamene
        } else if(state == 3) {

            gl.glMatrixMode(GL2.GL_MODELVIEW);
            gl.glPushMatrix();
            transformHandler.move(tempMoveS[0],tempMoveS[1]);
            transformHandler.throwRock(xMove,yMove,zMove);
            transformHandler.rotateLauncher(alpha);
            new Rock(glu,gl,rockTexture);
            gl.glPopMatrix();

            gl.glMatrixMode(GL2.GL_MODELVIEW);
            gl.glPushMatrix();
            generateBase(gl);
            gl.glPopMatrix();

        }


        // pokud netrefil / trefil
        if(!gotHit){
            new Tower(glu,gl,towerTexture,roofTexture);
        } else {

            pieceR = pieceR - .004f;
            pieceG = pieceG - .014f;

            for(int i = 0; i < numOfPieces; i++) {

                new Piece(glu,gl, towerTexture,pieceX[i],pieceY[i],pieceZ[i]);

                if(pieceZ[i] > -3) {
                    pieceZ[i] += pieceG;
                    pieceX[i] += pieceR;
                    pieceY[i] += pieceR;

                    if(pieceZ[i] > -3) {
                        pieceZ[i] += .1f ;
                    } else {
                        pieceZ[i] -= .1f ;
                    }

                    if(pieceX[i] > 0) {
                        pieceX[i] += .1f ;
                    } else {
                        pieceX[i] -= .1f ;
                    }

                    if(pieceY[i] > 0) {
                        pieceY[i] += .1f ;
                    } else {
                        pieceY[i] -= .1f ;
                    }

                }

            }


            new Explosion(glu,gl,explosionSize,explosionVisibility);
            explosionSize+=.6;
            explosionVisibility -= .03f;

        }

    }

    // metoda pro vykreslení katapultu
    private void generateBase(GL2 gl){
        transformHandler.move(movementSpeedX, movementSpeedY);
        transformHandler.turn(orientation);
        new Base(glu,gl,baseTexture,steelTexture,woodWheels,isMoving, wheelRotation);
        transformHandler.rotateLauncher(alpha);
        new Launcher(glu,gl,baseTexture);
    }


    // výpočet smeru hodu
    private void calculateDirection(float alpha) {

        alpha = (alpha - 115) * (float)(Math.PI / 180);

        direction[0] = (float) - Math.sin(alpha + 90);
        direction[1] = (float) Math.cos(alpha + 90);

        if(!threw) {
            rockBaseXSpeed = direction[0] * strength;
            rockBaseYSpeed = direction[1] * strength;


        }
    }

    //nastavení síly hodu
    void setInitialStrength(int k){
        alpha = 87 - (0.87f * k);
        strength = k / 100f;
        calculateDirection(orientation);
    }


    @Override
    public void reshape(GLAutoDrawable glDrawable, int x, int y, int width,
                        int height) {
        this.width = width;
        this.height = height;
        glDrawable.getGL().getGL2().glViewport(0, 0, width , height);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {


    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                isMovingForward = true;
                break;
            case KeyEvent.VK_S:
                isMovingBackwards = true;
                break;
            case KeyEvent.VK_SPACE:
                state = 1;
                threw = true;
                strengthSlider.setEnabled(false);
                break;
            case KeyEvent.VK_R:
                reset();
                break;
        }

        if(e.getKeyCode() == KeyEvent.VK_A) {
            orientation += 3;
            calculateDirection(orientation);
        }
        if(e.getKeyCode() == KeyEvent.VK_D) {
            orientation -= 3;
            calculateDirection(orientation);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                isMovingForward = false;
                break;
            case KeyEvent.VK_S:
                isMovingBackwards = false;
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void dispose(GLAutoDrawable glDrawable) {
    }

    private void reset(){
        state = 0;
        alpha = 87 - (0.87f * strengthSlider.getValue());
        zMove = 0;
        yMove = 0;
        xMove = 0;
        resistance = 0;
        gravity = 0;
        gotHit = false;
        threw = false;

        strengthSlider.setEnabled(true);

        explosionSize = 1;
        explosionVisibility = 1;
        pieceG = 0;
        pieceR = 0;

        calculateDirection(orientation);

        boolean isPositive;
        for (int i = 0; i < numOfPieces; i++) {

            isPositive = random.nextBoolean();
            if(isPositive){
                pieceX[i] = random.nextFloat()*10;
            }else {
                pieceX[i] = -random.nextFloat()*10;
            }

            isPositive = random.nextBoolean();
            if(isPositive){
                pieceY[i] = random.nextFloat()*10;
            }else {
                pieceY[i] = -random.nextFloat()*10;
            }

            isPositive = random.nextBoolean();
            if(isPositive){
                pieceZ[i] = random.nextFloat()*10;
            }else {
                pieceZ[i] = -random.nextFloat()*10;
            }

        }


    }
    private JSlider strengthSlider;
    public void setStrengthSlider(JSlider s){
        strengthSlider = s;
    }

}
