package com.embeddeprinter;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import android.content.Context;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;

import com.basewin.define.GlobalDef;
import com.basewin.models.BitmapPrintLine;
import com.basewin.models.PrintLine;
import com.basewin.models.TextPrintLine;
import com.basewin.services.ServiceManager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.annotation.NonNull;

import com.basewin.define.GlobalDef;
import com.basewin.models.BitmapPrintLine;
import com.basewin.models.PrintLine;
import com.basewin.models.TextPrintLine;
import com.basewin.services.ServiceManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class EmbeddedPrinter extends ReactContextBaseJavaModule implements EasyPermissions.PermissionCallbacks{
    ReactApplicationContext context;
    TextPrintLine textPrintLine = new TextPrintLine();
    private int align = PrintLine.LEFT;
    private String LINE_BREAK = "------------------------------------------------";
    private String egpaySite = "www.egpay.com";
    private String egpayTel = "Tel: +202 22760666 | +202 22760660";
    public static final int REQUEST_PERMISSION = 0x01;
    public static final String TAG = "EGPAY";

    EmbeddedPrinter(ReactApplicationContext context) {
        super(context);

        this.context = context;

        Log.v("PrinterReceiver", "------------ Embedded Printer called ");

    }


    @Override
    public String getName() {
        return "EmbeddedPrinter";
    }


    @ReactMethod
    public void initPrinter(final Promise p) {
        getSmartpeakPermissions();
        p.resolve("Hello world");
    }

    @ReactMethod
    public void getSmartpeakPermissions(final Promise p) {
        String[] perms = {
                "com.pos.permission.SECURITY",
                "com.pos.permission.PRINTER",
        };
        if (EasyPermissions.hasPermissions(context, perms)) {
            startPrinting();
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(
                    new PermissionRequest
                            .Builder(context.getCurrentActivity(), REQUEST_PERMISSION, perms)
                            .setRationale("Dear users\n need to apply for storage Permissions for\n your better use of this application")
                            .setNegativeButtonText("NO")
                            .setPositiveButtonText("YES")
                            .build()
            );

            p.resolve("Hello world");
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if (requestCode == 1) {
            startPrinting();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }

    private void startPrinting() {
        try {
            ServiceManager.getInstence().init(context);
            ServiceManager.getInstence().getPrinter().setPrintTypesettingType(GlobalDef.ANDROID_TYPESETTING);
            ServiceManager.getInstence().getPrinter().cleanCache();
            ServiceManager.getInstence().getPrinter().setPrintGray(2000);
            ServiceManager.getInstence().getPrinter().setLineSpace(0);
            printRecipt();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "print: Exception" + e);
        }
    }

    public boolean printRecipt() throws Exception {


//        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.egpay_rev_m);
//        bitmapPrintLine.setPosition(PrintLine.CENTER);
//        bitmapPrintLine.setBitmap(bitmap);
//        ServiceManager.getInstence().getPrinter().addPrintLine(bitmapPrintLine);

        textPrintLine.setContent(LINE_BREAK);
        ServiceManager.getInstence().getPrinter().addPrintLine(textPrintLine);

        textPrintLine.setContent(LINE_BREAK);
        ServiceManager.getInstence().getPrinter().addPrintLine(textPrintLine);
        printLine("asasasasaas", align);
        textPrintLine.setContent(LINE_BREAK);
        ServiceManager.getInstence().getPrinter().addPrintLine(textPrintLine);
        textPrintLine.setContent(egpaySite);
        ServiceManager.getInstence().getPrinter().addPrintLine(textPrintLine);
        textPrintLine.setContent(egpayTel);
        ServiceManager.getInstence().getPrinter().addPrintLine(textPrintLine);
        textPrintLine.setContent("\n\n\n");
        ServiceManager.getInstence().getPrinter().addPrintLine(textPrintLine);

        ServiceManager.getInstence().getPrinter().beginPrint(null);

        return true;


    }

    private void printLine(String text, int position) throws Exception {
        textPrintLine.setType(PrintLine.TEXT);
        textPrintLine.setSize(24);
        textPrintLine.setPosition(position);
        textPrintLine.setContent(text);
        ServiceManager.getInstence().getPrinter().addPrintLine(textPrintLine);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            startPrinting();
        }
    }
}
