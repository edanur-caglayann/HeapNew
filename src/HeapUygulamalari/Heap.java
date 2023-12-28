package HeapUygulamalari;

public class Heap {
    Element[] array; // Element türünden bir dizi tanımlarız.
    int N; // Kaç eleman var
    int piece;

    public Heap(int N) {
        array=new Element[N]; // N elemanlı dizi oluşturduk. (Kapasite)
        this.N=N;
        piece=0;
    }
    boolean HeapEmpty(){
        if(N==0){
            return true;
        }
        else{
            return false;
        }
    }
    void swap(int index1,int index2){ // 2 elemanı takas eden metot
        Element tmp; //Element türünden bir tmp alalım.
        if(index1 == index2){
            return;
        }
        tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
        /*
        Buradaki işlemlerde dizinin elemanları üzerinden takas yapacağımız için ve
        dizi de Element türünden olduğu için tmp'i de Element türünden tanımladık.
         */
    }


    public void down(int i){ // Max Heap
        int downLeft, downRight; //Sol alt ve Sağ alt düğümün indislerini tutması için değişken tanımladık.
        downLeft= 2*i+1; // Sol alt düğümlerin indeksleri 2*i+1 ile bulunur.
        downRight= 2*i+2; // Sağ alt düğümlerin indeksleri 2*i+2 ile bulunur.
        //Yukarıdaki 'i' incelenen düğümün indeksidir.


        /*
        While döngüsü incelenen düğümün sol ve sağ alt düğümünden daha küçük olduğu
        sürece devam eder. Döngü içinde, incelenen düğümün hangi alt düğümü ile yer değiştirileceğine karar verilir.
        Eğer sağ alt düğüm yoksa veya sol alt düğüm sağ alt düğümden büyükse, incelenen düğüm sol alt düğüm ile yer değiştirilir.
        Aksi takdirde, incelenen düğüm sağ alt düğüm ile yer değiştirilir.
         */
        while (downLeft < piece && array[i].contents < array[downLeft].contents ||
                downRight < piece && array[i].contents < array[downRight].contents){
            if(downRight >= piece || array[downLeft].contents > array[downRight].contents){
                /*
                downRight >= N koşulu downRight indeksinin heap boyutunu(N) aşıp aşmadığını kontrol eder.
                Eğer downRight indeksi N 'den büyük veya eşit ise bu, sağ alt düğümün heap
                dışında olduğunu gösterir. Bu durumda, incelenen düğümün sadece sol alt düğümü vardır.
                Else koşulunda da bu durumun sol alt indeksi için geçerlidir.
                 */
                swap(i,downLeft);
                i=downLeft;
            }
            else{
                swap(i,downRight);
                i=downRight;
            }
            /*
            İnceleme işleminden sonra sağ ve sol alt düğümler güncellenir.
             */
            downLeft= 2*i+1;
            downRight= 2*i+2;
            /*
            Bu işlem incelenen düğümün değeri, alt düğümlerinin değerlerinden
            büyük veya onlara eşit olana kadar devam eder.
             */
        }

    }

    // Yığın özelliğinin yeniden oluşturulması için belirli bir düğümden yukarı çıkma metodu
   public void ascend(int i){
        int upper; // İncelenen düğümün bir üstündeki (ebeveyn) düğümün indeksini tutar.
        upper = (i-1)/2; // Bu ebeveyn düğümün indeksi (i-1)/2 formülü ile hesaplanır.
        while (upper >= 0 && array[upper].contents < array[i].contents){
            /*
            While döngüsü, incelenen düğümün üst düğümünden büyük olduğu ve üst düğümün indeksinin
            0 veya daha büyük oldupu sürece devam eder. (Dizi üzerinden baktığımız için negatif bir değer olamaz.)
             Bu durum, heap özelliğinin bozulduğunu gösterir. Heap özelliği, her düğümün değerinin,
             alt düğümlerinin değerlerinden büyük veya onlara eşit olduğunu belirtir.
             */
            swap(upper,i);
            /*
            Döngü içinde, incelenen düğüm üst düğüm ile yer değiştirilir. Bu, incelenen düğümün değerinin
            üst düğümün değerinden büyük olduğu durumlarda gerçekleşir.
             */
            i=upper;
            upper = (i-1)/2;
        }
    }

    // Yığında eleman arama
    public int heapSearch(int number) { // Aranan sayı dışardan parametre olarak alınır.
        for (int i = 0; i < piece; i++) {  // Dizinin sonuna kadar gider
            if (array[i].contents == number) { // Eğer dizinin o anki elemanı aranan elemana eşit ise
                System.out.println("Aranan eleman bulundu."+ array[i].contents);
                return i; // Dizinin o anki elemanını döndürür.

            }
        }
        System.out.println("Aranan eleman bulunamadı.");
        return -1; // Bulamaz ise -1 döndürür.
    }

    // En büyük düğümü (kök düğüm) silme metodu
    public Element maxTurn(){
        Element tmp; // Element türünde bir tmp değişkeni tanımlarız.
        tmp = array[0]; // Bu tmp değişkeni, heap'in kök düğümünü tutar. Bu silinecek olan düğümdür.
        array[0] = array[piece-1]; // Heap'in kök düğümü, heap'in son ögesi ile yer değiştirir.
        down(0); // down metodu ile yeni kök düğümün (yani eski son düğüm) doğru konumunu bulmak için kullanılır.
        // Heap üzerinde aşağı doğru inerek doğru konumu bulur.
        piece--; // Heap boyutu bir azalır. (Kök düğüm silindi.)
        return tmp;
    }

    // Heap' e yeni eleman ekleme
    public void heapAdd(Element newNode){
        piece++; // Yeni bir düğüm ekleneceği için öncelikle heap'in boyutunu arttıralım.
        array[piece-1] = newNode; // Yeni düğümü heap'in sonuna ekler.
        ascend(piece-1);
        /*
        Yukarıdaki ifade, ascend metodunu çağırır ve yeni eklenen düğümün indeksini parametre olarak geçer. 'ascend' metodu,
        yeni eklenen düğümün doğru konumunu bulmak için kullanılır.
        Bu metod, düğümün doğru konumunu bulmak için heap üzerinde “yukarı doğru” hareket eder.
         */
    }

    public void heapLook(){
        for (int i = 0; i <piece; i++) {
            System.out.print(array[i].contents + "-");
        }
    }
}
