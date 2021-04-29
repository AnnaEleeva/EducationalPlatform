package com.project.EducationalPlatform.controller;

import com.project.EducationalPlatform.users.UserService;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import com.vk.api.sdk.objects.users.responses.GetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RestController
public class VKController {

    private final String clientSecret="N8BemovEaCQgFBMvQRRq";
    private final int clientId=7835777;
    private final String host="localhost";
    private final Integer port=8080;
   // private final VkApiClient vk=;

    @Autowired
    private UserService userService;

    TransportClient transportClient = HttpTransportClient.getInstance();
    VkApiClient vk = new VkApiClient(transportClient);



   // UserActor actor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());

    public VKController() throws ClientException, ApiException {

    }

    @GetMapping("/authorize")
    @ResponseBody
    public void authorize(@RequestBody(required = false) Map<String, String> json, HttpServletResponse response) throws IOException {
        System.out.println("мы в authorize");
        String redirAuth=getOAuthUrl();
        System.out.println(redirAuth);
        response.sendRedirect(redirAuth);

    }
    @GetMapping("/info") //login?user=st@mail.ru&password=12er
    public void info(@RequestParam("user") String user, @RequestParam("token") String token,HttpServletResponse response) throws ServletException, ClientException, ApiException, IOException {

        System.out.println("мы в info");
        System.out.println("access_token: "+token);
        try {
            UserActor actor = new UserActor(Integer.parseInt(user), token);
            List<GetResponse> getUsersResponse = vk.users().get(actor).userIds(user).execute();
            GetResponse userR = getUsersResponse.get(0);

           String email= userR.getEmail();
           String phone=userR.getMobilePhone();
           String first_name=userR.getFirstName();
           String last_name=userR.getLastName();
            Integer vkId=userR.getId();

      /*      User u = new User();
            u.setFirstName(userR.getFirstName());
            u.setLastName(userR.getLastName());
            Token t=new Token();
            t.setAccess_token_VK(token);
            Set<Token> tokenSet= new HashSet<>();
            tokenSet.add(t);
            u.setTokens(tokenSet);*/



           System.out.println(email+" "+phone+" "+first_name+" "+last_name+" "+vkId);

            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            Cookie cookie_access_token = new Cookie("access_token_VK", token);
            cookie_access_token.setHttpOnly(true);
            response.addCookie(cookie_access_token);
            response.getWriter().println(getInfoPage(userR));

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            response.getWriter().println("error");
            response.setContentType("text/html;charset=utf-8");
            e.printStackTrace();
        }
    }
        @GetMapping("/callback")
    public void callback(@RequestParam("code") String code, HttpServletResponse response) throws ServletException, ClientException, ApiException, IOException {

 //   @PostMapping("/callback")
 //   @ResponseBody
 //   public void callback(@RequestBody(required = false) Map<String, String> json, HttpServletResponse response) throws IOException {

        System.out.println("мы в callback");
        System.out.println("code: "+code);
        //response.sendRedirect(getOAuthUrl());

     /*   UserAuthResponse authResponse = vk.oauth()
                .userAuthorizationCodeFlow(	7835777, "N8BemovEaCQgFBMvQRRq", "/localhost:8080", code)
                .execute();
        UserActor actor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());*/

        try {
            UserAuthResponse authResponse = vk.oAuth().userAuthorizationCodeFlow(clientId, clientSecret, getRedirectUri(), code).execute();
           response.sendRedirect("/?token=" + authResponse.getAccessToken() + "&user=" + authResponse.getUserId());

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            response.getWriter().println("error");
            response.setContentType("text/html;charset=utf-8");
            e.printStackTrace();
        }

    }

    private String getOAuthUrl() {

        return "https://oauth.vk.com/authorize?client_id=" + clientId + "&display=page&redirect_uri=" + getRedirectUri() + "&scope=groups&response_type=code";
    }

    private String getRedirectUri() {
        return String.format("http://%s:%d", host, port) + "/callback";
    }

    private String getInfoPage(GetResponse user) {
        return "Hello <a href='https://vk.com/id" + user.getId() + "'>" + user.getFirstName() + "</a>";
    }
}
