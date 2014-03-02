package com.refactech.driibo.type.dribble;

import com.google.gson.Gson;
import com.refactech.driibo.dao.DevicesDataHelper;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Issac on 7/18/13.
 */
public class Device extends BaseType {
	  
	   private int id;

       private String name;

       private String icon;

       private int cooldown;

       private int rounds;
       
       private int petTypeId;
       
       private String isPassive;
       
       private String hideHints;
       
       private static final HashMap<Long, Device> CACHE = new HashMap<Long, Device>();
       
       public int getId() {
           return id;
       }

       public String getName() {
           return name;
       }

       public String getIcon() {
           return icon;
       }

       public int getCooldown() {
           return cooldown;
       }

       public int getRounds() {
           return rounds;
       }
       
       public int getperTypeId() {
           return petTypeId;
       }
       
       public String getPassive() {
           return isPassive;
       }
       
       public String getHideHints() {
           return hideHints;
       }
	
	
   

  /*  private long id;

    private String title;

    private String url;

    private String short_url;

    private String image_url;

    private String image_teaser_url;

    private int width;

    private int height;

    private int views_count;

    private int likes_count;

    private int comments_count;

    private int rebounds_count;

    private long rebound_source_id;

    private String created_at;

    private Player player;
*/
    private static void addToCache(Device device) {
        CACHE.put((long) device.getId(), device);
    }

    private static Device getFromCache(long id) {
        return CACHE.get(id);
    }

    public static Device fromJson(String json) {
        return new Gson().fromJson(json, Device.class);
    }

    public static Device fromCursor(Cursor cursor) {
        long id = cursor.getLong(cursor.getColumnIndex(DevicesDataHelper.DevicesDBInfo._ID));
        Device device = getFromCache(id);
        if (device != null) {
            return device;
        }
        device = new Gson().fromJson(
                cursor.getString(cursor.getColumnIndex(DevicesDataHelper.DevicesDBInfo.JSON)),
                Device.class);
        addToCache(device);
        return device;
    }

/*    public long getId() {
        return 0;
    }*/

    /*public String getTitle() {
        return "getTitle";
    }

    public String getUrl() {
        return "getUrl";
    }

    public String getShort_url() {
        return "getShort_url";
    }

    public String getImage_url() {
        return "getImage_url";
    }

    public String getImage_teaser_url() {
        return "getImage_teaser_url";
    }

    public int getWidth() {
        return 1;
    }

    public int getHeight() {
        return 1;
    }

    public int getViews_count() {
        return 1;
    }

    public int getLikes_count() {
        return 1;
    }

    public int getComments_count() {
        return 1;
    }

    public int getRebounds_count() {
        return 1;
    }

    public long getRebound_source_id() {
        return 1;
    }

    public String getCreated_at() {
        return "getCreated_at";
    }
*/
   /* public Player getPlayer() {
        return player;
    }*/

    /*public static class ShotsRequestData {
        private int page;

        private int pages;

        private int per_page;

        private int total;

        private ArrayList<Shot> shots;

        public int getPage() {
            return page;
        }

        public int getPages() {
            return pages;
        }

        public int getPer_page() {
            return per_page;
        }

        public int getTotal() {
            return total;
        }

        public ArrayList<Shot> getShots() {
            return shots;
        }
    }*/
    
    /*public static class ArmaryData {
        private int id;

        private String name;

        private String icon;

        private int cooldown;

        private int rounds;
        
        private int petTypeId;
        
        private String isPassive;
        
        private String hideHints;
        
        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getIcon() {
            return icon;
        }

        public int getCooldown() {
            return cooldown;
        }

        public int getRounds() {
            return rounds;
        }
        
        public int getperTypeId() {
            return petTypeId;
        }
        
        public String getPassive() {
            return isPassive;
        }
        
        public String getHideHints() {
            return hideHints;
        }
    }*/
}
