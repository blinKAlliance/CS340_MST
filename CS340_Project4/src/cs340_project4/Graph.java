/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs340_project4;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
/**
 *
 * @author Jordan
 */
public class Graph {
    ArrayList<Node> graph;
    
    Graph() {
        ArrayList< Node > graph2 = new ArrayList< Node >();
        graph = graph2;
    }
    
    
    public void addEdge( int u, int v, int weight ) {
        
        Edge e = new Edge();
        Edge backEdge = new Edge();
        
        e.u = u;
        e.v = v;
        
        backEdge.u = v;
        backEdge.v = u;
        
        e.weight = weight;
        backEdge.weight = weight;
        
        Node nodeU = new Node();
        Node nodeV = new Node();
        
        nodeU.edges.add(e);
        nodeV.edges.add(backEdge);

        try { graph.get( u ).edges.add(e);
        } catch ( IndexOutOfBoundsException ex ) { graph.add( u, nodeU ); }
        
        try { graph.get( v ).edges.add(backEdge);
        } catch ( IndexOutOfBoundsException ex ) { graph.add( v, nodeV ); }
    }
    
    public void prim( int r ) {
        
        PriorityQueue queue = new PriorityQueue();
        
        String[] array = new String[ graph.size() ];
        boolean[] foundArray = new boolean[ graph.size() ];
            
        for(int k = 0; k < foundArray.length; k++) {
            foundArray[k] = false;
        }

        graph.get(r).locateKeyPrim();
        
        queue.insert(graph.get(r));
        
        Node newNode = graph.get(r);
        
        while( !queue.isEmpty() ) {
            
            newNode = queue.extractMin();
            
            int nodeParent = newNode.parent;
            int nodeId = newNode.id;
            
            
            
            if(foundArray[newNode.parent] == false ){
                if(array[ nodeId ] == null){ array[ nodeId ] = newNode.parent + " " + newNode.key;
                } else{ array[ nodeId ] = array[nodeId] + " " + newNode.parent + " " + newNode.key; }
                
                if(array[ nodeParent ] == null){ array[ nodeParent ] = newNode.id + " " + newNode.key;
                } else{ array[ nodeParent ] =  array[ nodeParent ] + " " + newNode.id + " " + newNode.key; }
              
                foundArray[ newNode.id ] = true;
                foundArray[ newNode.parent ] = true;
            
            }       
            
            boolean currTruth = false;
        
            for(int i = 0; i < queue.priorityQueueArray.length; i++){
                if(queue.priorityQueueArray[i].id == newNode.parent){
                    currTruth = true;
                }
            }

            if(!currTruth && graph.get(newNode.parent).edges.size() > 0){
                graph.get(newNode.parent).locateKeyPrim();
                queue.insert(graph.get(newNode.parent));
            }

            if(newNode.edges.size() > 0){
                newNode.locateKeyPrim();
                queue.insert(newNode);
            }
        }   
            
        PrintStream oFile = null;
            
        try { // Makes appropriate oFile Name
                oFile = new PrintStream( new File( "primout.txt" ) );          
        } catch ( IOException ex ) {
            System.err.println( "File not found." );
            return;
        }
        
        for(int j = 0; j < array.length; j++) {
            oFile.println(j + " " + array[j]);
        }
    }
        
    
    public void dijkstra( int source ) {
        
        PriorityQueue queue = new PriorityQueue();
        
        String[] array = new String[ graph.size() ];
        boolean[] foundArray = new boolean[ graph.size() ];
        double [] distance = new double[ graph.size() ];    
        
        for( int i = 0; i < foundArray.length; i++ ) {
            foundArray[ i ] = false;
        }
        
        for( int k = 0; k < foundArray.length; k++ ) {
            distance[ k ] = Integer.MAX_VALUE;
        }
        
        distance[ source ] = 0; // Distance to source will always be 0
        
        graph.get( source ).locateKeyDijkstra( distance[ source ] );

        queue.insert( graph.get( source ) ); // Adds to queue
        
        Node newNode = graph.get( source );
        
        int counter = 0;
        
        while( !queue.isEmpty() ) {
            
            newNode = queue.extractMin();
            
            int nodeParent = newNode.parent;
            int nodeId = newNode.id;
  

            if(foundArray[ newNode.parent ] == false ){
                
                if( array[ nodeId ] == null ){ array[ nodeId ] = newNode.parent + " " + newNode.key;
                } else{ array[ nodeId ] =  array[ nodeId ] + " " + newNode.parent + " " + newNode.key; }
                
                if( array[ nodeParent ] == null ){ array[ nodeParent ] = newNode.id + " " + newNode.key;
                } else{ array[ nodeParent ] =  newNode.id + " " + newNode.key + " " + array[ nodeParent ]; }
                
              
                foundArray[ newNode.id ] = true;
                foundArray[ newNode.parent ] = true;
            
            }
            
            boolean currTruth = false;
        
            for( int i = 0; i < queue.priorityQueueArray.length; i++ ) {
                if( queue.priorityQueueArray[ i ].id == newNode.parent ){
                    currTruth = true;
                }
            }

            if( !currTruth && graph.get( newNode.parent ).edges.size() > 0 ) {
                
                distance[ newNode.parent ] = newNode.key;
                
                graph.get( newNode.parent ).locateKeyDijkstra( distance[ newNode.parent ] );
                queue.insert( graph.get( newNode.parent ) );
            }

            if( newNode.edges.size() > 0 ) {
                
                newNode.locateKeyDijkstra( distance[ newNode.id ] );
                queue.insert( newNode );
            }
        }   
        
        // Prints to Out File
        PrintStream oFile = null;
            
        try { // Makes appropriate oFile Name
                oFile = new PrintStream( new File( "dijkstraout.txt" ) );          
        } catch ( IOException ex ) {
            System.err.println( "File not found." );
            return;
        }
        
        for( int j = 0; j < array.length; j++ ) {
            oFile.println( j + " " + array[ j ] );
        }
            
    }
}
