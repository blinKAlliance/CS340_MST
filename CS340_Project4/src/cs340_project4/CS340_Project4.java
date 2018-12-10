/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs340_project4;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 *
 * @author Jordan
 */
public class CS340_Project4 {

    /**
     * @param args the command line arguments
     */
    public static void main( String[] args ) {
        
        int vertex = 0;
        Scanner keyboard = new Scanner( System.in );
        
        System.out.println( "Starting Graph: \n" );
        
        // Creates Graphs
        Graph newGraph = readGraph( "graph.txt" );
        Graph newGraph2 = readGraph( "graph.txt" );
        
        // Prints Starting Graph
        printGraph( newGraph, "" );
        
        System.out.println( "What is your starting vertex?" );
        
        vertex = keyboard.nextInt();
        
        // Creates Prim and Dijkstra Graphs
        newGraph.prim( vertex );
        newGraph2.dijkstra( vertex );
              
    }
    
    public static Graph readGraph( String fileName ) {
        
        Graph graph = new Graph();
        Scanner inFile = null;
        
        try {
                inFile = new Scanner( new File( fileName ) );
            } catch ( FileNotFoundException e ) {
                System.err.println( "File not found." );
                
            }
        
        
        // Reads graph of any size from input file
        while( inFile.hasNextLine() ) {
            String tempVal = inFile.nextLine();
            String[] tempArray = tempVal.split( " " );
            
            Node newNode = new Node();
            
            newNode.id = Integer.parseInt( tempArray[ 0 ] );
            
            for(int i = 1; i < tempArray.length; i++ ) {
                
                Edge newEdge = new Edge();
                
                newEdge.u = newNode.id;
                newEdge.v = Integer.parseInt( tempArray[ i ] );
                
                i++;
                
                newEdge.weight = Double.parseDouble( tempArray[ i ] );
                newNode.edges.add(newEdge);
            }
            
            graph.graph.add( newNode );
        }
        
        return graph;
    }
    
    public static void printGraph( Graph graphToPrint, String fileName ) {
        
        for( int i = 0; i < graphToPrint.graph.size(); i++ ) {
            
            System.out.print( graphToPrint.graph.get( i ).id + " " );
            
            
            for (int j = 0; j < graphToPrint.graph.get( i ).edges.size(); j++ ) {
                
                System.out.print( graphToPrint.graph.get( i ).edges.get( j ).v + " " + graphToPrint.graph.get( i ).edges.get( j ).weight + " " );
            }
            System.out.println();
        }
        System.out.println();
    } 
}
