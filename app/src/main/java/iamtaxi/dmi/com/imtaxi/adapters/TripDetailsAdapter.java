package iamtaxi.dmi.com.imtaxi.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import iamtaxi.dmi.com.imtaxi.R;
import iamtaxi.dmi.com.imtaxi.listeners.OnRecyclerItemClickListener;

/**
 * Created by Ankit on 20-01-2017.
 *
 * @author Ankit Jindal
 */

public class TripDetailsAdapter extends RecyclerView.Adapter<TripDetailsAdapter.TripDetailsViewHolder> {

    private Context mContext;
    private OnRecyclerItemClickListener mOnRecyclerItemClickListener;

    public TripDetailsAdapter(Context context, OnRecyclerItemClickListener listener) {
        mContext = context;
        mOnRecyclerItemClickListener = listener;
    }

    @Override
    public TripDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.item_trip_details, parent, false);
        return new TripDetailsViewHolder(view, mOnRecyclerItemClickListener);
    }

    @Override
    public void onBindViewHolder(TripDetailsViewHolder holder, int position) {

        if (position % 2 == 0) {
            holder.textStatus.setBackgroundColor(Color.RED);
            holder.textStatus.setText(mContext.getString(R.string.text_complete));
            holder.mStatusLine.setBackgroundColor(Color.RED);
        } else {
            holder.textStatus.setBackgroundColor(Color.GREEN);
            holder.textStatus.setText(mContext.getString(R.string.text_pending));
            holder.mStatusLine.setBackgroundColor(Color.GREEN);
        }
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public static class TripDetailsViewHolder extends RecyclerView.ViewHolder {

        public TextView textStatus;
        public View mStatusLine;

        public TripDetailsViewHolder(View itemView, final OnRecyclerItemClickListener listener) {
            super(itemView);
            mStatusLine = itemView.findViewById(R.id.line_view);
            textStatus = (TextView) itemView.findViewById(R.id.text_status);
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
