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


import java.io.UnsupportedEncodingException;

import xyz.rocko.security.security.util.DESUtils;

public class DESCipherStrategy extends CipherStrategy {

	private String key;// 解密密码

	/**
	 * @param key 加解密的 key
	 */
	public DESCipherStrategy(String key) {
		this.key = key;
	}

	@Override
	public String encrypt(String content) {
		byte[] encryptByte = null;
		try {
			encryptByte = DESUtils.encrypt(content.getBytes(CHARSET), key);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encodeConvert(encryptByte);
	}

	@Override
	public String decrypt(String encryptContent) {
		byte[] encrypByte = decodeConvert(encryptContent);
		byte[] decryptByte = DESUtils.decrypt(encrypByte, key);
		String result = "";
		try {
			result = new String(decryptByte, CHARSET);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return result;
	}

}
