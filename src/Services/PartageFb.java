/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import com.restfb.BinaryAttachment;
import com.restfb.types.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 *
 * @author SLIMEN
 */
public class PartageFb {

    public void partager(String titre, String image) throws FileNotFoundException {

        String domain = "http://localhost/";
        //domain="https://google.fr/";
        String appId = "539407321125401";
        String appSecret = "bfc26b359943ff4353b22607261fc563";
        String authURL = "https://graph.facebook.com/oauth/authorize?type=user_agent&client_id=" + appId + "&redirect_uri=" + domain
                + "&scope=ads_management,publish_actions";
// slimayadi59@yahoo.fr
        System.setProperty("webdriver.chrome.driver", "api/chromedriver_win32/chromedriver.exe");
        WebDriver driver = new ChromeDriver(DesiredCapabilities.chrome());
        driver.get(authURL);
        String accessToken = "EAAHqlo3D0hkBANjikKZAkXFGZCJ2ZAPEU3aDVGrZCHQaLWDZBxBZAAiiShNjSLBdrX1MmhlCyvLSvmwlE2iWUxUb1joZAmJgXw27VMzdqm6vJVnT0QyaIkN95wO5lDKLKGJl5DuVcVxKLHrsOmSdHXD5zTUjYIaT9E3C7UVlMZB3U5sFOv46GFYbEXvHZBN63FXyQh3k6skG4MZB6XTYJdFQHMYU1kMpVAjMsZD";

        boolean ok = true;
        while (ok) {
            if ((!driver.getCurrentUrl().contains("facebook.com")) && (driver.getCurrentUrl() != authURL)) {
                String url = driver.getCurrentUrl();
                //accessToken =url.replaceAll(".*#access_token=(.+)&.*", "$1");
                System.out.println(accessToken);

                ok = false;
            }

        }

        System.out.println("act:" + accessToken);
        driver.quit();
        FacebookClient fbClient = new DefaultFacebookClient(accessToken);
        User me = fbClient.fetchObject("me", User.class);
        // System.out.println(me.g0<etUsername());
        FileInputStream fs = new FileInputStream(new File(image));
        FacebookType publishPhotoResponse = fbClient.publish("me/photos", FacebookType.class,
                BinaryAttachment.with(image, fs),
                Parameter.with("message", titre));
//            
//              FacebookType publishMessageResponse =
//            fbClient.publish("me/feed", FacebookType.class,
//                    com.restfb.Parameter.with("message","Evenement"));
//          
//
//              System.out.println("Published message ID: " + publishMessageResponse.getId());

        /**
         *
         */
    }
}
