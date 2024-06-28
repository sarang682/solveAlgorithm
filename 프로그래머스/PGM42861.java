import java.util.*;

class Solution {
    static int[] parent;
    static class Edge {
        int v1;
        int v2;
        int w;
        public Edge (int v1, int v2, int w) {
            this.v1 = v1;
            this.v2 = v2;
            this.w = w;
        }
    }
    public int solution(int n, int[][] costs) {
        int answer = 0;
        parent = new int[n];
        for(int i=0;i<n;i++) {
            parent[i]=i;
        }
        Queue<Edge> queue = new PriorityQueue<>((o1,o2) -> o1.w-o2.w);
        for(int i=0;i<costs.length;i++) {
            queue.offer(new Edge(costs[i][0],costs[i][1],costs[i][2]));
        }
        
        while(!queue.isEmpty()) {
            Edge e = queue.poll();
            if(find(e.v1) != find(e.v2)) {
                union(e.v1, e.v2);
                answer+=e.w;
            }
        }
        return answer;
    }
    
    static public int find(int x) {
        if(parent[x]==x) return x;
        return find(parent[x]);
    }
    
    static public void union(int x,int y) {
        x = find(x);
        y = find(y);
        if(x<y)
            parent[x]=y;
        else
            parent[y]=x;
    }
}
