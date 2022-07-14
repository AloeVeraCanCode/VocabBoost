package com.example.vocabboost.Common;

import android.content.Context;

import java.io.Serializable;

public class SerializedContext implements Serializable {
    public Context context;
    public SerializedContext(Context c){context=c;}
}
