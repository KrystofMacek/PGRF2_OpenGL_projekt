package models;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.texture.Texture;

public class SkyBox {

    public SkyBox(GLU glu, GL2 gl, Texture skyboxTex) {

        skyboxTex.enable(gl);
        skyboxTex.bind(gl);

        gl.glColor3f(.2f,.2f,1f);
        GLUquadric quad = glu.gluNewQuadric();
        glu.gluQuadricTexture(quad,true);

        glu.gluSphere(quad,200,30,30);

        skyboxTex.disable(gl);

    }

}
