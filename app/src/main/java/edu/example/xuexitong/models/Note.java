package edu.example.xuexitong.models;

import java.io.Serializable;

/**
 * 笔记类
 */
public class Note implements Serializable {
    private int noteId;
    private int userId;
    private String title;
    private String content;
    private String creationTime;

    public Note(int userId, String title, String content, String creationTime) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.creationTime = creationTime;
    }

    public Note(int noteId, int userId, String title, String content, String creationTime) {
        this.noteId = noteId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.creationTime = creationTime;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public String toString() {
        return "Note{" +
            "noteId=" + noteId +
            ", title='" + title + '\'' +
            ", author='" + userId + '\'' +
            ", content='" + content + '\'' +
            ", creationTime='" + creationTime + '\'' +
            '}';
    }
}