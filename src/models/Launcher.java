package models;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.texture.Texture;

public class Launcher {

    //vytvoření launcheru
    public Launcher(GLU glu, GL2 gl, Texture baseTexture) {

        baseTexture.enable(gl);
        baseTexture.bind(gl);

        GLUquadric quad = glu.gluNewQuadric();
        glu.gluQuadricTexture(quad,true);

        gl.glColor3f(1,0,0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glPushMatrix();
        gl.glTranslatef(2,5.3f,1.9f);
        gl.glRotatef(90, 0, 1, 0);
        glu.gluCylinder(quad,.5,.5, 1,24,24);
        gl.glColor3f(0,1,1);
        glu.gluDisk(quad, 0,.5,24,10);
        gl.glTranslatef(0,0,1);
        glu.gluDisk(quad, 0,.5,24,10);
        gl.glPopMatrix();

        gl.glBegin(GL2.GL_QUAD_STRIP);
        gl.glColor3f(1,1,0);

        gl.glTexCoord2f(0,0);
        gl.glVertex3f(2,5.3f,2);
        gl.glTexCoord2f(0,1);
        gl.glVertex3f(2,-0.5f,2);
        gl.glTexCoord2f(1,1);

        gl.glVertex3f(3,5.3f,2);
        gl.glTexCoord2f(1,0);
        gl.glVertex3f(3,-0.5f,2);
        gl.glTexCoord2f(0,0);

        gl.glTexCoord2f(0,1);
        gl.glVertex3f(3,5.3f,1.7f);
        gl.glTexCoord2f(1,1);
        gl.glVertex3f(3,-0.5f,1.7f);
        gl.glTexCoord2f(1,0);

        gl.glVertex3f(2,5.3f,1.7f);
        gl.glVertex3f(2,-0.5f,1.7f);
        gl.glTexCoord2f(0,0);


        gl.glTexCoord2f(0,1);
        gl.glVertex3f(2,5.3f,2);
        gl.glTexCoord2f(1,1);
        gl.glVertex3f(2,-0.5f,2);
        gl.glEnd();

        gl.glBegin(GL2.GL_QUAD_STRIP);
        gl.glColor3f(.5f,.5f,0);

        gl.glTexCoord2f(0,0);
        gl.glVertex3f(2,-0.5f,2);
        gl.glTexCoord2f(0,1);
        gl.glVertex3f(3,-0.5f,2);
        gl.glTexCoord2f(1,1);
        gl.glVertex3f(3,-0.5f,2);
        gl.glTexCoord2f(1,0);
        gl.glVertex3f(2,-0.5f,2);
        gl.glEnd();

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glPushMatrix();
        gl.glTranslatef(2.5f,0,2);
        glu.gluCylinder(quad,.5,1,.5,24,24);
        gl.glPopMatrix();

        baseTexture.disable(gl);
    }
}
