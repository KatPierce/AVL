/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.*;
import java.util.Map.Entry;
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
        tree.add(6, "b");
        tree.add(3, "c");
        tree.add(5, "c");
        tree.add(9, "g");
        
     //   System.out.println( tree.find(10));
//        tree.remove(90);
//        System.out.println( tree);
        
//        int a = tree.size();
//        System.out.println(tree.size());
//        tree.remove(7);
//        tree.print2();
        //Iterator it = tree.iterator();
        Set<Entry<Integer, String>> lalala = tree.entrySet();
        System.out.println(lalala);
         System.out.println(tree.size());
        lalala.remove(7);
         System.out.println(tree.size());
        lalala.add(new Data (10, "kkkk"));
        System.out.println(lalala);
        tree.add(8, "th");
        System.out.println(tree.size());
        tree.remove(9);
        System.out.println(tree.size());
        System.out.println(tree);
        System.out.println(tree.entrySet());
        System.out.println(tree.values());
        
//        while (it.hasNext()) {
//            System.out.println(it.next());
//        }

    }

}
