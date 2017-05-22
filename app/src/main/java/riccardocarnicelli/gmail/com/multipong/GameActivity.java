package riccardocarnicelli.gmail.com.multipong;

import android.app.Activity;
import android.os.Bundle;

public class GameActivity extends Activity {

    private MainGamePanel mgp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mgp = new MainGamePanel(this);
        setContentView(mgp);

    }

    @Override
    public void onBackPressed(){

        mgp = null;
        this.finish();
    }



}
