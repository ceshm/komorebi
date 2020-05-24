package renderEngine;

import entity.Entity;
import models.RawModel;
import models.TexturedModel;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import shaders.StaticShader;
import textures.ModelTexture;

import java.util.HashMap;
import java.util.List;

import static utils.MathKt.createTransformMatrix;


/**
 * Handles the rendering of a model to the screen.
 *
 * @author Karl
 *
 */
public class EntityRenderer {
    // 13. preparing MasterRenderer
    private StaticShader shader;

    public EntityRenderer(StaticShader shader, Matrix4f projectionMatrix) {
        this.shader = shader;
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
    }

    public void render(HashMap<TexturedModel, List<Entity>> entities) {
        entities.keySet().forEach(model -> {
            prepareTexturedModel(model);
            entities.get(model).forEach(entity -> {
                prepareInstance(entity);
                GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
            });
            unbindTexturedModel();
        });
    }

    public void prepareTexturedModel(TexturedModel model){
        RawModel rawModel = model.getRawModel();

        // bind the entity model
        GL30.glBindVertexArray(rawModel.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);

        ModelTexture texture = model.getTexture();
        shader.loadShine(texture.getShineDamper(), texture.getReflectivity());

        // bind the entity texture
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
    }

    public void unbindTexturedModel(){
        // unbind stuff
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
    }

    public void prepareInstance(Entity entity){
        Matrix4f transformMatrix = createTransformMatrix(entity.getPosition(), entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale());
        shader.loadTransformationMatrix(transformMatrix);
    }

}