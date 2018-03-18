package searchromania;

import java.util.*;

/*
* Name:    Akshay Nalwaya
* Unity ID: 200159155
*/

public class SearchRomania {
    //initializing the number of vertices to 20
    static int vertices = 20;
    public static Stack dfs_path = new Stack();
    public static LinkedList<Integer>[] adj_list = new LinkedList[20];
    int expanded=0; //to count expanded nodes
    static List<String> city_names = Arrays.asList("arad","bucharest","craiova",
                "dobreta","eforie","fagaras","giurgiu","hirsova","iasi","lugoj",
                "mehadia","neamt","oradea","pitesti","rimnicu_vilcea","sibiu",
                "timisoara","urziceni","vaslui","zerind");
    
    //function to implement Depth-First Search
    void DFS_Search(int s, int d, int[] traversed, int[] parent) {
        int n,temp;
        traversed[s]=1;
        System.out.print(city_names.get(s)+" ");
        expanded++;
        //exit if goal node is reached
        if(s==d){
            System.out.println("\nNodes Expanded: "+expanded);
            int child_id = d;
            dfs_path.push(d);
            while(parent[child_id]!=-1){
                dfs_path.push(parent[child_id]);
                child_id = parent[child_id];
            }
            System.out.print("DFS Path: ");
            while(!dfs_path.isEmpty()){
                temp = (int)dfs_path.pop();
                System.out.print(city_names.get(temp)+" ");
            }
            System.out.println();
            System.exit(1);
        }
        //if not a goal node, then continue
        else{
            
            for(int i=0;i<adj_list[s].size();i++){
                n = adj_list[s].get(i);
                if(traversed[n]==0){
                    parent[n] = s;
                    DFS_Search(n, d, traversed,parent);   
                }
            }
        }
    }
    
    //function to implement Breadth-First Search
    void BFS_Search(int s, int d, int[] traversed, int[] parent) {
        int n,temp;
        LinkedList<Integer> bfs_queue = new LinkedList<Integer>();
        Stack bfs_path = new Stack();
        traversed[s]=1;
        bfs_queue.add(s);
        //continue till queue is not empty
        while(!bfs_queue.isEmpty())
        {
            s = bfs_queue.poll();
            System.out.print(city_names.get(s)+" ");
            //exit if goal node is reached
            if(s==d){
                System.out.println("\nNodes Expanded: "+(expanded-1));
                int child_id = d;
                bfs_path.push(d);
                while(parent[child_id]!=-1){
                    bfs_path.push(parent[child_id]);
                    child_id = parent[child_id];
                }
                System.out.print("BFS Path: ");
                while(!bfs_path.isEmpty()){
                    temp = (int)bfs_path.pop();
                    System.out.print(city_names.get(temp)+" ");
                }
                System.out.println();
                System.exit(1);
            }
            //if not goal node then continue through the loop
            else{
            
                for(int i=0;i<adj_list[s].size();i++){
                    n = adj_list[s].get(i);
                    if(traversed[n]==0){
                        parent[n] = s;
                        traversed[n]=1;
                        expanded++;
                        bfs_queue.add(n);
                    }
                }
            }
        }
    }
    
    public static void main(String[] args){
        
        int temp=0;
        //creating adjacency list for the graph
        //adj_list[0]
        adj_list[temp] = new LinkedList<Integer>();
        adj_list[temp].add(15);
        adj_list[temp].add(16);
        adj_list[temp++].add(19);
        //adj_list[1]
        adj_list[temp] = new LinkedList<Integer>();
        adj_list[temp].add(5);
        adj_list[temp].add(6);
        adj_list[temp].add(13);
        adj_list[temp++].add(17);
        //adj_list[2]
        adj_list[temp] = new LinkedList<Integer>();
        adj_list[temp].add(3);
        adj_list[temp].add(13);
        adj_list[temp++].add(14);
        //adj_list[3]
        adj_list[temp] = new LinkedList<Integer>();
        adj_list[temp].add(2);
        adj_list[temp++].add(10);
        //adj_list[4]
        adj_list[temp] = new LinkedList<Integer>();
        adj_list[temp++].add(7);
        //adj_list[5]
        adj_list[temp] = new LinkedList<Integer>();
        adj_list[temp].add(1);
        adj_list[temp++].add(15);
        //adj_list[6]
        adj_list[temp] = new LinkedList<Integer>();
        adj_list[temp++].add(1);
        //adj_list[7]
        adj_list[temp] = new LinkedList<Integer>();
        adj_list[temp].add(4);
        adj_list[temp++].add(17);
        //adj_list[8]
        adj_list[temp] = new LinkedList<Integer>();
        adj_list[temp].add(11);
        adj_list[temp++].add(18);
        //adj_list[9]
        adj_list[temp] = new LinkedList<Integer>();
        adj_list[temp].add(10);
        adj_list[temp++].add(16);
        //adj_list[10]
        adj_list[temp] = new LinkedList<Integer>();
        adj_list[temp].add(3);
        adj_list[temp++].add(9);
        //adj_list[11]
        adj_list[temp] = new LinkedList<Integer>();
        adj_list[temp++].add(8);
        //adj_list[12]
        adj_list[temp] = new LinkedList<Integer>();
        adj_list[temp].add(15);
        adj_list[temp++].add(19);
        //adj_list[13]
        adj_list[temp] = new LinkedList<Integer>();
        adj_list[temp].add(1);
        adj_list[temp].add(2);
        adj_list[temp++].add(14);
        //adj_list[14]
        adj_list[temp] = new LinkedList<Integer>();
        adj_list[temp].add(2);
        adj_list[temp].add(13);
        adj_list[temp++].add(15);
        //adj_list[15]
        adj_list[temp] = new LinkedList<Integer>();
        adj_list[temp].add(0);
        adj_list[temp].add(5);
        adj_list[temp].add(12);
        adj_list[temp++].add(14);
        //adj_list[16]
        adj_list[temp] = new LinkedList<Integer>();
        adj_list[temp].add(0);
        adj_list[temp++].add(9);
        //adj_list[17]
        adj_list[temp] = new LinkedList<Integer>();
        adj_list[temp].add(1);
        adj_list[temp].add(7);
        adj_list[temp++].add(18);
        //adj_list[18]
        adj_list[temp] = new LinkedList<Integer>();
        adj_list[temp].add(8);
        adj_list[temp++].add(17);
        //adj_list[19]
        adj_list[temp] = new LinkedList<Integer>();
        adj_list[temp].add(0);
        adj_list[temp].add(12);
        
        //array to track the nodes already traversed
        int traversed[] = new int[vertices];
        for(int i=0;i<vertices;i++)
            traversed[i] = 0;
        //array to hold the parent node of a particular node
        int parent[] = new int[vertices];
        for(int i=0;i<vertices;i++)
                parent[i] = -1;
        SearchRomania search_rom = new SearchRomania();
        
        System.out.print("Nodes traversed: ");
        int source = city_names.indexOf(args[1]);
        int dest = city_names.indexOf(args[2]);
        
        //check of source and destination are correctly spelled
        if (source == -1 || dest == -1){
            System.out.println("Source or destination name mismatch.");
            System.exit(1);
        }
        //if DFS is to be executed
        else if(args[0].equals("DFS")){
            search_rom.DFS_Search(source,dest,traversed,parent);
        }
        //if BFS is to be executed
        else if(args[0].equals("BFS")){
            search_rom.BFS_Search(source,dest,traversed,parent);
        }
    }
}
