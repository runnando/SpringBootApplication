package com.company;

import java.util.*;

public class Song implements SongCache{
    //My solution implement LRU concept
    //In order to build LRU cache, I choose double linked list as it's data structure
    class node{
        String songId;
        int numPlays;
        public node pre;
        public node next;
        public node(){}
        public node(String songId,int numPlays){
            this.songId = songId;
            this.numPlays = numPlays;
        }
    }


    public Map<String,node> cache = new HashMap<String,node>();
    int size,capacity;
    public node head;
    public node tail;
    public node fixhead;

    public Song(int capacity){
        this.size = 0;
        this.capacity = capacity;
        head = new node();
        tail = new node();

        fixhead = head;

        head.next = tail;
        tail.pre = head;
    }

    @Override
    public void recordSongPlays(String songId, int numPlays) {
        node n = cache.get(songId);
        if(n == null){
            node newNode = new node(songId,numPlays);
            cache.put(songId,newNode);
            insert(newNode);
            ++size;
            if(size > capacity){
                node t = DeleteTail();
                cache.remove(t.songId);
                --size;
            }

        }
        else{
            n.numPlays = numPlays;
            MoveToHead(n);
        }
    }

    @Override
    public int getPlaysForSong(String songId) {
        node n = cache.get(songId);
        if(n==null){
            return -1;
        }
        MoveToHead(n);
        return n.numPlays;
    }

    //This method will return top n song in the cache
    //I use priority queue, and use lambda expression change it from min heap to max heap
    @Override
    public List<String> getTopNSongsPlayed(int n) {
        List<String> res = new ArrayList<>();

        PriorityQueue<String> pq = new PriorityQueue<>((a,b) -> cache.get(b).numPlays - cache.get(a).numPlays);
        for(String sid: cache.keySet()){
            pq.offer(sid);
            if(pq.size() > n){
                pq.poll();
            }
        }
        int index = pq.size() - 1;


        while(pq.size() > 0){
            res.add(pq.poll());
            index--;

        }
        return res;
    }

    //This method is used for insert node into dobule linked list
    public void insert(node n){

        n.pre = head;
        n.next = head.next;
        head.next.pre = n;
        head.next = n;
    }
    
    //This method is used for moving node to head of double linked list
    //By using map, we can check weather the node is alrady exist in cache or not
    public void MoveToHead(node n){
        RemoveNode(n);
        insert(n);
    }
    
    //This method remove node from double linked list
    public void RemoveNode(node n){
        n.pre.next = n.next;
        n.next.pre = n.pre;
    }

    //This method can delete last node in double link list, 
    //when current cache's size pass the capacity
    public node DeleteTail(){
        node res = tail.pre;
        RemoveNode(res);
        return res;
    }
    
    //This method return a string list; the list contain song order in the cache
    public List<String> songOrderIncache(){
        List<String> res = new ArrayList<>();
        node dummy = new node();
        dummy = head.next;
        while(dummy != null){
            res.add(dummy.songId);
            dummy = dummy.next;

        }
        return res;
    }



}
class test1{
    public static void main(String[] args){
        Song songCache = new Song(5);
        songCache.recordSongPlays("song1",2);
        System.out.println("Top 5 Song: " + songCache.getTopNSongsPlayed(5));
        System.out.println("Song order in the cache: " + songCache.songOrderIncache());
        songCache.recordSongPlays("song2",3);
        System.out.println("Top 5 Song: " + songCache.getTopNSongsPlayed(5));
        System.out.println("Song order in the cache: " + songCache.songOrderIncache());
        songCache.recordSongPlays("song3",1);
        System.out.println("Top 5 Song: " + songCache.getTopNSongsPlayed(5));
        System.out.println("Song order in the cache: " + songCache.songOrderIncache());
        songCache.recordSongPlays("song3",3);
        System.out.println("Top 5 Song: " + songCache.getTopNSongsPlayed(5));
        System.out.println("Song order in the cache: " + songCache.songOrderIncache());
        songCache.recordSongPlays("song4",5);
        System.out.println("Top 5 Song: " + songCache.getTopNSongsPlayed(5));
        System.out.println("Song order in the cache: " + songCache.songOrderIncache());
        songCache.recordSongPlays("song1",10);
        System.out.println("Top 5 Song: " + songCache.getTopNSongsPlayed(5));
        System.out.println("Song order in the cache: " + songCache.songOrderIncache());
        songCache.recordSongPlays("song5",2);
        System.out.println("Top 5 Song: " + songCache.getTopNSongsPlayed(5));
        System.out.println("Song order in the cache: " + songCache.songOrderIncache());
        songCache.recordSongPlays("song6",4);
        System.out.println("Top 5 Song: " + songCache.getTopNSongsPlayed(5));
        System.out.println("Song order in the cache: " + songCache.songOrderIncache());
        songCache.recordSongPlays("song3",1);
        System.out.println("Top 5 Song: " + songCache.getTopNSongsPlayed(5));
        System.out.println("Song order in the cache: " + songCache.songOrderIncache());
        songCache.recordSongPlays("song5",3);
        System.out.println("Top 5 Song: " + songCache.getTopNSongsPlayed(5));
        System.out.println("Song order in the cache: " + songCache.songOrderIncache());
        songCache.recordSongPlays("song5",4);
        System.out.println("Top 5 Song: " + songCache.getTopNSongsPlayed(5));
        System.out.println("Song order in the cache: " + songCache.songOrderIncache());
        songCache.recordSongPlays("song6",1);
        System.out.println("Top 5 Song: " + songCache.getTopNSongsPlayed(5));
        System.out.println("Song order in the cache: " + songCache.songOrderIncache());



    }
}
