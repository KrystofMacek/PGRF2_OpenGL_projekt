package models;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;


public class Piece {

    //jednotlivé části při výbuchu
    public Piece(GLU glu, GL2 gl, Texture texture , float x, float y, float z) {

        gl.glPushMatrix();
        gl.glColor3f(.5f,.5f,.5f);

        texture.enable(gl);
        texture.bind(gl);

        GLUquadric quad = glu.gluNewQuadric();
        glu.gluQuadricTexture(quad,true);


        gl.glTranslatef(2.5f + x,60f + y,5f + z);
        gl.glScalef(2f,1,1);
        glu.gluSphere(quad,1,4,4);

        texture.disable(gl);


        gl.glPopMatrix();


    }

}
