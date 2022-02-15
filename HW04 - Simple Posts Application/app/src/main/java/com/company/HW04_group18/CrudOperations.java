
package com.company.HW04_group18;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class CrudOperations {
    public static void login(String email, String passWord, Callback callback) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "email="+email+"&password="+passWord);
        Request request = new Request.Builder()
                .url("https://www.theappsdr.com/posts/login")
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void createAccount(String email, String passWord, String userName, Callback callback) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "email="+email+"&password="+passWord+"&name="+userName);
        Request request = new Request.Builder()
                .url("https://www.theappsdr.com/posts/signup")
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void getPosts(UserDetails user, int page, Callback callback) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://www.theappsdr.com/posts?page="+page)
                .method("GET", null)
                .addHeader("Authorization", "BEARER " + user.token)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void createPost(UserDetails user, String post, Callback callback) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "post_text=" + post);
        Request request = new Request.Builder()
                .url("https://www.theappsdr.com/posts/create")
                .method("POST", body)
                .addHeader("Authorization", "BEARER " + user.token)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void deletePost(String postId, UserDetails user, Callback callback) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "post_id="+postId);
        Request request = new Request.Builder()
                .url("https://www.theappsdr.com/posts/delete")
                .method("POST", body)
                .addHeader("Authorization", "BEARER " + user.token)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
        client.newCall(request).enqueue(callback);
    }
}
