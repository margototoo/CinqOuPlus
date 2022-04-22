package com.example.cinqouplus.geometrie;

import android.opengl.GLES30;
import android.util.Log;

import com.example.cinqouplus.MyGLRenderer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.Arrays;
import java.util.Objects;

public class Pentagone implements Geometrie{

    /* Le vertex shader avec la définition de gl_Position et les variables utiles au fragment shader
     */
    private final String vertexShaderCode =
            "#version 300 es\n"+
                    "uniform mat4 uMVPMatrix;\n"+
                    "in vec3 vPosition;\n" +
                    "in vec4 vCouleur;\n"+
                    "out vec4 Couleur;\n"+
                    "out vec3 Position;\n"+
                    "void main() {\n" +
                    "Position = vPosition;\n"+
                    "gl_Position = uMVPMatrix * vec4(vPosition,1.0);\n" +
                    "Couleur = vCouleur;\n"+
                    "}\n";

    private final String fragmentShaderCode =
            "#version 300 es\n"+
                    "precision mediump float;\n" + // pour définir la taille d'un float
                    "in vec4 Couleur;\n"+
                    "in vec3 Position;\n"+
                    "out vec4 fragColor;\n"+
                    "void main() {\n" +// transformation du carré en rond
            /*"float x = Position.x;\n"+
            "float y = Position.y;\n"+
            "float test = x*x+y*y;\n"+
            "if (test>1.0) \n"+
                "discard;\n"+*/
                    "fragColor = Couleur;\n" +
                    "}\n"
            ;

    /* les déclarations pour l'équivalent des VBO */

    private FloatBuffer vertexBuffer; // Pour le buffer des coordonnées des sommets du carré
    private ShortBuffer indiceBuffer; // Pour le buffer des indices
    private FloatBuffer colorBuffer; // Pour le buffer des couleurs des sommets

    /* les déclarations pour les shaders
    Identifiant du programme et pour les variables attribute ou uniform
     */
    private int IdProgram; // identifiant du programme pour lier les shaders
    private int IdPosition; // idendifiant (location) pour transmettre les coordonnées au vertex shader
    private int IdCouleur; // identifiant (location) pour transmettre les couleurs
    private int IdMVPMatrix; // identifiant (location) pour transmettre la matrice PxVxM

    static final int COORDS_PER_VERTEX = 3; // nombre de coordonnées par vertex
    static final int COULEURS_PER_VERTEX = 4; // nombre de composantes couleur par vertex

    int []linkStatus = {0};

    /* Attention au repère au niveau écran (x est inversé)
     Le tableau des coordonnées des sommets
     Oui ce n'est pas joli avec 1.0 en dur ....
     */

    // tableau qui sert de mémoire à la forme pour quelle utilise toujours le repère
    // qui part de 0/ milieu de l'ecran
    float initPentaCoords[] = {
            0f,   0.9f, 0.0f,
            -0.85f,  0.2f, 0.0f,
            0.85f,  0.2f, 0.0f,
            -0.5f,  -0.8f, 0.0f,
            0.5f,  -0.8f, 0.0f};
    float pentaCoords[] = {
            0f,   0.9f, 0.0f, //0
            -0.85f,  0.2f, 0.0f, //1
            0.85f,  0.2f, 0.0f, //2
            -0.5f,  -0.8f, 0.0f, //3
            0.5f,  -0.8f, 0.0f}; //4
    // Le tableau des couleurs
    float pentaColors[] = {
            1.0f,  0.0f, 0.0f, 1.0f,
            1.0f,  1.0f, 1.0f, 1.0f,
            0.0f,  1.0f, 0.0f, 1.0f,
            0.0f,  0.0f, 1.0f, 1.0f,
            1.0f,  1.0f, 1.0f, 1.0f,};

    // Le carré est dessiné avec 2 triangles
    private final short Indices[] = { 0, 1, 2,
                                    1, 2, 3,
                                    2, 3, 4};

    private final int vertexStride = COORDS_PER_VERTEX * 4; // le pas entre 2 sommets : 4 bytes per vertex

    private final int couleurStride = COULEURS_PER_VERTEX * 4; // le pas entre 2 couleurs

    private final float Position[] = {0.0f,0.0f};

    public Pentagone(float[] Pos, float red, float green, float blue) {

        // positionnnement de la forme en fonction du paramètre du constructeur et de sa position initial
        // avec le repère du milieu l'écran
        this.Position[0] = Pos[0];
        this.Position[1] = Pos[1];
        for (int i = 0; i < pentaCoords.length-1; i+=3) {
            pentaCoords[i] = initPentaCoords[i] + this.Position[0];
            pentaCoords[i+1] = initPentaCoords[i+1] + this.Position[1];
        }
        // Mise en place de la couleur du Plateau (carré mais plus gros)
        for (int i = 0; i < pentaColors.length-1; i+=4) {
            pentaColors[i] = red;
            pentaColors[i+1] = green;
            pentaColors[i+2] = blue;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pentagone pentagone = (Pentagone) o;
        Log.d("equals triangle", "Il se passe un truc par ici???");
        return Arrays.equals(initPentaCoords, pentagone.initPentaCoords);
        //return Float[].compare(triangleCoords, triangle.triangleCoords) == 0
        //return Arrays.equals(initTriangleCoords, triangle.initTriangleCoords) && Arrays.equals(triangleCoords, triangle.triangleCoords) && Arrays.equals(triangleColors, triangle.triangleColors) && Arrays.equals(Indices, triangle.Indices);
    }

    public void set_position(float[] pos) {
        for (int i = 0; i < pentaCoords.length-1; i+=3) {
            pentaCoords[i] = initPentaCoords[i] + pos[0];
            pentaCoords[i+1] = initPentaCoords[i+1] + pos[1];
        }
        Log.d("deplacement", "pos[0]= "+pos[0]+" ,pos[1]= "+pos[1]);
//        Log.d("deplacement", Arrays.toString(squareCoords));
        Position[0]=pos[0];
        Position[1]=pos[1];
    }

    public void draw(float[] mvpMatrix) {
        // initialisation du buffer pour les vertex (4 bytes par float)
        ByteBuffer bb = ByteBuffer.allocateDirect(pentaCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(pentaCoords);
        vertexBuffer.position(0);

        // initialisation du buffer pour les couleurs (4 bytes par float)
        ByteBuffer bc = ByteBuffer.allocateDirect(pentaColors.length * 4);
        bc.order(ByteOrder.nativeOrder());
        colorBuffer = bc.asFloatBuffer();
        colorBuffer.put(pentaColors);
        colorBuffer.position(0);

        // initialisation du buffer des indices
        ByteBuffer dlb = ByteBuffer.allocateDirect(Indices.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        indiceBuffer = dlb.asShortBuffer();
        indiceBuffer.put(Indices);
        indiceBuffer.position(0);

        /* Chargement des shaders */
        int vertexShader = MyGLRenderer.loadShader(
                GLES30.GL_VERTEX_SHADER,
                vertexShaderCode);
        int fragmentShader = MyGLRenderer.loadShader(
                GLES30.GL_FRAGMENT_SHADER,
                fragmentShaderCode);

        IdProgram = GLES30.glCreateProgram();             // create empty OpenGL Program
        GLES30.glAttachShader(IdProgram, vertexShader);   // add the vertex shader to program
        GLES30.glAttachShader(IdProgram, fragmentShader); // add the fragment shader to program
        GLES30.glLinkProgram(IdProgram);                  // create OpenGL program executables
        GLES30.glGetProgramiv(IdProgram, GLES30.GL_LINK_STATUS,linkStatus,0);

        // Add program to OpenGL environment
        GLES30.glUseProgram(IdProgram);

        // get handle to shape's transformation matrix
        IdMVPMatrix = GLES30.glGetUniformLocation(IdProgram, "uMVPMatrix");

        // Apply the projection and view transformation
        GLES30.glUniformMatrix4fv(IdMVPMatrix, 1, false, mvpMatrix, 0);

        // get handle to vertex shader's vPosition member et vCouleur member
        IdPosition = GLES30.glGetAttribLocation(IdProgram, "vPosition");
        IdCouleur = GLES30.glGetAttribLocation(IdProgram, "vCouleur");

        /* Activation des Buffers */
        GLES30.glEnableVertexAttribArray(IdPosition);
        GLES30.glEnableVertexAttribArray(IdCouleur);

        /* Lecture des Buffers */
        GLES30.glVertexAttribPointer(
                IdPosition, COORDS_PER_VERTEX,
                GLES30.GL_FLOAT, false,
                vertexStride, vertexBuffer);

        GLES30.glVertexAttribPointer(
                IdCouleur, COULEURS_PER_VERTEX,
                GLES30.GL_FLOAT, false,
                couleurStride, colorBuffer);

        // Draw the square
        GLES30.glDrawElements(
                GLES30.GL_TRIANGLES, Indices.length,
                GLES30.GL_UNSIGNED_SHORT, indiceBuffer);

        // Disable vertex array
        GLES30.glDisableVertexAttribArray(IdPosition);
        GLES30.glDisableVertexAttribArray(IdCouleur);
    }

    @Override
    public float[] getPosition() {
        return new float[0];
    }
}
