package com.synerise.sdk.sample.ui.dev.apiAdapter.fragments.promotion;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.textfield.TextInputLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.synerise.sdk.core.listeners.DataActionListener;
import com.synerise.sdk.core.net.IDataApiCall;
import com.synerise.sdk.error.ApiError;
import com.synerise.sdk.promotions.Promotions;
import com.synerise.sdk.promotions.model.AssignVoucherResponse;
import com.synerise.sdk.sample.R;
import com.synerise.sdk.sample.ui.dev.BaseDevFragment;

public class ClientGetOrAssignVoucherFragment extends BaseDevFragment {

    private TextInputLayout inputPool;
    private IDataApiCall<AssignVoucherResponse> call;

    public static ClientGetOrAssignVoucherFragment newInstance() { return new ClientGetOrAssignVoucherFragment(); }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_client_get_or_assign_voucher, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        inputPool = view.findViewById(R.id.input_pool);
        view.findViewById(R.id.get_or_assign).setOnClickListener(v -> getVoucherCodes());
    }

    @Override
    public void onStop() {
        super.onStop();
        if (call != null) call.cancel();
    }

    @SuppressWarnings("ConstantConditions")
    private void getVoucherCodes() {
        boolean isValid = true;

        inputPool.setError(null);

        String poolUuid = inputPool.getEditText().getText().toString();

        if (TextUtils.isEmpty(poolUuid)) {
            isValid = false;
            inputPool.setError(getString(R.string.error_empty));
        }

        if (isValid) {
            if (call != null) call.cancel();
            call = Promotions.getOrAssignVoucher(poolUuid);
            call.execute(this::onSuccess, new DataActionListener<ApiError>() {
                @Override
                public void onDataAction(ApiError apiError) {
                    onFailure(apiError);
                }
            });
        }
    }

    private void onSuccess(AssignVoucherResponse response) {
        super.onSuccess();
    }
}