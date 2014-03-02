package com.refactech.driibo.ui.adapter;

import com.android.volley.toolbox.ImageLoader;
import com.refactech.driibo.AppData;
import com.refactech.driibo.R;
import com.refactech.driibo.data.RequestManager;
import com.refactech.driibo.type.dribble.Device;
import com.refactech.driibo.util.TimeUtils;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Issac on 7/18/13.
 */

public class DeviceAdapter extends CursorAdapter {
    private LayoutInflater mLayoutInflater;

    private ListView mListView;

   // private BitmapDrawable mDefaultAvatarBitmap = (BitmapDrawable) AppData.getContext()
   //         .getResources().getDrawable(R.drawable.default_avatar);

  //  private Drawable mDefaultImageDrawable = new ColorDrawable(Color.argb(255, 201, 201, 201));

    public DeviceAdapter(Context context, ListView listView) {
        super(context, null, false);
        mLayoutInflater = ((Activity) context).getLayoutInflater();
        mListView = listView;
    }

    @Override
    public Device getItem(int position) {
        mCursor.moveToPosition(position);
        return Device.fromCursor(mCursor);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return mLayoutInflater.inflate(R.layout.listitem_device, null);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        //Holder holder = getHolder(view);
        Skill skill=getSkill(view);
    	//if (skill.imageRequest != null) {
       //     skill.imageRequest.cancelRequest();
      //  }

      /*  if (skill.avartarRequest != null) {
            skill.avartarRequest.cancelRequest();
        }*/

        view.setEnabled(!mListView.isItemChecked(cursor.getPosition()
                + mListView.getHeaderViewsCount()));

        Device device = Device.fromCursor(cursor);
     //   String img_str="http://us.media.blizzard.com/wow/icons/56/"+skill.icon+".jpg";
       // skill.imageRequest = RequestManager.loadImage(img_str, RequestManager
       //         .getImageListener(skill.icon_img, mDefaultImageDrawable, mDefaultImageDrawable));
      //  holder.avartarRequest = RequestManager.loadImage(shot.getPlayer().getAvatar_url(),
             //   RequestManager.getImageListener(holder.avatar, mDefaultAvatarBitmap,
              //          mDefaultAvatarBitmap));
        skill.id.setText(String.valueOf(device.getId()));
       // skill.userName.setText(device.getPlayer().getName());
        skill.name.setText(device.getName());
        skill.icon.setText(device.getIcon());
        skill.cooldown.setText(String.valueOf(device.getCooldown()));
        skill.rounds.setText(String.valueOf(device.getRounds()));
        skill.petTypeId.setText(String.valueOf(device.getperTypeId()));
        skill.isPassive.setText(device.getPassive());
        skill.hideHints.setText(device.getHideHints());
    }

    private Skill getSkill(final View view) {
        Skill skill = (Skill) view.getTag();
        if (skill == null) {
            skill = new Skill(view);
            view.setTag(skill);
        }
        return skill;
    }

    private class Skill {
       // public ImageView icon_img;

        public TextView id;

        public TextView name;

        public TextView icon;

        public TextView cooldown;

        public TextView rounds;

        public TextView petTypeId;

        public TextView isPassive;

        public TextView hideHints;
        
       // public ImageLoader.ImageContainer imageRequest;

        //public ImageLoader.ImageContainer avartarRequest;

        public Skill(View view) {
        //    icon_img = (ImageView) view.findViewById(R.id.image);
            id = (TextView) view.findViewById(R.id.id);
            icon = (TextView) view.findViewById(R.id.icon);
            name = (TextView) view.findViewById(R.id.name);
            cooldown = (TextView) view.findViewById(R.id.cooldown);
            rounds = (TextView) view.findViewById(R.id.rounds);
            petTypeId = (TextView) view.findViewById(R.id.petTypeId);
            isPassive = (TextView) view.findViewById(R.id.isPassive);
            hideHints = (TextView) view.findViewById(R.id.hideHints);
            //time = (TextView) view.findViewById(R.id.time);
        }
    }
}
