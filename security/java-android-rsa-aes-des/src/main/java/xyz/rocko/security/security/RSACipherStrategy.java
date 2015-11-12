/*
 * Copyright 2015 Rocko (http://rocko.xyz) <rocko.zxp@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package xyz.rocko.security.security;

import java.io.InputStream;
import java.security.PrivateKey;
import java.security.PublicKey;

import xyz.rocko.security.security.util.RSAUtils;


public class RSACipherStrategy extends CipherStrategy {

	private PublicKey mPublicKey;
	private PrivateKey mPrivateKey;

	public void initPublicKey(String publicKeyContentStr) {
		try {
			mPublicKey = RSAUtils.loadPublicKey(publicKeyContentStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initPublicKey(InputStream publicKeyIs) {
		try {
			mPublicKey = RSAUtils.loadPublicKey(publicKeyIs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initPrivateKey(String privateKeyContentStr) {
		try {
			mPrivateKey = RSAUtils.loadPrivateKey(privateKeyContentStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initPrivateKey(InputStream privateIs) {
		try {
			mPrivateKey = RSAUtils.loadPrivateKey(privateIs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String encrypt(String content) {
		if (mPublicKey == null) {
			throw new NullPointerException("PublicKey is null, please init it first");
		}
		byte[] encryptByte = RSAUtils.encryptData(content.getBytes(), mPublicKey);

		return encodeConvert(encryptByte);
	}

	@Override
	public String decrypt(String encryptContent) {
		if (mPrivateKey == null) {
			throw new NullPointerException("PrivateKey is null, please init it first");
		}
		byte[] encryptByte = decodeConvert(encryptContent);
		byte[] decryptByte = RSAUtils.decryptData(encryptByte, mPrivateKey);

		return new String(decryptByte);
	}

}
