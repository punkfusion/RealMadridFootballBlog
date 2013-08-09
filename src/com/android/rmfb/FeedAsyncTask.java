package com.android.rmfb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.rmfb.model.PostItem;
import com.android.rmfb.model.RssRoot;

public class FeedAsyncTask extends AsyncTask<String, Void, String> {
	
	private ListView viewList;
	private Context context;
	ArrayAdapter<PostItem> adapter;
	public FeedAsyncTask(ListView postList, Context context)
	{
		this.viewList=postList;
		this.context=context;
		
	}
	@Override
	protected String doInBackground(String... arg0) {
		String url = "";
		if (arg0.length > 0) {
			url = arg0[0];
		}

		String feed = null;
		try {
			feed = convertStreamToString(new URL(url).openStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return feed;
	}
	
	@Override
	protected void onPostExecute(String result)
	{
		
		Serializer serializer = new Persister();
		try {
			RssRoot rss = serializer.read(RssRoot.class, result);
			for(PostItem item : rss.channel.items) {
				System.out.println(item.link);
			}
			adapter = new ArrayAdapter<PostItem>(context, R.layout.list_row, R.id.title_name, rss.channel.items);
			viewList.setAdapter(adapter);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String convertStreamToString(InputStream is) {
		if (is != null) {
			StringBuilder sb = new StringBuilder();
			String line;
			try {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(is, "UTF-8"));
				while ((line = reader.readLine()) != null) {
					sb.append(line).append("\n");
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return sb.toString();
		} else {
			return "";
		}
	}

}
