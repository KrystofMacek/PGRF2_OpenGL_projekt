import com.jogamp.opengl.GL2;



// reří základní transformace
public class TransformHandler {

    private GL2 gl;

    public TransformHandler(GL2 gl) {
        this.gl = gl;
    }

    public void move(float movementSpeedX, float movementSpeedY) {
        gl.glTranslatef(movementSpeedX,movementSpeedY,0);
    }

    public void rotateLauncher(float alpha){

        gl.glTranslatef(2,5.3f,1.9f);
        gl.glRotated(-alpha, 1, 0, 0);
        gl.glTranslatef(-2,-5.3f,-1.9f);

    }

    public void throwRock(float x, float y, float z) {

        gl.glTranslatef(x,y,z);

    }
    public void turn (float alpha) {
        gl.glTranslatef(3,4,0);
        gl.glRotatef(alpha,0,0,1);
        gl.glTranslatef(-3,-4,0);
    }

    public void rotateFlyingRock(float alpha) {

        gl.glTranslatef(0, 0, 3);
        gl.glRotatef(-alpha, 1, 0, 0);
        gl.glTranslatef(0, 0, -3);

    }

}
