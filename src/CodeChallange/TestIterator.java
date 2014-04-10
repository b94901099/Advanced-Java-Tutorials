package CodeChallange;

import java.util.*;

/**
 * Created by Chen on 4/9/2014.
 */
public class TestIterator implements Iterable {

    ArrayList<Integer> list1 = new ArrayList<Integer>();
    ArrayList<Integer> list2 = new ArrayList<Integer>();
    ArrayList<Integer> list3 = new ArrayList<Integer>();
    ArrayList<Integer> list4 = new ArrayList<Integer>();
    Iterator<Integer> iterator1;
    Iterator<Integer> iterator2;
    Iterator<Integer> iterator3;
    Iterator<Integer> iterator4;

    public void initializeLists() {
        for (int i = 0; i < 10; i++) {
            list1.add(i);
        }
        iterator1 = list1.iterator();

        for (int i = 11; i < 16; i++) {
            list2.add(i);
        }
        iterator2 = list2.iterator();

        for (int i = 21; i < 30; i++) {
            list3.add(i);
        }
        iterator3 = list3.iterator();

        for (int i = 31; i < 37; i++) {
            list4.add(i);
        }
        iterator4 = list4.iterator();
    }

    @Override
    public Iterator<Integer> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<Integer> {

        private Queue<Iterator<Integer>> queue;

        MyIterator() {
            queue = new LinkedList<Iterator<Integer>>();
            queue.offer(iterator1);
            queue.offer(iterator2);
            queue.offer(iterator3);
            queue.offer(iterator4);
            System.out.println("MyIterator(): queue.size() == " + queue.size());
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public Integer next() {
            Iterator<Integer> curItr = queue.poll();
            int toReturn = curItr.next();
            if (curItr.hasNext()) {
                queue.offer(curItr);
            }
            return toReturn;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    public static void main(String[] args) {
        TestIterator t = new TestIterator();
        t.initializeLists();

        Iterator tItr = t.iterator();
        while (tItr.hasNext()) {
            System.out.print(tItr.next() + ", ");
        }
    }
}
