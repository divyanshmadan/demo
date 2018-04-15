/**
 * Created by dmadan on 4/14/18.
 */

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

class SearchRequest {
    String planName;
    String sponsorName;
    String sponsorState;

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }

    public String getSponsorState() {
        return sponsorState;
    }

    public void setSponsorState(String sponsorState) {
        this.sponsorState = sponsorState;
    }

    public SearchRequest(String planName, String sponsorName, String sponsorState) {
        this.planName = planName;
        this.sponsorName = sponsorName;
        this.sponsorState = sponsorState;
    }


}

public class LambdaIntegration implements RequestHandler<SearchRequest, JSONObject> {

    public JSONObject handleRequest(SearchRequest request, Context context) {

        String url = "https://search-divy-search-hk2xfp7rk4nysh5us3ddtzmoey.us-west-2.es.amazonaws.com/plans/_search?q=";
        try {
            if (request.planName != null) {
                url += "PLAN_NAME:\"" + URLEncoder.encode(request.planName, "UTF-8") + "\"";

            }

            if (request.sponsorName != null) {
                url += "SPONSOR_DFE_NAME:\"" + URLEncoder.encode(request.sponsorName, "UTF-8") + "\"";


            }
            if (request.sponsorState != null) {
                url += "SPONS_DFE_MAIL_US_STATE:\"" + URLEncoder.encode(request.sponsorState, "UTF-8") + "\"";

            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        StringBuffer response = new StringBuffer();
        JSONObject json = null;
        try {
            URL urlObject = new URL(url);
            System.out.println(url);
            HttpURLConnection con = (HttpURLConnection) urlObject.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONParser parser = new JSONParser();

        try {
            json = (JSONObject) parser.parse(response.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return json;
    }
}
