package com.example.master;

import android.content.SharedPreferences;
import com.example.master.structure.MemeInfo;

import java.util.ArrayList;

public class StaticVariable {
    public static ArrayList<MemeInfo> listMemes = new ArrayList<>();
    public static SharedPreferences sharedPref;
    public static long delayTime = 300;
}
