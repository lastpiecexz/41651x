package com.itfollowme.baidufanyidemo;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by notre on 2018/5/2.
 */

public class Utils {

  public static String MD5(String str) {
    try {
      MessageDigest messageDigest = MessageDigest.getInstance("MD5");
      byte[] inputArray = str.getBytes("UTF-8");
      messageDigest.update(inputArray);
      // 转换并返回结果，也是字节数组，包含16个元素
      byte[] resultByteArray = messageDigest.digest();
      // 字符数组转换成字符串返回
      str =  byteArrayToHex(resultByteArray);
      return str;
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return null;
  }

  private static String byteArrayToHex(byte[] byteArray) {
    // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
    char[] resultCharArray = new char[byteArray.length * 2];
    // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
    int index = 0;
    for (byte b : byteArray) {
      resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
      resultCharArray[index++] = hexDigits[b & 0xf];
    }

    // 字符数组组合成字符串返回
    return new String(resultCharArray);

  }
  private static final char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
      'e', 'f' };
}
