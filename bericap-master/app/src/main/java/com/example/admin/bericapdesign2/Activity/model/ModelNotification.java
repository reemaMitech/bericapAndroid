package com.example.admin.bericapdesign2.Activity.model;

/**
 * Class Name       :  <b>ModelNotification.java<b/>
 * Purpose          :  ModelNotification is pojo class of the notification table.
 * Developed By     :  <b>@Raghu_android</b>
 * Created Date     :  <b>14.10.2015</b>
 * <p/>
 * Modified Reason  :  <b></b>
 * Modified By      :  <b></b>
 * Modified Date    :  <b></b>
 * <p/>
 */
public class ModelNotification {
    private String id, title, message, date_time, is_view, type, created_time;
    private String request_id, response_id;

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getResponse_id() {
        return response_id;
    }

    public void setResponse_id(String response_id) {
        this.response_id = response_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getIs_view() {
        return is_view;
    }

    public void setIs_view(String is_view) {
        this.is_view = is_view;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }
}
