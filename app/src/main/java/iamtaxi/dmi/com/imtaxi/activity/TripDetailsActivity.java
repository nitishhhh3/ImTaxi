package iamtaxi.dmi.com.imtaxi.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import iamtaxi.dmi.com.imtaxi.R;
import iamtaxi.dmi.com.imtaxi.adapters.TripDetailsAdapter;

/**
 * Created by Ankit on 20-01-2017.
 *
 * @author Ankit Jindal
 */

public class TripDetailsActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private TripDetailsAdapter mTripDetailsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_details);
        setUpToolbar(getString(R.string.text_trip_details));
        initViews();
    }

    /**
     * Method used to initialise views from Xml
     */
    private void initViews() {
        mTripDetailsAdapter = new TripDetailsAdapter(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_trip_details);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mTripDetailsAdapter);

    }

}
