/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;




/**
 *
 * @author Eugenia
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AvlTree<Integer, String> tree = new AvlTree();
       
        tree.add(7, "c");
         tree.add(4, "a");
        tree.add(3, "b");
        tree.add(2, "c");
        tree.add(5, "c");
        tree.add(5, "g");
//        int a = tree.size();
//        System.out.println(tree.size());
//        tree.remove(7);
//        tree.print2();
        Iterator it = tree.iterator();
       while (it.hasNext()) {
        System.out.println(it.next());
       }     

    }
    
}
