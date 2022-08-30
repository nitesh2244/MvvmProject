package com.example.mvvmdemo.auth.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class  UserLoginResponse implements Serializable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("_token")
    @Expose
    private String token;
    @SerializedName("data")
    @Expose
    private Data data;

    @Override
    public String toString() {
        return "UserLoginResponse{" +
                "status='" + status + '\'' +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", token='" + token + '\'' +
                ", data=" + data +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token)  {
        this.token = token;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }


    public class Data implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("userEmail")
        @Expose
        private String userEmail;
        @SerializedName("number")
        @Expose
        private String number;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("userName")
        @Expose
        private String userName;
        @SerializedName("profileUrl")
        @Expose
        private Object profileUrl;

        @Override
        public String toString() {
            return "Data{" +
                    "id=" + id +
                    ", userEmail='" + userEmail + '\'' +
                    ", number='" + number + '\'' +
                    ", password='" + password + '\'' +
                    ", userName='" + userName + '\'' +
                    ", profileUrl=" + profileUrl +
                    '}';
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getUserEmail() {
            return userEmail;
        }

        public Serializable setUserEmail(String userEmail) {
            this.userEmail = userEmail;
            return null;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Object getProfileUrl() {
            return profileUrl;
        }

        public void setProfileUrl(Object profileUrl) {
            this.profileUrl = profileUrl;
        }

    }
}
