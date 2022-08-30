package com.example.mvvmdemo.auth.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetUserOrder {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("orderId")
    @Expose
    private Integer orderId;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("product")
    @Expose
    private Product product;
    @SerializedName("paymentDetailTable")
    @Expose
    private PaymentDetailTable paymentDetailTable;

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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public PaymentDetailTable getPaymentDetailTable() {
        return paymentDetailTable;
    }

    public void setPaymentDetailTable(PaymentDetailTable paymentDetailTable) {
        this.paymentDetailTable = paymentDetailTable;
    }


    public class Data {

        @SerializedName("restaurantId")
        @Expose
        private Integer restaurantId;
        @SerializedName("restaurantDetail")
        @Expose
        private RestaurantDetail restaurantDetail;

        public Integer getRestaurantId() {
            return restaurantId;
        }

        public void setRestaurantId(Integer restaurantId) {
            this.restaurantId = restaurantId;
        }

        public RestaurantDetail getRestaurantDetail() {
            return restaurantDetail;
        }

        public void setRestaurantDetail(RestaurantDetail restaurantDetail) {
            this.restaurantDetail = restaurantDetail;
        }



    }

    public class RestaurantDetail {

        @SerializedName("restaurantDetailId")
        @Expose
        private Integer restaurantDetailId;
        @SerializedName("cuisines")
        @Expose
        private String cuisines;
        @SerializedName("location")
        @Expose
        private String location;
        @SerializedName("lat")
        @Expose
        private Double lat;
        @SerializedName("lng")
        @Expose
        private Double lng;
        @SerializedName("rating")
        @Expose
        private Double rating;
        @SerializedName("time")
        @Expose
        private String time;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("restaurantName")
        @Expose
        private String restaurantName;

        public Integer getRestaurantDetailId() {
            return restaurantDetailId;
        }

        public void setRestaurantDetailId(Integer restaurantDetailId) {
            this.restaurantDetailId = restaurantDetailId;
        }

        public String getCuisines() {
            return cuisines;
        }

        public void setCuisines(String cuisines) {
            this.cuisines = cuisines;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public Double getLng() {
            return lng;
        }

        public void setLng(Double lng) {
            this.lng = lng;
        }

        public Double getRating() {
            return rating;
        }

        public void setRating(Double rating) {
            this.rating = rating;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getRestaurantName() {
            return restaurantName;
        }

        public void setRestaurantName(String restaurantName) {
            this.restaurantName = restaurantName;
        }

        @Override
        public String toString() {
            return "RestaurantDetail{" +
                    "restaurantDetailId=" + restaurantDetailId +
                    ", cuisines='" + cuisines + '\'' +
                    ", location='" + location + '\'' +
                    ", lat=" + lat +
                    ", lng=" + lng +
                    ", rating=" + rating +
                    ", time='" + time + '\'' +
                    ", price='" + price + '\'' +
                    ", restaurantName='" + restaurantName + '\'' +
                    '}';
        }
    }

    public class Product {

        @SerializedName("productId")
        @Expose
        private Integer productId;
        @SerializedName("productData")
        @Expose
        private ProductData productData;

        public Integer getProductId() {
            return productId;
        }

        public void setProductId(Integer productId) {
            this.productId = productId;
        }

        public ProductData getProductData() {
            return productData;
        }

        public void setProductData(ProductData productData) {
            this.productData = productData;
        }

    }

    public class ProductData {

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

    public class PaymentDetailTable {

        @SerializedName("paymentId")
        @Expose
        private Integer paymentId;
        @SerializedName("orderId")
        @Expose
        private Integer orderId;
        @SerializedName("userId")
        @Expose
        private Integer userId;
        @SerializedName("amount")
        @Expose
        private Double amount;
        @SerializedName("provider")
        @Expose
        private String provider;
        @SerializedName("orderStatus")
        @Expose
        private String orderStatus;
        @SerializedName("createdAt")
        @Expose
        private String createdAt;

        public Integer getPaymentId() {
            return paymentId;
        }

        public void setPaymentId(Integer paymentId) {
            this.paymentId = paymentId;
        }

        public Integer getOrderId() {
            return orderId;
        }

        public void setOrderId(Integer orderId) {
            this.orderId = orderId;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

        public String getProvider() {
            return provider;
        }

        public void setProvider(String provider) {
            this.provider = provider;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

    }

}

