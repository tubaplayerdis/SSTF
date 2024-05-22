package com.tbdis.sstf;

public class Setting extends Member {
    public Setting() {
        super("null", "null");
    };
    public Setting(String name, String data) {
        super(name, data);
    }
    public Setting(String name, int data) {
        super(name, String.valueOf(data));
    }
}
