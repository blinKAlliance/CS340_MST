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
public class PriorityQueue {
    Node[] priorityQueueArray;
    int heapSize;
    int[] locationTable;
    
    // From Project 1
    public void sort( ) {
        int heapSizeSort = priorityQueueArray.length - 1;
        heapSize = priorityQueueArray.length;
       
        
        for( int i = ( heapSize / 2 ); i >= 0; i-- ) { // Builds Heap
            heapify( i );
        }

        for ( int i = heapSizeSort; i >= 0; i-- ) { // Gets element from heap
            
            heapify( i );
        } 
    }
    
    // From Project 1
    public void heapify( int i ) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int smallest = i; // Root

        double left2 = 999999999;
        double right2 = 999999999;
        
        
        if( left < heapSize ) {
            left2 = priorityQueueArray[ left ].key;
        }
        
        if( right < heapSize ) {
            right2 = priorityQueueArray[ right ].key;
        }
        
        double smallest2 = priorityQueueArray[ smallest ].key;
        
        if ( left < heapSize && left2 < smallest2 ) { // Checks if left sub-node is larger than heap's root
            smallest = left;
            smallest2 = left2;
        }
        
        if ( right < heapSize && right2 < smallest2 ) { // Checks if right sub-node is larger than heap's root
            smallest = right;
            smallest2 = right2;
        }
        
        if ( smallest != i ) { // Checks if Largest isn't actually the root
            
            // swap the nodes
            Node temp = priorityQueueArray[ smallest ];
            priorityQueueArray[ smallest ] = priorityQueueArray[ i ];
            priorityQueueArray[ i ] = temp;
            
            // adjust the location table based on the two moved nodes
            locationTable[ priorityQueueArray[ smallest ].id ] = smallest;
            locationTable[ priorityQueueArray[ i ].id ] = i;
           
            heapify( smallest ); // Recursive Call
        }
    }
    
    public boolean isEmpty() {
        return heapSize == 0;
    }
    
    public void insert( Node n ) {
        int length = 0;
        int length2 = n.id + 1;
        
        if( this.isEmpty() ) {
            
            Node[] pqa = new Node[ 1 ];
            int[] locTab = new int[ 1 ];
            
            priorityQueueArray = pqa;
            locationTable = locTab;
            
            heapSize = 1;
            length = 1;
        } else {
            length = priorityQueueArray.length + 1;
            
            heapSize++;
            
            for( int i = 0; i < priorityQueueArray.length; i++ ) {
                
                if( priorityQueueArray[ i ].id > length2 ) {
                    length2 = priorityQueueArray[ i ].id;
                }
            }
        }  
        
        Node [] array2 = new Node[length];
        int [] location = new int[ length2 + 1 ];
        
       
        for(int i = 0; i < length - 1; i++) {
            array2[ i ] = priorityQueueArray[ i ];
            location[ i ] = locationTable[ i ];
        }
        
        array2[ length - 1 ] = n;
        location[ array2[ length - 1 ].id ] = 0;
        
        priorityQueueArray = array2;
        locationTable = location;
        
        if( heapSize > 1 ) {
            sort();
        }
        
     
        
        sort();
    }
    
    
    public Node extractMin() {
        
            Node minNode = priorityQueueArray[0];

            int length = priorityQueueArray.length - 1;

            Node [] array2 = new Node[length];

            for(int i = 1; i <= length; i++) {
                array2[ i - 1 ] = priorityQueueArray[ i ];
            }

            priorityQueueArray = array2;
            heapSize--;
            
            if( !isEmpty() ) {
                sort();
            }

            return minNode;
        
    }
    
    // Runs in O(1) Time
    public void decreaseKey( int nodeId, double key ) {
        
        priorityQueueArray[ locationTable[ nodeId ] ].key = key;
        sort();
    }
}
