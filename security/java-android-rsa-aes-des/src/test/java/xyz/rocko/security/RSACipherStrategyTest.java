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

package xyz.rocko.security;

import junit.framework.Assert;

import org.junit.Test;

import xyz.rocko.security.config.SecurityConfig;
import xyz.rocko.security.security.RSACipherStrategy;

/**
 * Created by rocko on 15-11-12.
 */
public class RSACipherStrategyTest {

	@Test
	public void testEncryptDecrypt() throws Exception {
		RSACipherStrategy cipherStrategy = new RSACipherStrategy();
		String source = "rocko";
		System.out.println("加密前密码：" + source);

		cipherStrategy.initPublicKey(SecurityConfig.RSA_PUCLIC_KEY);
		String encryptContent = cipherStrategy.encrypt(source);
		System.out.println("加密后密码：" + encryptContent);

		cipherStrategy.initPrivateKey(SecurityConfig.RSA_PRIVATE_KEY);
		String decryptContent = cipherStrategy.decrypt(encryptContent);
		System.out.println("解密后密码：" + decryptContent);

		Assert.assertEquals(source, decryptContent);

	}
}
