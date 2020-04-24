import java.util.HashMap;
import java.util.Scanner;

public class YunqiangWei_40084983_1 {

    private final short meadowNumber = 52;
    private short[][] graphMatrix;
    private short maxLength = 9999;
    private HashMap<String,Short> visitedArray;
    private HashMap<String,Integer> letterToNumber;
    private HashMap<Integer,String> numberToLetter;
    private HashMap<String,Short> unvisitedArray;

    public static void main(String[] args) {
        YunqiangWei_40084983_1 q1 = new YunqiangWei_40084983_1();
        q1.preSet();
        q1.input();
        q1.dijkstra();
        q1.output();
    }
    public void preSet(){
        visitedArray = new HashMap<String,Short>();
        graphMatrix = new short[meadowNumber][meadowNumber];
        for (short i = 0; i < meadowNumber; i++) {
            for (short j = 0; j < meadowNumber; j++) {
                graphMatrix[i][j] = maxLength;
            }
        }
        letterToNumber = new HashMap<String,Integer>(){
            {
                put("z",0);put("a",1);put("b",2);put("c",3);put("d",4);
                put("e",5);put("f",6);put("g",7);put("h",8);put("i",9);
                put("j",10);put("k",11);put("l",12);put("m",13);put("n",14);
                put("o",15);put("p",16);put("q",17);put("r",18);put("s",19);
                put("t",20);put("u",21);put("v",22);put("w",23);put("x",24);
                put("y",25);put("A",26);put("B",27);put("C",28);put("D",29);
                put("E",30);put("F",31);put("G",32);put("H",33);put("I",34);
                put("J",35);put("K",36);put("L",37);put("M",38);put("N",39);
                put("O",40);put("P",41);put("Q",42);put("R",43);put("S",44);
                put("T",45);put("U",46);put("V",47);put("W",48);put("X",49);
                put("Y",50);put("Z",51);
            }
        };
        numberToLetter = new HashMap<Integer,String>(){
            {
                put(0,"z");put(1,"a");put(2,"b");put(3,"c");put(4,"d");
                put(5,"e");put(6,"f");put(7,"g");put(8,"h");put(9,"i");
                put(10,"j");put(11,"k");put(12,"l");put(13,"m");put(14,"n");
                put(15,"o");put(16,"p");put(17,"q");put(18,"r");put(19,"s");
                put(20,"t");put(21,"u");put(22,"v");put(23,"w");put(24,"x");
                put(25,"y");put(26,"A");put(27,"B");put(28,"C");put(29,"D");
                put(30,"E");put(31,"F");put(32,"G");put(33,"H");put(34,"I");
                put(35,"J");put(36,"K");put(37,"L");put(38,"M");put(39,"N");
                put(40,"O");put(41,"P");put(42,"Q");put(43,"R");put(44,"S");
                put(45,"T");put(46,"U");put(47,"V");put(48,"W");put(49,"X");
                put(50,"Y");put(51,"Z");
            }
        };
        unvisitedArray = new HashMap<String,Short>(){
            {
                put("z",maxLength);put("a",maxLength);put("b",maxLength);put("c",maxLength);put("d",maxLength);
                put("e",maxLength);put("f",maxLength);put("g",maxLength);put("h",maxLength);put("i",maxLength);
                put("j",maxLength);put("k",maxLength);put("l",maxLength);put("m",maxLength);put("n",maxLength);
                put("o",maxLength);put("p",maxLength);put("q",maxLength);put("r",maxLength);put("s",maxLength);
                put("t",maxLength);put("u",maxLength);put("v",maxLength);put("w",maxLength);put("x",maxLength);
                put("y",maxLength);put("A",maxLength);put("B",maxLength);put("C",maxLength);put("D",maxLength);
                put("E",maxLength);put("F",maxLength);put("G",maxLength);put("H",maxLength);put("I",maxLength);
                put("J",maxLength);put("K",maxLength);put("L",maxLength);put("M",maxLength);put("N",maxLength);
                put("O",maxLength);put("P",maxLength);put("Q",maxLength);put("R",maxLength);put("S",maxLength);
                put("T",maxLength);put("U",maxLength);put("V",maxLength);put("W",maxLength);put("X",maxLength);
                put("Y",maxLength);put("Z",maxLength);
            }
        };
    }
    public void input(){
        int relationLines = 0;
        Scanner sc = new Scanner(System.in);
        relationLines = sc.nextInt();
        String skip = sc.nextLine();
        for (int i = 0; i < relationLines; i++) {
            String relations = sc.nextLine();
            String[] relationSplit = relations.split(" ");
            int startNode = letterToNumber.get(relationSplit[0]);
            int endNode = letterToNumber.get(relationSplit[1]);
            if(Short.parseShort(relationSplit[2])<graphMatrix[(short)startNode][(short)endNode]){
                graphMatrix[(short)startNode][(short)endNode] = Short.parseShort(relationSplit[2]);
                graphMatrix[(short)endNode][(short)startNode] = Short.parseShort(relationSplit[2]);
            }
        }
    }
    public void dijkstra(){
        for (int i = 0; i <meadowNumber ; i++) {
            if(graphMatrix[0][i]<maxLength){
                unvisitedArray.put(numberToLetter.get(i),graphMatrix[0][i]);
            }
        }
        while(unvisitedArray.size()!=0){
            String[] nameAndSmallerCost = findSmallestInUnvisited().split(" ");
            if (Short.parseShort(nameAndSmallerCost[1])==(short)9999){
                break;
            }
            for (String key:
                 unvisitedArray.keySet()) {
                if(graphMatrix[letterToNumber.get(nameAndSmallerCost[0])][letterToNumber.get(key)]+Short.parseShort(nameAndSmallerCost[1])<unvisitedArray.get(key)){
                    unvisitedArray.put(key,(short)(graphMatrix[letterToNumber.get(nameAndSmallerCost[0])][letterToNumber.get(key)]+Short.parseShort(nameAndSmallerCost[1])));
                }
            }
        }
    }
    public String findSmallestInUnvisited(){
        String name = "";
        Short shorterLength = maxLength;
        for (String key:
             unvisitedArray.keySet()) {
            if(unvisitedArray.get(key)<shorterLength){
                name = key;
                shorterLength = unvisitedArray.get(key);
            }
        }
        if(shorterLength!=maxLength){
            unvisitedArray.remove(name);
            visitedArray.put(name,shorterLength);
        }
        return name+" "+shorterLength;
    }
    public void output(){
        String ansName = "";
        Short ansLength = maxLength;

        for (String key:
             visitedArray.keySet()) {
            if(key.charAt(0)>='A'&&key.charAt(0)<='Z'){
                if(visitedArray.get(key)<ansLength){
                    ansLength = visitedArray.get(key);
                    ansName = key;
                }
            }
        }
        System.out.println(ansName+" "+ansLength);
    }
}

