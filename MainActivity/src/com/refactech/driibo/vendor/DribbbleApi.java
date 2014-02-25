
package com.refactech.driibo.vendor;

/**
 * Created by Issac on 7/18/13.
 */
public class DribbbleApi {
	
/*	private static final String BASE_URL = "http://www.battlenet.com.cn/api/wow/auction/data/medivh";

    public static final String SHOTS_LIST = "http://www.battlenet.com.cn/api/wow/auction/data/medivh";

    public static final String FOLLOWING = "http://www.battlenet.com.cn/api/wow/auction/data/medivh";

    public static final String LIKES = "http://www.battlenet.com.cn/api/wow/auction/data/medivh";*/
    private static final String BASE_URL = "http://api.dribbble.com";

    public static final String SHOTS_LIST = BASE_URL + "/shots/%1$s?page=%2$d";

    public static final String FOLLOWING = BASE_URL+"/players/%1$s/shots/following?page=%2$d";

    public static final String LIKES = BASE_URL+"/players/%1$s/shots/likes?page=%2$d";
}
