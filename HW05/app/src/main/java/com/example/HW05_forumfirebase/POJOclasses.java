/*HW 05
        Grouping3 - 18
        Name: Rahul Govindkumar
        Name: Amruth Nag
        */

package com.example.HW05_forumfirebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class POJOclasses {

    public static class Forum {
        String userName;
        String title;
        String content;
        String time;
        String Uid;
        String docId;
        List<String> likes;
        List<Map<String, String>> comments;

        public List<Map<String, String>> getComments() {
            if (comments == null) {
                comments = new List<Map<String, String>>() {
                    @Override
                    public int size() {
                        return 0;
                    }

                    @Override
                    public boolean isEmpty() {
                        return false;
                    }

                    @Override
                    public boolean contains(@Nullable Object o) {
                        return false;
                    }

                    @NonNull
                    @Override
                    public Iterator<Map<String, String>> iterator() {
                        return null;
                    }

                    @NonNull
                    @Override
                    public Object[] toArray() {
                        return new Object[0];
                    }

                    @NonNull
                    @Override
                    public <T> T[] toArray(@NonNull T[] ts) {
                        return null;
                    }

                    @Override
                    public boolean add(Map<String, String> stringStringMap) {
                        return false;
                    }

                    @Override
                    public boolean remove(@Nullable Object o) {
                        return false;
                    }

                    @Override
                    public boolean containsAll(@NonNull Collection<?> collection) {
                        return false;
                    }

                    @Override
                    public boolean addAll(@NonNull Collection<? extends Map<String, String>> collection) {
                        return false;
                    }

                    @Override
                    public boolean addAll(int i, @NonNull Collection<? extends Map<String, String>> collection) {
                        return false;
                    }

                    @Override
                    public boolean removeAll(@NonNull Collection<?> collection) {
                        return false;
                    }

                    @Override
                    public boolean retainAll(@NonNull Collection<?> collection) {
                        return false;
                    }

                    @Override
                    public void clear() {

                    }

                    @Override
                    public Map<String, String> get(int i) {
                        return null;
                    }

                    @Override
                    public Map<String, String> set(int i, Map<String, String> stringStringMap) {
                        return null;
                    }

                    @Override
                    public void add(int i, Map<String, String> stringStringMap) {

                    }

                    @Override
                    public Map<String, String> remove(int i) {
                        return null;
                    }

                    @Override
                    public int indexOf(@Nullable Object o) {
                        return 0;
                    }

                    @Override
                    public int lastIndexOf(@Nullable Object o) {
                        return 0;
                    }

                    @NonNull
                    @Override
                    public ListIterator<Map<String, String>> listIterator() {
                        return null;
                    }

                    @NonNull
                    @Override
                    public ListIterator<Map<String, String>> listIterator(int i) {
                        return null;
                    }

                    @NonNull
                    @Override
                    public List<Map<String, String>> subList(int i, int i1) {
                        return null;
                    }
                };
            }
            return comments;
        }

        public void setComments(List<Map<String, String>> comments) {
            this.comments = comments;
        }

        public Forum(String[] contents, List<String> likes, List<Map<String, String>> comments) {
            this.userName = contents[0];
            this.title = contents[1];
            this.content = contents[2];
            this.time = contents[3];
            this.Uid = contents[4];
            try {
                this.docId = contents[5];
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                this.likes = likes;
            } catch (Exception e) {
                e.printStackTrace();
                getLikes();
            }
            try {
                this.comments = comments;
            } catch (Exception e) {
                e.printStackTrace();
                getComments();
            }
        }

        public String getUid() {
            return Uid;
        }

        public void setUid(String uid) {
            Uid = uid;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public HashMap<Object, Object> toHashmap() {
            HashMap<Object, Object> dataMap = new HashMap<>();
            dataMap.put("userName", this.userName);
            dataMap.put("title", this.title);
            dataMap.put("content", this.content);
            dataMap.put("time", this.time);
            dataMap.put("Uid", this.Uid);
            dataMap.put("likes", Arrays.asList(likes));
            return dataMap;
        }

        public String getDocId() {
            return docId;
        }

        public void setDocId(String docId) {
            this.docId = docId;
        }

        public List<String> getLikes() {
            if (likes == null) {
                likes = new List<String>() {
                    @Override
                    public int size() {
                        return 0;
                    }

                    @Override
                    public boolean isEmpty() {
                        return false;
                    }

                    @Override
                    public boolean contains(@Nullable Object o) {
                        return false;
                    }

                    @NonNull
                    @Override
                    public Iterator<String> iterator() {
                        return null;
                    }

                    @NonNull
                    @Override
                    public Object[] toArray() {
                        return new Object[0];
                    }

                    @NonNull
                    @Override
                    public <T> T[] toArray(@NonNull T[] ts) {
                        return null;
                    }

                    @Override
                    public boolean add(String s) {
                        return false;
                    }

                    @Override
                    public boolean remove(@Nullable Object o) {
                        return false;
                    }

                    @Override
                    public boolean containsAll(@NonNull Collection<?> collection) {
                        return false;
                    }

                    @Override
                    public boolean addAll(@NonNull Collection<? extends String> collection) {
                        return false;
                    }

                    @Override
                    public boolean addAll(int i, @NonNull Collection<? extends String> collection) {
                        return false;
                    }

                    @Override
                    public boolean removeAll(@NonNull Collection<?> collection) {
                        return false;
                    }

                    @Override
                    public boolean retainAll(@NonNull Collection<?> collection) {
                        return false;
                    }

                    @Override
                    public void clear() {

                    }

                    @Override
                    public String get(int i) {
                        return null;
                    }

                    @Override
                    public String set(int i, String s) {
                        return null;
                    }

                    @Override
                    public void add(int i, String s) {

                    }

                    @Override
                    public String remove(int i) {
                        return null;
                    }

                    @Override
                    public int indexOf(@Nullable Object o) {
                        return 0;
                    }

                    @Override
                    public int lastIndexOf(@Nullable Object o) {
                        return 0;
                    }

                    @NonNull
                    @Override
                    public ListIterator<String> listIterator() {
                        return null;
                    }

                    @NonNull
                    @Override
                    public ListIterator<String> listIterator(int i) {
                        return null;
                    }

                    @NonNull
                    @Override
                    public List<String> subList(int i, int i1) {
                        return null;
                    }
                };
            }
            return likes;
        }

        public void setLikes(List<String> likes) {
            this.likes = likes;
        }
    }
}
