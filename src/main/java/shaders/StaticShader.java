package shaders;

import org.joml.Matrix4f;

public class StaticShader extends ShaderProgram{

    private static final String VERTEX_FILE = "src/main/java/shaders/vertexShader.vert";
    private static final String FRAGMENT_FILE = "src/main/java/shaders/fragmentShader.frag";
    //private static final String VERTEX_FILE = "src/main/java/shaders/vertexShaderColor.vert";
    //private static final String FRAGMENT_FILE = "src/main/java/shaders/fragmentShaderColor.frag";

    private int location_TransformationMatrix;
    private int location_ProjectionMatrix;

    public StaticShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void getAllUniformLocation() {
        location_TransformationMatrix = super.getUniformLocation("transformationMatrix");
        location_ProjectionMatrix = super.getUniformLocation("projectionMatrix");
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

}