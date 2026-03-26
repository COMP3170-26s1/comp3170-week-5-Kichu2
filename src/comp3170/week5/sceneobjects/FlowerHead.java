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

	public FlowerHead(int nPetals, Vector3f colour) {
		
		// TODO: Create the flower head. (TASK 1)
		// Consider the best way to draw the mesh with the nPetals input. 
		// Note that this may involve moving some code OUT of this class!
		
		shader = ShaderLibrary.instance.compileShader(VERTEX_SHADER, FRAGMENT_SHADER);		
		petalColour = colour;
		
		float innerRadius = 0.1f;
		float outerRadius = 0.4f;
		float angleSpan = (float)(2*Math.PI/nPetals);
		
		for(int i = 0; i < nPetals; i++) {
			Petal petal = new Petal(innerRadius, outerRadius, angleSpan, petalColour);
			
			float angle = i * angleSpan;
			petal.setLocalMatrix(petal.getLocalMatrix().rotateZ(angle));
		}
	}

	public void update(float dt) {
		// TODO: Make the flower head rotate. (TASK 5)
	}

	public void drawSelf(Matrix4f mvpMatrix) {
		// TODO: Add any appropriate draw code. (TASK 1)
	}
}
