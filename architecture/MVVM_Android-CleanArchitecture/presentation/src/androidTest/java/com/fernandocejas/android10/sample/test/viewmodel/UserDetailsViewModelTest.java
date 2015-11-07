package com.fernandocejas.android10.sample.test.viewmodel;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.fernandocejas.android10.sample.data.dto.User;
import com.fernandocejas.android10.sample.presentation.R;
import com.fernandocejas.android10.sample.presentation.view.activity.UserDetailsActivity;
import com.fernandocejas.android10.sample.presentation.view.fragment.UserDetailsFragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by rocko on 15-11-6.
 */
@RunWith(AndroidJUnit4.class)
public class UserDetailsViewModelTest {

	private final static int FAKE_USER_ID = 1;

	private final static String FAKE_EMAIL = "FAKE_EMAIL@fake.com";
	private final static String FAKE_DESCRIPTION = "FAKE_DESCRIPTION";
	private final static int FAKE_FOLLOWERS = 300;

	private User fakeUser;
	private UserDetailsFragment detailsFragment;


	@Rule
	public ActivityTestRule<UserDetailsActivity> mActivityRule = new ActivityTestRule(UserDetailsActivity.class);

	@Before
	public void setUp() {
		fakeUser = makeFakeUser();
		detailsFragment = (UserDetailsFragment) mActivityRule.getActivity().getFragment(UserDetailsFragment.TAG);
	}

	@Test
	public void testShowUser() throws Exception {
		detailsFragment.getViewModel().showUserDetails(fakeUser);
//		UserDetailsBinding userDetailsBinding = detailsFragment.getBinding();

		onView(withId(R.id.tv_email)).check(matches(withText(FAKE_EMAIL)));
		onView(withId(R.id.tv_followers)).check(matches(withText(String.valueOf(FAKE_FOLLOWERS))));
		onView(withId(R.id.tv_description)).check(matches(withText(FAKE_DESCRIPTION)));
	}


	private User makeFakeUser() {
		User fakeUser = new User(FAKE_USER_ID);
		fakeUser.setEmail(FAKE_EMAIL);
		fakeUser.setFollowers(FAKE_FOLLOWERS);
		fakeUser.setDescription(FAKE_DESCRIPTION);
		return fakeUser;
	}
}
