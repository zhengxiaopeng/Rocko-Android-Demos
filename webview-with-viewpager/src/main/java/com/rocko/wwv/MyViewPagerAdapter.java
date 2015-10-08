/**
 * Copyright 2015 Rocko(rocko.xyz) <rocko.zxp@gmail.com>
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rocko.wwv;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

/**
 * @author: Rocko
 */
public class MyViewPagerAdapter extends FragmentPagerAdapter {

	final int PAGE_COUNT = 3;
	private String tabTitles[] = new String[] { "Tab1", "Tab2", "Tab3" };

	@Override
	public CharSequence getPageTitle(int position) {
		return tabTitles[position];
	}

	public MyViewPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		return new WebViewsFragment();
	}

	@Override
	public int getCount() {
		return PAGE_COUNT;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// Do nothing
	}
}
