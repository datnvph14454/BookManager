package com.exam.vn.bookmanager;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.exam.vn.bookmanager.dao.NguoiDungDAO;
import com.exam.vn.bookmanager.model.NguoiDung;

public class NguoiDungActivity extends AppCompatActivity {
    Button btnThemNguoiDung;
    NguoiDungDAO nguoiDungDAO;
    EditText edUser, edPass, edRePass, edPhone, edFullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_dung);
        setTitle("THÊM NGƯỜI DÙNG");
        btnThemNguoiDung = (Button) findViewById(R.id.btnAddUser);
        edUser = (EditText) findViewById(R.id.edUserName);
        edPass = (EditText) findViewById(R.id.edPassword);
        edPhone = (EditText) findViewById(R.id.edPhone);
        edFullName = (EditText) findViewById(R.id.edFullName);
        edRePass = (EditText) findViewById(R.id.edRePassword);
    }

    public void showUsers(View view) {
        finish();
    }

    public void addUser(View view) {
        nguoiDungDAO = new NguoiDungDAO(NguoiDungActivity.this);
        NguoiDung user = new NguoiDung(edUser.getText().toString(), edPass.getText().toString(),
                edPhone.getText().toString(), edFullName.getText().toString());
        try {
            if (validateForm() > 0) {
                if (nguoiDungDAO.inserNguoiDung(user) > 0) {
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }

        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }

    public int validateForm() {
        int check = 1;
//        char c = edFullName.getText().charAt(0);
        if (edUser.getText().length() == 0 || edFullName.getText().length() == 0
                || edPhone.getText().length() == 0 || edPass.getText().length() == 0
                || edRePass.getText().length() == 0) {
            Toast.makeText(this, "Bạn phải nhập đầy đủ thông ", Toast.LENGTH_SHORT).show();
            check = -1;

        } else if (edFullName.getText().length() <= 5) {
            Toast.makeText(this, "Tên phải trên 5 ký tự", Toast.LENGTH_SHORT).show();
            check = -1;
        } else if (edFullName.getText().length() >= 15) {
            Toast.makeText(this, "Tên phải dưới 15 ký tự", Toast.LENGTH_SHORT).show();
            check = -1;
        } else if (!Character.isUpperCase(edFullName.getText().charAt(0))) {
            Toast.makeText(this, "Tên phải viết hoa ký tự đầu tiên", Toast.LENGTH_SHORT).show();
            check = -1;
        }else if (edUser.getText().length() == 0 || edFullName.getText().length() == 0
                || edPhone.getText().length() == 0 || edPass.getText().length() == 0
                || edRePass.getText().length() == 0) {
            Toast.makeText(this, "Bạn phải nhập đầy đủ thông ", Toast.LENGTH_SHORT).show();
            check = -1;

        }
         else {
        String pass = edPass.getText().toString();
        String rePass = edRePass.getText().toString();
        if (!pass.equals(rePass)) {
            Toast.makeText(getApplicationContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
            check = -1;
        }
    }



        return check;
}

}

