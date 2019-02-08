package tcss450.uw.edu.phishapp.setlist;


import java.io.Serializable;

import tcss450.uw.edu.phishapp.blog.BlogPost;

public class SetList implements Serializable {

    private final String mLongDate;
    private final String mLocation;
    private final String mVenue;
    private final String mData;
    private final String mNotes;
    private final String mUrl;

    /**
     * Helper class for building Credentials.
     *
     * @author Charles Bryan
     */
    public static class Builder {
        private final String mLongDate;
        private final String mLocation;
        private final String mVenue;
        private  String mUrl = "";
        private  String mNotes = "";
        private  String mData = "";



        /**
         * Constructs a new Builder.
         *
         * @param longDate the published date of the blog post
         * @param location the title of the blog post
         */
        public Builder(String longDate, String location, String venue) {
            this.mLongDate = longDate;
            this.mLocation = location;
            this.mVenue = venue;
        }

        /**
         * Add an optional url for the full blog post.
         * @param val an optional url for the full blog post
         * @return the Builder of this BlogPost
         */
        public SetList.Builder addUrl(final String val) {
            mUrl = val;
            return this;
        }

        /**
         * Add an optional teaser for the full blog post.
         * @param val an optional url teaser for the full blog post.
         * @return the Builder of this BlogPost
         */
        public SetList.Builder addData(final String val) {
            mData = val;
            return this;
        }

        /**
         * Add an optional author of the blog post.
         * @param val an optional author of the blog post.
         * @return the Builder of this BlogPost
         */
        public SetList.Builder addNotes(final String val) {
            mNotes = val;
            return this;
        }

        public SetList build() {
            return new SetList(this);
        }

    }

    private SetList(final SetList.Builder builder) {
        this.mLongDate = builder.mLongDate;
        this.mLocation = builder.mLocation;
        this.mVenue = builder.mVenue;
        this.mData = builder.mData;
        this.mNotes = builder.mNotes;
        this.mUrl = builder.mUrl;
    }

    public String getLongDate() {
        return mLongDate;
    }

    public String getLocatoin() {
        return mLocation;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getVenue() {
        return mVenue;
    }

    public String getData() {
        return mData;
    }

    public String getmNotes() {
        return mNotes;
    }
}

