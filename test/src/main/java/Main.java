import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

        BigDecimal result = new Calculator("3")
                .add("5")   //3 + 5 = 8
                .undo()     //第一次计算undo = 0
                .add("6")   //0 + 6 = 6
                .subtract("1")//6 - 1 = 5
                .undo()     //undo = 6
                .redo()     //redo = 5
                .divide("2")//5 / 2 = 2.5
                .undo()     //undo: 5
                .undo()     //undo: 6
                .getResult();
        System.out.println(result);
    }
}
