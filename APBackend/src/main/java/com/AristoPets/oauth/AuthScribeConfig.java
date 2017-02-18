package com.AristoPets.oauth;

import com.AristoPets.entity.enums.UserType;
import com.AristoPets.webconfig.CookieHelper;
import com.AristoPets.entity.User;
import com.AristoPets.entity.enums.AuthType;
import com.AristoPets.services.CookieService;
import com.AristoPets.services.UserService;
import com.github.scribejava.apis.FacebookApi;
import com.github.scribejava.apis.VkontakteApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.builder.api.BaseApi;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import java.io.IOException;

@Component
public class AuthScribeConfig {
    @Value(value = "${FBappID}")
    private String fbAppID;

    @Value(value = "${FBappSecret}")
    private String fbAppSecret;

    @Value(value = "${VKappID}")
    private String vkAppID;

    @Value(value = "${VKappSecret}")
    private String vkAppSecret;

    @Value(value = "${WebUrl}")
    private String webUrl;

    private final UserService userService;
    private final CookieService cookieService;

    @Autowired
    public AuthScribeConfig(UserService userService, CookieService cookieService) {
        this.userService = userService;
        this.cookieService = cookieService;
    }


    public String getAuthUrl(AuthType authType) {
        final OAuth20Service service = getOAuthService(authType);
        return service.getAuthorizationUrl();
    }


    public Cookie authorization(String code, AuthType authType) throws IOException {
        String body;
        String authId;
        if (authType == AuthType.FB) {
            body = this.getFaceBookData(code);
            authId = this.getFbUserId(body);
        } else if (authType == AuthType.VK) {
            body = this.getVkData(code);
            authId = this.getVkUserId(body);
        } else {
            throw new IOException("Can't read auth type");
        }
        User user = this.userService.getUserByAuthId(authId);
        if (user == null) {
            user = this.createUserBySocialAuth(body, authType);
            user = this.userService.save(user);
        }
        String uuid = this.cookieService.setCookie(user);
        Cookie cookie = new Cookie(CookieHelper.authCookie, uuid);
        cookie.setMaxAge(CookieHelper.MAX_AGE);
        cookie.setPath("/");
        return cookie;
    }

    public String getFaceBookData(String code) throws IOException {
        final OAuth20Service service = getOAuthService(AuthType.FB);
        OAuth2AccessToken accessToken = service.getAccessToken(code);
        OAuthRequest request = new OAuthRequest(Verb.GET, "https://graph.facebook.com/me?fields=id,name,email,first_name,last_name,picture.height(200).width(200)", service.getConfig());
        service.signRequest(accessToken, request);
        Response response = request.send();
        String body = response.getBody();
        return body;
    }

    public String getVkData(String code) throws IOException {
        final OAuth20Service service = getOAuthService(AuthType.VK);
        OAuth2AccessToken accessToken = service.getAccessToken(code);
        JSONObject json = new JSONObject(accessToken.getRawResponse());
        String email;
        try {
            email = json.getString("email");
        }catch (Exception e){ email = "";}
        OAuthRequest request = new OAuthRequest(Verb.GET, "https://api.vk.com/method/users.get?fields=email,photo_200", service.getConfig());
        service.signRequest(accessToken, request);
        Response response = request.send();
        String body = response.getBody();
        body = "{\"data\":" + body + ",\"email\":" + email + "}";
        return body;
    }

    public User createUserBySocialAuth(String body, AuthType authType) {
        User user = new User();
        String fName, lName, email, photo, authId;
        JSONObject userSocialInfo = new JSONObject(body);
        if (authType == AuthType.VK) {
            JSONObject data = userSocialInfo.getJSONObject("data");
            if (userSocialInfo.getString("email")!=null) {
                email = userSocialInfo.getString("email");
            } else {
                email="";
            }
            JSONArray response = data.getJSONArray("response");
            JSONObject json = response.getJSONObject(0);
            fName = json.getString("first_name");
            lName = json.getString("last_name");
            authId = String.valueOf(json.getInt("uid"));
            photo = json.getString("photo_200");
            user.setAuthType(AuthType.VK);
        } else {
            fName = userSocialInfo.getString("first_name");
            lName = userSocialInfo.getString("last_name");
            try {
                email = userSocialInfo.getString("email");
            }catch (Exception e){ email = "";}
            user.setAuthType(AuthType.FB);
            authId = userSocialInfo.getString("id");
            photo = userSocialInfo.getJSONObject("picture").getJSONObject("data").getString("url");
            user.setAuthType(AuthType.FB);
        }
        //TODO: OK auhtorization

        user.setFirstName(fName);
        user.setLastName(lName);
        user.setAuthId(authId);
        user.setPhoto(photo);
        user.setEmail(email);
        user.setUserType(UserType.USER);
        return user;
    }

    public String getFbUserId(String body) {
        JSONObject json = new JSONObject(body);
        return json.getString("id");
    }

    public String getVkUserId(String body) {
        JSONObject vkResponse = new JSONObject(body);
        JSONObject data = vkResponse.getJSONObject("data");
        JSONArray response = data.getJSONArray("response");
        JSONObject json = response.getJSONObject(0);
        int uid = json.getInt("uid");
        return String.valueOf(uid);
    }

    private OAuth20Service getOAuthService(AuthType authType) {
        if (authType == AuthType.FB) {
            return (OAuth20Service) new ServiceBuilder().apiKey(this.fbAppID)
                    .apiSecret(this.fbAppSecret).callback(webUrl + "/authorize/1").build((BaseApi) FacebookApi.instance());
        } else if (authType == AuthType.VK) {
            return (OAuth20Service) new ServiceBuilder().apiKey(this.vkAppID)
                    .apiSecret(this.vkAppSecret).callback(webUrl + "/authorize/2").scope("email").build((BaseApi) VkontakteApi.instance());
        }
        // TODO: Odnokls auth
        return null;
    }

}