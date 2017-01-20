package iamtaxi.dmi.com.imtaxi.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import iamtaxi.dmi.com.imtaxi.R;

/**
 * Created by Ankit on 20-01-2017.
 *
 * @author Ankit Jindal
 */

public class TripDetailsAdapter extends RecyclerView.Adapter<TripDetailsAdapter.TripDetailsViewHolder> {

    private Context mContext;

    public TripDetailsAdapter(Context context) {
        mContext = context;
    }

    @Override
    public TripDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.item_trip_details, parent, false);
        return new TripDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TripDetailsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public static class TripDetailsViewHolder extends RecyclerView.ViewHolder {

        public TripDetailsViewHolder(View itemView) {
            super(itemView);
        }
    }
}
