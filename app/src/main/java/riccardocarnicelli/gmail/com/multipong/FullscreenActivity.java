package riccardocarnicelli.gmail.com.multipong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FullscreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);


        final Intent playGameIntent = new Intent(this, GameActivity.class);

        (findViewById(R.id.play_game)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(playGameIntent);
            }
        });

    }

    @Override
    public void onBackPressed(){
        this.finish();
    }
}