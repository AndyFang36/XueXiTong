package edu.example.xuexitong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import edu.example.xuexitong.database.UserDAO;
import edu.example.xuexitong.models.User;

/**
 * 注册
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private UserDAO userDAO;
    private EditText etUsername, etPassword, etRePassword, etIntroduction;
    private RadioGroup rgGender;
    private RadioButton rbMale, rbFemale, rbUnknown;
    private TextView toLogin;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setTitle("用户注册");//顶部标题改成用户注册

        initView();
        userDAO = new UserDAO(this);
    }

    private void initView() {
        etUsername = findViewById(R.id.registerUsername);
        etPassword = findViewById(R.id.registerPassword);
        etRePassword = findViewById(R.id.registerRePassword);
        etIntroduction = findViewById(R.id.registerIntroduction);
        rgGender = findViewById(R.id.radioGroupGender);
        rbMale = findViewById(R.id.radioMale);
        rbFemale = findViewById(R.id.radioFemale);
        rbUnknown = findViewById(R.id.radioUnknown);

        toLogin = findViewById(R.id.toLogin);
        btnRegister = findViewById(R.id.btnRegister);

        toLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toLogin:
                // 返回登录界面
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btnRegister:
                /* 1.获取用户输入的用户名、密码、性别，个人简介 */
                String username = etUsername.getText().toString().trim();
                String password1 = etPassword.getText().toString().trim();
                String password2 = etRePassword.getText().toString().trim();
                String gender = "未知";
                for (int i = 0; i < rgGender.getChildCount(); i++) {
                    RadioButton rb = (RadioButton) rgGender.getChildAt(i);
                    if (rb.isChecked()) {
                        gender = String.valueOf(rb.getText());
                        break;
                    }
                }
                String introduction = etIntroduction.getText().toString().trim();
                /* 2.注册验证 */
                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password1) && !TextUtils.isEmpty(password2)) {
                    // 判断两次密码是否一致
                    if (password1.equals(password2)) {
                        Toast.makeText(this, "验证通过，注册成功！", Toast.LENGTH_SHORT).show();
                        User user = new User(username, password2, gender, introduction);
                        userDAO.add(user);
                        Intent intent1 = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent1);
                        finish();
                    } else {
                        Toast.makeText(this, "两次密码不一致，注册失败！", Toast.LENGTH_SHORT).show();
                        etRePassword.requestFocus();
                        etRePassword.setSelection(etRePassword.getText().length());
                    }
                } else {
                    Toast.makeText(this, "注册信息不完善,注册失败！", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

}