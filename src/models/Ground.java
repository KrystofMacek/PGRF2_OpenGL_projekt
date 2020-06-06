package models;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.glu.GLUquadric;
import com.jogamp.opengl.util.texture.Texture;

public class Ground {
    public Ground(GLU glu, GL2 gl, Texture texture) {


        texture.enable(gl);
        texture.bind(gl);

        gl.glColor3f(0.24f,.47f,.06f);

        GLUquadric quad = glu.gluNewQuadric();
        glu.gluQuadricTexture(quad,true);

        glu.gluDisk(quad, 0,200, 24,10);

        texture.disable(gl);

    }
}
