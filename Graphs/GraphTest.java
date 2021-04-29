import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Stack;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GraphTest {
  
  
  protected Graph graph;
  /**
   * @throws java.lang.Exception
   */
  @BeforeEach
  void setUp() throws Exception {
       graph = new Graph();
  }
  
  /**
   * @throws java.lang.Exception
   */
  @AfterEach
  void tearDown() throws Exception {}
  
  /**
   * tests add a valid vertex
   */
  @Test
  void testGraph_001_add_a_null_vertex() {
    try {
      graph.addVertex("a"); 
      assert(graph.order() == 1);
    }
    catch(Exception e) {
      fail("Could not add a valid vertex");
    }
  }
  
  /**
   * tests adding 10 vertices
   */
  @Test
  void testGraph_002_adding_10_vertices() {
    try {
      for(int i = 0; i <= 9; i++)
        graph.addVertex(Integer.toString(i));
      assert(graph.order() == 10);      
    }
    catch(Exception e) {
      fail("Could not add a valid vertex");
    }
  }
  
  /**
   * tests adding 1 vertex and removing it
   */
  @Test
  void testGraph_003_add_and_removing_vertex() {
    try {
      graph.addVertex("a");
      graph.removeVertex("a");
      assert(graph.numVertices == 0);
    }
    catch(Exception e) {
      fail("Could not remove a valid vertex");
    }
  }
  
  /**
   * tests adding 10 vertices and removing them
   */
  @Test
  void testGraph_004_add_and_removing_10_vertices() {
    try {
      for(int i = 0; i <= 9; i++)
        graph.addVertex(Integer.toString(i));
      assert(graph.order() == 10);
      for(int i = 0; i <= 9; i++)
        graph.removeVertex(Integer.toString(i));
      assert(graph.order() == 0);
    }
    catch(Exception e) {
      fail("Could not remove valid vertices");
    }
  }
  
  /**
   * tests adding 2 vertices and adding an edge between them
   */
  @Test
  void testGraph_003_adding_an_edge_between_2_vertices() {
    try {
      graph.addVertex("a");
      graph.addVertex("b");
      graph.addEdge("a", "b");
      assert(graph.numVertices == 2);
      assert(graph.numEdges == 1);      
    }
    catch(Exception e) {
      fail("Could not add valid edge");
    }
  }
  
  /**
   * tests adding 1 vertex and adding an edge
   */
  @Test
  void testGraph_004_adding_an_edge_between_2_vertices() {
    try {
      graph.addVertex("a");      
      graph.addEdge("a", "b");
      assert(graph.numVertices == 2);
      assert(graph.numEdges == 1);      
    }
    catch(Exception e) {
      fail("Could not add valid edge");
    }
  }
  
  /**
   * tests adding an edge and then removing it
   */
  @Test
  void testGraph_005_removing_an_edge() {
    try {
      graph.addVertex("a");      
      graph.addEdge("a", "b");
      graph.removeEdge("a", "b");  
      assert(graph.numVertices == 2);
      assert(graph.numEdges == 0);
    }
    catch(Exception e) {
      fail("Could not remove valid edge");
    }
  }
  
  /**
   * tests adding an edge and then a vertex associated with that edge
   */
  @Test
  void testGraph_006_removing_vertex_associated_with_edge() {
    try {
      graph.addVertex("a");      
      graph.addEdge("a", "b");
      graph.addEdge("a", "c");
      graph.removeVertex("b");
      assert(graph.numVertices == 2);
      assert(graph.numEdges == 1);
    }
    catch(Exception e) {
      e.printStackTrace();
      fail("Could not remove valid edge");
    }
  }
  
  /**
   * tests Cycle detection for 5 graph cases
   */
  @Test
  void testGraph_007_test_cycle_detection() {
    Graph graph1 = new Graph();
    graph1.addVertex("1"); graph1.addVertex("2"); graph1.addVertex("3");
    graph1.addEdge("1", "2"); graph1.addEdge("2", "3"); graph1.addEdge("1", "3");    
    assert (graph1.cycle(graph1) == false);

    Graph graph2 = new Graph();
    graph2.addVertex("1"); graph2.addVertex("2"); graph2.addVertex("3");
    graph2.addEdge("1", "2"); graph2.addEdge("2", "3"); graph2.addEdge("3", "1");    
    assert (graph2.cycle(graph2) == true);
  
    Graph graph3 = new Graph();
    graph3.addVertex("1"); graph3.addVertex("2"); graph3.addVertex("3"); graph3.addVertex("4"); graph3.addVertex("5"); 
    graph3.addEdge("1", "2"); graph3.addEdge("2", "3"); graph3.addEdge("2", "4"); graph3.addEdge("3", "4"); graph3.addEdge("4", "5");    
    assert (graph3.cycle(graph3) == false);


    Graph graph4 = new Graph();
    graph4.addVertex("1"); graph4.addVertex("2"); graph4.addVertex("3"); graph4.addVertex("4"); graph4.addVertex("5"); 
    graph4.addEdge("1", "2"); graph4.addEdge("2", "3"); graph4.addEdge("2", "4"); graph4.addEdge("3", "4"); graph4.addEdge("4", "5");  graph4.addEdge("5", "2");
    assert (graph4.cycle(graph4) == true);

    // disconnected graph.
    Graph graph5 = new Graph();
    graph5.addVertex("1"); graph5.addVertex("2"); graph5.addVertex("3"); graph5.addVertex("10"); graph5.addVertex("11");
    graph5.addEdge("1", "2"); graph5.addEdge("2", "3"); graph5.addEdge("3", "1"); graph5.addEdge("10", "11"); 
    assert (graph5.cycle(graph5) == true);
  
    //disconnected graph
    Graph graph6 = new Graph();
    graph6.addVertex("A"); graph6.addVertex("B"); graph6.addVertex("C"); graph6.addVertex("D"); graph6.addVertex("E"); graph6.addVertex("6");
    graph6.addEdge("A", "B"); graph6.addEdge("A", "C"); graph6.addEdge("B", "C"); graph6.addEdge("D", "E"); graph6.addEdge("D", "F");
    assert (graph6.cycle(graph6) == false);
    
    Graph graph7 = new Graph();
    graph7.addVertex("A"); graph7.addVertex("B"); graph7.addVertex("C"); 
    graph7.addEdge("A", "B"); graph7.addEdge("A", "C"); graph7.addEdge("B", "C"); graph7.addEdge("C", "A");
    assert (graph7.cycle(graph7) == true);
    
  }
  
  
  /**
   * tests topological sort
   */
  
  @Test
  void testGraph_008_topologicalOrder() {
    
      try {
        graph.addVertex("5");      
        graph.addEdge("5", "2");        
        graph.addEdge("5", "0");
        graph.addEdge("4", "0");
        graph.addEdge("4", "1");
        graph.addEdge("2", "3");
        graph.addEdge("3", "1");
        List<String> list = graph.topologicalSort();
        
       String str = "";
       int i = 0;
        while(i<list.size())
        {          
          str+=list.get(i);
          i++;
        }
        
        assert (str.equals("132054") == true);
      }
      catch(Exception e) {
        e.printStackTrace();
        fail("Not expected");
      }
    }
  
  
  /**
   * tests maximum dependency package
   */
  
  @Test
  void testGraph_009_maxpackageDependency() {
    try {
      graph.addVertex("5");      
      graph.addEdge("5", "2");        
      graph.addEdge("5", "0");
      graph.addEdge("4", "0");
      graph.addEdge("4", "1");
      graph.addEdge("2", "3");
      graph.addEdge("3", "1");
      assert(graph.maxPackageDependency().equals("4") == true);     
     
    }
    catch(Exception e) {
      e.printStackTrace();
      fail("Not expected");
    }
    
    
  }
  
  /**
   * tests installing a new package given installation of another package
   */
  
  @Test
  void testGraph_010_installNewPackage() {
    try {
      graph.addVertex("A");      
      graph.addEdge("A", "B");        
      graph.addEdge("A", "C");
      graph.addEdge("B", "D");
      
      System.out.print(graph.installNewPackage(0,1));     
     
    }
    catch(Exception e) {
      e.printStackTrace();
      fail("Not expected");
    }
    
    
  }

}
