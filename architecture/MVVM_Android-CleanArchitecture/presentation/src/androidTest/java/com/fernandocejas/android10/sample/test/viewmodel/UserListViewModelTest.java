package com.fernandocejas.android10.sample.test.viewmodel;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.fernandocejas.android10.sample.data.dto.User;
import com.fernandocejas.android10.sample.presentation.AndroidApplication;
import com.fernandocejas.android10.sample.presentation.R;
import com.fernandocejas.android10.sample.presentation.mapper.UserModelDataMapper;
import com.fernandocejas.android10.sample.presentation.model.UserModel;
import com.fernandocejas.android10.sample.presentation.navigation.ActivityNavigator;
import com.fernandocejas.android10.sample.presentation.view.activity.UserDetailsActivity;
import com.fernandocejas.android10.sample.presentation.view.activity.UserListActivity;
import com.fernandocejas.android10.sample.presentation.view.adapter.UsersAdapter;
import com.fernandocejas.android10.sample.presentation.view.fragment.UserListFragment;
import com.fernandocejas.android10.sample.presentation.viewmodel.UserListViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by rocko on 15-11-6.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class UserListViewModelTest {

	private UserModelDataMapper userModelDataMapper;
	private List<User> fakeUsersList;

	private UserListFragment userListFragment;
	private UserListViewModel userListViewModel;
	private UsersAdapter usersAdapter;

	@Rule
	public IntentsTestRule<UserListActivity> mActivityRule = new IntentsTestRule<>(
			UserListActivity.class);

	@Before
	public void setUp() {
//		intending(not(isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));

		userModelDataMapper = new UserModelDataMapper();
		fakeUsersList = makeFakeUsers();

		userListFragment = (UserListFragment) mActivityRule.getActivity().getFragmentManager().findFragmentById(R.id.fragmentUserList);
		userListViewModel = userListFragment.getViewModel();

		Collection<UserModel> userModelsCollection = userModelDataMapper.transformUsers(fakeUsersList);
		usersAdapter = new UsersAdapter(mActivityRule.getActivity(), userModelsCollection);
		usersAdapter.setOnItemClickListener(userListViewModel.onUserItemClick());
		userListViewModel.showContentList(usersAdapter);
	}

	@Test
	public void testShowContentList() throws Exception {
		assertEquals(fakeUsersList.size(), usersAdapter.getItemCount());

		User user = fakeUsersList.get(0);
		onView(withText(user.getFullName())).check(matches(isDisplayed()));
	}

	@Test
	public void testOnUserItemClick() throws Exception {
		User user = fakeUsersList.get(1);
		onView(withText(user.getFullName())).perform(click());

		intending(hasExtra(UserDetailsActivity.INTENT_EXTRA_PARAM_USER_ID, user.getUserId() + ""));
	}


	private List<User> makeFakeUsers() {
		List<User> fakeUsersList = new ArrayList<>();
		for (int i = 0; i < 15; i++) {
			User user = new User(i);
			user.setFullName("NAME: " + i);
			user.setEmail(i + "@fake.com");
			user.setFollowers(i);
			user.setDescription("Description: " + i);
			fakeUsersList.add(user);
		}

		return fakeUsersList;
	}
}
