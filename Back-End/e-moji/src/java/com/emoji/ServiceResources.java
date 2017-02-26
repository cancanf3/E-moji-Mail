/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emoji;


import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * REST Web Service
 *
 * @author josepena
 */
@Path("service")
public class ServiceResources {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServiceResources
     */
    public ServiceResources() {
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Text analyze ( String text) {

        ToneAnalyzer toneAnalyzer = new ToneAnalyzer(ToneAnalyzer.VERSION_DATE_2016_05_19);
        toneAnalyzer.setUsernameAndPassword("0c9aa4c7-3806-4b4b-9110-f97eb32eea93", "lA1fuWgH8ROI");

        ToneAnalysis toneAnalysis = toneAnalyzer.getTone(text, null).execute();
        DocumentTone documentTone = new DocumentTone();
        JSONObject jsonObject = new JSONObject(toneAnalysis.getDocumentTone().toString());
        JSONArray toneCategories = jsonObject.getJSONArray("tone_categories");
        JSONObject category = toneCategories.getJSONObject(0);
        ToneCategory toneCategory = new ToneCategory();
        toneCategory.categoryId = category.getString("category_id");
        toneCategory.categoryName = category.getString("category_name");
        documentTone.toneCategory = toneCategory;
        JSONArray tones = category.getJSONArray("tones");
        for (int i = 0; i < tones.length(); i++) {

            Tone tone = new Tone();
            JSONObject toneArrayObject = tones.getJSONObject(i);
            tone.toneId = toneArrayObject.getString("tone_id");
            tone.toneName = toneArrayObject.getString("tone_name");
            tone.score = toneArrayObject.getDouble("score");
            documentTone.toneCategory.tones.add(tone);
        }

        int maxPosition = 0;
        double max = 0.0;
        for(int i =0; i <documentTone.toneCategory.tones.size();i++){

            double score = documentTone.toneCategory.tones.get(i).score;

            if(score > max){

                max = score;
                maxPosition = i;
            }
        }

        Tone strongestTone = documentTone.toneCategory.tones.get(maxPosition);
        String responseEmoji;

        if(strongestTone.toneName.equals("Anger")){

            responseEmoji = "&#x1F620";

        }
        else if(strongestTone.toneName.equals("Disgust")){

            responseEmoji = "&#x1f922";
        }
        else if(strongestTone.toneName.equals("Fear")){

            responseEmoji = "&#x1f631";

        }
        else if(strongestTone.toneName.equals("Joy")){

            responseEmoji = "&#x1f602";
        }
        else if(strongestTone.toneName.equals("Sadness")){

            responseEmoji = "&#x1F622";
        }
        else{

            responseEmoji = "¯\\_(ツ)_/¯";
        }

        System.out.println();

       
        Text body = new Text();
        body.setBody(responseEmoji);
            return body;

    }
}
class Text {
    public String body;
       
    public void setBody (String body) {
        this.body = body;
    }
}

class DocumentTone {

    ToneCategory toneCategory  = new ToneCategory();
}

class Tone {

    String toneId;
    String toneName;
    double score;

}

class ToneCategory {

    String categoryId;
    String categoryName;
    ArrayList<Tone> tones = new ArrayList<>();
}

