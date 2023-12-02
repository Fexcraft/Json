package net.fexcraft.app.json;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

/**
 * 
 * @author Ferdinand Calo' (FEX___96)
 *
 */
public class JsonMap extends JsonValue<Map<String, JsonValue<?>>> {
	
	public JsonMap(){
		value = new LinkedHashMap<>();
	}

	public JsonMap(Object... elms){
		this();
		for(int i = 0; i < elms.length; i += 2){
			if(i + 1 >= elms.length) break;
			if(elms[i + 1] instanceof JsonValue){
				add(elms[i].toString(), (JsonValue<?>)elms[i + 1]);
			}
			else{
				add(elms[i].toString(), new JsonValue<>(elms[i + 1]));
			}
		}
	}
	
	public <V> JsonValue<V> get(String key){
		return (JsonValue<V>)value.get(key);
	}
	
	public JsonArray getArray(String key){
		return value.get(key).asArray();
	}
	
	public JsonArray getArray(String key, int size){
		if(!value.containsKey(key)){
			value.put(key, new JsonArray(size));
		}
		return value.get(key).asArray();
	}

	public List<JsonValue<?>> getArrayElements(String key){
		return getArray(key).elements();
	}
	
	public JsonMap getMap(String key){
		if(!value.containsKey(key)) addMap(key);
		return value.get(key).asMap();
	}
	
	public JsonValue<?> add(String key, JsonValue<?> elm){
		return value.put(key, elm);
	}
	
	public JsonValue<?> rem(String key){
		return value.remove(key);
	}
	
	public boolean has(String key){
		return value.containsKey(key);
	}
	
	public boolean has(boolean any, String... keys){
		for(String key : keys){
			if(value.containsKey(key)){
				if(any) return true;
			}
			else if(!any) return false;
		}
		return !any;
	}
	
	public boolean contains(JsonValue<?> val){
		return value.containsValue(val);
	}
	
	@Override
	public boolean isMap(){
		return true;
	}
	
	@Override
	public boolean isValue(){
		return false;
	}
	
	public JsonValue<?> add(String key, String val){
		return add(key, new JsonValue<String>(val));
	}
	
	public JsonValue<?> add(String key, byte val){
		return add(key, new JsonValue<Byte>(val));
	}
	
	public JsonValue<?> add(String key, char val){
		return add(key, new JsonValue<Character>(val));
	}
	
	public JsonValue<?> add(String key, short val){
		return add(key, new JsonValue<Short>(val));
	}
	
	public JsonValue<?> add(String key, int val){
		return add(key, new JsonValue<Integer>(val));
	}
	
	public JsonValue<?> add(String key, long val){
		return add(key, new JsonValue<Long>(val));
	}
	
	public JsonValue<?> add(String key, float val){
		return add(key, new JsonValue<Float>(val));
	}
	
	public JsonValue<?> add(String key, double val){
		return add(key, new JsonValue<Double>(val));
	}

	public JsonValue<?> add(String key, boolean val){
		return add(key, new JsonValue<Boolean>(val));
	}
	
	public JsonValue<?> addArray(String key){
		return add(key, new JsonArray());
	}
	
	public JsonValue<?> addMap(String key){
		return add(key, new JsonMap());
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

	public Set<Entry<String, JsonValue<?>>> entries(){
		return value.entrySet();
	}
	
	@Override
	public String toString(){
		return JsonHandler.toString(this);
	}

	public <V> V get(String key, V def){
		return value.containsKey(key) ? value.get(key).value() : def;
	}

	public float getFloat(String key, float def){
		return value.containsKey(key) ? value.get(key).float_value() : def;
	}

	public Float getFloat(String key, Float def){
		return value.containsKey(key) ? value.get(key).float_value() : def;
	}

	public int getInteger(String key, int def){
		return value.containsKey(key) ? value.get(key).integer_value() : def;
	}

	public long getLong(String key, long def){
		return value.containsKey(key) ? value.get(key).long_value() : def;
	}

	public long getLongTime(String key){
		return value.containsKey(key) ? value.get(key).long_value() : new Date().getTime();
	}

	public String getString(String key, String def){
		return value.containsKey(key) ? value.get(key).string_value() : def;
	}

	public boolean getBoolean(String key, boolean def){
		return value.containsKey(key) ? value.get(key).value() : def;
	}

	public UUID getUUID(String key, UUID def){
		return value.containsKey(key) ? UUID.fromString(value.get(key).string_value()) : def;
	}

	@Override
	public JsonMap copy(){
		JsonMap map = new JsonMap();
		for(Entry<String, JsonValue<?>> entry : entries()){
			map.add(entry.getKey(), entry.getValue().copy());
		}
		return map;
	}

}
