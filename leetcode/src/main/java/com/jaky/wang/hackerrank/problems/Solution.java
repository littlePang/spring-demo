package com.jaky.wang.hackerrank.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author xiaomo.wj
 * @date 2018/10/16.
 */
public class Solution {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int m = scanner.nextInt();
    int n = scanner.nextInt();
    int r = scanner.nextInt();
    List<Edge> edges = new ArrayList<>(r);
    while (r-- > 0) {
      int s = scanner.nextInt();
      int e = scanner.nextInt();
      int c = scanner.nextInt();
      int ss = scanner.nextInt();
      edges.add(new Edge(s-1, e-1, ss, c));
    }

    int minS = Integer.MAX_VALUE;
    while(true) {
      Graph graph = new Graph(n);
      graph.setAllCost(0);
      for (Edge edge : edges) {
        graph.insertEdge(edge);
      }
      Edge prim = graph.Prim(m);
      if (prim == null) {
        break;
      }

      List<Edge> newEdges = new ArrayList<>();
      for (Edge edge : edges) {
        if (edge.getWeight() > prim.getWeight()) {
          newEdges.add(edge);
        }
      }
      edges = newEdges;
      minS = prim.getWeight();

    }
    System.out.println(minS);

  }


  static class Graph {

    private int vNum;
    private int edgeNum;
    private LinkedList<Edge>[] edgeLinks;
    private LinkedList<Integer> TV;
    private LinkedList<Edge> T;
    private int allCost;

    public Graph(int vNum){
      this.vNum = vNum;
      this.edgeNum = 0;
      edgeLinks = new LinkedList[vNum];
      for(int i = 0;i<vNum;i++){
        edgeLinks[i] = new LinkedList<>();
      }
    }

    public void insertEdge(Edge edge){
      int v1 = edge.getV1();
      int v2 = edge.getV2();
      edgeLinks[v1].add(edge);
      Edge edge2 = new Edge(v2,v1,edge.getWeight(), edge.getCost());
      edgeLinks[v2].add(edge2);
      edgeNum++;
    }

    public void deleteEdge(Edge edge){
      int v1 = edge.getV1();
      int v2 = edge.getV2();
      Edge edge2 = new Edge(v2,v1,edge.getWeight(), edge.getCost());
      edgeLinks[v1].remove(edge);
      edgeLinks[v2].remove(edge2);
      edgeNum--;
      allCost += edge.getCost();
    }

    public int getAllCost() {
      return allCost;
    }

    public void setAllCost(int allCost) {
      this.allCost = allCost;
    }


    /**
     */
    public Edge Prim(int mLimit){

      TV = new LinkedList<>();
      T = new LinkedList<>();
      TV.add(0);

      while(edgeNum>0 && T.size()!=vNum-1){
        Edge edge = getMinEdge(TV);
        if(edge==null)
          break;
        this.deleteEdge(edge);
        T.add(edge);
        TV.add(edge.getV2());
      }

      if(T.size()==vNum-1){
        LinkedList<Edge> list = (LinkedList<Edge>) T.clone();
        int minWeight = Integer.MAX_VALUE;
        Edge minEdge = null;
        while(!list.isEmpty()){
          Edge edge = list.pop();
          mLimit -= edge.getCost();
          if (minWeight > edge.getWeight()) {
            minWeight = edge.getWeight();
            minEdge = edge;
          }
        }
        if (mLimit >= 0) {
          return minEdge;
        } else {
          return null;
        }
      }else{
        return null;
      }
    }

    public Edge getMinEdge(LinkedList<Integer> t){

      Edge minEdge = new Edge(1000000);
      LinkedList<Integer> tt = (LinkedList<Integer>) t.clone();

      while(!tt.isEmpty()){
        int i = tt.pop();
        LinkedList<Edge> list = (LinkedList<Edge>) edgeLinks[i].clone();
        while(!list.isEmpty()){
          Edge edge = list.pop();
          if(minEdge.getCost()>edge.getCost() && !t.contains(edge.getV2())){
            minEdge = edge;
          }
        }
      }

      if(minEdge.getCost()==1000000)
        return null;
      return minEdge;
    }

  }


  /**
   * @author sdu20
   *
   */
   static class Edge {

    private int v1;
    private int v2;
    private int weight;
    private int cost;

    /**
     * @param cost
     */
    public Edge(int cost){
      this.v1 = -1;
      this.v2 = -1;
      this.cost = cost;
    }

    public Edge(int v1,int v2,int weight, int cost){
      this.cost = cost;
      this.v1 = v1;
      this.v2 = v2;
      this.weight = weight;
    }

    public int getCost() {
      return cost;
    }

    public void setCost(int cost) {
      this.cost = cost;
    }

    public int getV1(){
      return v1;
    }

    public int getV2(){
      return v2;
    }

    public int getWeight(){
      return weight;
    }

    public String toString(){
      String str = "[ "+v1+" , "+v2+" , "+weight+" ]";
      return str;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) { return true; }
      if (o == null || getClass() != o.getClass()) { return false; }

      Edge edge = (Edge)o;

      if (v1 != edge.v1) { return false; }
      if (v2 != edge.v2) { return false; }
      if (weight != edge.weight) { return false; }
      return cost == edge.cost;
    }

    @Override
    public int hashCode() {
      int result = v1;
      result = 31 * result + v2;
      result = 31 * result + weight;
      result = 31 * result + cost;
      return result;
    }
  }


}
