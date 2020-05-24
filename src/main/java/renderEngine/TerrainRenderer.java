package renderEngine;

import entity.Entity;
import models.RawModel;
import models.TexturedModel;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import shaders.TerrainShader;
import terrains.Terrain;
import textures.ModelTexture;

import java.util.List;

import static utils.MathKt.createTransformMatrix;

public class TerrainRenderer {
    private TerrainShader shader;


    public TerrainRenderer(TerrainShader shader, Matrix4f projectionMatrix) {
        this.shader = shader;
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
    }

    public void render(List<Terrain> terrains) {
        terrains.forEach(terrain -> {
            prepareTerrain(terrain);
            loadModelMatrix(terrain);
            GL11.glDrawElements(GL11.GL_TRIANGLES, terrain.getModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
            unbindTexturedModel();
        });
    }

    public void prepareTerrain(Terrain terrain){
        RawModel rawModel = terrain.getModel();

        // bind the entity model
        GL30.glBindVertexArray(rawModel.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);

        ModelTexture texture = terrain.getTexture();
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

    public void loadModelMatrix(Terrain terrain){
        Matrix4f transformMatrix = createTransformMatrix(new Vector3f(terrain.getX(),0,terrain.getZ()), 0,0,0, 1);
        shader.loadTransformationMatrix(transformMatrix);
    }

}
