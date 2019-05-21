package shaders;

import entity.Camera;
import org.joml.Matrix4f;
import utils.Maths;

public class StaticShader extends ShaderProgram{

    private static final String VERTEX_FILE = "src/main/java/shaders/vertexShader.vert";
    private static final String FRAGMENT_FILE = "src/main/java/shaders/fragmentShader.frag";
    //private static final String VERTEX_FILE = "src/main/java/shaders/vertexShaderColor.vert";
    //private static final String FRAGMENT_FILE = "src/main/java/shaders/fragmentShaderColor.frag";

    private int location_TransformationMatrix;
    private int location_ProjectionMatrix;
    private int location_ViewMatrix;

    public StaticShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void getAllUniformLocation() {
        location_TransformationMatrix = super.getUniformLocation("transformationMatrix");
        location_ProjectionMatrix = super.getUniformLocation("projectionMatrix");
        location_ViewMatrix = super.getUniformLocation("viewMatrix");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
    }

    public void loadTransformationMatrix(Matrix4f matrix){
        super.loadMatrix(location_TransformationMatrix, matrix);
    }

    public void loadProjectionMatrix(Matrix4f matrix){
        super.loadMatrix(location_ProjectionMatrix, matrix);
    }

    public void loadViewMatrix(Camera camera){
        Matrix4f matrix = Maths.createViewMatrix(camera);
        super.loadMatrix(location_ViewMatrix, matrix);
    }

}