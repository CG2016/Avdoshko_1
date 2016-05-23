import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.glu.GLU;

public class Projector implements GLEventListener {

    public static final int XY_MODE = 0;
    public static final int XZ_MODE = 1;
    public static final int YZ_MODE = 2;

    private int mode;

    private GLU glu = new GLU();
    private float xrot, yrot, zrot;


    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity(); // Reset The View
        gl.glTranslatef(0f, 0f, -5.0f);

        gl.glRotatef(xrot, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(yrot, 0.0f, 1.0f, 0.0f);
        gl.glRotatef(zrot, 0.0f, 0.0f, 1.0f);

        gl.glBegin(GL2.GL_QUADS);

        // Left Leg
        gl.glVertex3f(0f, 0f, 0f);
        gl.glVertex3f(.2f, 0f, 0f);
        gl.glVertex3f(.2f, .2f, 0f);
        gl.glVertex3f(0f, .2f, 0f);

        gl.glVertex3f(.2f, 0f, 1f);
        gl.glVertex3f(.4f, 0f, 1f);
        gl.glVertex3f(.4f, .2f, 1f);
        gl.glVertex3f(.2f, .2f, 1f);

        gl.glVertex3f(0f, 0f, 0f);
        gl.glVertex3f(.2f, 0f, 0f);
        gl.glVertex3f(.4f, 0f, 1f);
        gl.glVertex3f(.2f, 0f, 1f);

        gl.glVertex3f(0f, .2f, 0f);
        gl.glVertex3f(.2f, .2f, 0f);
        gl.glVertex3f(.4f, .2f, 1f);
        gl.glVertex3f(.2f, .2f, 1f);

        gl.glVertex3f(.2f, 0f, 0f);
        gl.glVertex3f(.2f, .2f, 0f);
        gl.glVertex3f(.4f, .2f, 1f);
        gl.glVertex3f(.4f, .0f, 1f);

        gl.glVertex3f(0f, 0f, 0f);
        gl.glVertex3f(0f, .2f, 0f);
        gl.glVertex3f(.2f, .2f, 1f);
        gl.glVertex3f(.2f, .0f, 1f);

        // Right Leg
        gl.glVertex3f(.6f, 0f, 0f);
        gl.glVertex3f(.8f, 0f, 0f);
        gl.glVertex3f(.8f, .2f, 0f);
        gl.glVertex3f(.6f, .2f, 0f);

        gl.glVertex3f(.4f, 0f, 1f);
        gl.glVertex3f(.6f, 0f, 1f);
        gl.glVertex3f(.6f, .2f, 1f);
        gl.glVertex3f(.4f, .2f, 1f);

        gl.glVertex3f(.6f, 0f, 0f);
        gl.glVertex3f(.8f, 0f, 0f);
        gl.glVertex3f(.6f, 0f, 1f);
        gl.glVertex3f(.4f, 0f, 1f);

        gl.glVertex3f(.6f, .2f, 0f);
        gl.glVertex3f(.8f, .2f, 0f);
        gl.glVertex3f(.6f, .2f, 1f);
        gl.glVertex3f(.4f, .2f, 1f);

        gl.glVertex3f(.8f, 0f, 0f);
        gl.glVertex3f(.8f, .2f, 0f);
        gl.glVertex3f(.6f, .2f, 1f);
        gl.glVertex3f(.6f, .0f, 1f);

        gl.glVertex3f(.6f, 0f, 0f);
        gl.glVertex3f(.6f, .2f, 0f);
        gl.glVertex3f(.4f, .2f, 1f);
        gl.glVertex3f(.4f, .0f, 1f);

        // Middle
        gl.glVertex3f(.1f, 0f, .4f);
        gl.glVertex3f(.1f, .2f, .4f);
        gl.glVertex3f(.7f, .2f, .4f);
        gl.glVertex3f(.7f, .0f, .4f);

        gl.glVertex3f(.15f, 0f, .6f);
        gl.glVertex3f(.15f, .2f, .6f);
        gl.glVertex3f(.65f, .2f, .6f);
        gl.glVertex3f(.65f, .0f, .6f);

        gl.glVertex3f(.1f, 0f, .4f);
        gl.glVertex3f(.7f, 0f, .4f);
        gl.glVertex3f(.65f, 0f, .6f);
        gl.glVertex3f(.15f, 0f, .6f);

        gl.glVertex3f(.1f, .2f, .4f);
        gl.glVertex3f(.7f, .2f, .4f);
        gl.glVertex3f(.65f, .2f, .6f);
        gl.glVertex3f(.15f, .2f, .6f);

        gl.glEnd();
        gl.glFlush();
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
        // method body
    }

    @Override
    public void init(GLAutoDrawable drawable) {

        final GL2 gl = drawable.getGL().getGL2();

        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glClearColor(0f, 0f, 0f, 0f);
        gl.glClearDepth(10.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        final GL2 gl = drawable.getGL().getGL2();
        if (height <= 0)
            height = 1;

        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        glu.gluPerspective(45F, h, 1, 1000.0);
        switch (mode) {
            case XY_MODE: glu.gluLookAt(0, 0, 1, 0, 0, 0, 0, 1, 0); break;
            case XZ_MODE: glu.gluLookAt(0, 10, 0, 0, 0, 0, 1, 0, 1); break;
            case YZ_MODE: glu.gluLookAt(10, 0, 0, 0, 0, 0, 0, 10, 0); break;
        }
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void setXrot(float xrot) {
        this.xrot = xrot;
    }

    public void setYrot(float yrot) {
        this.yrot = yrot;
    }

    public void setZrot(float zrot) {
        this.zrot = zrot;
    }

    public Projector(int mode) {
        this(180, 180, 180);
        this.mode = mode;
    }

    public Projector(float xrot, float yrot, float zrot) {
        this.xrot = xrot;
        this.yrot = yrot;
        this.zrot = zrot;
    }
}