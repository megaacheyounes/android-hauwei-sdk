package com.synerise.sdk.sample.ui.dev.client;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.synerise.sdk.sample.R;
import com.synerise.sdk.sample.ui.BaseActivity;
import com.synerise.sdk.sample.ui.dev.apiAdapter.SyneriseSdkApi;
import com.synerise.sdk.sample.ui.dev.apiAdapter.ApiRecyclerAdapter;
import com.synerise.sdk.sample.ui.dev.apiAdapter.FragmentContainerActivity;
import com.synerise.sdk.sample.ui.dev.client.promotions.ClientPromotionApisActivity;
import com.synerise.sdk.sample.ui.dev.client.vouchers.ClientVoucherApisActivity;
import com.synerise.sdk.sample.util.ToolbarHelper;

public class ClientApiActivity extends BaseActivity {

    public static Intent createIntent(Context context) {
        return new Intent(context, ClientApiActivity.class);
    }

    // ****************************************************************************************************************************************

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synerise_sdk_api);

        RecyclerView recycler = findViewById(R.id.api_recycler);

        ToolbarHelper.setUpChildToolbar(this, R.string.client_api);

        ApiRecyclerAdapter adapter = new ApiRecyclerAdapter(LayoutInflater.from(this),
                                                            this::onClientApiClick,
                                                            SyneriseSdkApi.getClientApis());

        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
    }

    // ****************************************************************************************************************************************

    private void onClientApiClick(SyneriseSdkApi syneriseSdkApi) {
        if(!syneriseSdkApi.isGroup())
            startActivity(FragmentContainerActivity.createIntent(this, syneriseSdkApi.ordinal()));
        else if (syneriseSdkApi == SyneriseSdkApi.CLIENT_PROMOTIONS) {
            startActivity(ClientPromotionApisActivity.createIntent(this));
        } else if (syneriseSdkApi == SyneriseSdkApi.CLIENT_VOUCHERS) {
            startActivity(ClientVoucherApisActivity.createIntent(this));
        }
    }
}