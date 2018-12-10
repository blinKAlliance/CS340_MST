/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs340_project4;

/**
 *
 * @author Jordan
 */
public class Edge implements Comparable< Edge > {
    
    int u;
    int v;
    double weight;
       
    @Override
    public int compareTo( Edge t ) {

        if( t.weight < this.weight ) {
            return ( int )t.weight;
        } else {
            return ( int )this.weight;
        }    
    }
}
