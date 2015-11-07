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
package com.fernandocejas.android10.sample.data.entity.mapper;

import com.fernandocejas.android10.sample.data.dto.User;
import com.fernandocejas.android10.sample.data.entity.UserEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Mapper class used to transform {@link UserEntity} (in the data layer) to {@link User} in the
 * domain layer.
 */
public class UserEntityDataMapper {

	public UserEntityDataMapper() {
	}

	/**
	 * Transform a {@link UserEntity} into an {@link User}.
	 *
	 * @param userEntity Object to be transformed.
	 * @return {@link User} if valid {@link UserEntity} otherwise null.
	 */
	public User transform(UserEntity userEntity) {
		User user = null;
		if (userEntity != null) {
			user = new User(userEntity.getUserId());
			user.setCoverUrl(userEntity.getCoverUrl());
			user.setFullName(userEntity.getFullname());
			user.setDescription(userEntity.getDescription());
			user.setFollowers(userEntity.getFollowers());
			user.setEmail(userEntity.getEmail());
		}

		return user;
	}

	/**
	 * Transform a List of {@link UserEntity} into a Collection of {@link User}.
	 *
	 * @param userEntityCollection Object Collection to be transformed.
	 * @return {@link User} if valid {@link UserEntity} otherwise null.
	 */
	public List<User> transform(Collection<UserEntity> userEntityCollection) {
		List<User> userList = new ArrayList<>(20);
		User user;
		for (UserEntity userEntity : userEntityCollection) {
			user = transform(userEntity);
			if (user != null) {
				userList.add(user);
			}
		}

		return userList;
	}
}
