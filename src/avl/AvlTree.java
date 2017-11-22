/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avl;

import java.util.*;

/**
 *
 * @author Eugenia
 */
public class AvlTree<T1 extends Comparable<T1>,T2> implements Map<T1, T2> {
    private Node<T1, T2> root;
    private int size = 0;

    public AvlTree() {
        root = null;
    }
    
    private int height(Node node) {
        if (node == null)
            return -1;
        return node.height;
    }
    private int balance(Node node) {
        return (height(node.left)-height(node.right));
    }
    
    public T2 find(T1 key) {
       T2 result = find(root, key);       
       return result;
    }
    
    private T2 find(Node current, T1 key) {
        T2 result = null;
        int comparison = key.compareTo((T1)current.data.getKey());
        if (comparison >0)
            result = find(current.right, key);
        if (comparison <0)
            result = find(current.left, key);
        else 
            result = (T2)current.data.getValue();
        if (result == null) 
            return null;
        else 
            return result;   
            
    }
    
    public Node turnRight (Node pivot) {        //pivot- узел,вокруг которого вращаем
        Node node = pivot.left;                 // node - кого вращаем
        pivot.left = node.right;
        node.right = pivot;
        pivot.height = 1+ Math.max(height(pivot.left), height(pivot.right));
        node.height = 1+ Math.max(height(node.left), height(node.right));
        return node;
    }
    
    public Node turnLeft (Node pivot) {        //pivot- узел,вокруг которого вращаем
        Node node = pivot.right;
        pivot.right = node.left;
        node.left = pivot;
        pivot.height = 1+ Math.max(height(pivot.left), height(pivot.right));
        node.height = 1+ Math.max(height(node.left), height(node.right));
        return node;
    }
    
  
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size() == 0);
    }

    @Override
    public boolean containsKey(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T2 get(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T2 put(T1 key, T2 value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T2 remove(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void putAll(Map<? extends T1, ? extends T2> m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<T1> keySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<T2> values() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Entry<T1, T2>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
}
