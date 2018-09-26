package com.jaky.wang.leetcode.problems;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 将句子反转 例如 xiamo wj 变为 wj xiaomo
 * @author xiaomo.wj
 * @date 2018/9/21.
 *
 */
public class Problem151 {

  public static String reverseWords(String s) {
    if (null == s) {
      return s;
    }
    List<String> collect = Arrays.asList(s.split(" ")).stream().filter(e -> !isBlank(e)).collect(Collectors.toList());
    Collections.reverse(collect);
    StringBuilder sb = new StringBuilder("");
    boolean flag = false;

    for (String word : collect) {
      if (flag) {
        sb.append(" ");
      }
      flag = true;
      sb.append(word);
    }
    return sb.toString();
  }

  public static boolean isBlank(CharSequence cs) {
    int strLen;
    if (cs != null && (strLen = cs.length()) != 0) {
      for (int i = 0; i < strLen; ++i) {
        if (!Character.isWhitespace(cs.charAt(i))) {
          return false;
        }
      }

      return true;
    } else {
      return true;
    }
  }

  public static void main(String[] args) {

  }

}
