package com.example.jonat.services;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jonat on 10/10/2017.
 */

public class UFCContent implements Parcelable {

    @SerializedName("id")
    private String id;
    @SerializedName("event_date")
    private String eventdate;
    @SerializedName("secondary_feature_image")
    private String image_feature;
    @SerializedName("ticket_image")
    private String ticket_image;
    @SerializedName("event_time_zone_text")
    private String eventtime;
    @SerializedName("short_description")
    private String short_desc;
    @SerializedName("event_dategmt")
    private String eventdategmt;
    @SerializedName("end_event_dategmt")
    private String endeventdate;
    @SerializedName("ticketurl")
    private String ticketurl;
    @SerializedName("ticket_seller_name")
    private String eventseller;
    @SerializedName("base_title")
    private String base_title;
    @SerializedName("title_tag_line")
    private String title_tag;
    @SerializedName("twitter_hashtag")
    private String twittertag;
    @SerializedName("ticket_general_sale_date")
    private String ticketdate;
    @SerializedName("statid")
    private String statid;
    @SerializedName("feature_image")
    private String featuredImage;
    @SerializedName("event_time_text")
    private String event_time;
    @SerializedName("ticket_general_sale_text")
    private String ticket_general_sales_text;
    @SerializedName("subtitle")
    private String subtitle;
    @SerializedName("event_status")
    private String eventstatus;
    @SerializedName("isppvevent")
    private String ispp;
    @SerializedName("corner_audio_available")
    private String corneraudio;
    @SerializedName("corner_audio_blue_stream_url")
    private String corneraudioblue_stream;
    @SerializedName("corner_audio_red_stream_url")
    private String corneraudiored_stream;
    @SerializedName("last_modified")
    private String lastModified;
    @SerializedName("url_name")
    private String url_name;
    @SerializedName("created")
    private String created;
    @SerializedName("trailer_url")
    private String trailer_url;
    @SerializedName("arena")
    private String arena;
    @SerializedName("location")
    private String location;

    public UFCContent() {

    }

    public String getBase_title() {
        return base_title;
    }

    public String getEndeventdate() {
        return endeventdate;
    }

    public String getEvent_time() {
        return event_time;
    }

    public String getEventdate() {
        return eventdate;
    }

    public String getEventdategmt() {
        return eventdategmt;
    }

    public String getEventseller() {
        return eventseller;
    }

    public String getEventtime() {
        return eventtime;
    }

    public String getCorneraudiored_stream() {
        return corneraudiored_stream;
    }

    public void setCorneraudiored_stream(String corneraudiored_stream) {
        this.corneraudiored_stream = corneraudiored_stream;
    }

    public String getFeaturedImage() {
        return featuredImage;
    }

    public String getId() {
        return id;
    }

    public String getImage_feature() {
        return image_feature;
    }

    public String getEventstatus() {
        return eventstatus;
    }

    public String getShort_desc() {
        return short_desc;
    }

    public String getCorneraudio() {
        return corneraudio;
    }

    public String getIspp() {
        return ispp;
    }

    public String getCorneraudioblue_stream() {
        return corneraudioblue_stream;
    }

    public String getStatid() {
        return statid;
    }

    public String getLastModified() {
        return lastModified;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getTicket_general_sales_text() {
        return ticket_general_sales_text;
    }

    public String getTicket_image() {
        return ticket_image;
    }

    public String getTicketdate() {
        return ticketdate;
    }

    public String getCreated() {
        return created;
    }

    public String getTicketurl() {
        return ticketurl;
    }

    public String getArena() {
        return arena;
    }

    public String getLocation() {
        return location;
    }

    public String getTitle_tag() {
        return title_tag;
    }

    public String getTrailer_url() {
        return trailer_url;
    }

    public String getTwittertag() {
        return twittertag;
    }

    public String getUrl_name() {
        return url_name;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setBase_title(String base_title) {
        this.base_title = base_title;
    }

    public void setEndeventdate(String endeventdate) {
        this.endeventdate = endeventdate;
    }

    public void setEvent_time(String event_time) {
        this.event_time = event_time;
    }

    public void setEventdate(String eventdate) {
        this.eventdate = eventdate;
    }

    public void setCorneraudio(String corneraudio) {
        this.corneraudio = corneraudio;
    }

    public void setEventdategmt(String eventdategmt) {
        this.eventdategmt = eventdategmt;
    }

    public void setCorneraudioblue_stream(String corneraudioblue_stream) {
        this.corneraudioblue_stream = corneraudioblue_stream;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setEventseller(String eventseller) {
        this.eventseller = eventseller;
    }

    public void setEventstatus(String eventstatus) {
        this.eventstatus = eventstatus;
    }

    public void setEventtime(String eventtime) {
        this.eventtime = eventtime;
    }

    public void setImage_feature(String image_feature) {
        this.image_feature = image_feature;
    }

    public void setFeaturedImage(String featuredImage) {
        this.featuredImage = featuredImage;
    }

    public void setShort_desc(String short_desc) {
        this.short_desc = short_desc;
    }

    public void setIspp(String ispp) {
        this.ispp = ispp;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public void setStatid(String statid) {
        this.statid = statid;
    }

    public void setUrl_name(String url_name) {
        this.url_name = url_name;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setTicket_general_sales_text(String ticket_general_sales_text) {
        this.ticket_general_sales_text = ticket_general_sales_text;
    }

    public void setTicket_image(String ticket_image) {
        this.ticket_image = ticket_image;
    }

    public void setArena(String arena) {
        this.arena = arena;
    }

    public void setTicketdate(String ticketdate) {
        this.ticketdate = ticketdate;
    }

    public void setTicketurl(String ticketurl) {
        this.ticketurl = ticketurl;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTitle_tag(String title_tag) {
        this.title_tag = title_tag;
    }

    public void setTrailer_url(String trailer_url) {
        this.trailer_url = trailer_url;
    }

    public void setTwittertag(String twittertag) {
        this.twittertag = twittertag;
    }

    protected UFCContent(Parcel in) {
        id = in.readString();
        eventdate = in.readString();
        image_feature = in.readString();
        ticket_image = in.readString();
        eventtime = in.readString();
        short_desc = in.readString();
        eventdategmt = in.readString();
        endeventdate = in.readString();
        ticketurl = in.readString();
        eventseller = in.readString();
        base_title = in.readString();
        title_tag = in.readString();
        twittertag = in.readString();
        ticketdate = in.readString();
        statid = in.readString();
        featuredImage = in.readString();
        event_time = in.readString();
        ticket_general_sales_text = in.readString();
        subtitle = in.readString();
        eventstatus = in.readString();
        ispp = in.readString();
        corneraudio = in.readString();
        corneraudioblue_stream = in.readString();
        corneraudiored_stream = in.readString();
        lastModified = in.readString();
        url_name = in.readString();
        created = in.readString();
        trailer_url = in.readString();
        arena = in.readString();
        location = in.readString();
    }

    public static final Creator<UFCContent> CREATOR = new Creator<UFCContent>() {
        @Override
        public UFCContent createFromParcel(Parcel in) {
            return new UFCContent(in);
        }

        @Override
        public UFCContent[] newArray(int size) {
            return new UFCContent[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(eventdate);
        dest.writeString(image_feature);
        dest.writeString(ticket_image);
        dest.writeString(eventtime);
        dest.writeString(short_desc);
        dest.writeString(eventdategmt);
        dest.writeString(endeventdate);
        dest.writeString(ticketurl);
        dest.writeString(eventseller);
        dest.writeString(base_title);
        dest.writeString(title_tag);
        dest.writeString(twittertag);
        dest.writeString(ticketdate);
        dest.writeString(statid);
        dest.writeString(featuredImage);
        dest.writeString(event_time);
        dest.writeString(ticket_general_sales_text);
        dest.writeString(subtitle);
        dest.writeString(eventstatus);
        dest.writeString(ispp);
        dest.writeString(corneraudio);
        dest.writeString(corneraudioblue_stream);
        dest.writeString(corneraudiored_stream);
        dest.writeString(lastModified);
        dest.writeString(url_name);
        dest.writeString(created);
        dest.writeString(trailer_url);
        dest.writeString(arena);
        dest.writeString(location);
    }
}
