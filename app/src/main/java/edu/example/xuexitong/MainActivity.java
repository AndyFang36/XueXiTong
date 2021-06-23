package edu.example.xuexitong;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import edu.example.xuexitong.models.User;

public class MainActivity extends AppCompatActivity {
    /**
     * 底部导航栏
     */
    private BottomNavigationView mBottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, new IndexFragment())
                .commit();
        }

        mBottomNav = findViewById(R.id.bottomNav);
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.bottomNavIndex:
                        fragment = new IndexFragment();
                        break;
                    case R.id.bottomNavNotification:
                        fragment = new NotificationFragment();
                        break;
                    case R.id.bottomNavNotes:
                        fragment = new NotesFragment();
                        break;
                    case R.id.bottomNavMe:
                        fragment = new MeFragment();
                        break;
                }
                getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .commit();
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBottomNav = findViewById(R.id.bottomNav);
        Fragment fragment = null;
        Bundle bundle = new Bundle();
        /* 判断 */
        int id = getIntent().getIntExtra("id", 0);
        switch (id) {
            /* 首页 */
            case 0:
                mBottomNav.setSelectedItemId(R.id.bottomNavIndex);
                getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new IndexFragment())
                    .commit();
                break;
            case 1:
                /* 通知页面 */
                mBottomNav.setSelectedItemId(R.id.bottomNavNotification);
                getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new NotificationFragment())
                    .commit();
                break;
            case 2:
                /* 笔记页面 */
                mBottomNav.setSelectedItemId(R.id.bottomNavNotes);
                getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new NotesFragment())
                    .commit();
                break;
            case 3:
                /* 切换为'我的'页面*/
                mBottomNav.setSelectedItemId(R.id.bottomNavMe);
                /* 传递数据 */
                fragment = new MeFragment();
                bundle.putSerializable("user", getIntent().getSerializableExtra("user"));
                fragment.setArguments(bundle);
                /* 替换当前fragment */
                getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .commit();
                /* 结束 */
                break;
        }
    }
}