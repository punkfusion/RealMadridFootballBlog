package com.android.rmfb;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.ImageLoader;

public class DetailFragment extends Fragment {

  private LinearLayout blogLayout;
  private ImageView blogImage;
  private String linkString;
  @Override
  public void onCreate (Bundle savedInstanceState)
  {
	  super.onCreate(savedInstanceState);
	  linkString = getActivity().getIntent().getStringExtra("OPEN_DETAIL");
	  
  }
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_post_detail,
        container, false);
    blogLayout = (LinearLayout) view.findViewById(R.id.detailsLayout);
    blogImage = (ImageView) view.findViewById(R.id.detailsImage);
    DetailAsyncTask postDetail = new DetailAsyncTask(blogLayout, blogImage,getActivity());
    postDetail.execute(linkString);
    return view;
  }

} 