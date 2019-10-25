package id.innovation.libcore.contentprovider;

import android.net.Uri;

/**
 * Created by Rezky Aulia Pratama on 7/29/2017.
 */

public class DataPath {


    public static final String CONTENT_AUTHORITY = "id.rezkyauliapratama.dicodingmade";

    /*
     * Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
     * the content provider for Sunshine.
     */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_FAVORITE = "FAVORITE";

    /* The base CONTENT_URI used to query the Weather table from the content provider */
    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
            .appendPath(PATH_FAVORITE)
            .build();
}
