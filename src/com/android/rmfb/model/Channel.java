package com.android.rmfb.model;

import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "channel", strict=false)
public class Channel {
	@ElementList(inline = true)
	public List<PostItem> items;
}
