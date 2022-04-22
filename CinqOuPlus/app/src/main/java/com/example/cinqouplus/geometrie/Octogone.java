package com.example.cinqouplus.geometrie;

import android.opengl.GLES30;
import android.util.Log;

import com.example.cinqouplus.MyGLRenderer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.Arrays;

public class Octogone implements Geometrie{

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
    float initOctoCoords[] = {
            0.0f,   0.9f, 0.0f, //0
            -0.65f,  0.65f, 0.0f, //1
            0.65f,  0.65f, 0.0f, //2
            -0.9f,  0.0f, 0.0f, //3
            0.9f,  0.0f, 0.0f, //4
            -0.65f,  -0.65f, 0.0f, //5
            0.65f,  -0.65f, 0.0f, //6
            0.0f,   -0.9f, 0.0f}; //7

    float octoCoords[] = {
            0.0f,   0.9f, 0.0f, //0
            -0.65f,  0.65f, 0.0f, //1
            0.65f,  0.65f, 0.0f, //2
            -0.9f,  0.0f, 0.0f, //3
            0.9f,  0.0f, 0.0f, //4
            -0.65f,  -0.65f, 0.0f, //5
            0.65f,  -0.65f, 0.0f, //6
            0.0f,   -0.9f, 0.0f};
    // Le tableau des couleurs
    float octoColors[] = {
            1.0f,  0.0f, 0.0f, 1.0f,
            1.0f,  1.0f, 1.0f, 1.0f,
            0.0f,  1.0f, 0.0f, 1.0f,
            0.0f,  0.0f, 1.0f, 1.0f,
            0.0f,  1.0f, 0.0f, 1.0f,
            0.0f,  0.0f, 1.0f, 1.0f,
            0.0f,  1.0f, 0.0f, 1.0f,
            0.0f,  0.0f, 1.0f, 1.0f};

    // Le carré est dessiné avec 2 triangles
    private final short Indices[] = { 0, 1, 2,
                                    1, 2, 3,
                                    2, 3, 4,
                                    3, 4, 5,
                                    4, 5, 6,
                                    5, 6, 7};

    private final int vertexStride = COORDS_PER_VERTEX * 4; // le pas entre 2 sommets : 4 bytes per vertex

    private final int couleurStride = COULEURS_PER_VERTEX * 4; // le pas entre 2 couleurs

    private final float Position[] = {0.0f,0.0f};

    public Octogone(float[] Pos, float red, float green, float blue) {

        // positionnnement de la forme en fonction du paramètre du constructeur et de sa position initial
        // avec le repère du milieu l'écran
        Position[0] = Pos[0];
        Position[1] = Pos[1];
        for (int i = 0; i < octoCoords.length-1; i+=3) {
            octoCoords[i] = initOctoCoords[i] + Position[0];
            octoCoords[i+1] = initOctoCoords[i+1] + Position[1];
        }

        // Mise en place de la couleur du losange
        for (int i = 0; i < octoColors.length-1; i+=4) {
            octoColors[i] = red;
            octoColors[i+1] = green;
            octoColors[i+2] = blue;
        }
    }

    @Override
    public float[] getPosition(){
        return this.Position;
    }

    @Override
    public void set_position(float[] pos) {
        for (int i = 0; i < octoCoords.length-1; i+=3) {
            octoCoords[i] = initOctoCoords[i] + pos[0];
            octoCoords[i+1] = initOctoCoords[i+1] + pos[1];
        }
        Log.d("deplacement", "pos[0]= "+pos[0]+" ,pos[1]= "+pos[1]);
        Position[0]=pos[0];
        Position[1]=pos[1];
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Octogone octogone = (Octogone) o;
        Log.d("equals triangle", "Il se passe un truc par ici???");
        return Arrays.equals(initOctoCoords, octogone.initOctoCoords);
        //return Float[].compare(triangleCoords, triangle.triangleCoords) == 0
        //return Arrays.equals(initTriangleCoords, triangle.initTriangleCoords) && Arrays.equals(triangleCoords, triangle.triangleCoords) && Arrays.equals(triangleColors, triangle.triangleColors) && Arrays.equals(Indices, triangle.Indices);
    }

    public void draw(float[] mvpMatrix) {
        // initialisation du buffer pour les vertex (4 bytes par float)
        ByteBuffer bb = ByteBuffer.allocateDirect(octoCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(octoCoords);
        vertexBuffer.position(0);

        // initialisation du buffer pour les couleurs (4 bytes par float)
        ByteBuffer bc = ByteBuffer.allocateDirect(octoColors.length * 4);
        bc.order(ByteOrder.nativeOrder());
        colorBuffer = bc.asFloatBuffer();
        colorBuffer.put(octoColors);
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
}
