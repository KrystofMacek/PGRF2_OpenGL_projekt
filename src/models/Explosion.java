package models;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.gl2.GLUT;

public class Explosion {

    public Explosion (GLU glu, GL2 gl, double size, float visibility) {

        GLUquadric quad = glu.gluNewQuadric();

        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
        gl.glEnable(GL.GL_BLEND );

        gl.glColor4f(1,0,0,visibility);

        gl.glPushMatrix();
        gl.glTranslatef(2.5f,60,5);
        glu.gluSphere(quad, size, 50,50);

        gl.glPopMatrix();



    }

}
