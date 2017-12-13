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
    
    private int height(Node<T1, T2> node) {
        if (node == null)
            return -1;
        return node.height;
    }
    private int balanceCoef(Node<T1, T2> node) {
        return (height(node.left)-height(node.right));        
    }
    
    private Node<T1, T2> rebalance (Node<T1, T2> node) {
        int coef = balanceCoef(node);
        if (coef <-1) {
            if (balanceCoef(node.right)>0)
                node.right = turnRight(node.right);
            node = turnLeft(node);
        }
        else 
            if (coef > 1) {
                if (balanceCoef(node.left)<0) {
                    node.left = turnLeft(node.left);
                }
                node = turnRight(node);
            }
        return node;    
    }
    
    public void add( T1 key, T2 value) {
        Data<T1,T2> data = new Data(key, value);
        root = add(data,root);
    }
    
    private Node<T1, T2> add(Data<T1,T2> data, Node<T1, T2> node) {
        if (node == null)  {       
            size++;
            return new Node (data, 0);
        }    
        int comparison = ((T1)data.getKey()).compareTo((T1)node.data.getKey());
        if (comparison > 0)
            node.right = add(data, node.right);
        else 
            if (comparison <0) 
                node.left = add(data, node.left);
            else {
                node.data.setValue(data.getValue());
                return node;
            }
        node.height = 1+ Math.max(height(node.left), height(node.right));
        node = rebalance(node);          
        return node;
    }
    
    
    public T2 find(T1 key) {
       T2 result = find(root, key);       
       return result;
    }
    
    private T2 find(Node<T1, T2> current, T1 key) {
        T2 result = null;
        int comparison = key.compareTo((T1)current.data.getKey());
        if (comparison >0)
            result = (T2)find(current.right, key);
        else 
            if (comparison <0)
                result = (T2)find(current.left, key);
            else 
                result = (T2)current.data.getValue();
        if (result == null) 
            return null;
        else 
            return result;   
            
    }    
  
    
    
    public void remove( T1 key ) {
        root = remove(key, root);
    }
    
    private Node remove(T1 key, Node<T1, T2> node) {
        if (node == null || find(key) == null)   
            return node;         
        int comparison = key.compareTo( (T1) node.data.getKey());
        if (comparison < 0) {  
            node.left = remove(key, node.left);
        }
        else
            if (comparison > 0) { 
            node.right = remove( key, node.right);
        }
        else { 
            if (node.left == null) { 
                return node.right;
            }
            else
                if (node.right == null) { 
                return node.left;
                }
                else {  
                    Node<T1, T2> nodeToRemove = node;
                    node = maxInSubtree(nodeToRemove.right);
                    node.right = replaceMax(nodeToRemove.right);
                    node.left = nodeToRemove.left;

                }
        }
        node.height = 1 + Math.max(height(node.left), height(node.right));
        return rebalance(node);    
    }

        private Node maxInSubtree( Node<T1, T2> node ) 
    {
        if( node == null )
            return node;
        while( node.right != null )
            node = node.right;         
        return node;
    }
    private Node replaceMax(Node<T1, T2> node) {
        if (node.right == null) 
            return node.left;
        node.right = replaceMax(node.right);
        node.height = 1 + Math.max(height(node.left), height(node.right));
        return rebalance(node);
    }  

    
    //////////////////////////////////////////////
    
    
    
   //повороты     
    private Node<T1, T2> turnRight (Node<T1, T2> pivot) {        //pivot- узел,вокруг которого вращаем
        Node<T1, T2> node = pivot.left;                 // node - кого вращаем
        pivot.left = node.right;
        node.right = pivot;
        pivot.height = 1+ Math.max(height(pivot.left), height(pivot.right));
        node.height = 1+ Math.max(height(node.left), height(node.right));
        return node;
    }
    
    private Node<T1, T2> turnLeft (Node<T1, T2> pivot) {        //pivot- узел,вокруг которого вращаем
        Node<T1, T2> node = pivot.right;
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
      if ((isEmpty() || find((T1)key) == null))
          return false;
      return true;
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T2 get(Object key) {
        return find((T1)key);
    }

    @Override
    public T2 put(T1 key, T2 value) {
        if (containsKey(key)) {
           T2 result = find(key); 
           add(key,value);
           return result;
        }
        add(key,value);
        return null;         
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
        root = null;
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
    
    //iterator 
//    public class BinaryTreeIterator implements Iterator<Node<T1, T2>> {
//
//        @Override
//        public boolean hasNext() {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public Node<T1, T2> next() {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//        
//        private Node<T1, T2> findNext( Node<T1, T2> node) {
//            
//        }
//
//       
//    }
    



     
}
