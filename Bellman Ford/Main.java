// Program to implement Bellman Ford's Algorithm to Find the Shortest Path 
// between Vertices  

/*
n=4
0 2 1 0
2 0 3 4
1 3 0 2
0 4 2 0
*/

import java.util.Arrays;
import java.util.Scanner;

class Main {
    private static int n, graph[][];

    static void display(int distance[], int src){
        System.out.println("Vertex\tDistance From "+src);
        for (int i=1;i<=n;i++)
            System.out.println(i+"\t"+distance[i]);
    }   

    static void bellmanFord(int src) {
        int distance[] = new int[n+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[src] = 0;
        for (int i=2;i<=n;i++){
            for (int u=1;u<=n;u++){
                for (int v=1;v<=n;v++){
                    if (
                        graph[u][v] != 0 && 
                        distance[u] != Integer.MAX_VALUE && 
                        distance[u] + graph[u][v] < distance[v]
                       )
                        distance[v] = distance[u] + graph[u][v];
                }
            }
        }
        for (int u=1;u<=n;u++){
            for (int v=1;v<=n;v++){
                if (
                    graph[u][v] != 0 && 
                    distance[u] != Integer.MAX_VALUE && 
                    distance[u] + graph[u][v] < distance[v]
                   )
                {
                    System.out.println("Negative weight cycle detected.");
                    return;
                }
            }
        }
        display(distance, src);
    }

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Number of Vertices: ");
        n = sc.nextInt();
        System.out.println("Enter the Weight Matrix: ");
        graph = new int[n+1][n+1];
        for (int i=1;i<=n;i++)
            for(int j=1;j<=n;j++)
                graph[i][j] = sc.nextInt();
        System.out.println("Enter the Source Vertex: ");
        bellmanFord(sc.nextInt());
    }
}