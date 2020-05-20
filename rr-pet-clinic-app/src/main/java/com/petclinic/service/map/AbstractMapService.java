package com.petclinic.service.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import com.petclinic.model.BaseEntity;

//since all our model(T) extends BaseEntity class, we will make T extend BaseEntity below.
//public abstract class AbstractMapService <T extends BaseEntity, ID extends Long> {
//public abstract class AbstractMapService <T, ID extends Long> {
//public abstract class AbstractMapService <T, ID> {   //getNextId() comments



public abstract class AbstractMapService <T extends BaseEntity, ID extends Long> {

	protected Map<Long, T> map = new HashMap<Long, T>();
//	protected Map<ID, T> map = new HashMap<ID, T>();   //getNextId() comments
	
	List<T> findAll(){		
		return new ArrayList<>(map.values());
	}
	
	T findById(ID id) {
		return map.get(id);
	}
	
	//remove ID parameter as we are generating it automatically. Do this to all implementations.
	T save(T object) {
//	T save(ID id, T object) {
		
		//generate obj id start
		if(object != null) {
			if(object.getId() == null) {
				object.setId(getNextId());
			}
		}else {
			throw new RuntimeException("Object cannot be null");
		}
		//generate obj id end
		
		//change this to use auto generated id
		map.put(object.getId(), object);		
//		map.put(id, object);
		
		
		return object;
	}
	
	void deleteById(ID id) {
		map.remove(id);
	}
	
	void delete(T object) {// check for java 7 syntax
		map.entrySet().removeIf(entry -> entry.getValue().equals(object));
	}
	
/*Java being a strongly typed language we have some tricks that we can use to indicate that
 *  because we're doing an abstract class here.
This can get a little bit tricky, as far as how Java is handling the types.
What we can do is come up here and subset about this ID value.
 We can come in here and say extends,the ID extends Long.

So now we're really unhappy. 
But what we can do here is for our Map implementation, rather than using generic,
we can say that that is going to take a Long. 
So anytime we extend out this class, as long as a Long or something that extends it,
 it's specified for this, this is going to work happily.*/
	private Long getNextId() {
		
		//got Caused by: java.util.NoSuchElementException: null. so refactoring this.
		Long nextId = null;

        try {
            nextId = Collections.max(map.keySet()) + 1;
        } catch (NoSuchElementException e) {
            nextId = 1L;
        }

        return nextId;
//		return Collections.max(map.keySet()) + 1;
	}
}
