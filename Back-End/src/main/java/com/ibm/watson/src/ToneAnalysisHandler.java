import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ibm.watson.developer_cloud.alchemy.v1.model.Document;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Tim on 2/25/2017.
 */
public class ToneAnalysisHandler {

    public static void main(String[] args) {

        ToneAnalyzer toneAnalyzer = new ToneAnalyzer(ToneAnalyzer.VERSION_DATE_2016_05_19);
        toneAnalyzer.setUsernameAndPassword("0c9aa4c7-3806-4b4b-9110-f97eb32eea93", "lA1fuWgH8ROI");

        String text = "I know the times are difficult! Our sales have been "
                + "disappointing for the past three quarters for our data analytics "
                + "product suite. We have a competitive data analytics product "
                + "suite in the industry. But we need to do our job selling it! "
                + "We need to acknowledge and fix our sales challenges. "
                + "We can’t blame the economy for our lack of execution! " + "We are missing critical sales opportunities. "
                + "Our product is in no way inferior to the competitor products. "
                + "Our clients are hungry for analytical tools to improve their "
                + "business outcomes. Economy has nothing to do with it.";

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

        System.out.println(responseEmoji);

    }

}
