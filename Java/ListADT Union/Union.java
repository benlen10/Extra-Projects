public static <E> ListADT<E> union( ListADT<E> list1, ListADT<E> list2 ) {
	Iterator<E> itr = list1.iterator();            //Create initial iterators
	Iterator<E> itr2 = list2.iterator();
	
	if((list1 == null)||(list2 == null)){          //Check if either list is null
		throw new NullListException();
	}

	
	if((list1.isEmpty())&&(list2.isEmpty())){          //Check if both lists are empty
		ListADT<E> newList = new SimpleArrayList<>();
		return newList;
	}
	
	if(list1.isEmpty()){                       //Check if only list1 is empty
		ListADT<E> newList = new SimpleArrayList<>();
		
		while(itr2.hasNext()){                  
			newList.add(itr2.next)              //Iterate through remaining list2 elements and all all to newList
		}
		return newList;
		}
			

	if(list2.isEmpty()){                       //Check if only list2 is empty
		ListADT<E> newList = new SimpleArrayList<>();
		newList.add(0,list1.get(0));           //Add initial 0 position item from list1 to newList
		while(itr.hasNext()){                  
			newList.add(itr.next)              //Iterate through remaining list1 elements and all all to newList
		}
		return newList;
		}
  
  //Create union of two non empty ListADTs
													 
	ListADT<E> unionList = new SimpleArrayList<>();
	itr = list1.iterator();                  //Create new iterators to reset positions
	itr2 = list2.iterator();
	
		while(itr.hasNext()){                  
			unionList.add(itr.next)              //Iterate through remaining list1 elements and all all to newList
		}
	
	
		boolean dup = false;               //Create boolean value to determine if elements in list2 match list1
		while(itr2.hasNext()){  
			dup = false;                   //Reset duplicate boolean value every loop.
			E tmp = itr2.next;
			itr = list1.iterator();      //Reset list1 iterator every loop
			while(itr.hasNext()){        //Check for duplicates by comparing the current element of list2 against the entire list1
			if(tmp==itr.next){
				dup=true;
			}
			}
			
			if(dup==false){             //If the current element from list2 is not present in list1 then add to the end of newList.
			unionList.add(tmp);              
		}
		}
		return unionList;

	
}