package com.exam.vn.bookmanager.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.exam.vn.bookmanager.R;
import com.exam.vn.bookmanager.dao.NguoiDungDAO;
import com.exam.vn.bookmanager.model.NguoiDung;
import com.exam.vn.bookmanager.model.Sach;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NguoiDungAdapter extends BaseAdapter {
    List<NguoiDung> arrNguoiDung;
    List<NguoiDung> arrSortNguoiDung;
    public Activity context;
    private Filter sachFilter;
    public LayoutInflater inflater;
    NguoiDungDAO nguoiDungDAO;

    public NguoiDungAdapter() {
    }

    public NguoiDungAdapter(Activity context, List<NguoiDung> arrayNguoiDung) {
        super();
        this.context = context;
        this.arrNguoiDung = arrayNguoiDung;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        nguoiDungDAO = new NguoiDungDAO(context);
    }

    @Override
    public int getCount() {
        return arrNguoiDung.size();
    }

    @Override
    public Object getItem(int position) {
        return arrNguoiDung.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    public static class ViewHolder {

        ImageView img;
        TextView txtName;
        TextView txtPhone;
        ImageView imgDelete;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_nguoi_dung, null);
            holder.img = (ImageView) convertView.findViewById(R.id.ivIcon);
            holder.txtName = (TextView) convertView.findViewById(R.id.tvName);
            holder.txtPhone = (TextView) convertView.findViewById(R.id.tvPhone);
            holder.imgDelete = (ImageView) convertView.findViewById(R.id.ivDelete);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nguoiDungDAO.deleteNguoiDungByID(arrNguoiDung.get(position).getUserName());
                    arrNguoiDung.remove(position);
                    notifyDataSetChanged();
                }
            });

            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        NguoiDung _entry = (NguoiDung) arrNguoiDung.get(position);
        if (position % 2 == 0) {
            holder.txtName.setTextColor(Color.GREEN);
        } else {
            holder.txtName.setTextColor(Color.RED);
        }

        holder.txtName.setText(_entry.getHoTen());
        holder.txtPhone.setText(_entry.getPhone());

        return convertView;
    }

    public void resetData() {
        arrNguoiDung = arrSortNguoiDung;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataset(List<NguoiDung> items) {
        this.arrNguoiDung = items;
        notifyDataSetChanged();

    }
    public Filter getFilter() {
        if (sachFilter == null)
            sachFilter = new NguoiDungAdapter.CustomFilter();

        return sachFilter;
    }

    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            // We implement here the filter logic
            if (constraint == null || constraint.length() == 0) {
                results.values = arrSortNguoiDung;
                results.count = arrSortNguoiDung.size();
            }
            else {
                List<NguoiDung> lsSach = new ArrayList<>();

                for (NguoiDung p : arrNguoiDung) {
                    if (p.getUserName().toUpperCase().startsWith(constraint.toString().toUpperCase()))
                        lsSach.add(p);
                }

                results.values = lsSach;
                results.count = lsSach.size();

            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            if (results.count == 0)
                notifyDataSetInvalidated();
            else {
                arrNguoiDung = (List<NguoiDung>) results.values;
                notifyDataSetChanged();
            }

        }

    }



}

