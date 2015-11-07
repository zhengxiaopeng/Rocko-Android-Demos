package com.fernandocejas.android10.sample.presentation.viewmodel;


import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;

import com.fernandocejas.android10.sample.data.dto.User;
import com.fernandocejas.android10.sample.domain.interactor.DefaultSubscriber;
import com.fernandocejas.android10.sample.domain.interactor.GetUserList;
import com.fernandocejas.android10.sample.domain.interactor.UseCase;
import com.fernandocejas.android10.sample.presentation.AndroidApplication;
import com.fernandocejas.android10.sample.presentation.mapper.UserModelDataMapper;
import com.fernandocejas.android10.sample.presentation.model.UserModel;
import com.fernandocejas.android10.sample.presentation.navigation.ActivityNavigator;
import com.fernandocejas.android10.sample.presentation.view.activity.UserDetailsActivity;
import com.fernandocejas.android10.sample.presentation.view.adapter.UsersAdapter;

import java.util.Collection;
import java.util.List;

/**
 * Created by rocko on 15-11-5.
 */
public class UserListViewModel extends LoadingViewModel {
	private final static String TAG = UserListViewModel.class.getSimpleName();

	public final ObservableBoolean showContentList = new ObservableBoolean(false);
	public final ObservableField<UsersAdapter> usersListAdapter = new ObservableField<>();

	UseCase getUserList = new GetUserList(AndroidApplication.getContext());
	UserModelDataMapper userModelDataMapper = new UserModelDataMapper();


	@BindView
	@Override
	public void showLoading() {
		super.showLoading();
		showContentList.set(false);
	}

	@BindView
	@Override
	public void showRetry() {
		super.showRetry();
		showContentList.set(false);
	}

	@BindView
	public void showContentList(UsersAdapter usersAdapter) {
		showLoading.set(false);
		showRetry.set(false);
		showContentList.set(true);
		usersListAdapter.set(usersAdapter);
	}

	@BindView
	public void showMoreContent() {
		// userAdapter
	}

	@Command
	public void loadUsersCommand() {
		if (showLoading.get()) {
			return;
		}
		showLoading();
		getUserList.execute(new DefaultSubscriber<List<User>>() {
			@Override
			public void onNext(List<User> users) {
				Collection<UserModel> userModelsCollection = userModelDataMapper.transformUsers(users);
				UsersAdapter usersAdapter = new UsersAdapter(AndroidApplication.getContext(), userModelsCollection);
				usersAdapter.setOnItemClickListener(onUserItemClick());
				showContentList(usersAdapter);
			}

			@Override
			public void onError(Throwable e) {
				showRetry();
			}

		});
	}

	@Override
	public View.OnClickListener onRetryClick() {
		return new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				loadUsersCommand();

			}
		};
	}

	public UsersAdapter.OnItemClickListener onUserItemClick() {
		return new UsersAdapter.OnItemClickListener() {
			@Override
			public void onUserItemClicked(UserModel userModel) {
				Intent intent = UserDetailsActivity.getCallingIntent(AndroidApplication.getInstance().getCurrentActivity(), userModel.getUserId());
				ActivityNavigator.navigateTo(UserDetailsActivity.class, intent);
			}
		};
	}

}
