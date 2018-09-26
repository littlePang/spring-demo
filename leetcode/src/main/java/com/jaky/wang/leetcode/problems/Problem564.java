package com.jaky.wang.leetcode.problems;

import java.util.ArrayList;
import java.util.List;

/**
 * 和 n 差距最小的回文数字
 * @author xiaomo.wj
 * @date 2018/9/26.
 */
public class Problem564 {
  public String nearestPalindromic(String n) {
    Long n1 = Long.valueOf(n) - (long)Math.pow(10, (n.length()) / 2);
    Long n2 = Long.valueOf(n) + (long)Math.pow(10, (n.length()) / 2);

    List<Long> longs = new ArrayList<>();
    longs.add(find(n));
    longs.add(find(n1.toString()));
    longs.add(find(n2.toString()));

    longs.add((long)Math.pow(10, (n.length() - 1)) - 1);

    Long ret = Long.MAX_VALUE;
    Long nn = Long.valueOf(n);
    for (Long aLong : longs) {
      if (aLong.equals(nn)) {
        continue;
      }

      if (Math.abs(ret - nn) > Math.abs(aLong - nn)) {
        ret = aLong;
      } else if (Math.abs(ret - nn) == Math.abs(aLong - nn)) {
        ret = Math.min(ret, aLong);
      }
    }
    return ret.toString();



  }

  private Long find(String n) {
    if (n.length() == 1) {
      return Long.valueOf(n) - 1;
    }
    Long num = Long.valueOf(n);
    char[] chars = n.toCharArray();
    int length = chars.length;

    char[] retChars = new char[length];

    for (int i = 0; i < (length + 1) / 2; i++) {
      retChars[i] = chars[i];
    }

    for (int i = (length + 1) / 2, j=1; i < length; i++,j++) {
      retChars[i] = retChars[(length+1) / 2 - j - (length%2)];
    }
    String s = new String(retChars);
    return Long.valueOf(s);
  }

  public static void main(String[] args) {
    Problem564 problem564 = new Problem564();
    System.out.println(problem564.nearestPalindromic("123"));
    System.out.println(problem564.nearestPalindromic("1"));
    System.out.println(problem564.nearestPalindromic("1234567"));
    System.out.println(problem564.nearestPalindromic("12345678"));
    System.out.println(problem564.nearestPalindromic("1234"));
    System.out.println(problem564.nearestPalindromic("12345"));
    System.out.println(problem564.nearestPalindromic("100000"));
    System.out.println(problem564.nearestPalindromic("10")); // 9
    System.out.println(problem564.nearestPalindromic("11")); // 9
    System.out.println(problem564.nearestPalindromic("88")); // 77
  }
}
