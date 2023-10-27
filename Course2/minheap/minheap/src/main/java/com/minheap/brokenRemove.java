    /**
    * Removes and returns the min item of the heap. As usual for array-backed
    * structures, be sure to null out spots as you remove. Do not decrease the
    * capacity of the backing array.
    *
    * Method should run in O(log n) time.
    *
    * @return The data that was removed.
    * @throws java.util.NoSuchElementException If the heap is empty.
    */
    public T remove() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (backingArray[1] == null || size == 0) {
            throw new java.util.NoSuchElementException("Root is null");
        }
        
        T removedData = backingArray[1];
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        size--;
        
        rDownHeap(1);
        
        return removedData;
    }
    
    
    private void rDownHeap(int idx) {
        T node = backingArray[idx];
        T leftChild = null;
        T rightChild = null;
        
        if (idx*2 < backingArray.length) {
            leftChild = backingArray[idx*2];
        }
        if (idx*2 + 1 < backingArray.length) {
            rightChild = backingArray[idx*2 + 1];
        }     
        
        /* Base cases:
        * Both children null
        * Both children greater than data */
        
        if (leftChild == null && rightChild == null) {
            return;
        }
        else if ((leftChild != null && rightChild != null) && (node.compareTo(leftChild) < 0 && node.compareTo(rightChild) < 0)) {
            return;
        }
        // 1 child
        else if ((rightChild == null) && (node.compareTo(leftChild) > 0)) { // null right child and parent < child may be messing this up if java is ok with Null in CompareTo
            backingArray[idx*2] = node;
            backingArray[idx] = leftChild;
            rDownHeap(idx*2);
        }
        // 2 children - left is higher priority
        else if ((leftChild.compareTo(rightChild) < 0) && (node.compareTo(leftChild) > 0)) {
            backingArray[idx*2] = node;
            backingArray[idx] = leftChild;
            rDownHeap(idx*2);
        }
        // 2 children - right is higher priority
        else if ((rightChild.compareTo(leftChild) < 0) && (node.compareTo(rightChild) > 0)) {
            backingArray[idx*2 + 1] = node;
            backingArray[idx] = rightChild;
            rDownHeap(idx*2 + 1);
        }
        return;
    }
    