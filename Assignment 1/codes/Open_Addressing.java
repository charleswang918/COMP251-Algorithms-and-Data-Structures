import java.io.*;
import java.util.*;

public class Open_Addressing {
     public int m; // number of SLOTS AVAILABLE
     public int A; // the default random number
     int w;
     int r;
     public int[] Table;

     protected Open_Addressing(int w, int seed, int A) {

         this.w = w;
         this.r = (int) (w-1)/2 +1;
         this.m = power2(r);
         if (A==-1){
            this.A = generateRandom((int) power2(w-1), (int) power2(w),seed);
         }
        else{
            this.A = A;
        }
         this.Table = new int[m];
         for (int i =0; i<m; i++) {
             Table[i] = -1;
         }
         
     }
     
                 /** Calculate 2^w*/
     public static int power2(int w) {
         return (int) Math.pow(2, w);
     }
     public static int generateRandom(int min, int max, int seed) {     
         Random generator = new Random(); 
                 if(seed>=0){
                    generator.setSeed(seed);
                 }
         int i = generator.nextInt(max-min-1);
         return i+min+1;
     }
        /**Implements the hash function g(k)*/
        public int probe(int key, int i) {
            //TODO: implement this function and change the return statement.
            int g = (this.chain(key) + i) % (this.m); // linear probing for open addressing.
        return g;
     }
     
     
     /**Inserts key k into hash table. Returns the number of collisions encountered*/
        public int insertKey(int key){
            //TODO : implement this and change the return statement.
            int collision = 0; // initialize the number of collision.
            // using for loop to check empty slot and add key.
            for (int i=0; i<this.m; i++){
                int hashval = this.probe(key, i); // get hash value.
                if (this.Table[hashval] == -1){
                    this.Table[hashval] = key; // if a slot is empty, add key to arraylist.
                    break; // stop looping.
                }
                else{
                    collision++; // otherwise, increase the number of collision.
                }
            }
            return collision;
        }
        
        /**Sequentially inserts a list of keys into the HashTable. Outputs total number of collisions */
        public int insertKeyArray (int[] keyArray){
            int collision = 0;
            for (int key: keyArray) {
                collision += insertKey(key);
            }
            return collision;
        }
            
         /**Inserts key k into hash table. Returns the number of collisions encountered*/
        public int removeKey(int key){
            //TODO: implement this and change the return statement
            int collision = 0; // initialize the number of collision.
            // using for loop to check if a slot has the key we want to remove.
            for(int i=0; i<m; i++) {
                int hashval = this.probe(key, i); // get hash values.
                if (this.Table[hashval] == key) {
                    this.Table[hashval] = -1; // if we find the key we want to remove, then assign it to -1(remove it).
                    break; // stop looping.
                }
                else {
                    collision++; // otherwise, increase the number of collision.
                }
            }
            return collision;
        }
    // using chain method from chaining.
    public int chain (int key) {
        int h = (this.A * key) % (this.power2(this.w)) >> (this.w - this.r);
        return h;
    }
}
