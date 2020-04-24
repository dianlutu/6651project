import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class question3_1 {

    private int numberOfQueues;
    private ArrayList<Queue<Integer>> allQueue;
    private int answer;

    public static void main(String[] args) {
        question3_1 q3 = new question3_1();
        q3.preset();
        q3.input();
        q3.start();
        q3.output();
    }

    public void preset(){
        allQueue = new ArrayList<Queue<Integer>>();
        answer = 0;
    }

    public void input(){
        Scanner sc = new Scanner(System.in);
        numberOfQueues = sc.nextInt();
        String skip = sc.nextLine();
        for (int i = 0; i < numberOfQueues; i++) {
            String eachQueueInfo = sc.nextLine();
            String[] queueInfoSplit = eachQueueInfo.split(" ");
            Queue<Integer> singleQueue = new LinkedList<Integer>();
            for (int j = 1; j < Integer.parseInt(queueInfoSplit[0])+1; j++) {
                singleQueue.add(Integer.parseInt(queueInfoSplit[j]));
            }
            allQueue.add(singleQueue);
        }
    }

    public void start(){
        answer = calculate(allQueue,0);
    }

    public int calculate(ArrayList<Queue<Integer>> allQueueStats,int numberOfSuccess){

        int max = 0;
        while(true){
            int smallest = 100000001;
            int smallestPosition = -1;
            boolean isEmpty = true;
            for (Queue<Integer> singleLine:
                    allQueueStats) {
                if (!singleLine.isEmpty()){
                    isEmpty = false;
                }
                for (int peoplePatience :
                        singleLine) {
                    if(peoplePatience<smallest){
                        smallest=peoplePatience;
                        smallestPosition = allQueueStats.indexOf(singleLine);
                    }
                    if(peoplePatience<=numberOfSuccess){
                        return numberOfSuccess;
                    }
                }
            }
            if (isEmpty){
                return numberOfSuccess;
            }else{
                allQueueStats.get(smallestPosition).poll();
                numberOfSuccess = numberOfSuccess+1;
            }
        }
    }
    public void output(){
        System.out.println(answer);
    }
}
