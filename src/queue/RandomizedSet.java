package queue;

import java.util.HashMap;
import java.util.Random;

class RandomizedSet {

    private int[] arr;
    private HashMap<Integer, Integer> map;
    private Random random;
    private int n;

    public RandomizedSet() {
        arr = new int[200000];
        map = new HashMap<>();
        random = new Random();
        n = 0;
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        map.put(val, n);
        arr[n] = val;
        n++;
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        int index = map.remove(val);
        arr[index] = arr[n-1];
        arr[n-1] = 0;
        n--;
        return true;
    }

    public int getRandom() {
        return arr[random.nextInt(n)];
    }

    public static void main(String[] args) {
//        RandomizedSet randomizedSet = new RandomizedSet();
//        System.out.println(randomizedSet.insert(1));
//        System.out.println(randomizedSet.remove(2));
//        System.out.println(randomizedSet.insert(2));
//        System.out.println(randomizedSet.getRandom());
//        System.out.println(randomizedSet.remove(1));
//        System.out.println(randomizedSet.insert(2));
//        System.out.println(randomizedSet.getRandom());
    }
}
