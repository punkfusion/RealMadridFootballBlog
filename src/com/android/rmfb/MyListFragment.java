package com.android.rmfb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.android.rmfb.model.PostItem;

public class MyListFragment extends Fragment implements OnItemClickListener {
  
  private ListView postList;
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_post_list,
        container, false);
    postList = (ListView) view.findViewById(R.id.post_list);
    postList.setOnItemClickListener(this);
    FeedAsyncTask fetchPosts = new FeedAsyncTask(postList, getActivity());
    fetchPosts.execute("http://www.realmadridfootballblog.com/feed");
    
    
    return view;
  }

@Override
public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
	
	PostItem item = (PostItem)postList.getAdapter().getItem(position);
	Intent intent = new Intent(getActivity(), DetailActivity.class);
	intent.putExtra("OPEN_DETAIL", item.link);
	startActivity(intent);
	
}
} 