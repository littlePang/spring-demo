package com.jaky.wang.leetcode.problems;

/**
 * 将字符串中最前置的数字字符串转换为int，超过int的范围则返回 min_value 或者 max_value
 *
 * @author xiaomo.wj
 * @date 2018/9/21.
 */
public class Problem8 {

  public static void main(String[] args) {
    System.out.println(new Problem8().myAtoi("42"));
    System.out.println(new Problem8().myAtoi("-42"));
    System.out.println(new Problem8().myAtoi("4193 with words"));
    System.out.println(new Problem8().myAtoi("words and 987"));
    System.out.println(new Problem8().myAtoi("-91283472332"));
    System.out.println(new Problem8().myAtoi("91283472332"));
    System.out.println(new Problem8().myAtoi(".1"));
    System.out.println(new Problem8().myAtoi("+1"));
  }

  public int myAtoi(String str) {

    if (str == null) {
      return 0;
    }

    boolean isStart = false;
    boolean isNegative = false;
    long sum = 0;
    for (char c : str.toCharArray()) {
      if (c == ' ' && !isStart) {
        continue;
      }

      if (isStart) {
        if (c < '0' || c > '9') {
          return (int)sum;
        }
        int num = c - '0';
        if (isNegative) {
          sum = sum * 10 + num * -1;
        } else {
          sum = sum * 10 + num;
        }

        if (isNegative && sum < Integer.MIN_VALUE) {
          return Integer.MIN_VALUE;
        }
        if (!isNegative && sum > Integer.MAX_VALUE) {
          return Integer.MAX_VALUE;
        }

        continue;
      }

      isStart = true;
      if (c == '-') {
        isNegative = true;
      } else if (c >= '0' && c <= '9') {
        sum = c - '0';
      } else if (c != '+') {
        return 0;
      }
    }
    return (int)sum;
  }

}
