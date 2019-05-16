package shaders;

import org.joml.Matrix4f;

public class StaticShader extends ShaderProgram{

    private static final String VERTEX_FILE = "src/main/java/shaders/vertexShader.txt";
    private static final String FRAGMENT_FILE = "src/main/java/shaders/fragmentShader.txt";
    //private static final String VERTEX_FILE = "src/main/java/shaders/vertexShaderColor.txt";
    //private static final String FRAGMENT_FILE = "src/main/java/shaders/fragmentShaderColor.txt";

    private int location_TransformationMatrix;

    public StaticShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void getAllUniformLocation() {
        location_TransformationMatrix = super.getUniformLocation("transformationMatrix");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
    }

    public void loadTransformationMatrix(Matrix4f matrix){
        super.loadMatrix(location_TransformationMatrix, matrix);
    }

}