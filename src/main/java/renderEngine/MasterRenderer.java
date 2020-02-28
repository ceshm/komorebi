package renderEngine;

import entity.Camera;
import entity.Entity;
import entity.Light;
import models.TexturedModel;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import shaders.StaticShader;
import shaders.TerrainShader;
import terrains.Terrain;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * This class helps to optimize the rendering of multiple entities with the same config: model and texture, etc.
 * This is achieved by taken some code out of the Main game loop, and abstracting it to this class.
 * Created in video tutorial #13.
 */
public class MasterRenderer {
    private static final float FOV = 70;
    private static final float NEAR_PLANE = 0.1f;
    private static final float FAR_PLANE = 1000;

    private static Matrix4f projectionMatrix;

    private StaticShader shader = new StaticShader();
    private EntityRenderer renderer;

    private TerrainShader terrainShader = new TerrainShader();
    private TerrainRenderer terrainRenderer;

    private HashMap<TexturedModel, List<Entity>> entities = new HashMap<TexturedModel, List<Entity>>();
    private List<Terrain> terrains = new ArrayList<Terrain>();


    public MasterRenderer() {
        // ==== To not render the insides of geometries ====
        //GL11.glEnable(GL11.GL_CULL_FACE);
        //GL11.glCullFace(GL11.GL_BACK);
        // =================================================
        createProjectionMatrix();
        renderer = new EntityRenderer(shader, projectionMatrix);
        terrainRenderer = new TerrainRenderer(terrainShader, projectionMatrix);
    }

    public void render(Light sun, Camera camera) {
        // 13. Stuff that was on the main game loop
        prepare();

        shader.start();
        shader.loadLight(sun);
        shader.loadViewMatrix(camera);
        renderer.render(entities);

        shader.stop();

            // 14. ---
        terrainShader.start();
        terrainShader.loadLight(sun);
        terrainShader.loadViewMatrix(camera);
        terrainRenderer.render(terrains);
        terrainShader.stop();
        terrains.clear();
            // 14. ---

        entities.clear();
        // 13. ---
    }

    public void processEntity(Entity entity) {
        TexturedModel model = entity.getModel();
        List<Entity> batch = entities.get(model);
        if (batch != null) {
            batch.add(entity);
        } else {
            List<Entity> newBatch = new ArrayList<Entity>();
            newBatch.add(entity);
            entities.put(model, newBatch);
        }
    }

    public void processTerrain(Terrain terrain) {
        terrains.add(terrain);
    }

    /**
     * This method must be called each frame, before any rendering is carried
     * out. It basically clears the screen of everything that was rendered last
     * frame (using the glClear() method). The glClearColor() method determines
     * the colour that it uses to clear the screen. In this example it makes the
     * entire screen red at the start of each frame.
     */
    public void prepare() {
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClearColor(0, 0, 0, 1);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
    }

    private void createProjectionMatrix(){
        //float aspectRatio = (float) DisplayManager.get / (float) Display.getHeight();
        IntBuffer w = BufferUtils.createIntBuffer(4);
        IntBuffer h = BufferUtils.createIntBuffer(4);
        GLFW.glfwGetWindowSize(DisplayManager.getWindow(), w, h);
        float aspectRatio = (float) w.get(0) / (float) h.get(0);

        float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
        float x_scale = y_scale / aspectRatio;
        float frustum_length = FAR_PLANE - NEAR_PLANE;

        projectionMatrix = new Matrix4f();
        projectionMatrix.m00(x_scale);
        projectionMatrix.m11(y_scale);
        projectionMatrix.m22(-((FAR_PLANE + NEAR_PLANE) / frustum_length));
        projectionMatrix.m23(-1);
        projectionMatrix.m32(-((2 * NEAR_PLANE * FAR_PLANE) / frustum_length));
        projectionMatrix.m33(0);
    }

    public void cleanUp(){
        shader.cleanUp();
        terrainShader.cleanUp();
    }

}
