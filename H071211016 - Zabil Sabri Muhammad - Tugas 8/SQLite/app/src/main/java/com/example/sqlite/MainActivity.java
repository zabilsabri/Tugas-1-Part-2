package com.example.sqlite;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvTask;
    private TextView tvEmpty, tvNotFound;
    private SearchView svCari;
    private Button btnAdd;
    private Student student;
    private NoteAdapter adapter;
    private String title;
    public static final String EXTRA_TITLE = "extra_title";

    private ArrayList<Student> cek = new ArrayList<Student>();
    public final ActivityResultLauncher<Intent> resultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getData() != null) {
                            switch (result.getResultCode()) {
                                case FormActivity.RESULT_ADD:
                                    student = result.getData().getParcelableExtra(FormActivity.EXTRA_STUDENT);
                                    Toast.makeText(this, "Note added successfully", Toast.LENGTH_SHORT).show();
                                    title = result.getData().getStringExtra(FormActivity.EXTRA_TITLE);
                                    if (title == null){
                                        restartAdapter();
                                    } else {
                                        searchAdapter(title);
                                    }
                                    break;
                                case FormActivity.RESULT_UPDATE:
                                    student = result.getData().getParcelableExtra(FormActivity.EXTRA_STUDENT);
                                    Toast.makeText(this, "Note updated successfully", Toast.LENGTH_SHORT).show();
                                    title = result.getData().getStringExtra(FormActivity.EXTRA_TITLE);
                                    System.out.println(title);
                                    if (title == null){
                                        restartAdapter();
                                    } else {
                                        searchAdapter(title);
                                    }
                                    break;
                                case FormActivity.RESULT_DELETE:
                                    student = null;
                                    Toast.makeText(this, "Note deleted successfully", Toast.LENGTH_SHORT).show();
                                    title = result.getData().getStringExtra(FormActivity.EXTRA_TITLE);
                                    if (title == null){
                                        restartAdapter();
                                    } else {
                                        searchAdapter(title);
                                    }
                                    break;
                            }
                        }
                    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvTask = findViewById(R.id.rv_fitnah);
        btnAdd = findViewById(R.id.btn_add);
        tvEmpty = findViewById(R.id.tv_empty);
        svCari = findViewById(R.id.sv_zabilManiez);
        tvNotFound = findViewById(R.id.tv_notFound);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                resultLauncher.launch(intent);
            }
        });

        restartAdapter();
        search();
    }

    public void search() {
        svCari.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(s.isEmpty()){
                    restartAdapter();
                } else {
                    searchAdapter(s);
                }
                return false;
            }
        });
    }

    public void restartAdapter() {
        new LoadStudentsAsync(this, students -> {
            if (students.size() > 0) {
                svCari.setVisibility(View.VISIBLE);
                rvTask.setVisibility(View.VISIBLE);
                tvNotFound.setVisibility(View.GONE);
                tvEmpty.setVisibility(View.GONE);
                rvTask.setLayoutManager(new LinearLayoutManager(this));
                adapter = new NoteAdapter(new NoteAdapter.ClickListener() {
                    @Override
                    public void onItemClicked(Student note, int i) {
                        Intent intent = new Intent(MainActivity.this, FormActivity.class);
                        intent.putExtra(FormActivity.EXTRA_STUDENT, note);
                        resultLauncher.launch(intent);
                    }
                }, students);
                rvTask.setAdapter(adapter);
            } else {
                rvTask.setVisibility(View.GONE);
                tvNotFound.setVisibility(View.GONE);
                tvEmpty.setVisibility(View.VISIBLE);
                svCari.setVisibility(View.GONE);
            }
        }).execute();
    }

    public void searchAdapter(String s) {
        new SearchAsync(this, students -> {
            if (students.size() > 0) {
                rvTask.setVisibility(View.VISIBLE);
                tvNotFound.setVisibility(View.GONE);
                rvTask.setLayoutManager(new LinearLayoutManager(this));
                adapter = new NoteAdapter(new NoteAdapter.ClickListener() {
                    @Override
                    public void onItemClicked(Student note, int i) {
                        Intent intent = new Intent(MainActivity.this, FormActivity.class);
                        intent.putExtra(FormActivity.EXTRA_STUDENT, note);
                        intent.putExtra(FormActivity.EXTRA_TITLE, s);
                        resultLauncher.launch(intent);
                    }
                }, students);
                rvTask.setAdapter(adapter);
            } else {
                rvTask.setVisibility(View.GONE);
                tvNotFound.setVisibility(View.VISIBLE);
            }
        }, s).execute();
    }

    private static class LoadStudentsAsync {
        private final WeakReference<Context> weakContext;
        private final WeakReference<LoadStudentsCallback> weakCallback;
        private LoadStudentsAsync(Context context, LoadStudentsCallback callback) {
            weakContext = new WeakReference<>(context);
            weakCallback = new WeakReference<>(callback);
        }
        void execute() {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());
            executor.execute(() -> {
                Context context = weakContext.get();
                StudentHelper studentHelper = StudentHelper.getInstance(context);
                try {
                    studentHelper.open();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                Cursor studentsCursor = studentHelper.queryAll();
                ArrayList<Student> students =
                        MappingHelper.mapCursorToArrayList(studentsCursor);
                studentHelper.close();
                handler.post(() -> weakCallback.get().postExecute(students));
            });
        }
    }

    private static class SearchAsync {
        private final WeakReference<Context> weakContext;
        private final WeakReference<LoadStudentsCallback> weakCallback;
        private String w;

        private SearchAsync(Context context, LoadStudentsCallback callback, String s) {
            weakContext = new WeakReference<>(context);
            weakCallback = new WeakReference<>(callback);
            this.w = s;
        }
        void execute() {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());
            executor.execute(() -> {
                Context context = weakContext.get();
                StudentHelper studentHelper = StudentHelper.getInstance(context);
                try {
                    studentHelper.open();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                Cursor studentsCursor = studentHelper.queryByNote(w);
                ArrayList<Student> students =
                        MappingHelper.mapCursorToArrayList(studentsCursor);
                studentHelper.close();
                handler.post(() -> weakCallback.get().postExecute(students));
            });
        }
    }

    interface LoadStudentsCallback {
        void postExecute(ArrayList<Student> students);
    }
}