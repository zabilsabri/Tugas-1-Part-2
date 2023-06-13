package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class FormActivity extends AppCompatActivity {
    public static final String EXTRA_STUDENT = "extra_student";
    public static final String EXTRA_TITLE = "extra_title";
    public static final int RESULT_ADD = 101;
    public static final int RESULT_UPDATE = 201;
    public static final int RESULT_DELETE = 301;
    private StudentHelper studentHelper;
    private Student student;
    private EditText etTitle, etTask;
    private String walahi;
    private boolean isEdit = false;
    MainActivity mainActivity = new MainActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        etTitle = findViewById(R.id.et_title);
        etTask = findViewById(R.id.et_task);
        Button btnSave = findViewById(R.id.btn_save);
        Button btnDelete = findViewById(R.id.btn_delete);
        studentHelper = StudentHelper.getInstance(getApplicationContext());
        try {
            studentHelper.open();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        student = getIntent().getParcelableExtra(EXTRA_STUDENT);
        walahi = getIntent().getStringExtra(EXTRA_TITLE);
        if (student != null) {
            isEdit = true;
        } else {
            student = new Student();
        }
        String actionBarTitle;
        String buttonTitle;
        if (isEdit) {
            actionBarTitle = "Change";
            buttonTitle = "Update";
            if (student != null) {
                etTitle.setText(student.getName());
                etTask.setText(student.getNim());
            }
            btnDelete.setVisibility(View.VISIBLE);
        } else {
            actionBarTitle = "Add";
            buttonTitle = "Save";
        }
        btnSave.setText(buttonTitle);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(actionBarTitle);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        btnSave.setOnClickListener(view -> save());
        btnDelete.setOnClickListener(view -> delete());
    }
    private void save() {
        String name = etTitle.getText().toString().trim();
        String nim = etTask.getText().toString().trim();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        if (name.isEmpty()) {
            etTitle.setError("Please fill this field");
            return;
        }
        if (nim.isEmpty()) {
            etTask.setError("Please fill this field");
            return;
        }
        student.setName(name);
        student.setNim(nim);
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.StudentColumns.NAME, name);
        values.put(DatabaseContract.StudentColumns.NIM, nim);
        if (isEdit) {
            long result = studentHelper.update(String.valueOf(student.getId()), values);
            if (result > 0) {
                Intent intent = new Intent();
                intent.putExtra(EXTRA_TITLE, walahi);
                setResult(RESULT_UPDATE, intent);
                finish();
            } else {
                Toast.makeText(this, "Failed to update data", Toast.LENGTH_SHORT).show();
            }
        } else {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_STUDENT, student);
            student.setProdi(formatter.format(date));
            values.put(DatabaseContract.StudentColumns.PRODI, formatter.format(date));
            long result = studentHelper.insert(values);
            if (result > 0) {
                student.setId((int) result);
                setResult(RESULT_ADD, intent);
                finish();
            } else {
                Toast.makeText(this, "Failed to add data", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void delete() {
        long result = studentHelper.deleteById(String.valueOf(student.getId()));
        if (result > 0) {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_TITLE, walahi);
            setResult(RESULT_DELETE, intent);
            finish();
        } else {
            Toast.makeText(this, "Failed to delete data", Toast.LENGTH_SHORT).show();
        }
    }
}