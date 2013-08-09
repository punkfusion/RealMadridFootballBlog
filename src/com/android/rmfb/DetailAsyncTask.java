package com.android.rmfb;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.webkit.WebView.FindListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class DetailAsyncTask extends AsyncTask<String, Void, String[]> {
		
		private LinearLayout viewDetail;
		private ImageView imageViewer;
		private Context context;
		private int n = 0;
		private String imageURL = "";
		
		public DetailAsyncTask(LinearLayout postDetail, ImageView imageViewer, Context context)
		{
			this.viewDetail=postDetail;
			this.imageViewer = imageViewer;
			this.context=context;
			
		}
		@Override
		protected String[] doInBackground(String... arg0) {
			String url = "";
			if (arg0.length > 0) {
				url = arg0[0];
			}

			Document feed = null;
			try {
				feed = Jsoup.connect(url).get();
			} catch (Exception e) {
				e.printStackTrace();
				return new String[0];
			}	
			String[] content = new String[feed.select("p").size()];
			if(feed == null || feed.select("p") == null)
				return content;
			for(Element paragraph:feed.select("p")) {
				if (paragraph.parent().attr("class").equals("post-content clear-block") && paragraph.children().size() ==  0 && !"".equals(paragraph.text()))
				{

					System.out.println(paragraph.parent().attr("class").equals("post-content clear-block") && paragraph.children().size() ==  0 && !"".equals(paragraph.text()));
					content[n] = paragraph.text();
					n++;
				}
			}

			for(Element image:feed.select("img"))
			{
				if(isNotEmpty("width", image) && isNotEmpty("height", image))
				{
					if(Integer.decode(image.attr("width")) >=300 && Integer.decode(image.attr("height")) >= 200)
					{	
						imageURL = image.attr("src");
					}
				}
			}
			
			Log.i("details", imageURL);
			
			
			return content;
		}
		private boolean isNotEmpty(String attribute, Element e)
		{
			return e.attr(attribute) != null && !"".equals(e.attr(attribute));
		}
		@Override
		protected void onPostExecute(String[] result)
		{
			System.out.println(n);
			for(int i=0; i<n; i++)
			{
				final TextView newRowView = new TextView(context);
				newRowView.setText(result[i]);
				newRowView.setHapticFeedbackEnabled(true);
				newRowView.setTextSize(25);
				newRowView.setPadding(25, 25, 25, 25);
				viewDetail.addView(newRowView);
			}
			if(!"".equals(imageURL))
			{
				ImageLoader imageLoader = ImageLoader.getInstance();
				imageLoader.displayImage(imageURL, imageViewer);
			}			
		}
}
