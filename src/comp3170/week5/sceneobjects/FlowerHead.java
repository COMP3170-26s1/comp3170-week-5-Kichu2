package comp3170.week5.sceneobjects;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import comp3170.GLBuffers;
import comp3170.SceneObject;
import comp3170.Shader;
import comp3170.ShaderLibrary;
public class FlowerHead extends SceneObject {
	
	private static final String VERTEX_SHADER = "vertex.glsl";
	private static final String FRAGMENT_SHADER = "fragment.glsl";
	private Shader shader;

	private Vector3f petalColour = new Vector3f(1.0f,1.0f,1.0f);

	private Vector4f[] vertices;
	private int vertexBuffer;
	Private int indexBuffer;

	public FlowerHead(int nPetals, Vector3f colour) {
		
		// TODO: Create the flower head. (TASK 1)
		// Consider the best way to draw the mesh with the nPetals input. 
		// Note that this may involve moving some code OUT of this class!
		
		shader = ShaderLibrary.instance.compileShader(VERTEX_SHADER, FRAGMENT_SHADER);		
		petalColour = colour;
		
		float rInner = 0.1f;
		float rOuter = 0.4f;
		vertices = new Vector4f[nPetals * 4];
		int[] indices = new int[nPetals * 6];
		for (int i = 0; i < nPetals; i++) {
			float angle1= i * 2.0f * (float)Math.PI / nPetals;
			float angle2= (i + 1) * 2.0f * (float)Math.PI / nPetals;
			
			Vector4f inner1 = new Vector4f((float)Math.cos(angle1)* rInner, (float)Math.sin(angle1) * rInner, 0, 1);
			Vector4f outer1 = new Vector4f((float)Math.cos(angle1)* rOuter, (float)Math.sin(angle1) * rOuter, 0, 1);
			Vector4f inner2 = new Vector4f((float)Math.cos(angle1)* rInner, (float)Math.sin(angle2) * rInner, 0, 1);
			Vector4f outer2 = new Vector4f((float)Math.cos(angle1)* rOuter, (float)Math.sin(angle2) * rOuter, 0, 1);
			
			int vIndex = i*4;
			vertices[vIndex] = inner1;
			vertices[vIndex+1] = outer1;
			vertices[vIndex+2] = inner2;
			vertices[vIndex+3] = outer2;
			
			int iIndex = i*6;
			indices[iIndex] = vIndex;
			indices[iIndex+1] = vIndex+1;
			indices[iIndex+2] = vIndex+2;
			indices[iIndex+3] = vIndex+3;
			indices[iIndex+4] = vIndex+4;
			indices[iIndex+5] = vIndex+5;
			
		}
		vertexBuffer = GLBuffers.createBuffer(vertices);
		indexBuffer = GLBuffers.createIndexBuffer(indices);
	}

	public void update(float dt) {
		// TODO: Make the flower head rotate. (TASK 5)
	}

	public void drawSelf(Matrix4f mvpMatrix) {
		// TODO: Add any appropriate draw code. (TASK 1)
		shader.enable();
		shader.setUniform("u_mvpMatrix", mvpMatrix);
		shader.setAttribute("a_position", vertexBuffer);
		shader.setUniform("u_colour", petalColour);
		
		//glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indexBuffer);
	}
}
