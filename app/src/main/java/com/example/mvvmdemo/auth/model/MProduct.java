package com.example.mvvmdemo.auth.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MProduct {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Data> data = null;

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

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public class Data {

        @SerializedName("menuId")
        @Expose
        private Integer menuId;
        @SerializedName("dishName")
        @Expose
        private String dishName;
        @SerializedName("price")
        @Expose
        private Double price;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("productType")
        @Expose
        private String productType;
        @SerializedName("productImage")
        @Expose
        private String productImage;
        @SerializedName("veg")
        @Expose
        private Boolean veg;

        @Override
        public String toString() {
            return "Data{" +
                    "menuId=" + menuId +
                    ", dishName='" + dishName + '\'' +
                    ", price=" + price +
                    ", description='" + description + '\'' +
                    ", productType='" + productType + '\'' +
                    ", productImage='" + productImage + '\'' +
                    ", veg=" + veg +
                    '}';
        }

        public Integer getMenuId() {
            return menuId;
        }

        public void setMenuId(Integer menuId) {
            this.menuId = menuId;
        }

        public String getDishName() {
            return dishName;
        }

        public void setDishName(String dishName) {
            this.dishName = dishName;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getProductType() {
            return productType;
        }

        public void setProductType(String productType) {
            this.productType = productType;
        }

        public String getProductImage() {
            return productImage;
        }

        public void setProductImage(String productImage) {
            this.productImage = productImage;
        }

        public Boolean getVeg() {
            return veg;
        }

        public void setVeg(Boolean veg) {
            this.veg = veg;
        }
    }

}




