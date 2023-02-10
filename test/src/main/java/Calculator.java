import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Calculator {

    /**
     * 计算后得到的结果集
     */
    private final List<BigDecimal> resultList = new ArrayList<>();

    /**
     * undo redo下标
     */
    private int resultIndex = -1;

    /**
     * 每次计算后得到结果值
     */
    private BigDecimal lastOperateNum;

    public Calculator(String num) {
        this.lastOperateNum = new BigDecimal(num);
    }

    public Calculator add(String num) {
        lastOperateNum = lastOperateNum.add(new BigDecimal(num));
        addToList();
        return this;
    }

    public Calculator subtract(String num) {
        lastOperateNum = lastOperateNum.subtract(new BigDecimal(num));
        addToList();
        return this;
    }

    public Calculator multiply(String num) {
        lastOperateNum = lastOperateNum.multiply(new BigDecimal(num));
        addToList();
        return this;
    }

    public Calculator divide(String num) {
        lastOperateNum = lastOperateNum.divide(new BigDecimal(num), 2, RoundingMode.HALF_UP);
        addToList();
        return this;
    }

    public Calculator divide(String num, int scale, RoundingMode roundingMode) {
        lastOperateNum = lastOperateNum.divide(new BigDecimal(num), scale, roundingMode);
        addToList();
        return this;
    }

    private void addToList() {
        resultList.add(lastOperateNum);
        resultIndex++;
    }

    public BigDecimal getResult() {
        return lastOperateNum;
    }


    public Calculator redo() {
        if (resultIndex >= resultList.size() - 1) {
            return this;
        }
        lastOperateNum = resultList.get(++resultIndex);
        return this;
    }

    public Calculator undo() {

        //只计算了一次undo为0
        if (resultList.size() == 1) {
            lastOperateNum = BigDecimal.ZERO;
            return this;
        }

        if (resultIndex < 1) {
            System.out.println("无法在undo了");
            return this;
        }

        lastOperateNum = resultList.get(--resultIndex);
        return this;
    }
}
