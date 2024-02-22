package LeetcodeHot100.graph;

import java.util.*;

public class q3_canFinish {


    // 这是我自己想的解题方法，将给定的数据转换成左神的图论模板，然后再利用拓扑排序算法，整体大代码很长。44ms
    class Graph {
        public HashMap<Integer, Node> nodes;
        public HashSet<Edge> edges;

        public Graph() {
            nodes = new HashMap<>();
            edges = new HashSet<>();
        }
    }

    class Node{
        public int value;
        public int in;
        public int out;
        public ArrayList<Node> nexts;
        public ArrayList<Edge> edges;

        public Node(int value) {
            this.value = value;
            in = 0;
            out = 0;
            nexts = new ArrayList<>();
            edges = new ArrayList<>();
        }
    }

    class Edge {
        public Node from;
        public Node to;
        public Edge(Node from, Node to) {
            this.from = from;
            this.to = to;
        }
    }

    public Graph createGraph(int numCourses, int[][] prerequisites){
        Graph graph = new Graph();

        // 创建节点
        for(int i = 0;i < numCourses;i++) {
            graph.nodes.put(i, new Node(i));
        }

        for(int i = 0; i < prerequisites.length; i++) {
            // 准备数据创建边
            Node fromNode = graph.nodes.get(prerequisites[i][1]);
            Node toNode = graph.nodes.get(prerequisites[i][0]);
            Edge newEdge = new Edge(fromNode, toNode);  // 创建边

            // 边创建出来之后，节点中的属性也需要修改
            fromNode.nexts.add(toNode);
            fromNode.out++;
            fromNode.edges.add(newEdge);
            toNode.in++;

            graph.edges.add(newEdge);
        }
        return graph;
    }

    public List<Node> sortedTopology(Graph graph) {
        for(Node item : graph.nodes.values()){
            System.out.print(item.value + ", ");
        }
        // key：某个点Node，value：剩余的入度
        HashMap<Node, Integer> inMap = new HashMap<>();
        Queue<Node> zeroInQueue = new LinkedList<>();
        for (Node node: graph.nodes.values()) {
            inMap.put(node, node.in);
            if(node.in == 0) {
                zeroInQueue.add(node);
            }
        }  // 找到了第一批入度是0的点

        List<Node> res = new ArrayList<>();
        while(!zeroInQueue.isEmpty()) {
            Node cur = zeroInQueue.poll();
            res.add(cur);
            for(Node next : cur.nexts){
                inMap.put(next, inMap.get(next) - 1);
                if(inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }

        }
        return res;

    }

    // 这是一个图问题中的拓扑排序问题
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites.length == 0){
            return true;
        }
        Graph graph = createGraph(numCourses, prerequisites);
        List<Node> res = sortedTopology(graph);
        System.out.println();
        for(Node item : res) {
            System.out.print(item.value + ", ");
        }
        return res.size() == numCourses;
    }

}
