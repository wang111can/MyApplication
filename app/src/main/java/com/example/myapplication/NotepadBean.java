package com.example.myapplication;

public class NotepadBean {
    private String id;//记录编号
    private String notepadContent;//记录的内容
    private String notepadTime;//保存记录的时间

    public NotepadBean() {
        id = null;
        notepadContent = null;
        notepadTime = null;
    }
    public NotepadBean (String _id, String _notepadContent, String _notepadTime){
        id = _id;
        notepadContent = _notepadContent;
        notepadTime = _notepadTime;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getNotepadContent() {
        return notepadContent;
    }

    public void setNotepadContent(String notepadContent) {
        this.notepadContent = notepadContent;
    }

    public String getNotepadTime() {
        return notepadTime;
    }

    public void setNotepadTime(String notepadTime) {
        this.notepadTime = notepadTime;
    }
}
