/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avl;

/**
 *
 * @author Eugenia
 */
public class Node<T1, T2> {
    Data<T1,T2> data;
    Node left;
    Node right;
    int height;

    public Node(Data data, int height) {
           this.data = data;
           this.height = height;
    }
    
    
}
