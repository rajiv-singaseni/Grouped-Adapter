/**
 * 
 * Copyright (C) 2011 Webileapps
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

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * @author rajiv@webileapps.com
 *
 */
public class MainActivity extends ListActivity {

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getListView().setPadding(5, 0, 5, 0);
        getListView().setVerticalScrollBarEnabled(false);
        getListView().setDivider(null);
        TextView headerTextView = new TextView(this);
        headerTextView.setText("Section Header");
        getListView().addHeaderView(headerTextView);
        
        TextView footerTextView = new TextView(this);
        footerTextView.setText("Section footer");
		getListView().addFooterView(footerTextView);
		
        setListAdapter(
			new GroupedAdapterWrapper(
				new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1){
					public int getCount() {
						return 5;
					};
					
					public String getItem(int position) {
						return String.format("Cell %d",position+1);
					};
				}
			)
		);
	}
	
}
