
package com.company.HW04_group18;

import java.util.ArrayList;

public class POJOclasses {
    public static class postsResponse{
        ArrayList<Post> posts;
        int pageSize, totalCount;
    }
    public static class Post {
        String created_by_name, post_id, created_by_uid, post_text, created_at;
    }
}
