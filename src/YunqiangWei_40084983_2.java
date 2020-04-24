import java.util.HashMap;
import java.util.Scanner;

public class YunqiangWei_40084983_2 {

    private int numberOfIntervals;
    private short[][] graphMatrix;
    private short[][] intervals;
    private long answer;
    private long modulo;
    private HashMap<Short,Boolean> stats;

    public static void main(String[] args) {
        YunqiangWei_40084983_2 q2 = new YunqiangWei_40084983_2();
        q2.preset();
        q2.input();
        q2.start();
        q2.output();
    }

    public void preset(){
        answer = 0;
        modulo = 1000000007;
        stats = new HashMap<Short, Boolean>();
    }

    public void input(){
        numberOfIntervals = 0;
        Scanner sc = new Scanner(System.in);
        numberOfIntervals = sc.nextInt();
        String skip = sc.nextLine();
        intervals = new short[numberOfIntervals][2];
        graphMatrix = new short[numberOfIntervals][numberOfIntervals];
        for (int i = 0; i < numberOfIntervals; i++) {
            String singleInterval = sc.nextLine();
            String[] intervalStartAndEnd = singleInterval.split(" ");
            for (int j = 0; j < i; j++) {
                if(Math.max(Short.parseShort(intervalStartAndEnd[0]),intervals[j][0])<=Math.min(Short.parseShort(intervalStartAndEnd[1]),intervals[j][1])){
                    graphMatrix[i][j] = (short)1;
                    graphMatrix[j][i] = (short)1;
                }
            }
            intervals[i][0] = Short.parseShort(intervalStartAndEnd[0]);
            intervals[i][1] = Short.parseShort(intervalStartAndEnd[1]);
        }
    }

    public void start(){
        findAllPossiblePatterns(stats,(short)(0));
    }

    public int findAllPossiblePatterns(HashMap<Short,Boolean> stats,short numberToBeDetermined){
        if(numberToBeDetermined==(short)numberOfIntervals){
            judge(stats);
            return 1;
        }
        stats.put(numberToBeDetermined,false);
        findAllPossiblePatterns(stats,(short) (numberToBeDetermined+1));
        stats.put(numberToBeDetermined,true);
        findAllPossiblePatterns(stats,(short) (numberToBeDetermined+1));
        return 1;
    }

    public void judge(HashMap<Short,Boolean> stats){
        boolean flagIsASolution=false;
        for (Short key:
             stats.keySet()) {
            flagIsASolution = false;
            if(!stats.get(key)){
                for (short i = 0; i < numberOfIntervals; i++) {
                    if ((graphMatrix[key][i]==(short)(1))&&stats.get(i)){
                        flagIsASolution = true;
                        break;
                    }
                }
            }else{
                flagIsASolution = true;
            }
            if (!flagIsASolution){
                break;
            }
        }
        if(flagIsASolution){
            answer = (short)(answer+1);
        }
    }
    public void output(){
        System.out.println(answer%modulo);
    }
}

