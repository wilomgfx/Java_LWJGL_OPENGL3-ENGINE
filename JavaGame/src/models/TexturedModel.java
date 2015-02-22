package models;

import textures.ModelTexture;

public class TexturedModel {
	
	private RawModel rawModel;
	
	public RawModel getRawModel() {
		return rawModel;
	}

	public ModelTexture getTexture() {
		return texture;
	}

	private ModelTexture texture;
	
	public TexturedModel(RawModel model,ModelTexture texture)
	{
		this.rawModel = model;
		this.texture = texture;
	}

}
