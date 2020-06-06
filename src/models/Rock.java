package models;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.texture.Texture;

public class Rock {

    public Rock(GLU glu, GL2 gl, Texture texture) {


        gl.glColor3f(.7f,.7f,.7f);
        gl.glPushMatrix();
        gl.glTranslatef(2.5f,0,3);

        texture.enable(gl);
        texture.bind(gl);

        GLUquadric quad = glu.gluNewQuadric();
        glu.gluQuadricTexture(quad,true);
        glu.gluSphere(quad,1,6,6);
        gl.glPopMatrix();

        texture.disable(gl);
    }

}
