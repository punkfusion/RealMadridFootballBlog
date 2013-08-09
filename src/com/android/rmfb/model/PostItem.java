package com.android.rmfb.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="item", strict=false)
public class PostItem {
	@Element
	public String title;
	@Element
	public String link;
	@Element(name = "dc:creator", required = false)
	public String creator;
	
	public String toString() {
		return title;
	}

}
