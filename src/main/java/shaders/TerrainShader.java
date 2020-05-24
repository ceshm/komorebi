package shaders;

import entity.Camera;
import entity.Light;
import org.joml.Matrix4f;

import static utils.MathKt.createViewMatrix;

public class TerrainShader extends ShaderProgram {
    private static final String VERTEX_FILE = "src/main/java/shaders/terrainVertexShader.vert";
    private static final String FRAGMENT_FILE = "src/main/java/shaders/terrainFragmentShader.frag";

    private int location_TransformationMatrix;
    private int location_ProjectionMatrix;
    private int location_ViewMatrix;
    private int location_LightPosition;
    private int location_LightColor;
    private int location_ShineDamper;
    private int location_Reflectivity;


    public TerrainShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void getAllUniformLocation() {
        location_TransformationMatrix = super.getUniformLocation("transformationMatrix");
        location_ProjectionMatrix = super.getUniformLocation("projectionMatrix");
        location_ViewMatrix = super.getUniformLocation("viewMatrix");
        location_LightPosition = super.getUniformLocation("lightPosition");
        location_LightColor = super.getUniformLocation("lightColor");
        location_ShineDamper = super.getUniformLocation("shineDamper");
        location_Reflectivity = super.getUniformLocation("reflectivity");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
        super.bindAttribute(2, "normal");
    }

    public void loadTransformationMatrix(Matrix4f matrix){
        super.loadMatrix(location_TransformationMatrix, matrix);
    }

    public void loadProjectionMatrix(Matrix4f matrix){
        super.loadMatrix(location_ProjectionMatrix, matrix);
    }

    public void loadViewMatrix(Camera camera){
        Matrix4f matrix = createViewMatrix(camera);
        super.loadMatrix(location_ViewMatrix, matrix);
    }

    public void loadLight(Light light){
        super.loadVector(location_LightPosition, light.getPosition());
        super.loadVector(location_LightColor, light.getColor());
    }

    public void loadShine(float damper, float reflectivity){
        super.loadFloat(location_ShineDamper, damper);
        super.loadFloat(location_Reflectivity, reflectivity);
    }

}