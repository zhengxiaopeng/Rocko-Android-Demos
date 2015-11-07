/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
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
package com.fernandocejas.android10.sample.presentation;

import android.app.Activity;
import android.app.Application;
import android.support.annotation.NonNull;

/**
 * Android Main Application
 */
public class AndroidApplication extends Application {

	private static AndroidApplication instance;

	private Activity mCurrentActivity;

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
	}

	public static AndroidApplication getContext() {
		return instance;
	}

	public static AndroidApplication getInstance() {
		return instance;
	}

	public Activity getCurrentActivity() {
		return mCurrentActivity;
	}

	public void setCurrentActivity(@NonNull Activity mCurrentActivity) {
		this.mCurrentActivity = mCurrentActivity;
	}

}
