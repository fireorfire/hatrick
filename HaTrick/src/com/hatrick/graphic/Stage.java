package com.hatrick.graphic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Stage {
	private static HashMap<Integer,Sprite> elements = new HashMap<Integer,Sprite>();
	private static AppGameContainer container = null;
	private static final int FPS_MAX = 30;
	
	public Stage(AppGameContainer container){
		setContainer(container);
	}
	
	public static void setContainer(AppGameContainer agc){
		container = agc;
	}
	
	public static void add(Sprite sprt){
		elements.put(sprt.getId(), sprt);
	}
	
	public static void remove(int id){
		elements.remove(id);
	}
	public static Sprite get(int id){
		return elements.get(id);
	}
	public static void display(){
		//Display the elements in order of their depth.
		List<Map.Entry<Integer, Sprite>> sprites =
			    new ArrayList<Map.Entry<Integer, Sprite>>(elements.entrySet());

		Collections.sort(sprites, new Comparator<Map.Entry<Integer, Sprite>>() {   
		    public int compare(Map.Entry<Integer, Sprite> o1, Map.Entry<Integer, Sprite> o2) {      
		        //return (o2.getValue() - o1.getValue()); 
		        return (int) (o1.getValue().getY() - o2.getValue().getY());
		    }
		}); 
		
		for(Entry<Integer, Sprite> sprt_entry : sprites){
			sprt_entry.getValue().draw();
		}
	}

	public static int getFPS() {
		return container.getFPS();
	}
	
	public static int getCurrentDuration(){
		return 1000/getFPS();
	}

	public static int getMaxFPS() {
		return FPS_MAX;
	}
	public static int getMinDuration(){
		return 1000/FPS_MAX;
	}
}
