package com.example.caarpool;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.caarpool.databinding.ActivityHomepageBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomepageActivity extends AppCompatActivity {
    ActivityHomepageBinding binding;
    private ImageView favourites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomepageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new homefragment());
        favourites = findViewById(R.id.favourites);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.profile) {
                replaceFragment(new profilefragment());
            } else {
                replaceFragment(new homefragment());
            }
            return true;
        });
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        TextView textView = findViewById(R.id.tv);
//        textView.setText(MessageFormat.format("Hello {0} 👋", Objects.requireNonNull(user).getDisplayName()));

        favourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomepageActivity.this,favouritesActivity.class));
            }
        });
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
}