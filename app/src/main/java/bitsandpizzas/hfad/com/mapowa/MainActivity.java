package bitsandpizzas.hfad.com.mapowa;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button yesButton;
    private Button noButton;
    private TextView questionText;
    private question current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        yesButton = (Button) findViewById(R.id.YesButton);
        noButton = (Button) findViewById(R.id.NoButton);
        questionText = (TextView) findViewById(R.id.question);

        current = mainQuestions();
        questionText.setText(current.getQuestion());

        //testing code
        //current = current.getYesNext().getNoNext();
        //current = buildNext(current.getQuestion());

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //implementation
                if (current.getYesNext() != null) {
                    current = current.getYesNext();
                }
                if (current.getMammal() != null) {
                    questionText.setText(current.getMammal());
                } else {
                    if (current.getTransition() == Boolean.TRUE) {
                        current = buildNext(current.getQuestion());
                    }
                    questionText.setText(current.getQuestion());
                }
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //implementation
                if (current.getNoNext() != null) {
                    current = current.getNoNext();
                }
                if (current.getMammal() != null) {
                    questionText.setText(current.getMammal());
                } else {
                    if (current.getTransition() == Boolean.TRUE) {
                        current = buildNext(current.getQuestion());
                    }
                    questionText.setText(current.getQuestion());
                }
            }
        });
    }

    question buildNext(String nextTree) {
        if (nextTree.equals("noGrassland")) { return noGrassland(); }
        else if (nextTree.equals("noAntlers")) { return noAntlers(); }
        else if (nextTree.equals("noCat")) { return noCat();}
        else if (nextTree.equals("hasHair")) { return hasHair(); }
        else if (nextTree.equals("noSewer")) { return noSewer(); }
        else if (nextTree.equals("noPet")) { return noPet(); }
        else if (nextTree.equals("noTree")) { return noTree(); }
        else return null;
    }

    //initializing tree of depth 4 and 5 levels
    question initTree(question current, String[] questions, int depth, int i) {
        if (depth == 0) return new question(Boolean.TRUE, " ");
        if (questions[i].equals("4")) return new question(Boolean.TRUE, " ");

        question yesNext = new question(Boolean.FALSE, " ");
        question noNext = new question(Boolean.FALSE, " ");

        current.setYesNext(initTree(yesNext, questions, depth - 1, 2*i + 1));
        current.setNoNext(initTree(noNext, questions, depth - 1, 2*i + 2));

        return current;
    }

    //insert questions into the tree
    //check i out of bounds
    void insertQuestions(question current, int depth, String[] questions, int i, String[] answers, countAnswer j) {
        if (current.getTransition() == Boolean.TRUE) { return;  }
        if (depth == 0 || current.isLeaf()) {
            current.setMammal(answers[j.getValue()]);
            j.incrementAnswer();
            return;
        }
        current.setQuestion(questions[i]);
        insertQuestions(current.getYesNext(), depth - 1, questions, 2*i + 1, answers, j);
        insertQuestions(current.getNoNext(), depth - 1, questions, 2*i + 2, answers, j);
    }

    question mainQuestions() {
        Resources res = getResources();
        String[] qs = res.getStringArray(R.array.mainQuestions);
        String[] as = res.getStringArray(R.array.mainAnswers);

        question root = new question(Boolean.FALSE, " ");
        root = initTree(root, qs, 4, 0);

        root.getYesNext().getNoNext().setTransition();
        root.getYesNext().getNoNext().setQuestion("noCat");
        root.getYesNext().getYesNext().getNoNext().getNoNext().setTransition();
        root.getYesNext().getYesNext().getNoNext().getNoNext().setQuestion("noGrassland");

        countAnswer j = new countAnswer();
        insertQuestions(root, 4, qs, 0, as, j);
        return root;
    }

    question noCat() {
        Resources res = getResources();
        String[] qs = res.getStringArray(R.array.noCat);
        String[] as = res.getStringArray(R.array.noCatAnswers);

        question root = new question(Boolean.FALSE, " ");
        root = initTree(root,qs,4,0);

        root.getYesNext().getNoNext().getNoNext().setTransition();
        root.getYesNext().getNoNext().getNoNext().setQuestion("noAntlers");
        root.getNoNext().getYesNext().setTransition();
        root.getNoNext().getYesNext().setQuestion("hasHair");

        countAnswer j = new countAnswer();
        insertQuestions(root, 4, qs, 0, as, j);
        return root;
    }

    question hasHair() {
        Resources res = getResources();
        String[] qs = res.getStringArray(R.array.hasHair);
        String[] as = res.getStringArray(R.array.hasHairAnswers);

        question root = new question(Boolean.FALSE, " ");
        root = initTree(root,qs,4,0);

        root.getNoNext().getYesNext().getNoNext().getNoNext().setTransition();
        root.getNoNext().getYesNext().getNoNext().getNoNext().setQuestion("noSewer");
        root.getNoNext().getNoNext().getNoNext().setTransition();
        root.getNoNext().getNoNext().getNoNext().setQuestion("noPet");

        countAnswer j = new countAnswer();
        insertQuestions(root, 4, qs, 0, as, j);
        return root;
    }

    question noPet() {
        Resources res = getResources();
        String[] qs = res.getStringArray(R.array.noPet);
        String[] as = res.getStringArray(R.array.noPetAnswers);

        question root = new question(Boolean.FALSE, " ");
        root = initTree(root,qs,4,0);

        root.getNoNext().getNoNext().getNoNext().getNoNext().setTransition();
        root.getNoNext().getNoNext().getNoNext().getNoNext().setQuestion("noTree");

        countAnswer j = new countAnswer();
        insertQuestions(root, 4, qs, 0, as, j);
        return root;
    }

    question noTree() {
        Resources res = getResources();
        String[] qs = res.getStringArray(R.array.noTree);
        String[] as = res.getStringArray(R.array.noTreeAnswers);


        question root = new question(Boolean.FALSE, " ");
        root = initTree(root,qs,4,0);
        countAnswer j = new countAnswer();
        insertQuestions(root, 4, qs, 0, as, j);
        return root;
    }

    question noAntlers() {
        Resources res = getResources();
        String[] qs = res.getStringArray(R.array.noAntlers);
        String[] as = res.getStringArray(R.array.noAntlersAnswers);

        question root = new question(Boolean.FALSE, " ");
        root = initTree(root,qs,4,0);
        countAnswer j = new countAnswer();
        insertQuestions(root, 4, qs, 0, as, j);
        return root;
    }
    question noGrassland() {
        Resources res = getResources();
        String[] qs = res.getStringArray(R.array.noGrassland);
            String[] as = res.getStringArray(R.array.noGrasslandAnswers);

        question root = new question(Boolean.FALSE, " ");
        root = initTree(root,qs,2,0);
        countAnswer j = new countAnswer();
        insertQuestions(root, 2, qs, 0, as, j);
        return root;
    }

    question noSewer() {
        Resources res = getResources();
        String[] qs = res.getStringArray(R.array.noSewer);
        String[] as = res.getStringArray(R.array.noSewerAnswers);

        question root = new question(Boolean.FALSE, " ");
        root = initTree(root,qs,2,0);
        countAnswer j = new countAnswer();
        insertQuestions(root, 2, qs, 0, as, j);
        return root;
    }
}


