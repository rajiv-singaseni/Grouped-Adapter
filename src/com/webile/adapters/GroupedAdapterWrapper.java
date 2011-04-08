/*
 * Copyright (C) 2010 WebileApps
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.webile.adapters;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
/**
 * 
 * @author "rajiv@webileapps.com"
 *
 */
public class GroupedAdapterWrapper extends BaseAdapter {

	private ListAdapter mAdapter;
	
	private int mTopCorneredResourceId = R.drawable.top_cornered;
	private int mNoCorneredResourceId = R.drawable.no_cornered;
	private int mBottomCorneredResourceId = R.drawable.bottom_cornered;
	private int mFullyCorneredResourceId = R.drawable.full_cornered;
	
	public GroupedAdapterWrapper(ListAdapter adapter) {
		this.mAdapter = adapter;
		this.mAdapter.registerDataSetObserver(new CascadedDataSetObserver());
	}
	
	public GroupedAdapterWrapper(ListAdapter adapter, 
			int topCorneredResourceId,
			int noCorneredResourceId,
			int bottomCorneredResourceId,
			int fullyCorneredResourceId) {
		this(adapter);
		this.mTopCorneredResourceId = topCorneredResourceId;
		this.mNoCorneredResourceId = noCorneredResourceId;
		this.mBottomCorneredResourceId = bottomCorneredResourceId;
		this.mFullyCorneredResourceId = fullyCorneredResourceId;
	}
	
	
	@Override
	public int getCount() {
		return mAdapter.getCount();
	}

	@Override
	public Object getItem(int position) {
		return mAdapter.getItem(position);
	}

	@Override
	public long getItemId(int position) {
		return mAdapter.getItemId(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = mAdapter.getView(position, convertView, parent);
		int count = getCount();
		if(count == 1) {
			view.setBackgroundResource(mFullyCorneredResourceId);
		} else if(position == 0) {
			view.setBackgroundResource(mTopCorneredResourceId);			
		} else if(position == count-1) {
			view.setBackgroundResource(mBottomCorneredResourceId);
		} else {
			view.setBackgroundResource(mNoCorneredResourceId);
		}
		return view;
	}
	
	@Override
	public int getViewTypeCount() {
		return mAdapter.getViewTypeCount();
	}
	
	@Override
	public int getItemViewType(int position) {
		return mAdapter.getItemViewType(position);
	}
	
	@Override
	public boolean areAllItemsEnabled() {
		return mAdapter.areAllItemsEnabled();
	}
	
	@Override
	public boolean isEnabled(int position) {
		return mAdapter.isEnabled(position);
	}
	
	private class CascadedDataSetObserver extends DataSetObserver {
		@Override
		public void onChanged() {
			notifyDataSetChanged();
		}
		
		@Override
		public void onInvalidated() {
			notifyDataSetInvalidated();
		}
	}
}