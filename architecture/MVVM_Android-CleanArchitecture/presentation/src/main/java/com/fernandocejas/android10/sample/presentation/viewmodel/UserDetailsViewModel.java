package com.fernandocejas.android10.sample.presentation.viewmodel;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;

import com.fernandocejas.android10.sample.data.dto.User;
import com.fernandocejas.android10.sample.domain.interactor.DefaultSubscriber;
import com.fernandocejas.android10.sample.domain.interactor.GetUserDetails;
import com.fernandocejas.android10.sample.presentation.AndroidApplication;
import com.fernandocejas.android10.sample.presentation.mapper.UserModelDataMapper;
import com.fernandocejas.android10.sample.presentation.model.UserModel;

/**
 * Created by rocko on 15-11-5.
 */
public class UserDetailsViewModel extends LoadingViewModel {

	public final ObservableBoolean showUserDetails = new ObservableBoolean(true);
	public final ObservableField<UserModel> userObs = new ObservableField<>();

	GetUserDetails getUserDetailsUseCase = new GetUserDetails(AndroidApplication.getContext());
	UserModelDataMapper userModelDataMapper = new UserModelDataMapper();

	@BindView
	@Override
	public void showLoading() {
//		super.showLoading(); // show Details
		showRetry.set(false);
		showLoading.set(true);
		showUserDetails.set(true);
	}

	@BindView
	@Override
	public void showRetry() {
		super.showRetry();
		showUserDetails.set(false);
	}

	@BindView
	public void showUserDetails(UserModel userModel) {
		showLoading.set(false);
		showRetry.set(false);
		showUserDetails.set(true);
		userObs.set(userModel);
	}


	@Command
	public void loadUserDetailsCommand(int userId) {
		showLoading();
		getUserDetailsUseCase.setUserId(userId);
		getUserDetailsUseCase.execute(new DefaultSubscriber<User>(){
			@Override
			public void onNext(User user) {
				showUserDetails(userModelDataMapper.transformUser(user));
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
				loadUserDetailsCommand(userObs.get().getUserId());
			}
		};
	}
}
