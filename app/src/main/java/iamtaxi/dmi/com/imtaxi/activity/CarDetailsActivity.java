package iamtaxi.dmi.com.imtaxi.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import iamtaxi.dmi.com.imtaxi.R;

/**
 * Created by Ankit on 20-01-2017.
 *
 * @author Ankit Jindal
 */

public class CarDetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);
        setUpToolbar(getString(R.string.text_car_details));
    }
}
