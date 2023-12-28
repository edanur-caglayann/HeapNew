import HeapUygulamalari.Element;
import HeapUygulamalari.Heap;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
    Heap heap = new Heap(13); // 13 Elemanlı bir heap oluşturduk.

        //Yeni düğümleri oluşturalım.
        Element node1 = new Element(5);
        Element node2 = new Element(3);
        Element node3 = new Element(17);
        Element node4 = new Element(10);
        Element node5 = new Element(22);
        Element node6 = new Element(55);
        Element node7 = new Element(94);
        Element node8 = new Element(33);

        //Düğümleri heap'e ekleyelim.
        heap.heapAdd(node1);
        heap.heapAdd(node2);
        heap.heapAdd(node3);
        heap.heapAdd(node4);
        heap.heapAdd(node5);
        heap.heapAdd(node6);
        heap.heapAdd(node7);
        heap.heapAdd(node8);


        //Heap'in durumunu ekrana yazdıralım.
        heap.heapLook();
        System.out.println("-----------------------");

        //Heap'ten en büyük elemanı çıkaralım.
        Element maxElement = heap.maxTurn();
        System.out.println("Heap'ten çıkarılan en büyük öge:"+maxElement.contents);

        heap.heapSearch(2);
        heap.heapSearch(10);




    }
}