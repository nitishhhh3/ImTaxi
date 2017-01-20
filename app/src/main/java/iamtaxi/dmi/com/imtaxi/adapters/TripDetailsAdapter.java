package iamtaxi.dmi.com.imtaxi.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import iamtaxi.dmi.com.imtaxi.R;
import iamtaxi.dmi.com.imtaxi.listeners.OnRecyclerItemClickListener;
import iamtaxi.dmi.com.imtaxi.model.CabRequest;
import iamtaxi.dmi.com.imtaxi.utill.AppConstants;

/**
 * Created by Ankit on 20-01-2017.
 *
 * @author Ankit Jindal
 */

public class TripDetailsAdapter extends RecyclerView.Adapter<TripDetailsAdapter.TripDetailsViewHolder> {

    private Context mContext;
    private OnRecyclerItemClickListener mOnRecyclerItemClickListener;
    private List<CabRequest> cabRequestList;

    public TripDetailsAdapter(Context context, OnRecyclerItemClickListener listener) {
        mContext = context;
        mOnRecyclerItemClickListener = listener;
    }

    public void setData(List<CabRequest> cabList) {
        cabRequestList = new ArrayList<>();
        cabRequestList.addAll(cabList);
        notifyDataSetChanged();
    }

    @Override
    public TripDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.item_trip_details, parent, false);
        return new TripDetailsViewHolder(view, mOnRecyclerItemClickListener);
    }

    @Override
    public void onBindViewHolder(TripDetailsViewHolder holder, int position) {

        holder.textStatus.setText(cabRequestList.get(position).getStatus());
        holder.time.setText(cabRequestList.get(position).getTime());
        holder.managerName.setText(cabRequestList.get(position).getManagerEmail());
        holder.source.setText(cabRequestList.get(position).getSource());
        holder.dest.setText(cabRequestList.get(position).getDestination());

        if (cabRequestList.get(position).equals(AppConstants.PENDING_STATUS)) {
            holder.textStatus.setBackgroundColor(Color.GREEN);

        } else if (cabRequestList.get(position).equals(AppConstants.COMPLETE_STATUS)) {
            holder.textStatus.setBackgroundColor(Color.RED);
        }



/*
        if (position % 2 == 0) {
            holder.textStatus.setBackgroundColor(Color.RED);
            holder.textStatus.setText(mContext.getString(R.string.text_complete));
            holder.mStatusLine.setBackgroundColor(Color.RED);
        } else {
            holder.textStatus.setBackgroundColor(Color.GREEN);
            holder.textStatus.setText(mContext.getString(R.string.text_pending));
            holder.mStatusLine.setBackgroundColor(Color.GREEN);
        }*/
    }

    @Override
    public int getItemCount() {
        if (cabRequestList == null || cabRequestList.size() == 0) {
            return 0;
        } else {
            return cabRequestList.size();
        }
    }

    public static class TripDetailsViewHolder extends RecyclerView.ViewHolder {

        private final TextView dest;
        private final TextView source;
        public TextView textStatus, managerName, time;
        public View mStatusLine;

        public TripDetailsViewHolder(View itemView, final OnRecyclerItemClickListener listener) {
            super(itemView);
            mStatusLine = itemView.findViewById(R.id.line_view);
            textStatus = (TextView) itemView.findViewById(R.id.text_status);
            managerName = (TextView) itemView.findViewById(R.id.manager_name);
            source = (TextView) itemView.findViewById(R.id.source);
            dest = (TextView) itemView.findViewById(R.id.source);
            time = (TextView) itemView.findViewById(R.id.time);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.onRecyclerItemClick(getLayoutPosition(), v);
                }
            });
        }
    }
}
