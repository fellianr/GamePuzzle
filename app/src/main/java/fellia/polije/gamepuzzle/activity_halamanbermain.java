package fellia.polije.gamepuzzle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class activity_halamanbermain extends AppCompatActivity {
    private TextView moveCounter;
    private TextView feedbackText;
    private Button[] buttons;
    private Boolean bad_move = false;
    private static final Integer[] goal = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
    private ArrayList<Integer> cells = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halaman_bermain);
        buttons = findButtons();
        for (int i = 0; i < 9; i++){
            this.cells.add(i);
        }
        Collections.shuffle(this.cells);
        fill_grid();

        moveCounter = (TextView) findViewById(R.id.MoveCounter);
        feedbackText = (TextView) findViewById(R.id.FeedbackText);

        for (int i = 1; i < 9; i++) {
            buttons[i].setOnClickListener(new View.OnClickListener() {
                public void onClick (View v) {
                    makeMove ((Button) v);
                }
            });
        }

        moveCounter.setText("0");
        feedbackText.setText(R.string.text_feedback);
    }

    private Button[] findButtons() {
        Button[] b = new Button[9];
        b[0] = (Button) findViewById(R.id.button_00);
        b[1] = (Button) findViewById(R.id.button_01);
        b[2] = (Button) findViewById(R.id.button_02);
        b[3] = (Button) findViewById(R.id.button_03);
        b[4] = (Button) findViewById(R.id.button_04);
        b[5] = (Button) findViewById(R.id.button_05);
        b[6] = (Button) findViewById(R.id.button_06);
        b[7] = (Button) findViewById(R.id.button_07);
        b[8] = (Button) findViewById(R.id.button_08);
        return b;
    }

    public void makeMove(final Button b) {
        bad_move = true;
        int b_text, b_pos, zuk_pos;
        b_text = Integer.parseInt((String) b.getText());
        b_pos = find_pos(b_text);
        zuk_pos = find_pos(0);
        switch (zuk_pos) {
            case (0):
                if (b_pos == 1 || b_pos == 3)
                    bad_move = false;
                break;
            case (1):
                if (b_pos == 0 || b_pos == 2 || b_pos == 4)
                    bad_move = false;
                break;
            case (2):
                if (b_pos == 1 || b_pos == 5)
                    bad_move = false;
                break;
            case (3):
                if (b_pos == 0 || b_pos == 4 || b_pos == 6)
                    bad_move = false;
                break;
            case (4):
                if (b_pos == 1 || b_pos == 3 || b_pos == 5 || b_pos == 7)
                    bad_move = false;
                break;
            case (5):
                if (b_pos == 2 || b_pos == 4 || b_pos == 8)
                    bad_move = false;
                break;
            case (6):
                if (b_pos == 3 || b_pos == 7)
                    bad_move = false;
                break;
            case (7):
                if (b_pos == 4 || b_pos == 6 || b_pos == 8)
                    bad_move = false;
                break;
            case (8):
                if (b_pos == 5 || b_pos == 7)
                    bad_move = false;
                break;
        }
        if (bad_move == true) {
            feedbackText.setText("Tidak !");
            return;
        }
        feedbackText.setText("Ya");
        cells.remove(b_pos);
        cells.add(b_pos,0);
        cells.remove(zuk_pos);
        cells.add(zuk_pos, b_text);

        fill_grid();

        moveCounter.setText(Integer.toString(Integer
                        .parseInt((String) moveCounter.getText()) + 1 ));

        for (int i = 0; i < 9 ; i++){
            if (cells.get(i) != goal[i]) {
                return;
            }
        }
        feedbackText.setText("Selesai !");

        AlertDialog.Builder peringatan = new AlertDialog.Builder(
                activity_halamanbermain.this);
        peringatan.setTitle("Selamat !");
        peringatan.setMessage("Anda berhasil menyelesaikan permainan ini");
        peringatan.setNegativeButton("OK", null);
        peringatan.show();
    }

    public void fill_grid(){

    }

    public int find_pos(int element){
        int i = 0;
        for (i = 0; i < 9; i++) {
            if (cells.get(i) == element){
                break;
            }
        }
        return i;
    }
}