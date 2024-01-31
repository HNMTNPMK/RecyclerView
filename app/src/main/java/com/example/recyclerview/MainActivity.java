package com.example.recyclerview;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        // Tạo danh sách người dùng
        List<User> userList = createUserList();

        // Tạo một ArrayAdapter để hiển thị danh sách người dùng trong ListView
        ArrayAdapter<User> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userList);

        // Đặt adapter cho ListView
        listView.setAdapter(adapter);

        // Xử lý sự kiện khi người dùng nhấn vào một mục trong ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User selectedUser = (User) parent.getItemAtPosition(position);

                // Hiển thị dialog thông tin người dùng
                showUserInfoDialog(selectedUser);
            }
        });
    }

    private void showUserInfoDialog(User user) {
        // Tạo đối tượng UserInfoDialogFragment và truyền thông tin người dùng
        UserInfoDialogFragment dialog = UserInfoDialogFragment.newInstance(user.getId(), user.getEmail(), user.getPassword(), user.getRole());
        dialog.show(getSupportFragmentManager(), "user_info_dialog");
    }

    private List<User> createUserList() {
        // Tạo danh sách người dùng
        List<User> userList = new ArrayList<>();
        userList.add(new User(1, "user1@example.com", "password1", "role1"));
        userList.add(new User(2, "user2@example.com", "password2", "role2"));
        userList.add(new User(3, "user3@example.com", "password3", "role3"));
        // Thêm các người dùng khác vào danh sách

        return userList;
    }

    private static class User {
        private int id;
        private String email;
        private String password;
        private String role;

        public User(int id, String email, String password, String role) {
            this.id = id;
            this.email = email;
            this.password = password;
            this.role = role;
        }

        public int getId() {
            return id;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

        public String getRole() {
            return role;
        }

        @Override
        public String toString() {
            return email;
        }
    }

    public static class UserInfoDialogFragment extends DialogFragment {

        private static final String ARG_USER_ID = "user_id";
        private static final String ARG_USER_EMAIL = "user_email";
        private static final String ARG_USER_PASSWORD = "user_password";
        private static final String ARG_USER_ROLE = "user_role";

        public static UserInfoDialogFragment newInstance(int userId, String userEmail, String userPassword, String userRole) {
            UserInfoDialogFragment fragment = new UserInfoDialogFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_USER_ID, userId);
            args.putString(ARG_USER_EMAIL, userEmail);
            args.putString(ARG_USER_PASSWORD, userPassword);
            args.putString(ARG_USER_ROLE, userRole);
            fragment.setArguments(args);
            return fragment;
        }

        @NonNull
        @Override
        public AlertDialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            // Lấy thông tin người dùng từ đối số
            int userId = getArguments().getInt(ARG_USER_ID);
            String userEmail = getArguments().getString(ARG_USER_EMAIL);
            String userPassword = getArguments().getString(ARG_USER_PASSWORD);
            String userRole = getArguments().getString(ARG_USER_ROLE);

            // Xây dựng dialog và hiển thị thông tin người dùng
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("User Info")
                    .setMessage("User ID: " + userId +
                            "\nEmail: " + userEmail +
                            "\nPassword: " + userPassword +
                            "\nRole: " + userRole)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Đóng dialog khi người dùng nhấn nút OK
                            dismiss();
                        }
                    });

            // Tạo dialogfragment từ builder và trả về
            return builder.create();
        }
    }
}