package com.solrom.edgar.petagram40.restApi;

/**
 * Created by edgarsr on 16/10/17.
 */

public class ConstantesRestApi {

    public static String usuario;
    public static String idUsuario;
    public static String urlPerfil;
    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com" + VERSION;
    public static final String ACCESS_TOKEN = "6216568161.c002059.032bb9dc8fe84b62ad3e36566348d490";
    public static final String KEY_ACCESS_TOKEN = "?access_token=";
    public static final String KEY_ACCESS_TOKEN_SEARCH =    "&access_token=";
    public static final String KEY_GET_RECENT_MEDIA_USER = "users/self/media/recent/";
    public static final String KEY_GET_RECENT_MEDIA =       "/media/recent/?access_token=" + ACCESS_TOKEN;
    public static final String KEY_USERS =                  "users/";
    public static final String KEY_GET_USER =               "users/search?q=";
    public static final String URL_GET_RECENT_MEDIA_USER = KEY_GET_RECENT_MEDIA_USER + KEY_ACCESS_TOKEN + ACCESS_TOKEN;
    public static final String URL_GET_USER =               KEY_GET_USER + "edgarsrsr" + KEY_ACCESS_TOKEN_SEARCH + ACCESS_TOKEN;

    //https://api.instagram.com/v1/users/self/media/recent/?access_token=6216568161.c002059.032bb9dc8fe84b62ad3e36566348d490





}
