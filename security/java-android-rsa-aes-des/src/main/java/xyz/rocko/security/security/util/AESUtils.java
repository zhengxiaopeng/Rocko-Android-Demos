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

package xyz.rocko.security.security.util;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES 对称加密
 */
public class AESUtils {
    /**
     * 算法/模式/填充 *
     */
    private static final String CipherMode = "AES";

    /**
     * 创建密钥
     *
     * @param password 例如："0123456701234567" 128位 16*8 <br>
     *                 所有密钥长度不能超过16字符中文占两个。192 24； 256 32
     * @return SecretKeySpec 实例
     */
    private static SecretKeySpec generateAESKey(String password) {
        byte[] data = null;
        StringBuilder sb = new StringBuilder();
        sb.append(password);
        while (sb.length() < 16)
            sb.append("0");
        if (sb.length() > 16)
            sb.setLength(16);
        try {
            data = sb.toString().getBytes("UTF-8");
            return new SecretKeySpec(data, "AES");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 加密字节数据
     *
     * @param content  需要加密的字节数组
     * @param password 密钥 128 <16个字节 192 <24,256 <32个字节
     * @return 加密完后的字节数组
     */
    public static byte[] encryptData(byte[] content, String password) {
        try {
            SecretKeySpec key = generateAESKey(password);
            Cipher cipher = Cipher.getInstance(CipherMode);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加密(结果为16进制字符串), UTF-8 编码
     *
     * @param content  要加密的字符串
     * @param password 密钥
     * @return 加密后的16进制字符串
     */
    public static String encryptData(String content, String password) {
        byte[] data = null;
        try {
            data = content.getBytes("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        data = encryptData(data, password);
        String result = byte2hex(data);
        return result;
    }

    /**
     * 解密字节数组 UTF-8
     *
     * @param content
     * @param password
     * @return
     */
    public static byte[] decryptData(byte[] content, String password) {
        try {
            SecretKeySpec key = generateAESKey(password);
            Cipher cipher = Cipher.getInstance(CipherMode);
            cipher.init(Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密16进制的字符串为字符串 *
     */
    public static String decryptData(String content, String password) {
        byte[] data = null;
        try {
            data = hex2byte(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        data = decryptData(data, password);
        if (data == null)
            return null;
        String result = null;
        try {
            result = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 字节数组转成16进制字符串
     *
     * @param b
     * @return 16进制字符串
     */
    public static String byte2hex(byte[] b) { // 一个字节的数，
        StringBuffer sb = new StringBuffer(b.length * 2);
        String tmp = "";
        for (int n = 0; n < b.length; n++) {
            // 整数转成十六进制表示
            tmp = (Integer.toHexString(b[n] & 0XFF));
            if (tmp.length() == 1) {
                sb.append("0");
            }
            sb.append(tmp);
        }
        return sb.toString().toUpperCase(); // 转成大写
    }

    /**
     * 将hex字符串转换成字节数组 *
     *
     * @param inputString 16进制的字符串
     * @return 字节数组
     */
    public static byte[] hex2byte(String inputString) {
        if (inputString == null || inputString.length() < 2) {
            return new byte[0];
        }
        inputString = inputString.toLowerCase();
        int l = inputString.length() / 2;
        byte[] result = new byte[l];
        for (int i = 0; i < l; ++i) {
            String tmp = inputString.substring(2 * i, 2 * i + 2);
            result[i] = (byte) (Integer.parseInt(tmp, 16) & 0xFF);
        }
        return result;
    }

}