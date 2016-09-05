package bitsandpizzas.hfad.com.mapowa;

/**
 * Created by Jeff on 2016/8/31.
 */
public class countAnswer {
    int countAnswer;

    countAnswer() {
        this.countAnswer = 0;
    }

    void incrementAnswer() {
        this.countAnswer = this.countAnswer + 1;
    }

    int getValue() {
        return this.countAnswer;
    }
}

