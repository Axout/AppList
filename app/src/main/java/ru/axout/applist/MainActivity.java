package ru.axout.applist;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.*;

public class MainActivity extends AppCompatActivity {

    private List<String> result = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        PackageManager pm = this.getPackageManager();

        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> list = pm.queryIntentActivities(intent, PackageManager.PERMISSION_GRANTED);
        for (ResolveInfo rInfo : list) {
            result.add(rInfo.activityInfo.applicationInfo.loadLabel(pm).toString());
//            Log.i("tag", rInfo.activityInfo.applicationInfo.loadLabel(pm).toString());
        }

//        Collections.sort(result);
        HashMap<String, Integer> hashMap = new HashMap<>();
        Integer item;
        for (String appName : result) {
            item = hashMap.get(appName);
            if (item == null) hashMap.put(appName, 1);
            else hashMap.put(appName, item + 1);
        }

        result.clear();
        for (Map.Entry<String, Integer> app : hashMap.entrySet()) {
            result.add(app.getKey() + " : " + app.getValue());
            Log.i("tag", app.getKey() + " : " + app.getValue());
        }

        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, result));
    }
}