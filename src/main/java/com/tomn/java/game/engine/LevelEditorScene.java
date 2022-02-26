package com.tomn.java.game.engine;

import com.tomn.java.game.engine.renderer.Shader;
import org.joml.Vector2f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class LevelEditorScene extends Scene {
    private int vertexID, fragmentID, shaderProgram;

    private float[] vertexArray = {
      // positions                // color
      100.5f, 0.5f,  0.0f,           1.0f, 0.0f, 0.0f, 1.0f, // Bottom right 0
      0.5f,   100.f, 0.0f,           0.0f, 1.0f, 0.0f, 1.0f, // Top left 1
      100.5f, 100.5f,0.0f,           1.0f, 0.0f, 1.0f, 1.0f, // Top right 2
      0.5f,   0.5f,  0.0f,           1.0f, 1.0f, 0.0f, 1.0f, // Bottom left 3
    };

    private int[] elementArray = {
        2,1,0, // Top right
        0,1,3, // Bottom left
    };

    private int vaoID, vboID, eboID;

    private Shader defaultShader;

    public LevelEditorScene() {

    }

    @Override
    public void init() {

        this.camera = new Camera(new Vector2f());
        defaultShader = new Shader("assets/shaders/default.glsl");
        defaultShader.compile();


        // generate VAO, VBO, and EBO buffers, and sending to GPU
        vaoID = glGenVertexArrays();
        glBindVertexArray(vaoID);

        // Create a float buffer of vertices
        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertexArray.length);
        vertexBuffer.put(vertexArray).flip();

        // Create VBO upload the vertex buffer
        vboID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);

        // Create the indices and upload
        IntBuffer elementBuffer = BufferUtils.createIntBuffer(elementArray.length);
        elementBuffer.put(elementArray).flip();

        eboID = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, eboID);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, elementBuffer, GL_STATIC_DRAW);

        // Add the vertex attribute pointers
        int positionsSize = 3;
        int colorSize = 4;
        int floatSizeBytes = 4;
        int vertexSizeBytes = (positionsSize + colorSize) * floatSizeBytes;
        glVertexAttribPointer(0, positionsSize, GL_FLOAT, false, vertexSizeBytes, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, colorSize, GL_FLOAT, false, vertexSizeBytes, positionsSize * floatSizeBytes);
        glEnableVertexAttribArray(1);
    }

    @Override
    public void update(float dt) {
        camera.position.x -= dt * 50.0f;
        defaultShader.use();
        defaultShader.uploadMat4f("uProjection", camera.getProjectionMatrix());
        defaultShader.uploadMat4f("uView", camera.getViewMatrix());
        // Bind the VAO
        glBindVertexArray(vaoID);

        // Enable the vertex pointers
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        glDrawElements(GL_TRIANGLES, elementArray.length, GL_UNSIGNED_INT, 0);

        // unbind everything
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);

        glBindVertexArray(0);

        defaultShader.detach();
    }
}