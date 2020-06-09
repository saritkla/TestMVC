package com.example.testmvc;

import android.os.Environment;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;


public class Model {
    private String json;
    private JSONObject jOb;
    private JSONArray  subjects;
    private JSONArray  platform;
    private JSONArray  lecture;

    public Model(){
        this.json = null;
    }
    //เพื่ออ่าน json ใน assets
    private void readJSONFromfileLocal() {
        try {
            File file = new File(Environment.getExternalStorageDirectory ()+"/Download/database.json");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null){
                stringBuilder.append(line).append("\n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            json = stringBuilder.toString();
//            Log.e("Json  ==>>",json);
            jOb = new JSONObject(json);
            subjects = jOb.getJSONArray("SUBJECTS");
            platform = jOb.getJSONArray("PLATFORMS");
            lecture = jOb.getJSONArray("LECTURER");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("json  ====", "BAD");
        }
    }

    //get data all
    public String getSubAndTeach() throws JSONException {
        readJSONFromfileLocal();
        StringBuilder stringBuilder = new StringBuilder();
        String teachname;
        for (int i = 0; i < subjects.length(); ++i){
            JSONObject sub = subjects.getJSONObject(i);
            String subjectname = sub.getString("Subject_name");
            String teachIDOUT = sub.getString("TID");
            for (int j = 0; j < lecture.length(); ++j){
                JSONObject lect = lecture.getJSONObject(j);
                String teachIDIN = lect.getString("TID");
                if (teachIDIN.equals(teachIDOUT)){
                    teachname = lect.getString("Firstname") + " " +lect.getString("Lastname");
                    stringBuilder.append(subjectname).append("    <===>   ").append(teachname).append("\n");
                }
            }
        }
        String done = stringBuilder.toString();
        return done;
    }
    //get จำนวนวิชาที่สอน
    public String getnumteach() throws JSONException {
        readJSONFromfileLocal();
        StringBuilder stringBuilder = new StringBuilder();
        String teachname;
        int count = 0;
        for (int j = 0; j < lecture.length(); ++j){
            JSONObject lect = lecture.getJSONObject(j);
            String teachIDOUT = lect.getString("TID");
            teachname = lect.getString("Firstname") + " " +lect.getString("Lastname");
            for (int i = 0; i < subjects.length(); ++i) {
                JSONObject sub = subjects.getJSONObject(i);
                String teachIDIN = sub.getString("TID");
                if (teachIDIN.equals(teachIDOUT)){
                    count += 1;
                }
            }
            stringBuilder.append(teachname).append("    <===>   ").append(count).append("\n");
            count = 0;
        }
        String done = stringBuilder.toString();
        return done;
    }
    //getsortting
    public String getsortting() throws JSONException {
        readJSONFromfileLocal();
        JSONArray Nsort = new JSONArray();
        StringBuilder stringBuilder = new StringBuilder();
        JSONObject subNsc = new JSONObject();
        for (int i = 0; i < subjects.length(); ++i) {
            JSONObject sub = subjects.getJSONObject(i);
            String subID = sub.getString("SID");
            String subName = sub.getString("Subject_name");
            for (int j =0;j< platform.length();j++){
                JSONObject plat = platform.getJSONObject(i);
                String platName = plat.getString("Platform_name");
                Double platscore = plat.getDouble("score");
                subNsc.put("SID",subID);
                subNsc.put("Subject_name",subName);
                subNsc.put("Platform_name",platName);
                subNsc.put("score",platscore);
                Nsort.put("\n\n"+subNsc);
            }
        }
        Arrays.sort(new JSONArray[]{Nsort});
        return Arrays.toString(new JSONArray[]{Nsort});
    }
}
