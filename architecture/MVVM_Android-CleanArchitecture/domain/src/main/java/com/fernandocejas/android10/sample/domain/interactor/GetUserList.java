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
package com.fernandocejas.android10.sample.domain.interactor;


import android.content.Context;

import com.fernandocejas.android10.sample.data.dto.User;
import com.fernandocejas.android10.sample.domain.interactor.repository.UserDataRepository;
import com.fernandocejas.android10.sample.domain.interactor.repository.UserRepository;

import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving a collection of all {@link User}.
 */
public class GetUserList extends UseCase {

	UserRepository userRepository;

	public GetUserList(Context appContext) {
		this(new UserDataRepository(appContext));
	}

	public GetUserList(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public Observable buildUseCaseObservable() {
		return this.userRepository.users();
	}
}
