package bitsandpizzas.hfad.com.mapowa;

/**
 * Created by Jeff on 2016/8/27.
 */
public class question {
    private Boolean end;
    private String mammal;
    private question yesNext;
    private question noNext;
    private String question;
    private Boolean transition;

    //constructor
    question(Boolean endLeaf, String question) {
        this.end = endLeaf;
        this.mammal = null;
        this.question = question;
        this.transition = Boolean.FALSE;
    }

    Boolean isLeaf() {
        return this.end;
    }

    //reach the end of questions?
    Boolean isEnd() {
        return this.end;
    }

    void setTransition() {
        this.transition = Boolean.TRUE;
    }

    Boolean getTransition() {
        return this.transition;
    }

    question getYesNext() {
        return yesNext;
    }

    question getNoNext() {
        return noNext;
    }

    void setMammal(String Mammal) {
        this.mammal = Mammal;
    }

    String getMammal () {
        return this.mammal;
    }

    void setYesNext(question Next) {
        this.yesNext = Next;
    }

    void setNoNext(question Next) {
        this.noNext = Next;
    }

    void setQuestion(String question) {
        this.question = question;
    }

    String getQuestion () {
        return this.question;
    }
}
