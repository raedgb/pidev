/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tn.esprit.services;

import java.sql.Date;
import java.util.Objects;
import tn.esprit.entity.User;

/**
 *
 * @author gabsi
 */
public class UserSession {
     public static String userMail= "";
    public static String userString;
    public static Date userDate;
    private final UserService userService = new UserService();


    public void setUserId(String email,String mail)
    {
        if(Objects.equals(email, "")) return ;
        UserSession.userMail = email ;
        UserSession.userString=mail;
    }
    
    public String getUserEmail() {
        return userMail;
    }

    public void setUserEmail(String userIntEmail) {
    if (userIntEmail != null) {
        UserSession.userMail = userIntEmail;
    }
}
    

    public static Date getUserDate() {
        return userDate;
    }

    public static void setUserDate(Date userDate) {
        UserSession.userDate = userDate;
    }

    public static String getUserString() {
        return userString;
    }

    public static void setUserString(String userString) {
        UserSession.userString = userString;
    }
    public static String getUserId()
    {
        return UserSession.userMail;
    }
    
    public User getUser()
    {
        return userService.GetUserByMailSession(userMail);
    }


}
