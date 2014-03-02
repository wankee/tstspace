package com.refactech.driibo.ui.fragment;

import com.refactech.driibo.AppData;
import com.refactech.driibo.R;
import com.refactech.driibo.dao.DevicesDataHelper;
import com.refactech.driibo.type.dribble.Category;
import com.refactech.driibo.type.dribble.Device;
import com.refactech.driibo.ui.adapter.DeviceAdapter;
import com.refactech.driibo.vendor.DribbbleApi;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class DeviceFragment extends BasePageListFragment<Device>
		implements LoaderManager.LoaderCallbacks<Cursor> {

	public static final String EXTRA_CATEGORY = "EXTRA_CATEGORY";

	private Category mCategory;

	private DevicesDataHelper mDataHelper;

	public static DeviceFragment newInstance(Category category) {
		System.out.println("Category:" + category);
		DeviceFragment fragment = new DeviceFragment();
		Bundle bundle = new Bundle();
		bundle.putString(EXTRA_CATEGORY, category.name());
		fragment.setArguments(bundle);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View contentView = super.onCreateView(inflater, container,
				savedInstanceState);
		parseArgument();
		mDataHelper = new DevicesDataHelper(AppData.getContext(), mCategory);

		getLoaderManager().initLoader(0, null, this);

		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Device device = (Device) getAdapter().getItem(
						position - mListView.getHeaderViewsCount());
				Intent intent = new Intent(Intent.ACTION_VIEW);
				//intent.setData(Uri.parse(device.getUrl()));
				startActivity(intent);
			}
		});

		mListView
				.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
					@Override
					public boolean onItemLongClick(AdapterView<?> parent,
							View view, int position, long id) {
						Device device = (Device) getAdapter().getItem(
								position - mListView.getHeaderViewsCount());
						Intent intent = new Intent(Intent.ACTION_VIEW);
						//intent.setData(Uri.parse(device.getImage_url()));
						startActivity(intent);
						return true;
					}
				});

		return contentView;
	}

	private void parseArgument() {
		Bundle bundle = getArguments();
		mCategory = Category.valueOf(bundle.getString(EXTRA_CATEGORY));
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		return mDataHelper.getCursorLoader();
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		getAdapter().changeCursor(data);
		if (data != null && data.getCount() == 0) {
			loadFirstPage();
		}
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		getAdapter().changeCursor(null);
	}

	@Override
	protected int getContentViewResId() {
		return R.layout.fragment_shot;
	}

	@Override
	protected CursorAdapter getAdapter() {
		return (CursorAdapter) super.getAdapter();
	}

	@Override
	protected BaseAdapter newAdapter() {
		return new DeviceAdapter(getActivity(), mListView);
	}

	@Override
	protected void processData(Device response) {
		System.out.println("on processData s");
		System.out.println(response);
		mPage = response.getId();
		String str = response.getName();
		System.out.println("page is " + mPage + " " + str);
		if (mPage == 1) {
			mDataHelper.deleteAll();
		}
		 //ArrayList<Shot> shots = response.getDevice();
		// System.out.println(shots+"is here");
		//mDataHelper.bulkInsert(null);
		mDataHelper.insert(response);
	}

	@Override
	protected String getUrl(int page) {

		return "http://www.battlenet.com.cn/api/wow/battlePet/ability/640";
		// return "http://us.battle.net/api/wow/battlePet/ability/640";
		// return "http://www.battlenet.com.cn/api/wow/auction/data/��ˮ����";
		// return "http://us.battle.net/api/wow/auction/data/medivh";
		// return "http://m.weather.com.cn/data/101010100.html";
		// return "http://www.weather.com.cn/data/cityinfo/101010100.html";
		// return "http://us.battle.net/api/wow/achievement/6";
		// return String.format(DribbbleApi.SHOTS_LIST, mCategory.name(), page);
	}

	@Override
	protected Class getResponseDataClass() {
		return Device.class;
	}
}
