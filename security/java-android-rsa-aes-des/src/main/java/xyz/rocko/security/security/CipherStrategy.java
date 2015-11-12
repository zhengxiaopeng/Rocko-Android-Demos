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

import xyz.rocko.security.util.Base64Utils;

/**
 * 加解密处理的抽象接口
 */
public abstract class CipherStrategy {

	public final static String CHARSET = "UTF-8";

	/**
	 * 将加密内容的 Base64 编码转换为二进制内容
	 * @param str
	 * @return
	 */
	protected byte[] decodeConvert(String str) {
		return Base64Utils.decode(str);
	}

	/**
	 * 对加密后的二进制结果转换为 Base64 编码
	 * @param bytes
	 * @return
	 */
	protected String  encodeConvert(byte[] bytes) {
		return Base64Utils.encode(bytes);
	}

	/**
	 * 对字符串进行加密
	 *
	 * @param content 需要加密的字符串
	 * @return
	 */
	public abstract String encrypt(String content);

	/**
	 * 对字符串进行解密
	 *
	 * @param encryptContent 加密内容的 Base64 编码
	 * @return
	 */
	public abstract String decrypt(String encryptContent);

	// 文件、流等
}
