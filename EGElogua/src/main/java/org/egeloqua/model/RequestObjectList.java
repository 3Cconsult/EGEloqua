package org.egeloqua.model;
import java.util.ArrayList;
public class RequestObjectList<T> {
	public ArrayList <T> elements = new ArrayList <T>();
	public Integer total;
	public Integer pageSize;
	public Integer page;
}
