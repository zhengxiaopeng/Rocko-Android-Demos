/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.sample.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Window;

import com.fernandocejas.android10.sample.presentation.R;

/**
 * Activity that shows a list of Users.
 */
public class UserListActivity extends BaseActivity{

	public static Intent getCallingIntent(Context context) {
		return new Intent(context, UserListActivity.class);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

		DataBindingUtil.setContentView(this, R.layout.user_list_activity);
	}

}
