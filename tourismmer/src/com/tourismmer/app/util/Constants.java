package com.tourismmer.app.util;

public class Constants {
	
	 // CONSTANTS BUSSINESS

    public static Integer TOURIST = 1;

    public static Integer GUIDE = 2;

    public static Integer VISIBILITY_ALL = 1;

    public static Integer VISIBILITY_FRIENDS = 2;

    public static Integer VISIBILITY_NOBODY = 3;

    public static Integer TYPE_EVENT = 1;

    public static Integer TYPE_ACTIVITY = 2;

    public static Integer TYPE_PLACES = 3;

    public static Integer TYPE_LODGING = 4;

    public static Integer TYPE_TRANSPORT_CAR = 5;

    public static Integer TYPE_TRANSPORT_BUS = 6;

    public static Integer TYPE_TRANSPORT_PLANE = 7;

    public static Integer COMMENT_EVENT = 1;

    public static Integer COMMENT_TRIP = 2;

    public static Integer THUMBS_UP = 1;

    public static Integer THUMBS_DOWN = 2;

    public static Integer JOIN = 8;

    public static Integer NEW_UPDATE = 1;

    public static Integer OLD_UPDATE = 0;

    public static Integer TRIP = 1;

    public static Integer EVENT = 2;

    public static Integer COMMENT = 9;

    // CONSTANTS SESSION

    public static String SESSION_LOGGED_USER = "loggedUser";

    public static String SESSION_ID_EVENT_SELECTED = "selectedEventId";

    public static String SESSION_ID_TRIP_SELECTED = "selectedTripId";

    public static String SESSION_TRANSPORT_SELECTED = "selectedTransport";

    // CONSTANTS OTHER

    public static String HTML_EMAIL_HEADER = "<div style=\"width:100%; height:100%; \"><div style=\"margin: 0 auto; width:550px; height:300px; border:thin solid lightgray; padding:50px\">" +
            "<center><b>";

    public static String HTML_EMAIL_TITLE = "</b></center><font color=\"Gray\"><br />";

    public static String HTML_EMAIL_FOOTER = "<br /><br /><br />Equipe Tourismmer</font></div></div>";

    public static String ENCRYPT_KEY = "@#$tour@#$";

    public static String BYTE_ZERO = "48";

    public static String BYTE_ONE = "49";

    public static Long INVITE_IN = Numeros.UM_L;

    public static Long INVITE_OUT = Numeros.DOIS_L;

    public static Object DEFAULT_DATE_MYSQL = null;

    public static Integer EVENT_NO_ADDED_AGENDA = 1;

    public static Integer EVENT_ADDED_AGENDA = 2;

}
