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
public class Data<T1,T2> {
    private final T1 key;
    private T2 value;

    public Data(T1 key, T2 value) {
        this.key = key;
        this.value = value;
    }

    public T1 getKey() {
        return key;
    }

    public T2 getValue() {
        return value;
    }

    public void setValue(T2 value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return key + " - " + value;
    }
    
    
    
}
