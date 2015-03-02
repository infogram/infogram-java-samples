package net.infogram.example;

import net.infogram.api.InfogramAPI;
import net.infogram.api.response.Response;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CreateInfographic {

    public static void main(String[] args) throws IOException {

        InfogramAPI api = new InfogramAPI("<your API key>", "<your API secret>");


        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("theme_id", "32");
        parameters.put("publish", "false");
        parameters.put("content", "[{\"text\": \"Testing API\", \"type\": \"h1\"}, {\"text\": \"Lorem ipsum dolor sit amet...\", \"type\": \"body\"}, {\"text\": \"God does not play dice\", \"type\": \"quote\", \"author\": \"Альберт Эйнштейн\"}, {\"chart_type\": \"bar\", \"type\": \"chart\", \"data\": [[[\"\\u0100boli\", \"\\u0161odien\", \"vakar\", \"aizvakar\"], [\"J\\u0101nis\", 4, 6, 7], [\"P\\u0113teris\", 1, 3, 9], [\"Juris\", 4, 4, 3]]], \"colors\": [\"ff0\", \"#ccffdd\", \"#00F\"]}]");
        parameters.put("title", "Testing API");

        Response response = api.sendRequest("POST", "infographics", parameters);

        int statusCode = response.getHttpStatusCode();
        if (statusCode == 201) {
            String infographicId = response.getHeaders().get("X-Infogram-Id").get(0);
            System.out.println("Infographic created, id=" + infographicId);
        } else {
            String messageBody = IOUtils.toString(response.getResponseBody(), "UTF-8");
            System.err.println("Could not create infographic, HTTP status=" + response.getHttpStatusCode() + ", message=" + messageBody);
        }
    }

}
