/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs340_project4;
import java.util.ArrayList;
/**
 *
 * @author Jordan
 */
public class Node implements Comparable< Node > {

    int id;
    ArrayList< Edge > edges;
    Double key;
    int parent;
    
    Node() {
        ArrayList< Edge > edge = new ArrayList< Edge > ();
        edges = edge;
    }
    
    @Override
    public int compareTo(Node t) {
 
        if( t.key < this.key ) { return t.id;
        } else { return this.id; }    
    }
    
    // Search used in Prim
    public void locateKeyPrim() {
        
      if( !edges.isEmpty() ) {
          
        
        int smallest = Integer.MAX_VALUE;
        int location = 0;
        
        for( int i = 0; i < edges.size(); i++ ) {
            
            if( smallest > edges.get( i ).weight ) {
                
                smallest = (int)edges.get( i ).weight;
                key = (double)edges.get( i ).weight;
                parent = edges.get( i ).v;
                
                location = i;
            }
        }
        
        edges.remove( location );
      }
        
    }
    
    // Search used in Dijkstra
    public void locateKeyDijkstra( double distance ) {
        
      if( !edges.isEmpty() ) {
          
        
        int smallest = Integer.MAX_VALUE;
        int location = 0;
        
        for( int i = 0; i < edges.size(); i++ ) {
            
            if( smallest > edges.get( i ).weight + distance ) {
                
                smallest = ( int )edges.get( i ).weight + ( int )distance;
                key = ( double )edges.get( i ).weight + distance;
                parent = edges.get( i ).v;
                
                location = i;
            }
        }
        
        edges.remove( location );
      }
        
    }
    
}
