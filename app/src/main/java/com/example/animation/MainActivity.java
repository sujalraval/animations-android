package com.example.animation;


import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Spinner spinner;
    private String selectedAnimation = "Scale";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        spinner = findViewById(R.id.spinner);
        Button btnAnimate = findViewById(R.id.btnAnimate);

        // Set up spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.animation_effects, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedAnimation = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Button click listener
        btnAnimate.setOnClickListener(v -> applyAnimation(selectedAnimation));
    }

    private void applyAnimation(String animationType) {
        Animation animation;
        switch (animationType) {
            case "Scale":
                animation = new ScaleAnimation(0.5f, 1.5f, 0.5f, 1.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                animation.setDuration(1000);
                break;
            case "Rotate (Half)":
                animation = AnimationUtils.loadAnimation(this, R.anim.rotate_half);
                break;
            case "Rotate (Full)":
                animation = AnimationUtils.loadAnimation(this, R.anim.rotate_full);
                break;
            case "Bounce":
                animation = AnimationUtils.loadAnimation(this, R.anim.bounce);
                break;
            case "Fade":
                animation = new AlphaAnimation(0.0f, 1.0f);
                animation.setDuration(1000);
                break;
            case "Slide":
                animation = AnimationUtils.loadAnimation(this, R.anim.slide);
                break;
            case "Flip":
                animation = AnimationUtils.loadAnimation(this, R.anim.flip);
                break;
            case "Zoom In":
                animation = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
                break;
            case "Zoom Out":
                animation = AnimationUtils.loadAnimation(this, R.anim.zoom_out);
                break;
            default:
                animation = null;
        }
        if (animation != null) {
            imageView.startAnimation(animation);
        }
    }
}
