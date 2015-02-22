package enginetester;

import models.RawModel;
import models.TexturedModel;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.OBJLoader;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {
		
		DisplayManager.createdDisplay();
		
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);
		
//		float[] vertices = {
//			//left
//			-0.5f,0.5f,0, //v0
//			-0.5f,-0.5f,0, //v1
//			0.5f,-0.5f,0, //v2
//			0.5f,0.5f,0 //v3
//			
//		};
//		
//		int[] indices = {
//			0,1,3, //top left(v0,v1,v3)
//			3,1,2 //bottom right(v3,v1,v1)
//		};
//		
//		float[] textureCoords = {
//				0,0, //v0
//				0,1, //v1
//				1,1, //v2
//				1,0  //v3
//		};
		
//		float[] vertices = {			
//				-0.5f,0.5f,-0.5f,	
//				-0.5f,-0.5f,-0.5f,	
//				0.5f,-0.5f,-0.5f,	
//				0.5f,0.5f,-0.5f,		
//				
//				-0.5f,0.5f,0.5f,	
//				-0.5f,-0.5f,0.5f,	
//				0.5f,-0.5f,0.5f,	
//				0.5f,0.5f,0.5f,
//				
//				0.5f,0.5f,-0.5f,	
//				0.5f,-0.5f,-0.5f,	
//				0.5f,-0.5f,0.5f,	
//				0.5f,0.5f,0.5f,
//				
//				-0.5f,0.5f,-0.5f,	
//				-0.5f,-0.5f,-0.5f,	
//				-0.5f,-0.5f,0.5f,	
//				-0.5f,0.5f,0.5f,
//				
//				-0.5f,0.5f,0.5f,
//				-0.5f,0.5f,-0.5f,
//				0.5f,0.5f,-0.5f,
//				0.5f,0.5f,0.5f,
//				
//				-0.5f,-0.5f,0.5f,
//				-0.5f,-0.5f,-0.5f,
//				0.5f,-0.5f,-0.5f,
//				0.5f,-0.5f,0.5f
//				
//		};
//		
//		float[] textureCoords = {
//				
//				0,0,
//				0,1,
//				1,1,
//				1,0,			
//				0,0,
//				0,1,
//				1,1,
//				1,0,			
//				0,0,
//				0,1,
//				1,1,
//				1,0,
//				0,0,
//				0,1,
//				1,1,
//				1,0,
//				0,0,
//				0,1,
//				1,1,
//				1,0,
//				0,0,
//				0,1,
//				1,1,
//				1,0
//
//				
//		};
//		
//		int[] indices = {
//				0,1,3,	
//				3,1,2,	
//				4,5,7,
//				7,5,6,
//				8,9,11,
//				11,9,10,
//				12,13,15,
//				15,13,14,	
//				16,17,19,
//				19,17,18,
//				20,21,23,
//				23,21,22
//
//		};
		
		//RawModel model = loader.loadToVAO(vertices,textureCoords,indices);
		RawModel model = OBJLoader.loadObjModel("stall", loader);
		//ModelTexture texture = new ModelTexture(loader.loadTextures("dirt"));
		ModelTexture texture = new ModelTexture(loader.loadTextures("stallTextures"));
		//TexturedModel texturedModel = new TexturedModel(model,new ModelTexture(loader.loadTextures("stallTextures")));
		TexturedModel texturedModel = new TexturedModel(model,texture);
		Entity entity = new Entity(texturedModel, new Vector3f(0,0,-50),0,0,0,1);
		
		Camera camera = new Camera();
		
		while(!Display.isCloseRequested()) {
			entity.inscreaseRotation(1, 0, 0);
			camera.move();
			renderer.prepare();
			//game logic
			//render
			shader.Start();
			shader.loadViewMatrix(camera);
			
			renderer.render(entity,shader);
			shader.Stop();
			DisplayManager.updateDisplay();
		}
		shader.CleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
		
	}

}
