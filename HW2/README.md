# HW2
## Descripition
1. This question require to build a simple cache.  My solution implement LRU concept.
2. In order to build LRU cache, I choose double linked list and HashMap as it's data structure
3. For dobule link list, each node have pre and next pointer; it also have two attribue: song id and play number
4. Four basic method:
  - insert(): This method is used for insert node into dobule linked list.
  - MoveToHead(): This method is used for moving node to head of double linked list; With hashMap, we can check weather the node is alrady exist in cache or not.
  - RemoveNode(): This method remove node from double linked list.
  - DeleteTail(): This method is used when current cache's size pass the capacity.
5. Implement method:
  - recordSongPlays(): This method is used for insert song node in the cache.
  - getPlaysForSong(): return play numbner of exisisting song in the cache.
  - getTopNSongsPlayed(): This method will return top n song in the cache;I implement it by using priority queue, and also use lambda expression change it from min heap to max heap
    
