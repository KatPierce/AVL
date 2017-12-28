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
public class AvlTree<T1 extends Comparable<T1>, T2> implements Map<T1, T2> {

    private Node<T1, T2> root;
    private int size = 0;

    public AvlTree() {
        root = null;
    }

    private int height(Node<T1, T2> node) {
        if (node == null) {
            return -1;
        }
        return node.height;
    }

    private int balanceCoef(Node<T1, T2> node) {
        return (height(node.left) - height(node.right));
    }

    private Node<T1, T2> rebalance(Node<T1, T2> node) {
        int coef = balanceCoef(node);
        if (coef < -1) {
            if (balanceCoef(node.right) > 0) {
                node.right = turnRight(node.right);
            }
            node = turnLeft(node);
        } else if (coef > 1) {
            if (balanceCoef(node.left) < 0) {
                node.left = turnLeft(node.left);
            }
            node = turnRight(node);
        }
        return node;
    }

    public boolean add(T1 key, T2 value) {
        Data<T1, T2> data = new Data(key, value);
        root = add(data, root);
        updatedelegate();
        return true;

    }

    private Node<T1, T2> add(Data<T1, T2> data, Node<T1, T2> node) {
        if (node == null) {
            size++;
            return new Node(data, 0);
        }
        int comparison = (data.getKey()).compareTo(node.data.getKey());
        if (comparison > 0) {
            node.right = add(data, node.right);
        } else if (comparison < 0) {
            node.left = add(data, node.left);
        } else {
            node.data.setValue(data.getValue());
            return node;
        }
        node.height = 1 + Math.max(height(node.left), height(node.right));
        node = rebalance(node);
        return node;
    }

    public T2 find(T1 key) {
        return find(root, key);        
    }

    private T2 find(Node<T1, T2> current, T1 key) {
        T2 result;
        int comparison = key.compareTo(current.data.getKey());
        if (comparison > 0) {
            result = (T2) find(current.right, key);
        } else if (comparison < 0) {
            result = (T2) find(current.left, key);
        } else {
            result = current.data.getValue();
        }
        return result;
    }

    public void remove(T1 key) {
        root = remove(key, root);
        size--;
    }

    private Node remove(T1 key, Node<T1, T2> node) {
        if (node == null || find(key) == null) {
            return node;
        }
        int comparison = key.compareTo(node.data.getKey());
        if (comparison < 0) {
            node.left = remove(key, node.left);
        } else if (comparison > 0) {
            node.right = remove(key, node.right);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                Node<T1, T2> nodeToRemove = node;
                node = maxInSubtree(nodeToRemove.left);
                node.left = replaceMax(nodeToRemove.left);
                node.right = nodeToRemove.right;

            }
        }
        node.height = 1 + Math.max(height(node.left), height(node.right));
        return rebalance(node);
    }

    private Node maxInSubtree(Node<T1, T2> node) {
        if (node == null) {
            return null;
        }
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    private Node replaceMax(Node<T1, T2> node) {
        if (node.right == null) {
            return node.left;
        }
        node.right = replaceMax(node.right);
        node.height = 1 + Math.max(height(node.left), height(node.right));
        return rebalance(node);
    }

    //////////////////////////////////////////////
    //повороты     
    private Node<T1, T2> turnRight(Node<T1, T2> pivot) {        //pivot- узел,вокруг которого вращаем
        Node<T1, T2> node = pivot.left;                 // node - кого вращаем
        pivot.left = node.right;
        node.right = pivot;
        pivot.height = 1 + Math.max(height(pivot.left), height(pivot.right));
        node.height = 1 + Math.max(height(node.left), height(node.right));
        return node;
    }

    private Node<T1, T2> turnLeft(Node<T1, T2> pivot) {        //pivot- узел,вокруг которого вращаем
        Node<T1, T2> node = pivot.right;
        pivot.right = node.left;
        node.left = pivot;
        pivot.height = 1 + Math.max(height(pivot.left), height(pivot.right));
        node.height = 1 + Math.max(height(node.left), height(node.right));
        return node;
    }

//////
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
        return (!isEmpty() && find((T1) key) != null);

    }

    @Override
    public boolean containsValue(Object value) {
        if (isEmpty()) {
            return false;
        }
        ArrayList<T2> mas = (ArrayList) values();
        return mas.contains(value);

    }

    @Override
    public T2 get(Object key) {
        return find((T1) key);
    }

    @Override
    public T2 put(T1 key, T2 value) {
        if (find(key) != null) {
            T2 result = find(key);
            add(key, value);
            updatedelegate();
            return result;
        }
        add(key, value);
        updatedelegate();
        return null;
    }

    @Override
    public T2 remove(Object key) {
        if (find((T1) key) != null) {
            T2 result = find((T1) key);
            remove((T1) key);
            updatedelegate();
            return result;
        }
        updatedelegate();
        return null;
    }

    @Override
    public void putAll(Map<? extends T1, ? extends T2> m) {
        for (Map.Entry entry : m.entrySet()) {
            put((T1) entry.getKey(), (T2) entry.getValue());
        }
        updatedelegate();
    }

    @Override
    public void clear() {
        root = null;
        updatedelegate();
    }

    @Override
    public Set<T1> keySet() {
        Set<T1> result = new TreeSet();
        Iterator it = iterator();
        while (it.hasNext()) {
            Node<T1, T2> current = (Node) it.next();
            result.add(current.data.getKey());
        }
        return result;
    }

    @Override
    public Collection<T2> values() {
        Collection<T2> result = new ArrayList<T2>();
        Iterator it = iterator();
        while (it.hasNext()) {
            Node<T1, T2> current = (Node) it.next();
            result.add(current.data.getValue());
        }
        return result;
    }

    @Override
    public Set<Entry<T1, T2>> entrySet() {
        updatedelegate();
        return delegate;
    }

    private SetTree<Entry<T1, T2>> delegate = new SetTree(new HashSet<>());

    private void updatedelegate() {
        delegate.clear();
        Iterator it = iterator();
        while (it.hasNext()) {
            Node current = (Node) it.next();
            delegate.add2(current.data);
        }
    }

// delegate
    private class SetTree<T3> extends AbstractSet<Entry<T1, T2>> {

        private Set<Entry<T1, T2>> delegate;

        public SetTree(Set<T3> delegate) {
            this.delegate = (Set<Entry<T1, T2>>) delegate;
        }

        @Override
        public boolean add(Entry<T1, T2> t) {
            return AvlTree.this.add(t.getKey(), t.getValue()) && delegate.add(t);
        }

        public void add2(Entry<T1, T2> t) {
            delegate.add(t);
        }

        public boolean remove(Object o) {
            Entry<T1, T2> result = null;
            for (Entry<T1, T2> t : this) {
                if (t.getKey() == o) {
                    result = t;
                    break;
                }
            }
            return delegate.remove(result) && (null != AvlTree.this.remove(o));
        }

        public Iterator<Entry<T1, T2>> iterator() {
            return delegate.iterator();
        }

        @Override
        public int size() {
            return delegate.size();
        }

        @Override
        public String toString() {
            return delegate.toString();
        }
    }

    //iterator 
    public class TreeIterator implements Iterator<Node<T1, T2>> {

        ArrayDeque<Node<T1, T2>> deque;

        public TreeIterator(Node<T1, T2> root) {
            deque = new ArrayDeque<>();
            while (root != null) {
                deque.push(root);
                root = root.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !deque.isEmpty();
        }

        @Override
        public Node<T1, T2> next() {
            Node<T1, T2> result = deque.pop();
            if (result.right != null) {
                Node<T1, T2> node = result.right;
                while (node != null) {
                    deque.push(node);
                    node = node.left;
                }
            }
            return result;
        }

    }

    public Iterator<Node<T1, T2>> iterator() {
        return new TreeIterator(root);
    }

    @Override
    public String toString() {
        List<List<Object>> str = toStr(root, 0, new ArrayList<>());
        StringBuilder result = new StringBuilder();
        int i = 5;
        for (List<Object> aList : str) {
            i--;
            result.append(spaceAdd((int) Math.pow(2.0, (double) i - 1)));
            for (Object bList : aList) {
                result.append(bList).append(spaceAdd((int) Math.pow(2.0, (double) i) - 1));
            }
            result.append("\n");
        }
        return result.toString();
    }

    private String spaceAdd(int i) {
        StringBuilder result = new StringBuilder();
        for (; i > 0; i--) {
            result.append(" ");
        }
        return result.toString();
    }

    private void addNull(int depth, List<List<Object>> list) {
        if (depth >= 7) {
            return;
        }
        if (list.size() <= depth) {
            list.add(new ArrayList<>());
        }
        list.get(depth).add(" ");
        addNull(depth + 1, list);
        addNull(depth + 1, list);
    }

    public List<List<Object>> toStr(Node<T1, T2> root, int depth, List<List<Object>> list) {
        if (depth >= 5) {
            return list;
        }
        if (list.size() <= depth) {
            list.add(new ArrayList<>());
        }
        if (root == null) {
            list.get(depth).add(" ");
            addNull(depth + 1, list);
            addNull(depth + 1, list);
            return list;
        }
        if (list.size() <= depth) {
            list.add(new ArrayList<>());
        }
        list.get(depth).add(root.data.getKey());
        toStr(root.left, depth + 1, list);
        toStr(root.right, depth + 1, list);
        return list;
    }
}
