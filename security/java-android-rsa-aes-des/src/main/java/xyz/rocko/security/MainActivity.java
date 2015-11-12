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

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import xyz.rocko.security.config.SecurityConfig;
import xyz.rocko.security.databinding.MainActivityBinding;
import xyz.rocko.security.security.AESCipherStrategy;
import xyz.rocko.security.security.CipherStrategy;
import xyz.rocko.security.security.DESCipherStrategy;
import xyz.rocko.security.security.RSACipherStrategy;

public class MainActivity extends AppCompatActivity {

	private MainActivityBinding mBinding;

	RSACipherStrategy rsaCipherStrategy = new RSACipherStrategy();
	CipherStrategy aesCipherStrategy = new AESCipherStrategy(SecurityConfig.KEY);
	CipherStrategy desCipherStrategy = new DESCipherStrategy(SecurityConfig.KEY);


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mBinding = DataBindingUtil.setContentView(this, R.layout.main_activity);
		setSupportActionBar(mBinding.toolbar);

	}

	public void onClick(View v) {
		switch (v.getId()) { // Note: 加解密最好不要放在主线程，demo 为了简单展示
			case R.id.encrypt:
				encrypt();
				break;
			case R.id.decrypt:
				decrypt();
				break;
		}

	}

	/**
	 * 加密
	 */
	private void encrypt() {
		String sourceContent = mBinding.sourceContent.getText().toString().trim();
		// rsa 公钥加密
		rsaCipherStrategy.initPublicKey(SecurityConfig.RSA_PUCLIC_KEY);
		String rsaEncrypt = rsaCipherStrategy.encrypt(sourceContent);
		// aes
		String aesEncrypt = aesCipherStrategy.encrypt(sourceContent);
		//des
		String desEncrypt = desCipherStrategy.encrypt(sourceContent);


		mBinding.encryptRsa.setText(rsaEncrypt);
		mBinding.encryptAes.setText(aesEncrypt);
		mBinding.encryptDes.setText(desEncrypt);
	}

	/**
	 * 解密
	 */
	private void decrypt() {
		String rsaEncrypt = mBinding.encryptRsa.getText().toString().trim();
		String aesEncrypt = mBinding.encryptAes.getText().toString().trim();
		String desEncrypt = mBinding.encryptDes.getText().toString().trim();
		// rsa 私钥解密
		rsaCipherStrategy.initPrivateKey(SecurityConfig.RSA_PRIVATE_KEY);
		String rsaDecrypt = rsaCipherStrategy.decrypt(rsaEncrypt);
		// aes
		String aesDecrypt = aesCipherStrategy.decrypt(aesEncrypt);
		// des
		String desDecrypt = desCipherStrategy.decrypt(desEncrypt);


		mBinding.decryptRsa.setText(rsaDecrypt);
		mBinding.decryptAes.setText(aesDecrypt);
		mBinding.decryptDes.setText(desDecrypt);
	}

}
