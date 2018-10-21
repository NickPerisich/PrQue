package MinBinHeap_A3;

public class MinBinHeap implements Heap_Interface {
  private EntryPair[] array; //load this array
  private int size;
  private static final int arraySize = 10000; //Everything in the array will initially 
                                              //be null. This is ok! Just build out 
                                              //from array[1]

  public MinBinHeap() {
    this.array = new EntryPair[arraySize];
    array[0] = new EntryPair(null, -100000); //0th will be unused for simplicity 
                                             //of child/parent computations...
                                             //the book/animation page both do this.
  }
    
  //Please do not remove or modify this method! Used to test your entire Heap.
  @Override
  public EntryPair[] getHeap() { 
    return this.array;
  }

	@Override
	public void insert(EntryPair entry) {
		size++;
		array[size] = entry;
		bubbleUp(size);
	}

	@Override
	public void delMin() {
		size--;
		array[1] = null;
		delBubbleDown(1);
	}

	@Override
	public EntryPair getMin() {
		return array[1];
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void build(EntryPair[] entries) {
		for (int i = 0; i < entries.length; i++) {
			array[i+1] = entries[i];
		}
		int lastParent = (entries.length + 1) / 2;
		for (int i = lastParent; i > 0; i--) {
			bubbleDown(i);
		}
		size = entries.length;
	}
	
	public int childMin (int index) {
		if (array[index * 2 + 1] != null) {
			return (array[index * 2].priority < array[index * 2 + 1].priority) ? index * 2 : index * 2 + 1;
		}
		else {
			return index * 2;
		}
	}
	
	public void delBubbleDown (int index) {
		if (array[index * 2] != null) {
			int child = childMin(index);
			EntryPair temp = array[index];
			array[index] = array[child];
			array[child] = temp;
			delBubbleDown(child);
		}
		else {
			array[index] = array[size+1];
		}
	}
	
	public void bubbleDown (int index) {
		int child = childMin(index);
		if (array[index].priority > array[child].priority) {
			EntryPair temp = array[index];
			array[index] = array[child];
			array[child] = temp;
			bubbleDown(child);
		}
	}
	
	public void bubbleUp (int index) {
		int parent = index / 2;
		if (array[index].priority < array[parent].priority) {
			EntryPair temp = array[index];
			array[index] = array[parent];
			array[parent] = temp;
			bubbleUp(parent);
		}
	}
}