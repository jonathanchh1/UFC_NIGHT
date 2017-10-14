package com.example.jonat.services.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jonat on 10/11/2017.
 */

public class Medias implements Parcelable{

    @SerializedName("id")
    private int id;
    @SerializedName("type")
    private String type;
    @SerializedName("media_date")
    private String media_date;
    @SerializedName("description")
    private String description;
    @SerializedName("more_link_text")
    private String more_link;
    @SerializedName("internal_url")
    private String internalUrl;
    @SerializedName("thumbnail")
    private String thumbnail;
    @SerializedName("title")
    private String title;
    @SerializedName("more_linkurl")
    private String moreLinkurl;
    @SerializedName("embedded_type")
    private String embeddedType;
    @SerializedName("embedded_id")
    private String embedded_id;
    @SerializedName("mobile_stream_url")
    private String mobileStream;
    @SerializedName("media_video_url")
    private String mediaVideo;
    @SerializedName("last_modified")
    private String lastModified;
    @SerializedName("url_name")
    private String urlName;
    @SerializedName("created")
    private String created;
    @SerializedName("keyword_ids")
    private String keywords;
    @SerializedName("published_start_date")
    private String published_start_date;

    public int getId() {
        return id;
    }

    public String getCreated() {
        return created;
    }

    public String getDescription() {
        return description;
    }

    public String getEmbeddedType() {
        return embeddedType;
    }

    public String getInternalUrl() {
        return internalUrl;
    }

    public String getEmbedded_id() {
        return embedded_id;
    }

    public String getMore_link() {
        return more_link;
    }

    public String getMedia_date() {
        return media_date;
    }

    public String getLastModified() {
        return lastModified;
    }

    public String getMediaVideo() {
        return mediaVideo;
    }

    public String getMoreLinkurl() {
        return moreLinkurl;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getMobileStream() {
        return mobileStream;
    }

    public String getKeywords() {
        return keywords;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getUrlName() {
        return urlName;
    }

    public String getPublished_start_date() {
        return published_start_date;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmbedded_id(String embedded_id) {
        this.embedded_id = embedded_id;
    }

    public void setEmbeddedType(String embeddedType) {
        this.embeddedType = embeddedType;
    }

    public void setInternalUrl(String internalUrl) {
        this.internalUrl = internalUrl;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public void setMore_link(String more_link) {
        this.more_link = more_link;
    }

    public void setMedia_date(String media_date) {
        this.media_date = media_date;
    }

    public void setMediaVideo(String mediaVideo) {
        this.mediaVideo = mediaVideo;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void setMobileStream(String mobileStream) {
        this.mobileStream = mobileStream;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setMoreLinkurl(String moreLinkurl) {
        this.moreLinkurl = moreLinkurl;
    }

    public void setPublished_start_date(String published_start_date) {
        this.published_start_date = published_start_date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    protected Medias(Parcel in) {
        id = in.readInt();
        type = in.readString();
        media_date = in.readString();
        description = in.readString();
        more_link = in.readString();
        internalUrl = in.readString();
        thumbnail = in.readString();
        title = in.readString();
        moreLinkurl = in.readString();
        embeddedType = in.readString();
        embedded_id = in.readString();
        mobileStream = in.readString();
        mediaVideo = in.readString();
        lastModified = in.readString();
        urlName = in.readString();
        created = in.readString();
        keywords = in.readString();
        published_start_date = in.readString();
    }

    public static final Creator<Medias> CREATOR = new Creator<Medias>() {
        @Override
        public Medias createFromParcel(Parcel in) {
            return new Medias(in);
        }

        @Override
        public Medias[] newArray(int size) {
            return new Medias[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(type);
        parcel.writeString(media_date);
        parcel.writeString(description);
        parcel.writeString(more_link);
        parcel.writeString(internalUrl);
        parcel.writeString(thumbnail);
        parcel.writeString(title);
        parcel.writeString(moreLinkurl);
        parcel.writeString(embeddedType);
        parcel.writeString(embedded_id);
        parcel.writeString(mobileStream);
        parcel.writeString(mediaVideo);
        parcel.writeString(lastModified);
        parcel.writeString(urlName);
        parcel.writeString(created);
        parcel.writeString(keywords);
        parcel.writeString(published_start_date);
    }
}
