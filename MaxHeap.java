public class MaxHeap {
    // Java program to implement Max Heap

    // Main class

     private int[] Heap;
       private int size;
       private int maxsize;
            // Constructor to initialize an
       // empty max heap with given maximum
       // capacity
       public MaxHeap(int maxsize)
       {
           // This keyword refers to current instance itself
           this.maxsize = maxsize;
           this.size = 0;
           Heap = new int[this.maxsize];
       }
            // Method 1
       // Returning position of parent
       private int parent(int pos) { return (pos - 1) / 2; }
            // Method 2
       // Returning left children
       private int leftChild(int pos) { return (2 * pos); }
            // Method 3
       // Returning right children
       private int rightChild(int pos)
       {
           return (2 * pos) + 1;
       }
            // Method 4
       // Returning true of given node is leaf
       private boolean isLeaf(int pos)
       {
           if (pos > (size / 2) && pos <= size) {
               return true;
           }
           return false;
       }
            // Method 5
       // Swapping nodes
       private void swap(int fpos, int spos)
       {
           int tmp;
           tmp = Heap[fpos];
           Heap[fpos] = Heap[spos];
           Heap[spos] = tmp;
       }
            // Method 6
       // Recursive function to max heapify given subtree
       private void maxHeapify(int pos)
       {
           if (isLeaf(pos))
               return;
                if (Heap[pos] < Heap[leftChild(pos)]
               || Heap[pos] < Heap[rightChild(pos)]) {
                    if (Heap[leftChild(pos)]
                   > Heap[rightChild(pos)]) {
                   swap(pos, leftChild(pos));
                   maxHeapify(leftChild(pos));
               }
               else {
                   swap(pos, rightChild(pos));
                   maxHeapify(rightChild(pos));
               }
           }
       }
            // Method 7
       // Inserts a new element to max heap
       public void insert(int element)
       {
           Heap[size] = element;
                // Traverse up and fix violated property
           int current = size;
           while (Heap[current] > Heap[parent(current)]) {
               swap(current, parent(current));
               current = parent(current);
           }
           size++;
       }

            // Method 9
       // Remove an element from max heap
       public int extractMax()
       {
           int popped = Heap[1];
           Heap[1] = Heap[size--];
           maxHeapify(1);
           return popped;
       }
            // Method 10
       // main dri er method
       public static void main(String[] arg)
       {
                MaxHeap maxHeap = new MaxHeap(15);
                // Inserting nodes
           // Custom inputs
           maxHeap.insert(5);
                // Calling maxHeap() as defined above

                // Print and display the maximum value in heap
           System.out.println("The max val is " + maxHeap.extractMax());
       }
    }