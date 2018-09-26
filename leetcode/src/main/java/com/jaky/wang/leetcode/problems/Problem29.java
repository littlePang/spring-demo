package com.jaky.wang.leetcode.problems;

/**
 * 两数相除，考虑溢出
 * @author xiaomo.wj
 * @date 2018/9/26.
 */
public class Problem29 {


  public int divide(int dividend, int divisor) {
    if (dividend == Integer.MIN_VALUE && divisor == -1) {
      return Integer.MAX_VALUE;
    }
    return dividend/divisor;
  }

  public static void main(String[] args) {

  }

}
