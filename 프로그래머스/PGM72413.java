import java.util.*;

class Solution {
    
    static final int INF = 20000001;
    
    static ArrayList<ArrayList<Node>> adj;
    
    static class Node {
        int v;
        int w;
        public Node(int v,int w) {
            this.v = v;
            this.w = w;
        }
    }
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        
        adj = new ArrayList<>();
        for(int i=0;i<=n;i++) {
            adj.add(new ArrayList<>());
        }
        
        for(int i=0;i<fares.length;i++) {
            int x = fares[i][0];
            int y = fares[i][1];
            int w = fares[i][2];
            adj.get(x).add(new Node(y,w));
            adj.get(y).add(new Node(x,w));
        }   
        int[] dis_s=dijkstra(n, s);
        int[] dis_a=dijkstra(n, a);
        int[] dis_b=dijkstra(n, b);
        int answer = INF;
        for(int i=1;i<=n;i++) { // i 까지 합승
            int cost = dis_s[i] + dis_a[i] + dis_b[i];
            if(cost<answer)
                answer=cost;
        }
        return answer;
    }
    
    public static int[] dijkstra(int n, int start) {
        int[] dis = new int[n+1];
        boolean[] visit = new boolean[n+1];
        Queue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.w-o2.w);
        for(int i=0;i<=n;i++) {
            dis[i]=INF;
        }
        
        queue.offer(new Node(start,0));
        
        while(!queue.isEmpty()) {
            Node now = queue.poll();
            if(visit[now.v]) continue;
            visit[now.v] = true;
            dis[now.v] = now.w;
            
            for(Node node: adj.get(now.v)) {
                if(dis[node.v] > dis[now.v] + node.w) {
                    queue.offer(new Node(node.v, dis[now.v] + node.w));
                }
            }
        }
        return dis;
    }
}
