package com.example.cinqouplus.geometrie;

import android.opengl.GLES20;
import android.opengl.GLES30;

import com.example.cinqouplus.MyGLRenderer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Lines implements Geometrie{
    private FloatBuffer VertexBuffer;
    //private final FloatBuffer colorBuffer;

    private final String VertexShaderCode =
            // This matrix member variable provides a hook to manipulate
            // the coordinates of the objects that use this vertex shader
            "uniform mat4 uMVPMatrix;" +

                    "attribute vec4 vPosition;" +
                    "void main() {" +
                    // the matrix must be included as a modifier of gl_Position
                    "  gl_Position = uMVPMatrix * vPosition;" +
                    "}";

    private final String FragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";

    protected int GlProgram;
    protected int PositionHandle;
    protected int ColorHandle;
    protected int MVPMatrixHandle;

    private int[] linkStatus = {0};

    // number of coordinates per vertex in this array
    static final int COORDS_PER_VERTEX = 3;
    float LineCoords[] = {
            -9.0f, 9.0f, 0.0f,
            -9.0f, -9.0f, 0.0f
    };
    
    private final int VertexCount = LineCoords.length / COORDS_PER_VERTEX;
    private final int VertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex

    // Set color with red, green, blue and alpha (opacity) values
    float color[] = { 0.f, 0.f, 0.f, 1.5f };

    private final float Position[] = {0.0f,0.0f};

    public Lines() {
    }

    public void SetVerts(float v0, float v1, float v2, float v3, float v4, float v5) {
        LineCoords[0] = v0;
        LineCoords[1] = v1;
        LineCoords[2] = v2;
        LineCoords[3] = v3;
        LineCoords[4] = v4;
        LineCoords[5] = v5;

        VertexBuffer.put(LineCoords);
        // set the buffer to read the first coordinate
        VertexBuffer.position(0);
    }

    public void SetColor(float red, float green, float blue, float alpha) {
        color[0] = red;
        color[1] = green;
        color[2] = blue;
        color[3] = alpha;
    }

    @Override
    public void draw(float[] mvpMatrix) {

        ByteBuffer bb = ByteBuffer.allocateDirect(
                LineCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        VertexBuffer = bb.asFloatBuffer();
        VertexBuffer.put(this.LineCoords);
        VertexBuffer.position(0);

        int vertexShader = MyGLRenderer.loadShader(GLES30.GL_VERTEX_SHADER, this.VertexShaderCode);
        int fragmentShader = MyGLRenderer.loadShader(GLES30.GL_FRAGMENT_SHADER, this.FragmentShaderCode);

        GlProgram = GLES30.glCreateProgram();             // create empty OpenGL ES Program
        GLES30.glAttachShader(GlProgram, vertexShader);   // add the vertex shader to program
        GLES30.glAttachShader(GlProgram, fragmentShader); // add the fragment shader to program
        GLES30.glLinkProgram(GlProgram);                  // creates OpenGL ES program executables
        GLES30.glGetProgramiv(this.GlProgram, GLES20.GL_LINK_STATUS, linkStatus,0);


        // Add program to OpenGL ES environment
        GLES30.glUseProgram(GlProgram);

        PositionHandle = GLES30.glGetAttribLocation(GlProgram, "vPosition");
        MVPMatrixHandle = GLES30.glGetUniformLocation(GlProgram, "uMVPMatrix");
        ColorHandle = GLES30.glGetUniformLocation(GlProgram, "vColor");

        GLES30.glUniform4fv(ColorHandle, 1, color, 0);

        GLES30.glEnableVertexAttribArray(this.PositionHandle);
        GLES20.glEnableVertexAttribArray(this.ColorHandle);

        // Prepare the triangle coordinate data
        GLES30.glVertexAttribPointer(PositionHandle, COORDS_PER_VERTEX,
                GLES30.GL_FLOAT, false,
                VertexStride, VertexBuffer);


        // Apply the projection and view transformation
        GLES30.glUniformMatrix4fv(MVPMatrixHandle, 1, false, mvpMatrix, 0);

        // Draw the triangle
        GLES30.glDrawArrays(GLES30.GL_LINES, 0, VertexCount);

        // Disable vertex array
        GLES30.glDisableVertexAttribArray(this.PositionHandle);
        GLES30.glDisableVertexAttribArray(this.ColorHandle);
    }

    @Override
    public float[] getPosition() {
        return new float[0];
    }

    @Override
    public void set_position(float[] pos) {
        Position[0]=pos[0];
        Position[1]=pos[1];
    }

    public void setCoords(float[] coords) {
        this.LineCoords = coords;
    }
}
