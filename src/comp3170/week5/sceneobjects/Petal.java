package comp3170.week5.sceneobjects;

import static org.lwjgl.opengl.GL41.*;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import comp3170.GLBuffers;
import comp3170.SceneObject;
import comp3170.Shader;
import comp3170.ShaderLibrary;

public class Petal extends SceneObject {

    private static final String VERTEX_SHADER = "vertex.glsl";
    private static final String FRAGMENT_SHADER = "fragment.glsl";
    private Shader shader;

    private Vector3f colour;
    private Vector4f[] vertices;
    private int vertexBuffer;
    private int[] indices;
    private int indexBuffer;

    public Petal(float innerRadius, float outerRadius, float angleSpan, Vector3f colour) {
        shader = ShaderLibrary.instance.compileShader(VERTEX_SHADER, FRAGMENT_SHADER);
        this.colour = colour;

        // create 4 vertices for the petal (2 triangles)
        vertices = new Vector4f[4];

        vertices[0] = new Vector4f((float)Math.cos(0)*innerRadius, (float)Math.sin(0)*innerRadius, 0, 1);
        vertices[1] = new Vector4f((float)Math.cos(0)*outerRadius, (float)Math.sin(0)*outerRadius, 0, 1);
        vertices[2] = new Vector4f((float)Math.cos(angleSpan)*innerRadius, (float)Math.sin(angleSpan)*innerRadius, 0, 1);
        vertices[3] = new Vector4f((float)Math.cos(angleSpan)*outerRadius, (float)Math.sin(angleSpan)*outerRadius, 0, 1);

        vertexBuffer = GLBuffers.createBuffer(vertices);

        // indices for two triangles
        indices = new int[] {
            0, 1, 2,
            2, 1, 3
        };

        indexBuffer = GLBuffers.createIndexBuffer(indices);
    }

    @Override
    public void drawSelf(Matrix4f mvpMatrix) {
        shader.enable();
        shader.setUniform("u_mvpMatrix", mvpMatrix);
        shader.setAttribute("a_position", vertexBuffer);
        shader.setUniform("u_colour", colour);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indexBuffer);
        glDrawElements(GL_TRIANGLES, indices.length, GL_UNSIGNED_INT, 0);
    }
}
