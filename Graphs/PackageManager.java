import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



/**
 * Filename:   PackageManager.java
 * Project:    p4
 * Authors:    
 * 
 * PackageManager is used to process json package dependency files
 * and provide function that make that information available to other users.
 * 
 * Each package that depends upon other packages has its own
 * entry in the json file.  
 * 
 * Package dependencies are important when building software, 
 * as you must install packages in an order such that each package 
 * is installed after all of the packages that it depends on 
 * have been installed.
 * 
 * For example: package A depends upon package B,
 * then package B must be installed before package A.
 * 
 * This program will read package information and 
 * provide information about the packages that must be 
 * installed before any given package can be installed.
 * all of the packages in
 * 
 * You may add a main method, but we will test all methods with
 * our own Test classes.
 */

public class PackageManager {
    
    private Graph graph;
    
    /*
     * Package Manager default no-argument constructor.
     */
    public PackageManager() {
        graph = new Graph();
    }
    
    /**
     * Takes in a file path for a json file and builds the
     * package dependency graph from it. 
     * 
     * @param jsonFilepath the name of json data file with package dependency information
     * @throws FileNotFoundException if file path is incorrect
     * @throws IOException if the give file cannot be read
     * @throws ParseException if the given json cannot be parsed 
     */
    public void constructGraph(String jsonFilepath) throws FileNotFoundException, IOException, ParseException {
      try {
        try {
          Object obj = new JSONParser().parse(new FileReader(jsonFilepath));
          JSONObject jo = (JSONObject) obj;
          JSONArray packages = (JSONArray) jo.get("packages");
          
          Package[] packageArray = new Package[packages.size()];
          for(int i = 0; i < packageArray.length; i++)
          {
            JSONObject jsonPackage = (JSONObject) packages.get(i);
            String name = (String) jsonPackage.get("name");
            JSONArray dependencies = (JSONArray) jsonPackage.get("dependencies");
            
            //add vertex to graph
            graph.addVertex(name);
            
            //convert JSON to String array
            ArrayList<String> depend = new ArrayList<String>();
            
            for(int j = 0; j < dependencies.size(); j++)
            {
              depend.add(dependencies.get(j).toString());
            }
            
            //Create edges between vertex and its dependencies
            for(int j = 0; j < depend.size(); j++)
            {
              graph.addEdge(name, depend.get(j));
            }
          }
        }
        catch(FileNotFoundException e) {
          System.out.print("File Not Found Exception was thrown");
        }
        catch(IOException e) {
          System.out.print("IO Exception was thrown");
        }    
      }
      catch(ParseException e) {
        System.out.print("Parse Exception was thrown");
      }      
            
   }
    
    /**
     * Helper method to get all packages in the graph.
     * 
     * @return Set<String> of all the packages
     */
    public Set<String> getAllPackages() {
        return graph.getAllVertices();
    }
    
    //gets package index in nodes array
    public int getPkgIndex(String pkg) {
      for(int i = 0; i < graph.nodes.size(); i++)
      {
        if(graph.nodes.get(i).viewVertex().equals(pkg) == true)
          return i;
      }
      return -1;
    }
    
    /**
     * Given a package name, returns a list of packages in a
     * valid installation order.  
     * 
     * Valid installation order means that each package is listed 
     * before any packages that depend upon that package.
     * 
     * @return List<String>, order in which the packages have to be installed
     * 
     * @throws CycleException if you encounter a cycle in the graph while finding
     * the installation order for a particular package. Tip: Cycles in some other
     * part of the graph that do not affect the installation order for the 
     * specified package, should not throw this exception.
     * 
     * @throws PackageNotFoundException if the package passed does not exist in the 
     * dependency graph.
     */
    public List<String> getInstallationOrder(String pkg) throws CycleException, PackageNotFoundException {
      List<String> list = new ArrayList<String>(); 
      
      //check if package is in graph
      for(int i = 0; i < graph.nodes.size(); i++) {
        if(graph.containsVertex(pkg) == false)
          throw new PackageNotFoundException();
      }
      
      if(graph.cycle(graph) == true)
        throw new CycleException();      
      
      list = installationOrder(pkg);
      return list;
    }
    
    private List<String> installationOrder(String pkg) {
      List<String> list = new ArrayList<String>();
      List<String> dependencies;
      String current = pkg;
      Queue<String> q = new LinkedList<>();
      q.add(current);
      while(!q.isEmpty())
      {
        current = q.peek();
        list.add(current);        
        dependencies = graph.getAdjacentVerticesOf(current);
        if(!dependencies.isEmpty())
        {
          for(int i = 0; i < dependencies.size(); i++)
          {
            q.add(dependencies.get(i));
          }
        }
        else break;        
      }
      q.remove();
      
      Collections.reverse(list);
      return list;
      
    }
    
    /**
     * Given two packages - one to be installed and the other installed, 
     * return a List of the packages that need to be newly installed. 
     * 
     * For example, refer to shared_dependecies.json - toInstall("A","B") 
     * If package A needs to be installed and packageB is already installed, 
     * return the list ["A", "C"] since D will have been installed when 
     * B was previously installed.
     * 
     * @return List<String>, packages that need to be newly installed.
     * 
     * @throws CycleException if you encounter a cycle in the graph while finding
     * the dependencies of the given packages. If there is a cycle in some other
     * part of the graph that doesn't affect the parsing of these dependencies, 
     * cycle exception should not be thrown.
     * 
     * @throws PackageNotFoundException if any of the packages passed 
     * do not exist in the dependency graph.
     */
    public List<String> toInstall(String newPkg, String installedPkg) throws CycleException, PackageNotFoundException {
      List<String> list = new ArrayList<String>(); 
      
      //check if newPkg is in graph
      for(int i = 0; i < graph.nodes.size(); i++) {
        if(graph.containsVertex(newPkg) == false)
          throw new PackageNotFoundException();
      }      
    //check if installedPkg is in graph
      for(int i = 0; i < graph.nodes.size(); i++) {
        if(graph.containsVertex(installedPkg) == false)
          throw new PackageNotFoundException();
      }
     //check cycle exception 
      if(graph.cycle(graph) == true)
        throw new CycleException();  
      //change package name to its index in nodes array
      int nodeIndex1 = graph.getNodeIndex(newPkg);
      int nodeIndex2 = graph.getNodeIndex(installedPkg);
      
      list = graph.installNewPackage(nodeIndex1, nodeIndex2);
      return list;
    }
    
    
    
    /**
     * Return a valid global installation order of all the packages in the 
     * dependency graph.
     * 
     * assumes: no package has been installed and you are required to install 
     * all the packages
     * 
     * returns a valid installation order that will not violate any dependencies
     * 
     * @return List<String>, order in which all the packages have to be installed
     * @throws CycleException if you encounter a cycle in the graph
     */
    public List<String> getInstallationOrderForAllPackages() throws CycleException {
      //check cycle exception 
      if(graph.cycle(graph) == true)
        throw new CycleException(); 
      
      
      return graph.topologicalSort();
    }
   
    
    /**
     * Find and return the name of the package with the maximum number of dependencies.
     * 
     * Tip: it's not just the number of dependencies given in the json file.  
     * The number of dependencies includes the dependencies of its dependencies.  
     * But, if a package is listed in multiple places, it is only counted once.
     * 
     * Example: if A depends on B and C, and B depends on C, and C depends on D.  
     * Then,  A has 3 dependencies - B,C and D.
     * 
     * @return String, name of the package with most dependencies.
     * @throws CycleException if you encounter a cycle in the graph
     */
    public String getPackageWithMaxDependencies() throws CycleException {
    //check cycle exception 
      if(graph.cycle(graph) == true)
        throw new CycleException();  
      
      return graph.maxPackageDependency();
    }

    public static void main (String [] args) {
        System.out.println("PackageManager.main()");
    }
    
}
