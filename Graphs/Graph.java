import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * Filename:   Graph.java
 * Project:    p4
 * Authors:    
 * 
 * Directed and unweighted graph implementation
 */

public class Graph implements GraphADT {

  int numVertices;
  int numEdges;
  ArrayList<GraphNode> nodes;
  
  //Inner class
  class GraphNode{    
    private String node;    
    private ArrayList<String> successor;
    
    public GraphNode() {      
    }
    
    public GraphNode(String node) {
      this.node = node;
      successor = new ArrayList<String>();
    }   
        
    public void viewEdges() {
      for(String edge : successor)
        System.out.println(edge);
    }
    
    public String viewVertex() {
      return node;
    }
  }
  //constructor
  public Graph() {
    nodes = new ArrayList<GraphNode>();
    numEdges = 0;
    numVertices = 0;
  }
  
  @Override
  public void addVertex(String vertex) {
    GraphNode obj = new GraphNode(vertex);
    //check is vertex is null
    if(vertex == null)
      return;
    //check if vertex already exists
    for(GraphNode node : nodes) {
      if(node.node.equals(vertex))
        return;
    }
    //add vertex
    nodes.add(obj);
    numVertices++;    
  }

  @Override
  public void removeVertex(String vertex) {
    boolean flag = false;
    //check if vertex is null
    if(vertex == null)
      return;
    //check is vertex exists
    for(GraphNode node : nodes) {
      if(node.node.equals(vertex))
        flag = true;
    }
    //do nothing is vertex doesn't exist
    if(flag == false)
      return;
    
   try {
   //remove vertex node   
    for(GraphNode node : nodes) {
      if(node.node.equals(vertex))
        {
          nodes.remove(node);
          break;
        }
    }
   }
   catch(Exception e) {
     e.printStackTrace();
   }
    numVertices--;
    
   //remove corresponding edges
    for(GraphNode node : nodes) {
      for(String edge: node.successor) {
        if(edge.equals(vertex))
        {
          node.successor.remove(edge);
          numEdges--;
          break;
        }
      }
    }
    
  }

  @Override
  public void addEdge(String vertex1, String vertex2) {
    boolean flag1 = false;
    boolean flag2 = false;
   //check for null vertices
    if(vertex1 == null)
      return;
    if(vertex2 == null)
      return;
   //check if vertex1 is present
    for(GraphNode node : nodes) {
      if(node.node.equals(vertex1))
        flag1 = true;
    }
   //add vertex1 if not present
    if(flag1 == false)
      addVertex(vertex1);
  //check if vertex2 is present
    for(GraphNode node : nodes) {
      if(node.node.equals(vertex2))
        flag2 = true;
    }
  //add vertex2 if not present
    if(flag2 == false)
      addVertex(vertex2); 
    
  //add edge
    for(GraphNode node : nodes) {
      if(node.node.equals(vertex1)) {
        node.successor.add(vertex2);
      }
    }
    numEdges++;
        
  }

  @Override
  public void removeEdge(String vertex1, String vertex2) {
    boolean flag1 = false;
    boolean flag2 = false;
    boolean flag3 = false;
   //check for null vertices
    if(vertex1 == null)
      return;
    if(vertex2 == null)
      return;
  //check if vertex1 is present
    for(GraphNode node : nodes) {
      if(node.node.equals(vertex1))
        flag1 = true;
    }
   //do nothing if vertex1 if not present
    if(flag1 == false)
      return;
  //check if vertex2 is present
    for(GraphNode node : nodes) {
      if(node.node.equals(vertex2))
        flag2 = true;
    }
  //do nothing if vertex2 if not present
    if(flag2 == false)
      return;
    
   //check for presence of edge from vertex1 to veretex2
    for(GraphNode node : nodes) {
      if(node.node.equals(vertex1)) {
        for(String edge: node.successor) {
          if(edge.equals(vertex2))
            flag3 = true;
        }
      }
    }    
   //do nothing if edge not found
    if(flag3 == false)
      return;
    
    //remove edge
    for(GraphNode node : nodes) {
      if(node.node.equals(vertex1)) {
        for(String edge: node.successor) {
          if(edge.equals(vertex2)) {
            node.successor.remove(edge);
            break;
          }            
        }
      }
    }  
    numEdges--;
  }

  @Override
  public Set<String> getAllVertices() {
    Set<String> set = new HashSet<String>();    
    for(GraphNode node : nodes) {
      set.add(node.node);
    }
    return set;
  }

  @Override
  public List<String> getAdjacentVerticesOf(String vertex) {
    List<String> list = new ArrayList<String>();
    for(GraphNode node : nodes) {
      if(node.node.equals(vertex)) {
        for(String edge : node.successor) {
          list.add(edge);
        }
      }
    }
    return list;
  }

  @Override
  public int size() {
    return numEdges;
  }

  @Override
  public int order() {
    return numVertices;
  }
  
  /**
   * check if graph contains vertex
   * @param vertex
   * @return true if yes, false if not
   */
  public boolean containsVertex(String vertex) {
    for(GraphNode nodee : nodes) {
      if(nodee.node.equals(vertex))
        return true;
    }
   return false;
  }
  
  
  
  /**
   * Used to check for cycle in graph
   * @param graph
   * @return true if cycle, false if not   *
   */
 public boolean cycle(Graph graph) {
   final Set<String> visitedNodes = new HashSet<String>();
   Set<String> completedNodes = new HashSet<String>();
   
   for(GraphNode node : graph.nodes)
   {
     if(dfs(graph, node.viewVertex(), visitedNodes, completedNodes))
       return true;
   }
   
   return false;
   
 } 
 
 /**
  * Cycle helper method
  * @param graph
  * @param node
  * @param visitedNodes
  * @param completedNodes
  * @return true if cycle, false if not
  */
 private boolean dfs(Graph graph, String node, Set<String> visitedNodes, Set<String> completedNodes) {
   assert graph !=null;
   assert node!=null;
   assert visitedNodes!=null;
   if(visitedNodes.contains(node)) {
     if(completedNodes.contains(node)) return false;
     return true;
   }
   
   visitedNodes.add(node);
   
   for(String edges : graph.getAdjacentVerticesOf(node))
   {
     if(dfs(graph, edges, visitedNodes, completedNodes)) return true;
   }
   
   completedNodes.add(node);
   return false;
 }
 

/**
 * gets package index in nodes array
 * @param node
 * @return node index of node in nodes array
 */
 public int getNodeIndex(String node) {
   for(int i = 0; i < nodes.size(); i++)
   {
     if(nodes.get(i).node.equals(node))
       return i;
   }
   return -1;
 } 
 
 /**
  * sort graph topologically
  * @return List of sorted graph
  */
 public List<String> topologicalSort() {
   Stack<String> stack = new Stack<String>();
   boolean visited[] = new boolean[nodes.size()];
   //go to each unvisited node
   for(int i = 0; i < nodes.size(); i++)
   {    
     if(visited[getNodeIndex(nodes.get(i).node)] == false)
     {  
       topologicalSortUtil(getNodeIndex(nodes.get(i).node), visited, stack);     
     }
   }
   //convert stack to list
   List<String> list = new ArrayList<String>();
   while(!stack.isEmpty())
   {
     list.add(stack.pop());
   }   
   //reverse list
   Collections.reverse(list);
   return list;
 }
     
 /**
  * Helper function to sort topologically
  * @param nodeIndex
  * @param visited
  * @param stack
  * @return none
  */
 private void topologicalSortUtil(int nodeIndex, boolean visited[], Stack<String> stack ){
   visited[nodeIndex] = true;
   String i="";   
   
   //iterate through adjacent vertices of each unvisited node
   Iterator<String> itr = getAdjacentVerticesOf(nodes.get(nodeIndex).node).iterator();
   while(itr.hasNext())
   {
     i = itr.next();     
     
     //if unvisited, visit the adjacent vertex
     if(!visited[getNodeIndex(i)])
       topologicalSortUtil(getNodeIndex(i), visited, stack);
   }      
   stack.push(nodes.get(nodeIndex).node);
 }
 
 
 /**
  * sort graph topologically
  * @return List of sorted graph
  */
 public String maxPackageDependency() {
   String[][] arr = new String[nodes.size()][2];
   
   //check dependency for each vertex individually and store in 2D array
   for(int i = 0; i < nodes.size(); i++)
   {      
     arr[i][0] = nodes.get(i).node;
     //check off boolean array when visiting node
     boolean visited[] = new boolean[nodes.size()];
     //keep track of dependencies
     int count = 0;       
     count = maxPackageDependencyUtil(getNodeIndex(nodes.get(i).node), visited, count);    
     arr[i][1] = Integer.toString(count);
   }
   //find max dependency in 2D array
   int maxElement = Integer.MIN_VALUE;
   String pkg = "";
   for (int i = 0; i < arr.length; i++) {     
       if (Integer.parseInt(arr[i][1]) > maxElement) { 
           maxElement = Integer.parseInt(arr[i][1]); 
           pkg = arr[i][0];
       } 
   }
   //decrement dependencies by 1 if not 0
   if(!pkg.equals("0"))
   {
     pkg = Integer.toString(Integer.parseInt(pkg) - 1);
   }
   
   return pkg;
   
  
 
 }
 
 private int maxPackageDependencyUtil(int nodeIndex, boolean visited[], int count){
   visited[nodeIndex] = true;
   String i="";   
   
   //iterate through adjacent vertices of each unvisited node
   Iterator<String> itr = getAdjacentVerticesOf(nodes.get(nodeIndex).node).iterator();
   while(itr.hasNext())
   {
     i = itr.next();     
     
     //if unvisited, visit the adjacent vertex
     if(!visited[getNodeIndex(i)])
       count = maxPackageDependencyUtil(getNodeIndex(i), visited, count);
   }     
   //increment count when all adjacent vertices have been visited
   count++;
   return count;
 }
 
 
 /**
  * function to sort topologically in order to find installation order for a newer packagey
  * @param nodeIndex1 - index of package to be installed in nodes array
  * @param nodeIndex2 - index of package already installed in nodes array
  * @return List of sorted graph
  */
 public List<String> installNewPackage(int nodeIndex1, int nodeIndex2) {
   Stack<String> stack = new Stack<String>();
   boolean visited[] = new boolean[nodes.size()]; 
   topologicalSortUtil(nodeIndex2, visited, stack);
   stack = new Stack<String>();
   topologicalSortUtil(nodeIndex1, visited, stack);    
   //convert stack to list
   List<String> list = new ArrayList<String>();
   while(!stack.isEmpty())
   {
     list.add(stack.pop());
   }   
   //reverse list
   Collections.reverse(list);
   return list;
 }     

 
 }
     
   
 

 
