import java.util.*;

class Solution {
    static final int INF = 500001;
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        int[][] adj = new int[N+1][N+1];
        for(int i=1;i<=N;i++) {
            for(int j=1;j<=N;j++) {
                if(i==j) continue;
                adj[i][j]=INF;
            }
        }
        for(int i=0;i<road.length;i++) {
            int a = road[i][0];
            int b = road[i][1];
            int w = road[i][2];
            if(adj[a][b] > w){
                adj[a][b]=w;
                adj[b][a]=w;
            }
        }
        
        int[] dis = new int[N+1];
        for(int i=2;i<=N;i++) {
            dis[i] = INF;
        }
        
        boolean[] visit = new boolean[N + 1];
        visit[1] = true;
        
        for(int i=1;i<=N-1;i++) {
            
            int min_idx = 1;
            int min_value = INF;
            for(int j=2; j<=N;j++) {
                if(!visit[j] && dis[j] < min_value) {
                    min_value=dis[j];
                    min_idx=j;
                }
            }
            
            visit[min_idx] = true;
            for (int j = 2; j <= N; j++) {
                if (dis[j] > dis[min_idx] + adj[min_idx][j]) {
                    dis[j] = dis[min_idx] + adj[min_idx][j];
                }
            }
        }
        
        for(int i=1;i<=N;i++) {
            if(dis[i]<=K) answer++;
        }
        return answer;
    }
}
