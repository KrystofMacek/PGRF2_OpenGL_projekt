package models;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.texture.Texture;

public class Base {


    public Base(GLU glu, GL2 gl, Texture baseTex, Texture wheelTex, Texture diskTexture, boolean isMoving, float wheelRoation) {

        GLUquadric quad = glu.gluNewQuadric();

        baseTex.enable(gl);
        baseTex.bind(gl);

        gl.glColor3f(1,1,1);


        for(int i = 0; i < 5; i+=4) {
            // Base

            gl.glColor3f(1,1,1);
            gl.glBegin(GL2.GL_QUAD_STRIP);

            gl.glTexCoord2f(0,0);
            gl.glVertex3f(i, 0, 0.5f);
            gl.glTexCoord2f(0,1);
            gl.glVertex3f(1 + i, 0, 0.5f);
            gl.glTexCoord2f(1,1);

            gl.glVertex3f(i, 0, 1.5f);
            gl.glTexCoord2f(1,0);
            gl.glVertex3f(1 + i, 0, 1.5f);
            gl.glTexCoord2f(0,0);

            gl.glVertex3f(i, 5, 1.5f);
            gl.glTexCoord2f(0,1);
            gl.glVertex3f(1 + i, 5f, 1.5f);
            gl.glTexCoord2f(1,1);

            gl.glVertex3f(i, 5, 5.5f);
            gl.glTexCoord2f(1,0);
            gl.glVertex3f(1 + i, 5, 5.5f);
            gl.glTexCoord2f(0,0);

            gl.glVertex3f(i, 6, 5.5f);
            gl.glTexCoord2f(0,1);
            gl.glVertex3f(1 + i, 6, 5.5f);
            gl.glTexCoord2f(1,1);

            gl.glVertex3f(i, 6, 1.5f);
            gl.glTexCoord2f(1,0);
            gl.glVertex3f(1 + i, 6, 1.5f);
            gl.glTexCoord2f(0,0);

            gl.glVertex3f(i, 10, 1.5f);
            gl.glTexCoord2f(0,1);
            gl.glVertex3f(1 + i, 10, 1.5f);
            gl.glTexCoord2f(1,1);

            gl.glVertex3f(i, 10, .5f);
            gl.glTexCoord2f(1,0);
            gl.glVertex3f(1 + i, 10, .5f);
            gl.glTexCoord2f(0,0);

            gl.glVertex3f(i, 0, .5f);
            gl.glTexCoord2f(0,1);
            gl.glVertex3f(1 + i, 0, .5f);
            gl.glEnd();


            // Sides
            gl.glBegin(GL2.GL_QUADS);
            gl.glTexCoord2f(0,0);
            gl.glVertex3f(i, 0, 0.5f);
            gl.glTexCoord2f(0,1);
            gl.glVertex3f(i, 0, 1.5f);
            gl.glTexCoord2f(1,1);
            gl.glVertex3f(i, 10, 1.5f);
            gl.glTexCoord2f(1,0);
            gl.glVertex3f(i, 10, 0.5f);
            gl.glEnd();

            gl.glBegin(GL2.GL_QUADS);
            gl.glTexCoord2f(0,0);
            gl.glVertex3f(1 + i, 0, 0.5f);
            gl.glTexCoord2f(0,1);
            gl.glVertex3f(1 + i, 0, 1.5f);
            gl.glTexCoord2f(1,1);
            gl.glVertex3f(1 + i, 10, 1.5f);
            gl.glTexCoord2f(1,0);
            gl.glVertex3f(1 + i, 10, 0.5f);
            gl.glEnd();

            gl.glBegin(GL2.GL_QUADS);
            gl.glTexCoord2f(0,0);
            gl.glVertex3f(i, 5, 1.5f);
            gl.glTexCoord2f(0,1);
            gl.glVertex3f(i, 5, 5.5f);
            gl.glTexCoord2f(1,1);
            gl.glVertex3f(i, 6, 5.5f);
            gl.glTexCoord2f(1,0);
            gl.glVertex3f(i, 6, 1.5f);
            gl.glEnd();

            gl.glBegin(GL2.GL_QUADS);
            gl.glTexCoord2f(0,0);
            gl.glVertex3f(1 + i, 5, 1.5f);
            gl.glTexCoord2f(0,1);
            gl.glVertex3f(1 + i, 5, 5.5f);
            gl.glTexCoord2f(1,1);
            gl.glVertex3f(1 + i, 6, 5.5f);
            gl.glTexCoord2f(1,0);
            gl.glVertex3f(1 + i, 6, 1.5f);
            gl.glEnd();

            //front
            gl.glBegin(GL2.GL_QUAD_STRIP);
            gl.glTexCoord2f(0,0);
            gl.glVertex3f(i + 0.2f,6f,5);
            gl.glTexCoord2f(0,1);
            gl.glVertex3f(i + 0.2f,9.5f,1.5f);
            gl.glTexCoord2f(1,1);

            gl.glVertex3f(i + .8f,6f,5);
            gl.glTexCoord2f(1,0);
            gl.glVertex3f(i + .8f,9.5f,1.5f);
            gl.glTexCoord2f(0,0);
            gl.glVertex3f(i + .8f,6f,4);
            gl.glTexCoord2f(0,1);
            gl.glVertex3f(i + .8f,8.5f,1.5f);
            gl.glTexCoord2f(1,1);

            gl.glVertex3f(i + 0.2f,6f,4);
            gl.glTexCoord2f(1,0);
            gl.glVertex3f(i + 0.2f,8.5f,1.5f);
            gl.glTexCoord2f(0,0);
            gl.glVertex3f(i + 0.2f,6f,5);
            gl.glTexCoord2f(0,1);
            gl.glVertex3f(i + 0.2f,9.5f,1.5f);
            gl.glTexCoord2f(1,1);

            gl.glEnd();

        }

        gl.glBegin(GL2.GL_QUAD_STRIP);
        gl.glTexCoord2f(0,0);
        gl.glVertex3f(1,7f,1);
        gl.glTexCoord2f(0,1);
        gl.glVertex3f(4,7f,1);
        gl.glTexCoord2f(1,1);
        gl.glVertex3f(1,7f,1.5f);
        gl.glTexCoord2f(1,0);
        gl.glVertex3f(4,7f,1.5f);
        gl.glTexCoord2f(0,0);
        gl.glVertex3f(1,8f,1.5f);
        gl.glTexCoord2f(0,1);
        gl.glVertex3f(4,8f,1.5f);
        gl.glTexCoord2f(1,1);
        gl.glVertex3f(1,8f,1);
        gl.glTexCoord2f(1,0);
        gl.glVertex3f(4,8f,1);
        gl.glEnd();

        gl.glBegin(GL2.GL_QUAD_STRIP);
        gl.glTexCoord2f(0,0);
        gl.glVertex3f(1,.5f,1);
        gl.glTexCoord2f(0,1);
        gl.glVertex3f(4,.5f,1);
        gl.glTexCoord2f(1,1);
        gl.glVertex3f(1,.5f,1.5f);
        gl.glTexCoord2f(1,0);
        gl.glVertex3f(4,.5f,1.5f);
        gl.glTexCoord2f(0,0);
        gl.glVertex3f(1,1.5f,1.5f);
        gl.glTexCoord2f(0,1);
        gl.glVertex3f(4,1.5f,1.5f);
        gl.glTexCoord2f(1,1);
        gl.glVertex3f(1,1.5f,1f);
        gl.glTexCoord2f(1,0);
        gl.glVertex3f(4,1.5f,1f);
        gl.glEnd();



        glu.gluQuadricTexture(quad,true);

        // Mid Bars
        gl.glColor3f(1,1,1);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glPushMatrix();
        gl.glTranslatef(0f,5.5f,5f);
        gl.glRotatef(90, 0, 1, 0);
        glu.gluCylinder(quad,.3,.3, 5,24,24);
        gl.glPopMatrix();

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glPushMatrix();
        gl.glTranslatef(0f,5.3f,1.9f);
        gl.glRotatef(90, 0, 1, 0);
        glu.gluCylinder(quad,.3,.3, 5,24,24);
        gl.glPopMatrix();


        baseTex.disable(gl);




        //Wheels
        float x = -1f;
        float y = 1f;

        for(int i = 0; i < 4; i++) {
            if(i == 1) x = 5;
            if(i == 2){
                x = -1;
                y = 7.5f;
            }
            if(i == 3) x = 5;

            wheelTex.enable(gl);
            wheelTex.bind(gl);
            quad = glu.gluNewQuadric();
            glu.gluQuadricTexture(quad,true);

            gl.glMatrixMode(GL2.GL_MODELVIEW);
            gl.glPushMatrix();
            gl.glTranslatef(x,y,1);
            gl.glRotatef(-wheelRoation, 1, 0, 0);
            gl.glRotatef(90, 0, 1, 0);
            glu.gluCylinder(quad,1,1, 1,24,24);

            wheelTex.disable(gl);

            //disks
            diskTexture.enable(gl);

            diskTexture.bind(gl);
            quad = glu.gluNewQuadric();
            glu.gluQuadricTexture(quad,true);
            glu.gluDisk(quad, 0,1,24,10);
            gl.glTranslatef(0,0,1);
            glu.gluDisk(quad, 0,1,24,10);

            diskTexture.disable(gl);

            gl.glPopMatrix();
        }




    }


}
