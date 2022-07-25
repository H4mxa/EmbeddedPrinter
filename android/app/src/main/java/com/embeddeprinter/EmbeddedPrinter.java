package com.embeddeprinter;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;


public class EmbeddedPrinter extends ReactContextBaseJavaModule {

    EmbeddedPrinter(ReactApplicationContext context) {
        super(context);
    }


    @Override
    public String getName() {
        return "EmbeddedPrinter";
    }


    @ReactMethod
    public void initPrinter(final Promise p) {
        p.resolve("Hello world");
    }
}
