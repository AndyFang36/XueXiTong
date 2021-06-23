package edu.example.xuexitong;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.example.xuexitong.database.UserDAO;
import edu.example.xuexitong.models.User;

/**
 * 登录<br/>
 * 此类 implements View.OnClickListener 之后，
 * 就可以把onClick事件写到onCreate()方法之外
 * 这样，onCreate()方法中的代码就不会显得很冗余
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private UserDAO openHelper;
    private SharedPreferences.Editor editor;
    private EditText etUsername, etPassword;
    private CheckBox keepLogin;
    private TextView toRegister;
    private Button btnLogin;
    private User mUser;

    /**
     * 创建 Activity 时先来重写 onCreate() 方法
     * 保存实例状态
     * super.onCreate(savedInstanceState);
     * 设置视图内容的配置文件
     * setContentView(R.layout.activity_login);
     * 上面这行代码真正实现了把视图层 View 也就是 layout 的内容放到 Activity 中进行显示
     * 初始化视图中的控件对象 initView()
     * 实例化 DBOpenHelper，待会进行登录验证的时候要用来进行数据查询
     * mDBOpenHelper = new DBOpenHelper(this);
     */
    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("用户登录");//顶部标题改成用户注册
        // 初始化界面
        initView();
        openHelper = new UserDAO(this);
        // 读取sp
        SharedPreferences sp = getSharedPreferences("userinfo", MODE_PRIVATE);
        editor = sp.edit();
        if (sp.getBoolean("flag", false)) {
            String user_read = sp.getString("username", "");
            String psw_read = sp.getString("password", "");
            etUsername.setText(user_read);
            etPassword.setText(psw_read);
        }
    }

    /**
     * 初始化控件
     */
    private void initView() {
        etUsername = findViewById(R.id.loginUsername);
        etPassword = findViewById(R.id.loginPassword);
        keepLogin = findViewById(R.id.keepLogin);
        btnLogin = findViewById(R.id.btnLogin);
        toRegister = findViewById(R.id.toRegister);
        /* 设置点击事件监听器 */
        btnLogin.setOnClickListener(this);
        toRegister.setOnClickListener(this);
    }

    /* 单击事件 */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 跳转到注册界面
            case R.id.toRegister:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
            /*
             * 登录验证：
             *
             * 从EditText的对象上获取文本编辑框输入的数据，并把左右两边的空格去掉
             *  String username = mEtLoginactivityUsername.getText().toString().trim();
             *  String password = mEtLoginactivityPassword.getText().toString().trim();
             *  进行匹配验证,先判断一下用户名密码是否为空，
             *  if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password))
             *  再进而for循环判断是否与数据库中的数据相匹配
             *  if (username.equals(user.getName()) && password.equals(user.getPassword()))
             *  一旦匹配，立即将match = true；break；
             *  否则 一直匹配到结束 match = false；
             *
             *  登录成功之后，进行页面跳转：
             *
             *  Intent intent = new Intent(this, MainActivity.class);
             *  startActivity(intent);
             *  finish();//销毁此Activity
             */
            case R.id.btnLogin:
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
                    ArrayList<User> users = openHelper.getAllUsers();
                    boolean match = false;
                    boolean match2 = false;
                    for (int i = 0; i < users.size(); i++) {
                        User user = users.get(i);
                        if ((username.equals(user.getUsername()) && password.equals(user.getPassword()))) {
                            mUser = user;
                            match = true;
                            if (keepLogin.isChecked()) {
                                editor.putBoolean("flag", true);
                                editor.putInt("userId", user.getUserId());
                                editor.putString("username", user.getUsername());
                                editor.putString("password", user.getPassword());
                                editor.putString("introduction", user.getIntroduction());
                                editor.apply();
                                match2 = true;
                            } else {
                                editor.putString("username", user.getUsername());
                                editor.putString("password", "");
                                editor.putInt("userId", user.getUserId());
//                                editor.clear();
                                editor.apply();
                                match2 = false;
                            }
                            break;
                        } else {
                            match = false;
                        }
                    }
                    if (match) {
                        if (match2) {
                            Toast.makeText(this, "成功记住密码", Toast.LENGTH_SHORT).show();
                            keepLogin.setChecked(true);
                        }
                        Toast.makeText(this, "登录成功！", Toast.LENGTH_SHORT).show();
                        Runnable target;
                        //用线程启动
                        Thread thread = new Thread() {
                            @Override
                            public void run() {
                                try {
                                    sleep(500);  // 延迟1秒，模拟登录
                                    // 设置自己跳转到成功的界面
                                    Intent intent1 = new Intent(LoginActivity.this, MainActivity.class);
                                    intent1.putExtra("id", 3);
                                    intent1.putExtra("user", mUser);
                                    startActivity(intent1);
                                    finish();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        thread.start();  // 打开线程
                    } else {
                        Toast.makeText(this, "用户名或密码不正确，请重新输入！", Toast.LENGTH_SHORT).show();
                        etUsername.requestFocus();
                        etUsername.setSelection(etUsername.getText().length());
                    }
                } else {
                    if (TextUtils.isEmpty(username)) {
                        Toast.makeText(this, "请输入您的用户名！", Toast.LENGTH_SHORT).show();
                        etUsername.requestFocus();
                    } else {
                        Toast.makeText(this, "请输入您的密码！", Toast.LENGTH_SHORT).show();
                        etPassword.requestFocus();
                    }
                }
                break;
        }
    }

}