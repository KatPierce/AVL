/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avl;

import java.util.Map;

/**
 *
 * @author Eugenia
 */
public class Data<T1,T2> implements Map.Entry<T1,T2>{
    private final T1 key;
    private T2 value;

    public Data(T1 key, T2 value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public T1 getKey() {
      return key; 
    }

    @Override
    public T2 getValue() {
       return value;
    }

    @Override
    public T2 setValue(T2 value) {
        T2 result = this.value;
        this.value = value; 
        return result;
    }
  
    @Override
    public String toString() {
        return key + " - " + value;
    }
    
    
    
}
