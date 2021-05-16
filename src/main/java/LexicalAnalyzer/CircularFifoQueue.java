package LexicalAnalyzer;

import java.util.LinkedList;
import java.util.List;


public class CircularFifoQueue{
    private int buforSize;
    private LinkedList<Character> lastCharacters;

    public CircularFifoQueue(int buforSize) {
        this.buforSize = buforSize;
        this.lastCharacters = new LinkedList<>();
    }

    public void add(char c){
        lastCharacters.addFirst(c);
        if(lastCharacters.size() > buforSize)
            lastCharacters.removeLast();
    }

    public void printContent(){
        StringBuilder r = new StringBuilder();
        for(Character c : lastCharacters){
            r.insert(0, c);
        }
        System.out.println("..."+r);
    }
}
