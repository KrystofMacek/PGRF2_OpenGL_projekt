package models;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.texture.Texture;

public class Tower {

    //Vytvoření věže
    public Tower(GLU glu, GL2 gl, Texture wallTex, Texture roofTex) {

        wallTex.enable(gl);
        wallTex.bind(gl);

        GLUquadric quad = glu.gluNewQuadric();
        glu.gluQuadricTexture(quad,true);


        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glColor3f(.5f,.5f,.5f);
        gl.glPushMatrix();
        gl.glTranslatef(2.5f,60,0);
        glu.gluCylinder(quad, 5,5,10,25,25);
        gl.glPopMatrix();

        wallTex.disable(gl);


        roofTex.enable(gl);
        roofTex.bind(gl);
        quad = glu.gluNewQuadric();
        glu.gluQuadricTexture(quad,true);

        gl.glColor3f(.8f,0,0);
        gl.glPushMatrix();
        gl.glTranslatef(2.5f,60,10);
        glu.gluCylinder(quad, 5,0,5,25,25);
        gl.glPopMatrix();

        roofTex.disable(gl);
    }

}
