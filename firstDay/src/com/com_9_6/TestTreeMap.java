package com_9_6;


import org.junit.Test;

import java.lang.reflect.Field;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Administrator on 2018/9/6.
 */
public class TestTreeMap {

	/**
	 *
	 */

	public static void travelMap(Map map){
		for(Object obj : map.entrySet()){
			Map.Entry e = (Map.Entry) obj;
			Object key = e.getKey() ;
			Object value = e.getValue();
			System.out.println(key + " : " + value);
		}
	}

	@Test
	public void testNewTree2() throws Exception {

		TreeMap<Integer, String> map = new TreeMap<Integer, String>();
		map.put(1 , "tom1");
		map.put(2 , "tom1");
		map.put(3 , "tom1");
		map.put(4 , "tom1");
		map.put(5 , "tom1");

		//preOrderTravel(getRoot(map));
		//lastOrderTravel(getRoot(map));
		//middleOrderTravel(getRoot(map));
		ArrayList<Object> entrys = new ArrayList<Object>();
		entrys.add(getRoot(map));
		getchild(getRoot(map),entrys);
		for (Object entry : entrys) {
			System.out.print("1"+getKey((Map.Entry) entry));
		}


	}

	public static Map.Entry getRoot(TreeMap map) throws Exception {
		Field f = TreeMap.class.getDeclaredField("root") ;
		f.setAccessible(true);
		Object o = f.get(map) ;
		return (Map.Entry) o;
	}

	//得到e节点的k值
	public static Object getKey(Map.Entry e) throws Exception {
		Field f = e.getClass().getDeclaredField("key") ;
		f.setAccessible(true);
		Object o = f.get(e) ;
		return o;
	}

	public static Map.Entry getLeft(Map.Entry e) throws Exception {
		Field f = e.getClass().getDeclaredField("left") ;
		f.setAccessible(true);
		Object o = f.get(e) ;
		return (Map.Entry) o;
	}

	public static Object getLeftKey(Map.Entry e) throws Exception {
		Field f = e.getClass().getDeclaredField("left") ;
		f.setAccessible(true);
		Object o = f.get(e) ;
		if(o != null){
			Field k = o.getClass().getDeclaredField("key") ;
			k.setAccessible(true);
			return k.get(o) ;
		}
		return null;
	}

	public static Map.Entry getRight(Map.Entry e) throws Exception {
		Field f = e.getClass().getDeclaredField("right") ;
		f.setAccessible(true);
		Object o = f.get(e) ;
		return (Map.Entry) o;
	}

	public static Object getRightKey(Map.Entry e) throws Exception {
		Field f = e.getClass().getDeclaredField("right");
		f.setAccessible(true);
		Object o = f.get(e);
		if (o != null) {
			Field k = o.getClass().getDeclaredField("key");
			k.setAccessible(true);
			return k.get(o);
		}
		return null;
	}

	public static void preOrderTravel(Map.Entry e) throws Exception {
		if(e != null){
			System.out.println(getKey(e));
			preOrderTravel(getLeft(e)) ;
			preOrderTravel(getRight(e)) ;
		}
	}
	public static void lastOrderTravel(Map.Entry e) throws Exception {
		if(e != null){
			preOrderTravel(getLeft(e)) ;
			preOrderTravel(getRight(e)) ;
			System.out.println(getKey(e));
		}
	}
	public static void middleOrderTravel(Map.Entry e) throws Exception {
		if(e != null){
			preOrderTravel(getLeft(e)) ;
			System.out.println(getKey(e));
			preOrderTravel(getRight(e)) ;

		}
	}
	public static void getchild(Map.Entry e,ArrayList entrys) throws Exception {
		ArrayList<Map.Entry> ent = new ArrayList<Map.Entry>();
		if(e != null){
			if (getLeft(e)!=null){
				ent.add(getLeft(e));
			}
			if (getLeft(e)!=null){
				ent.add(getRight(e));
			}
			if (ent.size()!=0){
				for (Map.Entry entry : ent) {
					entrys.add(entry);
				}
			}

		}
	}
}

