package com.jaky.wang.leetcode.problems;


/**
 * 一个字符串能否 可以表示一个 数字
 *
 * .1 是正常的
 * 3. 也是正常的
 * e的指数不能出现小数
 * 最多只能出现一个e
 *
 * @author xiaomo.wj
 * @date 2018/9/26.
 */
public class Problem65 {
  public boolean isNumber(String s) {

    if (null == s) {
      return false;
    }

    s = s.trim();
    if ("".equalsIgnoreCase(s)) {
      return false;
    }

    for (char c : s.toCharArray()) {
      if (!valid(c)) {
        return false;
      }
    }


    String num = "";
    boolean alreadyHiveE = false;
    for (char c : s.toCharArray()) {
      if (c == 'e') {
        if (alreadyHiveE) {
          return false;
        }
        alreadyHiveE = true;
        if("".equalsIgnoreCase(num)) {
          return false;
        }
        boolean cctn = canConvertToNumber(num);
        if (!cctn) {
          return false;
        }
        num = "";
      } else {
        num += c;
      }
    }


    if (!canConvertToNumber(num)) {
      return false;
    }
    if (alreadyHiveE && num.contains(".")) {
      return false;
    }

    return true;

  }

  private boolean canConvertToNumber(String ssub) {
    if ("".equalsIgnoreCase(ssub)) {
      return false;
    }
    if (".".equalsIgnoreCase(ssub)) {
      return false;
    }
    if (ssub.toCharArray()[0] == '+' || ssub.toCharArray()[0] == '-' ) {
      ssub = ssub.substring(1);
    }
    if ("".equalsIgnoreCase(ssub)) {
      return false;
    }

    boolean alreadyPoint = false;
    char[] chars = ssub.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      char c = chars[i];
      if (c == '.') {
        if (alreadyPoint) {
          return false;
        }
        if (i == chars.length -1 && !(i>0&&chars[i-1]>='0'&&chars[i-1]<='9')) {
          return false;
        }
        alreadyPoint = true;
        continue;
      }

      if (c < '0' || c>'9') {
        return false;
      }
    }
    return true;
  }

  private boolean valid(char c) {
    if (c >= '0' && c <= '9') {
      return true;
    }
    if (c == 'e') {
      return true;
    }
    if (c == '+' || c == '-') {
      return true;
    }
    if (c == '.') {
      return true;
    }
    return false;
  }

  public static void main(String[] args) {
    Problem65 problem65 = new Problem65();
    System.out.println(problem65.isNumber("0"));
    System.out.println(problem65.isNumber(" 0.1 "));
    System.out.println(problem65.isNumber("abc"));
    System.out.println(problem65.isNumber("1 a"));
    System.out.println(problem65.isNumber("2e10"));
    System.out.println(problem65.isNumber(" -90e3   "));
    System.out.println(problem65.isNumber(" 1e"));
    System.out.println(problem65.isNumber("e3"));
    System.out.println(problem65.isNumber(" 6e-1"));
    System.out.println(problem65.isNumber(" 99e2.5 "));
    System.out.println(problem65.isNumber("53.5e93"));
    System.out.println(problem65.isNumber(" --6 "));
    System.out.println(problem65.isNumber("-+3"));
    System.out.println(problem65.isNumber("95a54e53"));
    System.out.println(problem65.isNumber(".1"));
    System.out.println(problem65.isNumber("."));
    System.out.println(problem65.isNumber("-."));
    System.out.println(problem65.isNumber("+."));
    System.out.println(problem65.isNumber("+.5"));
    System.out.println(problem65.isNumber("-e58"));
    System.out.println(problem65.isNumber("92e1740e91"));
  }

}
