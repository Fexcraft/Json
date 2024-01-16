package net.fexcraft.app.json;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Ferdinand Calo' (FEX___96)
 *
 */
public class JsonArray extends JsonValue<List<JsonValue<?>>> {
	
	public JsonArray(){
		value = new ArrayList<>();
	}
	
	public JsonArray(int size){
		value = new ArrayList<>(size);
	}

	public JsonArray(Object... elms){
		this();
		for(Object elm : elms) add(new JsonValue<>(elm));
	}
	
	public boolean add(JsonValue<?> elm){
		return value.add(elm);
	}
	
	public boolean rem(JsonValue<?> elm){
		return value.remove(elm);
	}
	
	public JsonValue<?> get(int index){
		return value.get(index);
	}
	
	public JsonArray getArray(int index){
		return value.get(index).asArray();
	}
	
	public JsonMap getMap(int index){
		return value.get(index).asMap();
	}
	
	public JsonValue<?> rem(int index){
		return value.remove(index);
	}
	
	public JsonValue<?> set(int index, JsonValue<?> elm){
		return value.set(index, elm);
	}
	
	public boolean contains(JsonValue<?> val){
		return value.contains(val);
	}
	
	@Override
	public boolean isArray(){
		return true;
	}
	
	@Override
	public boolean isValue(){
		return false;
	}
	
	public boolean add(String val){
		return add(new JsonValue<String>(val));
	}
	
	public boolean add(byte val){
		return add(new JsonValue<Byte>(val));
	}
	
	public boolean add(char val){
		return add(new JsonValue<Character>(val));
	}
	
	public boolean add(short val){
		return add(new JsonValue<Short>(val));
	}
	
	public boolean add(int val){
		return add(new JsonValue<Integer>(val));
	}
	
	public boolean add(long val){
		return add(new JsonValue<Long>(val));
	}
	
	public boolean add(float val){
		if(val == 0) val = 0;
		return add(new JsonValue<Float>(val));
	}
	
	public boolean add(double val){
		return add(new JsonValue<Double>(val));
	}
	
	public boolean add(boolean val){
		return add(new JsonValue<Boolean>(val));
	}
	
	public boolean addArray(){
		return add(new JsonArray());
	}
	
	public boolean addMap(){
		return add(new JsonMap());
	}
	
	public int size(){
		return value.size();
	}
	
	public boolean empty(){
		return value.size() == 0;
	}
	
	public boolean not_empty(){
		return value.size() > 0;
	}

	public List<JsonValue<?>> elements(){
		return value;
	}
	
	@Override
	public String toString(){
		return JsonHandler.toString(this);
	}

	@Override
	public JsonArray copy(){
		JsonArray arr = new JsonArray();
		for(JsonValue elm : elements()){
			arr.add(elm.copy());
		}
		return arr;
	}

	public String[] toStringArray(){
		String[] str = new String[value.size()];
		for(int i = 0; i < str.length; i++) str[i] = value.get(i).string_value();
		return str;
	}

	public int[] toIntegerArray(){
		int[] arr = new int[value.size()];
		for(int i = 0; i < arr.length; i++) arr[i] = value.get(i).integer_value();
		return arr;
	}

	public float[] toFloatArray(){
		float[] arr = new float[value.size()];
		for(int i = 0; i < arr.length; i++) arr[i] = value.get(i).float_value();
		return arr;
	}

	public ArrayList<String> toStringList(){
		ArrayList<String> list = new ArrayList();
		for(JsonValue val : value) list.add(val.string_value());
		return list;
	}

	public ArrayList<Integer> toIntegerList(){
		ArrayList<Integer> list = new ArrayList();
		for(JsonValue val : value) list.add(val.integer_value());
		return list;
	}

	public ArrayList<Float> toFloatList(){
		ArrayList<Float> list = new ArrayList();
		for(JsonValue val : value) list.add(val.float_value());
		return list;
	}

	public static class Flat extends JsonArray {

		public Flat(){
			super();
		}

		public Flat(int size){
			super(size);
		}

		public Flat(Object... elms){
			super(elms);
		}

	}

}
