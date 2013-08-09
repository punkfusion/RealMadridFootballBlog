package com.android.rmfb.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="rss", strict = false)
public class RssRoot {
	@Element
	public Channel channel;
}
