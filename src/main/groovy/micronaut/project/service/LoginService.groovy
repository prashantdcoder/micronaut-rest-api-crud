package micronaut.project.service

import io.micronaut.context.annotation.Prototype
import micronaut.project.DTO.DTO
import javax.net.ssl.HttpsURLConnection

@Prototype
class LoginService {

    public static final String FB_APP_ID = "Your APP ID"
    public static final String REDIRECT_URI = "http://localhost:8080/login/facebook"
    public static final String END_POINT = "https://www.facebook.com/v3.2/dialog/oauth?"

    DTO faceBookLogin() {
        DTO dto
        try {
            String html = invokeLoginDialog()
            dto = new DTO(true, "Login dialog has been opened successfully")
        } catch (Exception e) {
            dto = new DTO(false, e.getMessage())
        }
        return dto
    }


    String invokeLoginDialog() {
        String urlParameters = "client_id=${FB_APP_ID}&redirect_uri=${REDIRECT_URI}"
        URL url = new URL(END_POINT)
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection()
        con.setRequestMethod("POST")
        con.setRequestProperty("Accept-Language", "en-US,enq=0.5")
        con.setDoOutput(true)

        DataOutputStream wr = new DataOutputStream(con.getOutputStream())
        wr.writeBytes(urlParameters)
        wr.flush()
        wr.close()

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()))
        String inputLine
        StringBuffer response = new StringBuffer()

        while ((inputLine = bufferedReader.readLine())) {
            response.append(inputLine)
        }
        bufferedReader.close()
        return response.toString()
    }
}
