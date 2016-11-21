package opengl;
/*
 * H2015Eksamensoppg_2JOGL_kont.java  JHN 2015-11-18
 *
 * Du skal i oppgave 2 a)c) tegne opp et tredimensjonalt rettvinklet høyrehånds koordinatsystem,
 * (X,Y,Z).  På enden av hver av de positive aksene, skal det tegnes en trekant (pilspiss)
 * som angir retningen på disse aksene. Fargen på alle aksene skal være svarte.
 * Fargen på trekantene skal være henholdsvis rød på X-aksen, grønn på Y-aksen og blå på Z-aksen
 * når de er plassert på enden av hver av de positive koordinatakse.
 *
*/

//JOGL classes:
import static com.jogamp.opengl.GL2.*;//slipper å bruke GL2.konstant
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.awt.GLCanvas;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

import com.jogamp.opengl.util.gl2.GLUT;


public class Oppgave extends GLCanvas implements GLEventListener{

	/* interfacet GLEventListener innholder de 5 metodene som må implemeenteres:
		- display(GLDrawable drawable)
		  Called by the drawable to initiate OpenGL rendering by the client
		- dispose(GLAutoDrawable d)
		  Notifies the listener to perform the release of all OpenGL resources per GLContext, such as memory buffers and GLSL programs.
		- init(GLDrawable drawable)
		  Called by the drawable immediately after the OpenGL context is initialized.
		- reshape(GLDrawable drawable, int x, int y, int width, int height)
		  Called by the drawable during the first repaint after the component has been resized.
    */

    private /*static final*/ GLU glu = new GLU();
    private /*static final*/ GLUT glut = new GLUT();
    private GLCanvas canvas;
    private float angle;

    public Oppgave(){
        this.addGLEventListener(this);//et objekt av klassen TegningOv1 fungerer som lytter. Det lytter på objektet selv
    }

    public void init(GLAutoDrawable glDrawable) {
        /*
        * h)
        * Forklar kort hvordan OpenGL-metoder kan fjerne skjulte flater.
        * Angi gjerne de OpenGL-metodene som kan kalles i init()-metoden for å
        * kunne utføre disse beregningene. For at OpenGL-metoder skal kunne
        * benytte vektorer/tabeller (arrays) som parametere, må denne
        * egenskapen slåes på. Angi de OpenGL-metodene som du kan benytte
        * deg av i 2 g) for å kunne bruke både fargetabeller, hjørnepunkttabeller
        * og normaliserte vektorer.
        */

        // Has to implementet due to the GLEventListener interface
        GL2 gl = glDrawable.getGL().getGL2();	//Get the GL2 object from glDrawable

        float width = (float)getSize().width;
        float hight = (float)getSize().height;

        gl.glShadeModel(GL_SMOOTH);           		// Enables Smooth Color Shading
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f); 	// Sets the background color to white

        gl.glClearDepth(1.0);                       // Enables Clearing Of The Depth Buffer
        gl.glEnable(GL_DEPTH_TEST);                	// Enables Depth Testing
        gl.glEnableClientState(GL_COLOR_ARRAY);   	// Enables color arrays
        gl.glEnableClientState(GL_VERTEX_ARRAY);  	// Enables vertex arrays
        gl.glDepthFunc(GL_LEQUAL);                  // The Type Of Depth opengl.Test To Do
        gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);  // Really Nice Perspective Calculations

        gl.glMatrixMode(GL_PROJECTION);                        // Select The Projection Matrix
        gl.glLoadIdentity();                                   // Reset The Projection Matrix
        glu.gluPerspective(90.0f, width / hight,0.1f, 100.0f); // Defines the Aspect Ratio Of the viewing pyramide
        gl.glMatrixMode(GL_MODELVIEW);                         // Select The Modelview Matrix
        gl.glLoadIdentity();                                   // Reset The ModelView Matrix
    }


    public void reshape(GLAutoDrawable glDrawable, int i, int i1, int width, int hight) {
        // Has to implementet due to the GLEventListener interface
    }

    public void tegnKoordinatsystem1(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2();

        gl.glColor3f(0, 0, 0);

        gl.glBegin(GL_LINES);
        gl.glVertex3f(-6, 0, 0);
        gl.glVertex3f(6, 0, 0);

        gl.glVertex3f(0, -6, 0);
        gl.glVertex3f(0, 6, 0);

        gl.glVertex3f(0, 0, -6);
        gl.glVertex3f(0, 0, 6);
        gl.glEnd();
    }

    public void tegnTrekant(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2();

        gl.glColor3f(0, 0, 0);

        gl.glBegin(GL_TRIANGLES);
        gl.glVertex2f(-1, -1); // A
        gl.glVertex2f(1, 0);   // B
        gl.glVertex2f(-1, 1);  // C
        gl.glEnd();
    }

    public void tegnKoordinatsystem2(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2();

        gl.glTranslatef(0, 0, -10);
        tegnKoordinatsystem1(glAutoDrawable);

        gl.glLoadIdentity();
        gl.glTranslatef(5, 0, -10);
        tegnTrekant(glAutoDrawable, 1, 0, 0);

        gl.glLoadIdentity();
        gl.glTranslatef(0, 5, -10);
        gl.glRotatef(90, 0, 0, 1);
        tegnTrekant(glAutoDrawable, 0, 1, 0);

        gl.glLoadIdentity();
        gl.glTranslatef(0, 0, -5);
        gl.glRotated(90, 1, 0, 0);
        gl.glRotatef(-90, 0, 0, 1);
        tegnTrekant(glAutoDrawable, 0, 0, 1);
    }

    private void tegnTrekant(GLAutoDrawable glAutoDrawable, float r, float g, float b) {
        GL2 gl = glAutoDrawable.getGL().getGL2();

        gl.glColor3f(r, g, b);

        gl.glBegin(GL_TRIANGLES);
        gl.glVertex2f(-1, -1); // A
        gl.glVertex2f(1, 0);   // B
        gl.glVertex2f(-1, 1);  // C
        gl.glEnd();
    }

    private void tegnKvadrat(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2();

        gl.glColor3f(0, 0, 1);
        gl.glBegin(GL_POLYGON);
        gl.glVertex2f(-2, -2); // D
        gl.glVertex2f(2, -2);  // E
        gl.glVertex2f(2, 2);   // F
        gl.glVertex2f(-2, 2);  // G
        gl.glEnd();
    }

    private float[][] pyramide = {
            {-2, -2, 0},
            {2, -2, 0},
            {2, 2, 0},
            {-2, 2, 0},
            {0, 0, 5}, // H
            {0, 0, -5} // I
    };

    private float[][] pyramideFarge = {
            {0,0,0},
            {0.5f,0.5f,0.5f},
            {0,0.5f,0.5f},
            {0.5f,0,0.5f},
            {0,0,0.5f},
            {1, 0, 0},
            {0, 1, 0},
            {0, 0, 1},
            {0, 0, 0.6f}
    };

    private void tegnFlate(GLAutoDrawable glDrawable, int flate, int a, int b, int c) {
        GL2 gl = glDrawable.getGL().getGL2();	//Get the GL2 object from glDrawable

        gl.glLoadIdentity();
        gl.glTranslatef(0, 0, -10);
        gl.glColor3fv(pyramideFarge[flate], 0);

        gl.glBegin(GL_POLYGON);
        gl.glVertex3fv(pyramide[a], 0);
        gl.glVertex3fv(pyramide[b], 0);
        gl.glVertex3fv(pyramide[c], 0);
        gl.glEnd();
    }

    private void tegnPyramide(GLAutoDrawable glAutoDrawable) {
        GL2 gl = glAutoDrawable.getGL().getGL2();	//Get the GL2 object from glDrawable

        gl.glLoadIdentity();

        // D: 0
        // E: 1
        // F: 2
        // G: 3
        // H: 4
        // I: 5
        tegnFlate(glAutoDrawable, 0, 0, 5, 1);
        tegnFlate(glAutoDrawable, 1, 1, 5, 2);
        tegnFlate(glAutoDrawable, 2, 2, 5, 3);
        tegnFlate(glAutoDrawable, 3, 3, 5, 0);

        tegnFlate(glAutoDrawable, 0, 0, 1, 4);
        tegnFlate(glAutoDrawable, 1, 1, 2, 4);
        tegnFlate(glAutoDrawable, 2, 2, 3, 4);
        tegnFlate(glAutoDrawable, 3, 3, 0, 4);

        tegnKvadrat(glAutoDrawable);
    }

    public void display(GLAutoDrawable glDrawable) {

        GL2 gl = glDrawable.getGL().getGL2();	//Get the GL2 object from glDrawable
        gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);  // Clear the Color buffer and the Depth Buffer
        gl.glLoadIdentity();

        tegnKoordinatsystem2(glDrawable);
        tegnPyramide(glDrawable);
    }

    public void dispose(GLAutoDrawable d){};
} // V2008Eksamensoppg_4JOGL



